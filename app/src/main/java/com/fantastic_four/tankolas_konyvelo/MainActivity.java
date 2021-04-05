package com.fantastic_four.tankolas_konyvelo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.fantastic_four.tankolas_konyvelo.Data.Converters;
import com.fantastic_four.tankolas_konyvelo.ViewModel.CarViewModel;
import com.fantastic_four.tankolas_konyvelo.ViewModel.PersonalChalkViewModel;
import com.fantastic_four.tankolas_konyvelo.ViewModel.StatisticsViewModel;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    boolean isMainWindow = false;

    private CarViewModel carViewModel;
    private PersonalChalkViewModel personalChalkViewModel;

    @Override
    public void onBackPressed() {
        if (isMainWindow) {
            this.moveTaskToBack(true);
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //ADATOK FELTÖLTÉSE
        Calendar calendar = Calendar.getInstance();
       /* calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 12);
        personalChalkViewModel.insertPersonalChalk(new PersonalChalk(120, 30, 390, 1, calendar.getTime()));
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        personalChalkViewModel.insertPersonalChalk(new PersonalChalk(520, 20, 410, 4, calendar.getTime()));
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 12);
        personalChalkViewModel.insertPersonalChalk(new PersonalChalk(750, 27, 400, 7, calendar.getTime()));
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DAY_OF_MONTH, 02);
        personalChalkViewModel.insertPersonalChalk(new PersonalChalk(920, 52, 450, 16, calendar.getTime()));
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DAY_OF_MONTH, 12);
        personalChalkViewModel.insertPersonalChalk(new PersonalChalk(1480, 47, 370, 13, calendar.getTime()));
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 7);
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        personalChalkViewModel.insertPersonalChalk(new PersonalChalk(1900, 18, 399, 10, calendar.getTime()));
*/

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //van-e kocsi regisztrálva a rendszerben
        carViewModel.getCarCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer > 0) {
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24px);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    changeFragment(MAINWINDOWFRAGMENT_ID);
                } else {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    changeFragment(FIRSTRUNFRAGMENT_ID);
                }
            }
        });

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getClickedButton().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer buttonId) {
                changeFragment(buttonId);
            }
        });
        mainViewModel.getClickedButton().observe(this, this);
    }

    private void changeFragment(int fragment_id) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        isMainWindow = false;
        switch (fragment_id) {
            case FIRSTRUNFRAGMENT_ID:
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
                fragmentTransaction.replace(R.id.fragment_placeholder, new CarRegistrationFragment());
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_menu_open:
                Toast.makeText(this, "Megnyitás", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_menu_save:
                Toast.makeText(this, "Mentés", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_menu_delete:
                Toast.makeText(this, "Törlés", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Megerősítés");
                dialog.setMessage("Biztos, hogy töröljük?");
                dialog.setNegativeButton("Mégse", null);
                dialog.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        carViewModel.deleteAllCars();
                        personalChalkViewModel.deleteAllPersonalChalks();
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        changeFragment(FIRSTRUNFRAGMENT_ID);
                    }
                });
                dialog.show();
                break;
        }
        return false;
    }

    @Override
    public void onChanged(Integer buttonId) {

    }
}