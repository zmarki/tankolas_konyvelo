package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;

import com.fantastic_four.tankolas_konyvelo.Workers.GSDatabaseWorker;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class GasStationRepository {
    private GasStationDao gasStationDao;

    private LiveData<List<GasStation>> allGS;
    private LiveData<List<String>> gsName;
    private LiveData<List<String>> fName;


    public GasStationRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(GSDatabaseWorker.class).build();
        WorkManager.getInstance(context).enqueue(workRequest);
        gasStationDao = appDatabase.gasStationDao();

    }


    public LiveData<List<String>> getGsName(int GSid) {

        return gsName;
    }

    public LiveData<List<GasStation>> getAllGS()
    {
        return allGS;
    }

    public LiveData<List<String>> getfName() {
        return fName;
    }
}
