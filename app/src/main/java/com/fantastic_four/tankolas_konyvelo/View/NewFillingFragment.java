package com.fantastic_four.tankolas_konyvelo.View;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fantastic_four.tankolas_konyvelo.Data.Fuel;
import com.fantastic_four.tankolas_konyvelo.Data.GasStation;
import com.fantastic_four.tankolas_konyvelo.MainActivity;
import com.fantastic_four.tankolas_konyvelo.PersonalChalk;
import com.fantastic_four.tankolas_konyvelo.R;
import com.fantastic_four.tankolas_konyvelo.ViewModel.FuelViewModel;
import com.fantastic_four.tankolas_konyvelo.ViewModel.PersonalChalkViewModel;
import com.fantastic_four.tankolas_konyvelo.databinding.NewFillingBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

//Fragment új tankolás felviteléhez
public class NewFillingFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

    private PersonalChalk personalChalk;

    private EditText mileageEditText;

    private MainViewModel mainViewModel;
    private PersonalChalkViewModel personalChalkViewModel;
    private FuelViewModel fuelViewModel;

    private Spinner fuelTypeSpinner;
    private Spinner stationTypeSpinner;
    private ArrayAdapter<String> fuelTypeSpinnerAdapter;

    private List<GasStation> gasStations;
    private List<Fuel> fuelTypes;
    private List<Fuel> selectedStationFuelTypes;

    private int lastChalkMileage = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NewFillingBinding binding = DataBindingUtil.inflate(inflater, R.layout.new_filling, container, false);
        personalChalk = new PersonalChalk();
        personalChalk.date = new Date(System.currentTimeMillis());
        binding.setPersonalChalk(personalChalk);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mileageEditText = view.findViewById(R.id.text_new_filling_KM_edittext);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        personalChalkViewModel = new ViewModelProvider(requireActivity()).get(PersonalChalkViewModel.class);
        fuelViewModel = new ViewModelProvider(requireActivity()).get(FuelViewModel.class);
        fuelViewModel.getFuels().observe(getViewLifecycleOwner(), fuelsObserver);
        fuelViewModel.getGasStations().observe(getViewLifecycleOwner(), gasStationsObserver);

        personalChalkViewModel.getLastChalkMileage().observe(getViewLifecycleOwner(), getLastChalkMileage);

        fuelTypeSpinner = view.findViewById(R.id.text_new_filling_fuel_type_spinner);
        fuelTypeSpinner.setOnItemSelectedListener(this);
        stationTypeSpinner = view.findViewById(R.id.text_new_filling_station_type_spinner);
        stationTypeSpinner.setOnItemSelectedListener(this);

        selectedStationFuelTypes = new ArrayList<>();

        TextView datePickerTextView = view.findViewById(R.id.text_new_filling_fuel_date_datepicker);
        datePickerTextView.setText(simpleDateFormat.format(new Date(System.currentTimeMillis())));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePickerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        datePickerTextView.setText(simpleDateFormat.format(calendar.getTime()));
                        personalChalk.date = calendar.getTime();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        Button okButton = view.findViewById(R.id.new_filling_OK_btn);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (personalChalk.liter == 0 || personalChalk.mileage == 0 || personalChalk.price == 0 ||
                        personalChalk.date == null) {
                    Toast.makeText(getActivity(), "Nincs minden mező kitöltve!", Toast.LENGTH_LONG).show();
                } else if (personalChalk.mileage <= lastChalkMileage) {
                    Toast.makeText(getActivity(), "A megadott KM óraállás kisebb, mint a korábbi!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Adatbevitel sikeres", Toast.LENGTH_LONG).show();
                    personalChalkViewModel.insertPersonalChalk(personalChalk);
                    mainViewModel.setClickedButtonId(MainActivity.MAINWINDOWFRAGMENT_ID);
                }
            }
        });

        List<String> selectedStationFuelTypesString = new ArrayList<>();
        selectedStationFuelTypesString.add("Válassz töltőállomást");

        fuelTypeSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, selectedStationFuelTypesString);
        fuelTypeSpinner.setAdapter(fuelTypeSpinnerAdapter);
    }

    private Observer getLastChalkMileage = new Observer() {
        @Override
        public void onChanged(Object o) {
            Integer mil = (Integer) o;
            if (mil != null) {
                lastChalkMileage = mil;
                personalChalk.mileage = lastChalkMileage;
            }
        }
    };

    private Observer fuelsObserver = new Observer() {
        @Override
        public void onChanged(Object o) {
            NewFillingFragment.this.fuelTypes = (List<Fuel>) o;
        }
    };

    private Observer gasStationsObserver = new Observer() {
        @Override
        public void onChanged(Object o) {
            gasStations = (List<GasStation>) o;
            List<String> stationTypes = new ArrayList<>();
            for (GasStation gasStation : gasStations) {
                stationTypes.add(gasStation.name);
            }
            ArrayAdapter<String> stationTypeSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, stationTypes);
            stationTypeSpinner.setAdapter(stationTypeSpinnerAdapter);
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.text_new_filling_station_type_spinner && fuelTypes != null) {
            selectedStationFuelTypes.clear();
            for (Fuel fuel : fuelTypes) {
                if (fuel.GSid == gasStations.get(i).getId()) {
                    selectedStationFuelTypes.add(fuel);
                }
            }

            List<String> selectedStationFuelTypesString = new ArrayList<>();
            for (Fuel fuel : selectedStationFuelTypes) {
                selectedStationFuelTypesString.add(fuel.getFuelName());
            }

            fuelTypeSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, selectedStationFuelTypesString);
            fuelTypeSpinner.setAdapter(fuelTypeSpinnerAdapter);
        } else if (adapterView.getId() == R.id.text_new_filling_fuel_type_spinner && selectedStationFuelTypes.size() > 0) {
            personalChalk.setFuelId(selectedStationFuelTypes.get(i).id);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
