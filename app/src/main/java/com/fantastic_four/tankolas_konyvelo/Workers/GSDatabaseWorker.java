package com.fantastic_four.tankolas_konyvelo.Workers;

import android.content.Context;

import com.fantastic_four.tankolas_konyvelo.Data.AppDatabase;
import com.fantastic_four.tankolas_konyvelo.Data.GasStation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



import java.io.InputStream;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class GSDatabaseWorker extends Worker {


    public GSDatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            InputStream inputStream = getApplicationContext().getAssets().open("GasStation.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");
            List<GasStation> gasStationList = new Gson().fromJson(json, new TypeToken<List<GasStation>>(){}.getType());
            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
            appDatabase.gasStationDao().insertAll(gasStationList);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure();
        }

    }
}
