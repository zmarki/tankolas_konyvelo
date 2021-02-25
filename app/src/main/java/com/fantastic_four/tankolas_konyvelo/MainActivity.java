package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements MainWindowFragmentCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainWindowFragment mainWindowFragment = new MainWindowFragment();
        mainWindowFragment.setMainWindowFragmentListener(this::mainWindowButtonClicked);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_placeholder, mainWindowFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void mainWindowButtonClicked(int buttonCode) {
        Log.e("valami", "vala");
        Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show();
    }
}