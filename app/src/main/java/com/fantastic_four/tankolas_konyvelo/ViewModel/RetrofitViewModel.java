package com.fantastic_four.tankolas_konyvelo.ViewModel;

import android.app.Application;

import com.fantastic_four.tankolas_konyvelo.RetrofitRepository;
import com.fantastic_four.tankolas_konyvelo.Data.ServerResponse;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

//Retrofit-es műveletek (letöltés, feltőltés)
public class RetrofitViewModel extends AndroidViewModel {

    private RetrofitRepository retrofitRepository;

    public RetrofitViewModel(@NonNull Application application) {
        super(application);
        retrofitRepository = RetrofitRepository.getInstance();
    }

    public LiveData<Boolean> isUpDownloading() {
        return retrofitRepository.isUpDownloading();
    }

    public LiveData<ServerResponse> getDownloadedData() {
        return retrofitRepository.getDownloadedData();
    }

    public void downloadData(String plateNumber) {
        retrofitRepository.downloadData(plateNumber);
    }

    public void uploadData(String data) {
        retrofitRepository.uploadData(data);
    }
}
