package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;

import com.fantastic_four.tankolas_konyvelo.Car;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CarRepository {
    private CarDao carDao;

    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<Car> actualCar;
    private LiveData<Integer> carCount = new MutableLiveData<>();

    public CarRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        carDao = appDatabase.carDao();
        actualCar = carDao.getCar();
    }


    public void insertCar(Car car) {
        insertCarAsync(car);

    }

    public void deleteCar(Car car) {
        deleteCarAsync(car);

    }

    public LiveData<Car> getCar() {

        return actualCar;
    }

    public void deleteAllCars() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                carDao.deleteAllCars();
            }
        });
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

    private void deleteCarAsync(final Car car) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    carDao.deleteCar(car);
                } catch (Exception e) {

                }
            }
        }).start();
    }

    public MutableLiveData<Integer> getInsertResult() {

        return insertResult;
    }

}
