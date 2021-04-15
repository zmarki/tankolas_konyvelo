package com.fantastic_four.tankolas_konyvelo.ViewModel;

import android.app.Application;

import com.fantastic_four.tankolas_konyvelo.Data.Fuel;
import com.fantastic_four.tankolas_konyvelo.Data.FuelRepository;
import com.fantastic_four.tankolas_konyvelo.Data.GasStation;
import com.fantastic_four.tankolas_konyvelo.Data.GasStationRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

//Fuel és GasStation táblához kapcsolódó műveletek
public class FuelViewModel extends AndroidViewModel {

    private FuelRepository fuelRepository;
    private GasStationRepository gasStationRepository;

    public FuelViewModel(@NonNull Application application) {
        super(application);
        fuelRepository = new FuelRepository(application);
        gasStationRepository = new GasStationRepository(application);
    }

    public LiveData<List<GasStation>> getGasStations() {
        return gasStationRepository.getAllGS();
    }

    public LiveData<List<Fuel>> getFuels() {
        return fuelRepository.getAllFuel();
    }
}
