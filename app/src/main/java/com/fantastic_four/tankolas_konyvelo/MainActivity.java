package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstRunOpenScreenFragmentCallback, MainWindowFragmentCallBack, NewFillingFragment.NewFillingFragmentCallback {

    private FragmentTransaction fragmentTransaction;

    private FirstRunOpenScreenFragment firstRunOpenScreenFragment;
    private MainWindowFragment mainWindowFragment;
    private NewFillingFragment newFillingFragment;
    private CarRegistrationFragment carRegistrationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstRunOpenScreenFragment = new FirstRunOpenScreenFragment();
        mainWindowFragment = new MainWindowFragment();
        newFillingFragment = new NewFillingFragment();
        carRegistrationFragment = new CarRegistrationFragment();
        firstRunOpenScreenFragment.setFirstRunCallback(this);
        mainWindowFragment.setMainWindowFragmentListener(this);
        newFillingFragment.setNewFillingFragemtListener(this);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (1 == 1) {  //Még nincsen új autó regisztrálva
            fragmentTransaction.replace(R.id.fragment_placeholder, firstRunOpenScreenFragment);
        } else {
            fragmentTransaction.replace(R.id.fragment_placeholder, mainWindowFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void mainWindowButtonClicked(int buttonCode) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (buttonCode == MainWindowFragment.ADD_NEW_FILLING_BTNCODE) {
            fragmentTransaction.replace(R.id.fragment_placeholder, newFillingFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onOkButtonClicked() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, mainWindowFragment);
        fragmentTransaction.commit();
        Toast.makeText(this, "Új adatok elmentve", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMenuPointClicked(int id) {
        if (id == FirstRunOpenScreenFragment.REGISTER_NEW_CAR_ID) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_placeholder, carRegistrationFragment);
            fragmentTransaction.commit();
        }
    }
}