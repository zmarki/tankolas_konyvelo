package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements MainWindowFragmentCallBack, NewFillingFragment.NewFillingFragmentCallback {

    private FragmentTransaction fragmentTransaction;
    private MainWindowFragment mainWindowFragment;
    private NewFillingFragment newFillingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainWindowFragment = new MainWindowFragment();
        newFillingFragment = new NewFillingFragment();
        mainWindowFragment.setMainWindowFragmentListener(this::mainWindowButtonClicked);
        newFillingFragment.setNewFillingFragemtListener(this::onOkButtonClicked);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (1 == 1) {  //Még nincsen új autó regisztrálva
            fragmentTransaction.replace(R.id.fragment_placeholder, new FirstRunOpenScreenFragment());
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
}