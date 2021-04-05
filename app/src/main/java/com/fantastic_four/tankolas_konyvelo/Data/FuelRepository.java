package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FuelRepository{
    private FuelDao fuelDao;

    public FuelRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
      /*  OneTimeWorkRequest workRequest1 = new OneTimeWorkRequest.Builder(FuelDatabaseWorker.class).build();
        WorkManager.getInstance(context).enqueue(workRequest1);*/
        fuelDao = appDatabase.fuelDao();

    }

    public LiveData<List<Fuel>> getAllFuel() {
        return fuelDao.getAllFuel();
    }


}
