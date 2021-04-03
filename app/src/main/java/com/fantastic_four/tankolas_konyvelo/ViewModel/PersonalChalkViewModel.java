package com.fantastic_four.tankolas_konyvelo.ViewModel;

import android.app.Application;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.LastFive;
import com.fantastic_four.tankolas_konyvelo.Data.PersonalChalk;
import com.fantastic_four.tankolas_konyvelo.Data.PersonalChalkRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PersonalChalkViewModel extends AndroidViewModel {
    private PersonalChalkRepository personalChalkRepository;
    private LiveData<List<CountSumMonth>> countMonthChalk;
    private LiveData<List<CountSumMonth>> sumLiterMonthChalk;
    private LiveData<List<LastFive>> lastFiveChalk;

    public PersonalChalkViewModel(@NonNull Application application) {
        super(application);
        personalChalkRepository = new PersonalChalkRepository(application);
        countMonthChalk = personalChalkRepository.getCountMonthChalk();
        sumLiterMonthChalk = personalChalkRepository.getSumLiterMonthChalk();
        lastFiveChalk = personalChalkRepository.getLastFiveChalk();

    }

    public void insertPersonalChalk(PersonalChalk personalChalk) {
        personalChalkRepository.insertPersonalChalk(personalChalk);
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
