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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MainViewModel mainViewModel;

    private DrawerLayout drawerLayout;
    private FragmentTransaction fragmentTransaction;

    public static final int FIRSTRUNFRAGMENT_ID = 100;
    public static final int MAINWINDOWFRAGMENT_ID = 101;
    public static final int NEWFILLINGFRAGMENT_ID = 102;
    public static final int CARREGISTRATIONFRAGMENT_ID = 103;


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