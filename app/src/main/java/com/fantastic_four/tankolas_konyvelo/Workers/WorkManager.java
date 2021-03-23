package com.fantastic_four.tankolas_konyvelo.Workers;

import androidx.work.OneTimeWorkRequest;

public class WorkManager {
    private OneTimeWorkRequest first = new OneTimeWorkRequest.Builder(FirstWorker.class).build();
    private OneTimeWorkRequest second = new OneTimeWorkRequest.Builder(SecondWorker.class).build();

    public WorkManager() {
        androidx.work.WorkManager.getInstance()
                .beginWith(first)
                .then(second)
                .enqueue();

    }
}
