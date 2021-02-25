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

public class NewFillingFragment extends Fragment {

    interface NewFillingFragmentCallback {
        void onOkButtonClicked();
    }

    private NewFillingFragmentCallback newFillingFragmentCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_filling, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner fuelTypeSpinner = view.findViewById(R.id.text_new_filling_fuel_type_spinner);
        Spinner stationTypeSpinner = view.findViewById(R.id.text_new_filling_station_type_spinner);
        Button okButton = view.findViewById(R.id.new_filling_OK_btn);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SQL műveletek ide jönnek
                if (newFillingFragmentCallback != null) {
                    newFillingFragmentCallback.onOkButtonClicked();
                }
            }
        });

        String fuelTypes[] = new String[]{"95", "98", "Speed max", "Egyebek"};

        ArrayAdapter<String> fuelTypeSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, fuelTypes);
        fuelTypeSpinner.setAdapter(fuelTypeSpinnerAdapter);

        String stationTypes[] = new String[]{"MOL", "OMV", "Lukoil", "Shell"};

        ArrayAdapter<String> stationTypeSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, stationTypes);
        stationTypeSpinner.setAdapter(stationTypeSpinnerAdapter);
    }

    public void setNewFillingFragemtListener(NewFillingFragmentCallback newFillingFragmentCallback) {
        this.newFillingFragmentCallback = newFillingFragmentCallback;
    }
}
