package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;

import java.util.List;

import androidx.lifecycle.LiveData;

public class GasStationRepository {
    private GasStationDao gasStationDao;


    public GasStationRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
       /* OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(GSDatabaseWorker.class).build();
        WorkManager.getInstance(context).enqueue(workRequest);*/
        gasStationDao = appDatabase.gasStationDao();

    }

    public LiveData<List<GasStation>> getAllGS()
    {
        return gasStationDao.getAllGS();
    }

}
