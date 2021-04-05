package com.fantastic_four.tankolas_konyvelo.ViewModel;

import android.app.Application;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.LastFive;
import com.fantastic_four.tankolas_konyvelo.PersonalChalk;
import com.fantastic_four.tankolas_konyvelo.Data.PersonalChalkRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PersonalChalkViewModel extends AndroidViewModel {
    private PersonalChalkRepository personalChalkRepository;

    public PersonalChalkViewModel(@NonNull Application application) {
        super(application);
        personalChalkRepository = new PersonalChalkRepository(application);
    }

    public void insertPersonalChalk(PersonalChalk personalChalk) {
        personalChalkRepository.insertPersonalChalk(personalChalk);
    }

    public void deleteAllPersonalChalks(){
        personalChalkRepository.deleteAllPersonalChalks();
    }
}
