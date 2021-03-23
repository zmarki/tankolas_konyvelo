package com.fantastic_four.tankolas_konyvelo.Workers;

import android.content.Context;
import android.util.Log;

import com.fantastic_four.tankolas_konyvelo.Data.AppDatabase;
import com.fantastic_four.tankolas_konyvelo.Data.Fuel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class FuelDatabaseWorker extends Worker {
    private static final String TAG ="FuelWorker";

    public FuelDatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {
        Log.e(TAG, "Fuel belovasás");
        try {
            InputStream inputStream = getApplicationContext().getAssets().open("Fuel.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            List<Fuel> fuelList = new Gson().fromJson(json, new TypeToken<List<Fuel>>(){}.getType());
            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
            appDatabase.fuelDao().insertAllFuel(fuelList);
            return ListenableWorker.Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ListenableWorker.Result.failure();
        }

    }
}
