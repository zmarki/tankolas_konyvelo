package com.fantastic_four.tankolas_konyvelo.ViewModel;

import android.app.Application;

import com.fantastic_four.tankolas_konyvelo.Data.PersonalChalkRepository;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.LastFive;
import com.fantastic_four.tankolas_konyvelo.PersonalChalk;
import com.fantastic_four.tankolas_konyvelo.StatThreeModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class StatisticsViewModel extends AndroidViewModel {

    private PersonalChalkRepository personalChalkRepository;

    private LiveData<Float> avgFilling = new MutableLiveData<>();

    public StatisticsViewModel(@NonNull Application application) {
        super(application);
        personalChalkRepository = new PersonalChalkRepository(application);
    }

    public LiveData<LastFive> getLastChalk() {
        return personalChalkRepository.getLastChalk();
    }

    public LiveData<Float> getAvgFilling() {
        avgFilling = personalChalkRepository.getAvgLiter();
        return avgFilling;
    }

    public LiveData<Double> getAvgConsumption() {
        return personalChalkRepository.getAvgConsumption();
    }

    public LiveData<Double> getAvgFillingKM() {
        return personalChalkRepository.getAvgFillingKM();
    }

    public LiveData<Double> getAvgFillingDuration() {
        return personalChalkRepository.getAvgFillingDuration();
    }

    public LiveData<List<LastFive>> getLastFiveChalk() {
        return personalChalkRepository.getLastFiveChalk();
    }

    public LiveData<List<PersonalChalk>> getAllData() {
        return personalChalkRepository.getAllPersonalChalks();
    }

    public LiveData<List<CountSumMonth>> getCountMonthChalk() {
        return personalChalkRepository.getCountMonthChalk();
    }

    public LiveData<List<CountSumMonth>> getSumLiterMonthChalk() {
        return personalChalkRepository.getSumLiterMonthChalk();
    }

    public LiveData<List<CountSumMonth>> getSumMoneyMonthChalk() {
        return personalChalkRepository.getSumMoneyMonthChalk();
    }

    public LiveData<List<StatThreeModel>> getStatThreeData() {
        return personalChalkRepository.getStatThreeData();
    }
}
