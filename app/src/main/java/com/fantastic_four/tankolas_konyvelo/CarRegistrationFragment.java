package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

interface CarRegistrationFragmentCallback {
    void onCarRegistrationButtonClicked();
}

public class CarRegistrationFragment extends Fragment {

    private CarRegistrationFragmentCallback carRegistrationFragmentCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.car_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button okButton = view.findViewById(R.id.text_car_registration_OK_btn);
        Spinner fuelTypeSpinner = view.findViewById(R.id.text_car_registration_fuel_spinner);

        String fuelTypes[] = new String[]{"benzin", "gázolaj"};

        ArrayAdapter<String> fuelTypeSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, fuelTypes);
        fuelTypeSpinner.setAdapter(fuelTypeSpinnerAdapter);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (carRegistrationFragmentCallback != null) {
                    carRegistrationFragmentCallback.onCarRegistrationButtonClicked();
                }
            }
        });
    }

    public void setCarRegistrationFragmentListener(CarRegistrationFragmentCallback carRegistrationFragmentCallback) {
        this.carRegistrationFragmentCallback = carRegistrationFragmentCallback;
    }
}
