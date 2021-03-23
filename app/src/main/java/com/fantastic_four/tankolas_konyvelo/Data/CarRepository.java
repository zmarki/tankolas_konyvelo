package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CarRepository {
    private CarDao carDao;

    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Car>> allCars;

    public CarRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        carDao = appDatabase.carDao();
        allCars = carDao.getAllCar();
    }


    public void insertCar(Car car) {
        insertCarAsync(car);

    }

    public void deleteCar(Car car) {

    }

    public LiveData<List<Car>> getAllCars() {

        return allCars;
    }


    // Beilleszti a táblázatba az adatokat egy külön szálon a háttérben, és az insertResult-ba rögziti hogy sikeres volt-e.
    private void insertCarAsync(final Car car) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    carDao.insertCar(car);
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

}
