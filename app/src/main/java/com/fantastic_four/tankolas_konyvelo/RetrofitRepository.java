package com.fantastic_four.tankolas_konyvelo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitRepository {
    private static RetrofitRepository retrofitRepository;

    private final MutableLiveData<ServerResponse> downloadedData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isUpdownloading = new MutableLiveData<>();

    public static RetrofitRepository getInstance() {
        if (retrofitRepository == null) {
            retrofitRepository = new RetrofitRepository();
        }
        return retrofitRepository;
    }

    public void uploadData(String data) {
        isUpdownloading.setValue(true);
        Call<Void> call = RetrofitClient.getInstance().uploadData(data);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                isUpdownloading.postValue(false);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                isUpdownloading.postValue(false);
            }
        });
    }

    public void downloadData(String plateNumber) {
        isUpdownloading.setValue(true);
        Call<ServerResponse> call = RetrofitClient.getInstance().getData(plateNumber);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                downloadedData.postValue(response.body());
                isUpdownloading.postValue(false);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                downloadedData.postValue(null);
                isUpdownloading.postValue(false);
            }
        });
    }

    public LiveData<ServerResponse> getDownloadedData() {
        return downloadedData;
    }

    public LiveData<Boolean> isUpDownloading() {
        return isUpdownloading;
    }

}
