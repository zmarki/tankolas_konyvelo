package com.fantastic_four.tankolas_konyvelo.Workers;

import android.content.Context;

import com.fantastic_four.tankolas_konyvelo.Data.GasStationRepository;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class FirstWorker extends Worker {
    private GasStationRepository gasStationRepository;

    public FirstWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {

        gasStationRepository = new GasStationRepository(getApplicationContext());
        return Result.success();
    }
}
