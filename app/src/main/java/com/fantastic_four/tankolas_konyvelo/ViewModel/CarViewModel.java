package com.fantastic_four.tankolas_konyvelo.ViewModel;

import android.app.Application;

import com.fantastic_four.tankolas_konyvelo.Car;
import com.fantastic_four.tankolas_konyvelo.Data.CarRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CarViewModel extends AndroidViewModel {
    private CarRepository carRepository;
    // private LiveData<Integer> insertResult;
    private LiveData<Car> actualCar;

    public CarViewModel(@NonNull Application application) {
        super(application);
        carRepository = new CarRepository(application);
        //   insertResult = carRepository.getInsertResult();
        actualCar = carRepository.getCar();

    }

  /*  public LiveData<Integer> getInsertResult() {
        return insertResult;
    }*/

    public void insertCar(Car car) {
        carRepository.insertCar(car);
    }

    public void delete(Car car) {
        carRepository.deleteCar(car);
    }

    public LiveData<Car> getCar() {
        return actualCar;
    }

    public void deleteAllCars() {
        carRepository.deleteAllCars();
    }

    public LiveData<Boolean> isAllCarDeleted() {
        return carRepository.getIsAllCarDeleted();
    }
}
