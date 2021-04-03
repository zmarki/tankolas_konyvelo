package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;

import com.fantastic_four.tankolas_konyvelo.Workers.FuelDatabaseWorker;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class FuelRepository{
    private FuelDao fuelDao;
    private LiveData<List<Fuel>> allFuel;



    public FuelRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(FuelDatabaseWorker.class).build();
        WorkManager.getInstance(context).enqueue(workRequest1);
        fuelDao = appDatabase.fuelDao();

    }

    public LiveData<List<Fuel>> getAllFuel() {
        return allFuel;
    }


}
