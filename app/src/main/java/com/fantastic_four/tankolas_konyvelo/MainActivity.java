package com.fantastic_four.tankolas_konyvelo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fantastic_four.tankolas_konyvelo.Data.Converters;
import com.fantastic_four.tankolas_konyvelo.Data.ServerResponse;
import com.fantastic_four.tankolas_konyvelo.View.CarRegistrationFragment;
import com.fantastic_four.tankolas_konyvelo.View.FirstRunOpenScreenFragment;
import com.fantastic_four.tankolas_konyvelo.View.MainViewModel;
import com.fantastic_four.tankolas_konyvelo.View.MainWindowFragment;
import com.fantastic_four.tankolas_konyvelo.View.NewFillingFragment;
import com.fantastic_four.tankolas_konyvelo.View.PrevDataFragment;
import com.fantastic_four.tankolas_konyvelo.View.StatisticsFragment;
import com.fantastic_four.tankolas_konyvelo.ViewModel.CarViewModel;
import com.fantastic_four.tankolas_konyvelo.ViewModel.PersonalChalkViewModel;
import com.fantastic_four.tankolas_konyvelo.ViewModel.RetrofitViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.TypeConverters;

@SuppressWarnings("ALL")
@TypeConverters(Converters.class)
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Observer<Integer> {

    private MainViewModel mainViewModel;

    private DrawerLayout drawerLayout;
    private FragmentTransaction fragmentTransaction;

    public static final int FIRSTRUNFRAGMENT_ID = 100;
    public static final int MAINWINDOWFRAGMENT_ID = 101;
    public static final int NEWFILLINGFRAGMENT_ID = 102;
    public static final int CARREGISTRATIONFRAGMENT_ID = 103;
    public static final int LOADDATA_ID = 104;
    public static final int PREVDATAFRAGMENT_ID = 105;
    public static final int STATISTICSFRAGMENT_ID = 106;
    boolean isMainWindow = false;
    boolean toFirstRunOpenScreen = false;

    private ServerResponse serverResponse;

    private CarViewModel carViewModel;
    private PersonalChalkViewModel personalChalkViewModel;
    private RetrofitViewModel retrofitViewModel;

    private ProgressDialog progressDialog;

    @Override
    public void onBackPressed() {
        if (isMainWindow) {
            this.moveTaskToBack(true);
        } else if (toFirstRunOpenScreen) {
            changeFragment(FIRSTRUNFRAGMENT_ID);
        } else {
            changeFragment(MAINWINDOWFRAGMENT_ID);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carViewModel = ViewModelProviders.of(this).get(CarViewModel.class);
        personalChalkViewModel = ViewModelProviders.of(this).get(PersonalChalkViewModel.class);
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);
        retrofitViewModel.isUpDownloading().observe(this, isUpDownloading);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Folyamatban....");
        progressDialog.setTitle("Adatok töltése");

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //van-e kocsi regisztrálva a rendszerben
        carViewModel.getCar().observe(this, new Observer<Car>() {
            @Override
            public void onChanged(Car car) {
                if (car != null) {
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24px);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    TextView textView = navigationView.getHeaderView(0).findViewById(R.id.drawer_header_textview);
                    textView.setText(car.brand + " (" + car.type + ") " + car.ccm + "ccm, " + car.kw + " KW");
                    changeFragment(MAINWINDOWFRAGMENT_ID);
                } else {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    changeFragment(FIRSTRUNFRAGMENT_ID);
                }
            }
        });

        //megvizsgáljuk, hogy ha törlődött a Car tábla, akkor korábban történt-e új adatok letöltése
        //ha igen, csere
        //utána a szerverválasz nullázása
        carViewModel.isAllCarDeleted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    if (serverResponse != null && serverResponse.getCar() != null) {
                        carViewModel.insertCar(serverResponse.getCar());
                        serverResponse.setCar(null);
                    }
                }
            }
        });

        //megvizsgáljuk, hogy ha törlődött a PersonalChalk tábla, akkor korábban történt-e új adatok letöltése
        //ha igen, csere
        //utána a szerverválasz nullázása
        personalChalkViewModel.isAllChalksDeleted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (serverResponse != null && serverResponse.getChalks() != null) {
                    for (PersonalChalk personalChalk : serverResponse.getChalks()) {
                        personalChalkViewModel.insertPersonalChalk(personalChalk);
                    }
                    serverResponse.setChalkList(null);
                }
            }
        });

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //a különböző Fragment oldalakon található gombok megnyomásaihoz tartozó figyelő
        //adott gomb megnyomásakor adott Fragment jelenjen meg
        mainViewModel.getClickedButton().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer buttonId) {
                if (buttonId == MainActivity.LOADDATA_ID) {
                    loadDataDialog();
                } else {
                    changeFragment(buttonId);
                }
            }
        });
    }

    //Kért Fragment betöltése
    private void changeFragment(int fragment_id) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        isMainWindow = false;
        toFirstRunOpenScreen = false;
        switch (fragment_id) {
            case FIRSTRUNFRAGMENT_ID:
                isMainWindow = true;
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                fragmentTransaction.replace(R.id.fragment_placeholder, new FirstRunOpenScreenFragment());
                break;
            case MAINWINDOWFRAGMENT_ID:
                isMainWindow = true;
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24px);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                fragmentTransaction.replace(R.id.fragment_placeholder, new MainWindowFragment());
                break;
            case NEWFILLINGFRAGMENT_ID:
                fragmentTransaction.replace(R.id.fragment_placeholder, new NewFillingFragment());
                break;
            case CARREGISTRATIONFRAGMENT_ID:
                toFirstRunOpenScreen = true;
                fragmentTransaction.replace(R.id.fragment_placeholder, new CarRegistrationFragment());
                break;
            case PREVDATAFRAGMENT_ID:
                fragmentTransaction.replace(R.id.fragment_placeholder, new PrevDataFragment());
                break;
            case STATISTICSFRAGMENT_ID:
                fragmentTransaction.replace(R.id.fragment_placeholder, new StatisticsFragment());
                break;
        }
        fragmentTransaction.commit();
    }

    //Ha van fel- vagy letöltés, jelenjen meg a progressdialog, egyébként tűnjön el
    private Observer isUpDownloading = new Observer() {
        @Override
        public void onChanged(Object o) {
            Boolean isLoading = (Boolean) o;
            if (isLoading) {
                progressDialog.show();
            } else {
                progressDialog.dismiss();
            }
        }
    };

    //Adatok letöltése után frissítsük-e a helyi adatokat. Ha igen, az összes helyi adatot töröljük.
    private Observer downloadedDataObserver = new Observer() {
        @Override
        public void onChanged(Object o) {
            serverResponse = (ServerResponse) o;
            if (serverResponse != null && serverResponse.getCar() != null && serverResponse.getChalks() != null) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Letöltött adatok (frissítése törli az összes korábbi adatot)");
                dialog.setMessage("Autó: " + serverResponse.getCar().brand + " (" + serverResponse.getCar().type + "), " + serverResponse.getChalks().size() + " db adat");
                dialog.setNegativeButton("Mégse", null);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        carViewModel.deleteAllCars();
                        personalChalkViewModel.deleteAllPersonalChalks();
                        Toast.makeText(MainActivity.this, "Új adatok beillesztve", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        changeFragment(MAINWINDOWFRAGMENT_ID);
                    }
                });
                dialog.show();
            } else {
                Toast.makeText(MainActivity.this, "Nincs ilyen rendszámmal adat a szerveren!", Toast.LENGTH_LONG).show();
            }
        }
    };

    //Dialog ablak, ahol megadjuk a rendszámot, amikhez tartozó adatokat letöltsük, majd meghívjuk a megfelelő
    //retrofit ViewModel metódust a letöltés kivitelezéséhez
    private void loadDataDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        EditText editText = new EditText(MainActivity.this);
        dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Rendszám");
        dialog.setMessage("Add meg a rendszámot:");
        dialog.setView(editText);
        dialog.setNegativeButton("Mégse", null);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editText.getText().toString().length() > 3) {
                    retrofitViewModel.downloadData(editText.getText().toString());
                    retrofitViewModel.getDownloadedData().observe(MainActivity.this, downloadedDataObserver);
                } else {
                    Toast.makeText(MainActivity.this, "Túl rövid a megadott rendszám!", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    //oldalsó navigációs ablakban található menüitemekhez tartozó interfészen keresztüli függvényhívások
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder dialog;
        switch (item.getItemId()) {
            //előző adatok megtekintése
            case R.id.drawer_menu_prev_data:
                changeFragment(PREVDATAFRAGMENT_ID);
                break;
                //adatok letöltése szerverről
            case R.id.drawer_menu_open:
                loadDataDialog();
                break;
                //adatok mentése szerverre
            case R.id.drawer_menu_save:
                dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Megerősítés");
                dialog.setMessage("Biztos, hogy mentsük? Minden korábbi mentés törlődik a szerveren.");
                dialog.setNegativeButton("Mégse", null);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        personalChalkViewModel.getAllPersonalChalks().observe(MainActivity.this, new Observer<List<PersonalChalk>>() {
                            @Override
                            public void onChanged(List<PersonalChalk> personalChalks) {
                                carViewModel.getCar().observe(MainActivity.this, new Observer<Car>() {
                                    @Override
                                    public void onChanged(Car car) {
                                        Gson gson = new Gson();
                                        String chalks = gson.toJson(personalChalks);
                                        String carString = gson.toJson(car);
                                        JsonObject jsonObject = new JsonObject();
                                        jsonObject.addProperty("chalks", chalks);
                                        jsonObject.addProperty("car", carString);
                                        retrofitViewModel.uploadData(jsonObject.toString());
                                        drawerLayout.closeDrawer(GravityCompat.START);
                                        Toast.makeText(MainActivity.this, "Feltöltés sikeres", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                    }
                });
                dialog.show();
                break;
                //helyi adatok törlése
            case R.id.drawer_menu_delete:
                dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Megerősítés");
                dialog.setMessage("Biztos, hogy töröljük?");
                dialog.setNegativeButton("Mégse", null);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        carViewModel.deleteAllCars();
                        personalChalkViewModel.deleteAllPersonalChalks();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        changeFragment(FIRSTRUNFRAGMENT_ID);
                    }
                });
                dialog.show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onChanged(Integer buttonId) {

    }
}