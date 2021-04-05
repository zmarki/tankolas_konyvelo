package com.fantastic_four.tankolas_konyvelo.ViewModel;

import android.app.Application;

import com.fantastic_four.tankolas_konyvelo.Car;
import com.fantastic_four.tankolas_konyvelo.Data.CarRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CarViewModel extends AndroidViewModel {
    private CarRepository carRepository;
   // private LiveData<Integer> insertResult;
    private LiveData<List<Car>> allCars;

    public CarViewModel(@NonNull Application application) {
        super(application);
        carRepository = new CarRepository(application);
     //   insertResult = carRepository.getInsertResult();
        allCars = carRepository.getAllCars();

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

    public LiveData<List<Car>> getAllCars() {
        return allCars;
    }

    public LiveData<Integer> getCarCount(){
        return carRepository.getCarCount();
    }

    public void deleteAllCars(){
        carRepository.deleteAllCars();
    }
}
