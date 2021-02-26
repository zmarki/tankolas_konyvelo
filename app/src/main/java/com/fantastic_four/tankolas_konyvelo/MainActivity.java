package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstRunOpenScreenFragmentCallback, MainWindowFragmentCallBack, NewFillingFragmentCallback, CarRegistrationFragmentCallback {


    private DrawerLayout drawerLayout;
    private FragmentTransaction fragmentTransaction;

    private FirstRunOpenScreenFragment firstRunOpenScreenFragment;
    private MainWindowFragment mainWindowFragment;
    private NewFillingFragment newFillingFragment;
    private CarRegistrationFragment carRegistrationFragment;

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


        firstRunOpenScreenFragment = new FirstRunOpenScreenFragment();
        mainWindowFragment = new MainWindowFragment();
        newFillingFragment = new NewFillingFragment();
        carRegistrationFragment = new CarRegistrationFragment();
        firstRunOpenScreenFragment.setFirstRunCallback(this);
        mainWindowFragment.setMainWindowFragmentListener(this);
        newFillingFragment.setNewFillingFragmentListener(this);
        carRegistrationFragment.setCarRegistrationFragmentListener(this);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (1 == 1) {  //Még nincsen új autó regisztrálva
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            fragmentTransaction.replace(R.id.fragment_placeholder, firstRunOpenScreenFragment);
        } else {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24px);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            fragmentTransaction.replace(R.id.fragment_placeholder, mainWindowFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onMainWindowButtonClicked(int buttonCode) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (buttonCode == MainWindowFragment.ADD_NEW_FILLING_BTNCODE) {
            fragmentTransaction.replace(R.id.fragment_placeholder, newFillingFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onNewFillingButtonClicked() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, mainWindowFragment);
        fragmentTransaction.commit();
        Toast.makeText(this, "Új adatok elmentve", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFirstRunScreenButtonClicked(int id) {
        if (id == FirstRunOpenScreenFragment.REGISTER_NEW_CAR_ID) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24px);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_placeholder, carRegistrationFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onCarRegistrationButtonClicked() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, mainWindowFragment);
        fragmentTransaction.commit();
    }
}