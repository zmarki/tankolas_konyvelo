package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.fantastic_four.tankolas_konyvelo.databinding.CarRegistrationBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class CarRegistrationFragment extends Fragment {

    private MainViewModel mainViewModel;

    private CarRegistrationBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CarRegistrationBinding.inflate(inflater);
        binding.setCar(new Car());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        Button okButton = view.findViewById(R.id.text_car_registration_OK_btn);
        Spinner fuelTypeSpinner = view.findViewById(R.id.text_car_registration_fuel_spinner);

        String fuelTypes[] = new String[]{"benzin", "gázolaj"};

        ArrayAdapter<String> fuelTypeSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, fuelTypes);
        fuelTypeSpinner.setAdapter(fuelTypeSpinnerAdapter);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Car car = binding.getCar();
                Log.i("Car License plate", ":" + car.licensePlate);
                Log.i("Car brand", ":" + car.brand);
                Log.i("Car type", ":" + car.type);
                Log.i("Car ccm", ":" + String.valueOf(car.ccm));
                Log.i("Car power", ":" + String.valueOf(car.power));

                mainViewModel.setClickedButtonId(MainActivity.MAINWINDOWFRAGMENT_ID);
            }
        });
    }
}
