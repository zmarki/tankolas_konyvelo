package com.fantastic_four.tankolas_konyvelo.Data;

import com.fantastic_four.tankolas_konyvelo.Car;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CarDao {

    @Insert
    void insertCar(Car cars);

    @Delete
    void deleteCar(Car cars);

    @Query("delete from Car")
    void deleteAllCars();

    @Query("SELECT * FROM Car limit 1")
    LiveData<Car> getCar();
}
