package com.fantastic_four.tankolas_konyvelo.Workers;

import android.content.Context;

import com.fantastic_four.tankolas_konyvelo.Data.FuelRepository;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class SecondWorker extends Worker {
    private FuelRepository fuelRepository;

    public SecondWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        fuelRepository = new FuelRepository(getApplicationContext());
        return Result.success();
    }
}
