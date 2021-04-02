package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.LastFive;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PersonalChalkRepository {
    private final PersonalChalkDao personalChalkDao;

  //  private final MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private final LiveData<List<CountSumMonth>> countMonthChalk;
    private final LiveData<List<CountSumMonth>> sumLiterMonthChalk;
    private final LiveData<List<LastFive>> lastFiveChalk;
    private final LiveData<Double> avgConsumption;
    private final LiveData<Integer> avgLiter;

    public PersonalChalkRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        personalChalkDao = appDatabase.personalChalkDao();
        countMonthChalk = personalChalkDao.countMonthChalk();
        sumLiterMonthChalk = personalChalkDao.sumLiterMonthChalk();
        lastFiveChalk = personalChalkDao.lastFiveChalk();
        avgConsumption = personalChalkDao.avgConsumption();
        avgLiter = personalChalkDao.avgLiter();

    }

    public void insertPersonalChalk(PersonalChalk personalChalk) {
        insertPersonalChalkAsync(personalChalk);
    }

    private void insertPersonalChalkAsync(final PersonalChalk personalChalk) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    personalChalkDao.insertAllChalk(personalChalk);
                    //insertResult.postValue(1);
                } catch (Exception e) {
                    //insertResult.postValue(0);
                }
            }
        }).start();
    }

    public void deleteALLPersonalChalk(PersonalChalk personalChalk) {
        deleteALLPersonalChalkAsync(personalChalk);
    }

    private void deleteALLPersonalChalkAsync(final PersonalChalk personalChalk) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    personalChalkDao.deleteALL();
                } catch (Exception e) {

                }
            }
        }).start();
    }

   /* public MutableLiveData<Integer> getInsertResult() {
        return insertResult;
    }*/

    public LiveData<Double> getAvgConsumption() {

        return avgConsumption;
    }

    public LiveData<Integer> getAvgLiter() {

        return avgLiter;
    }

    public LiveData<List<CountSumMonth>> getCountMonthChalk() {

        return countMonthChalk;
    }

    public LiveData<List<CountSumMonth>> getSumLiterMonthChalk() {

        return sumLiterMonthChalk;
    }

    public LiveData<List<LastFive>> getLastFiveChalk() {

        return lastFiveChalk;
    }
}
