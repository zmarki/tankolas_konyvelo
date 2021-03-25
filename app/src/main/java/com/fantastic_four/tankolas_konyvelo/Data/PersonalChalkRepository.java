package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.LastFive;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PersonalChalkRepository {
    private PersonalChalkDao personalChalkDao;

    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<CountSumMonth>> countMonthChalk;
    private LiveData<List<CountSumMonth>> sumLiterMonthChalk;
    private LiveData<List<LastFive>> lastFiveChalk;

    public PersonalChalkRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        personalChalkDao = appDatabase.personalChalkDao();
       /* countMonthChalk = personalChalkDao.countMonthChalk();
        sumLiterMonthChalk = personalChalkDao.sumLiterMonthChalk();*/
        lastFiveChalk = personalChalkDao.lastFiveChalk();
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
                    insertResult.postValue(1);
                } catch (Exception e) {
                    insertResult.postValue(0);
                }
            }
        }).start();
    }

    public MutableLiveData<Integer> getInsertResult() {
        return insertResult;
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
