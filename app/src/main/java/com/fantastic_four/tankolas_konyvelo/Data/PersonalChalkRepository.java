package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;
import android.util.Log;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.LastFive;
import com.fantastic_four.tankolas_konyvelo.PersonalChalk;
import com.fantastic_four.tankolas_konyvelo.PrevDataModel;
import com.fantastic_four.tankolas_konyvelo.StatThreeModel;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PersonalChalkRepository {
    private final PersonalChalkDao personalChalkDao;

    //  private final MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private final LiveData<List<CountSumMonth>> countMonthChalk;
    private final LiveData<List<CountSumMonth>> sumLiterMonthChalk;
    private final LiveData<List<LastFive>> lastFiveChalk;
    private final LiveData<LastFive> lastChalk;
    private final LiveData<Integer> lastChalkMileage;
    private final LiveData<Double> avgConsumption;
    private final LiveData<Double> avgFillingKM;
    private final LiveData<Float> avgLiter;
    private final LiveData<Double> avgFillingDuration;
    private final LiveData<List<PersonalChalk>> allData;
    private final LiveData<List<PrevDataModel>> allDataWithGSFuelName;
    private final LiveData<List<StatThreeModel>> statThreeData;

    public PersonalChalkRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        personalChalkDao = appDatabase.personalChalkDao();
        countMonthChalk = personalChalkDao.countMonthChalk();
        sumLiterMonthChalk = personalChalkDao.sumLiterMonthChalk();
        lastFiveChalk = personalChalkDao.lastFiveChalk();
        lastChalk = personalChalkDao.lastChalk();
        lastChalkMileage = personalChalkDao.lastChalkMileage();
        avgConsumption = personalChalkDao.avgConsumption();
        avgFillingKM = personalChalkDao.avgFillingKM();
        avgLiter = personalChalkDao.avgLiter();
        avgFillingDuration = personalChalkDao.avgFillingDuration();
        allData = personalChalkDao.getAllPersonalChalks();
        allDataWithGSFuelName = personalChalkDao.getAllPersonalChalksWithGSFuelNames();
        statThreeData = personalChalkDao.statThreeData();
    }

    public void insertPersonalChalk(PersonalChalk personalChalk) {
        insertPersonalChalkAsync(personalChalk);
    }

    private void insertPersonalChalkAsync(final PersonalChalk personalChalk) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("PersonalChalkRepo", "insert ok");
                    personalChalkDao.insertAllChalk(personalChalk);
                    //insertResult.postValue(1);
                } catch (Exception e) {
                    Log.e("PersonalChalkRepo", "insert nem ok");
                    e.printStackTrace();
                    //insertResult.postValue(0);
                }
            }
        }).start();
    }

    public void deleteAllPersonalChalks() {
        deleteALLPersonalChalkAsync();
    }

    private void deleteALLPersonalChalkAsync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    personalChalkDao.deleteALL();
                } catch (Exception e) {

                }
            }
        }).start();
    }

   /* public MutableLiveData<Integer> getInsertResult() {
        return insertResult;
    }*/

    public LiveData<LastFive> getLastChalk() {
        return lastChalk;
    }

    public LiveData<Double> getAvgConsumption() {

        return avgConsumption;
    }

    public LiveData<Float> getAvgLiter() {

        return avgLiter;
    }

    public LiveData<Double> getAvgFillingDuration() {

        return avgFillingDuration;
    }

    public LiveData<Double> getAvgFillingKM() {
        return avgFillingKM;
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

    public LiveData<Integer> getLastChalkMileage() {
        return lastChalkMileage;
    }

    public LiveData<List<PersonalChalk>> getAllPersonalChalks() {
        return allData;
    }

    public LiveData<List<PrevDataModel>> getAllDataWithGSFuelName() {
        return allDataWithGSFuelName;
    }

    public LiveData<List<StatThreeModel>> getStatThreeData() {
        return statThreeData;
    }
}
