package com.fantastic_four.tankolas_konyvelo.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.fantastic_four.tankolas_konyvelo.Car;
import com.fantastic_four.tankolas_konyvelo.MainActivity;
import com.fantastic_four.tankolas_konyvelo.R;
import com.fantastic_four.tankolas_konyvelo.ViewModel.CarViewModel;
import com.fantastic_four.tankolas_konyvelo.databinding.CarRegistrationBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class CarRegistrationFragment extends Fragment {

    private MainViewModel mainViewModel;
    private CarViewModel carViewModel;

    private Car car;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CarRegistrationBinding binding = CarRegistrationBinding.inflate(inflater, container, false);
        car = new Car();
        binding.setCar(car);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        carViewModel = new ViewModelProvider(requireActivity()).get(CarViewModel.class);

        Button okButton = view.findViewById(R.id.text_car_registration_OK_btn);
        Spinner fuelTypeSpinner = view.findViewById(R.id.text_car_registration_fuel_spinner);

        String fuelTypes[] = new String[]{"benzin", "gázolaj", "elektromos", "LPG"};
        car.fuel = fuelTypes[0];

        ArrayAdapter<String> fuelTypeSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, fuelTypes);
        fuelTypeSpinner.setAdapter(fuelTypeSpinnerAdapter);
        fuelTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                car.fuel = fuelTypes[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("CArereg", "brand: " + car.brand);
                if (car.type == null || car.brand == null || car.ccm == 0 || car.fuel == null || car.licensePlateNumber == null
                        || car.kw == 0 || car.type.length() < 1 || car.brand.length() < 1 || car.licensePlateNumber.length() < 5) {
                    Toast.makeText(getActivity(), "Hiányzó adatok!", Toast.LENGTH_LONG).show();
                } else {
                    carViewModel.insertCar(car);
                    mainViewModel.setClickedButtonId(MainActivity.MAINWINDOWFRAGMENT_ID);
                }
            }
        });
    }
}
