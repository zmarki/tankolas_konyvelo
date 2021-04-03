package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Button;

import com.fantastic_four.tankolas_konyvelo.Data.Car;
import com.fantastic_four.tankolas_konyvelo.Data.Converters;
import com.fantastic_four.tankolas_konyvelo.Data.PersonalChalk;
import com.fantastic_four.tankolas_konyvelo.ViewModel.CarViewModel;
import com.fantastic_four.tankolas_konyvelo.ViewModel.PersonalChalkViewModel;
import com.fantastic_four.tankolas_konyvelo.Workers.WorkManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.TypeConverters;

@SuppressWarnings("ALL")
@TypeConverters(Converters.class)
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MainViewModel mainViewModel;

    private DrawerLayout drawerLayout;
    private FragmentTransaction fragmentTransaction;

    public static final int FIRSTRUNFRAGMENT_ID = 100;
    public static final int MAINWINDOWFRAGMENT_ID = 101;
    public static final int NEWFILLINGFRAGMENT_ID = 102;
    public static final int CARREGISTRATIONFRAGMENT_ID = 103;
  
  private CarViewModel carViewModel;
    private PersonalChalkViewModel personalChalkViewModel;
    private WorkManager workManager = new WorkManager();
    // private GasStationRepository gasStationRepository;
    //private FuelRepository fuelRepository;


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


        Car car = new Car("EDK579", "Toyota", "Prius", 1600, 80, "benzin", 478747);

       /* carViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer result) {
                if (result==1) {
                    Toast.makeText(MainActivity.this, "Car Successfully save", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        PersonalChalk personalChalk;

        personalChalk = new PersonalChalk();
        try {
            personalChalk.date = sdf.parse("2021-05-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        personalChalk.fuelId = 1;
        personalChalk.liter = 15;
        personalChalk.mileage = 150;
        personalChalk.price = 415;


        carViewModel.insertCar(car);
        personalChalkViewModel.insertPersonalChalk(personalChalk);
      
      
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (1 == 1) {  //Még nincsen új autó regisztrálva
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            changeFragment(FIRSTRUNFRAGMENT_ID);
        } else {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24px);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           changeFragment(MAINWINDOWFRAGMENT_ID);
        }

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getClickedButton().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer buttonId) {
                changeFragment(buttonId);
            }
        });
    }

    private void changeFragment(int fragment_id) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (fragment_id) {
            case FIRSTRUNFRAGMENT_ID:
                fragmentTransaction.replace(R.id.fragment_placeholder, new FirstRunOpenScreenFragment());
                break;
            case MAINWINDOWFRAGMENT_ID:
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
                break;
        }
        return false;
    }
}