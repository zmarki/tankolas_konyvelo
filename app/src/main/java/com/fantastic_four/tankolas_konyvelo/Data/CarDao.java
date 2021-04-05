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

    @Query("SELECT * FROM Car")
    LiveData<List<Car>> getAllCar();

    @Query("Select count(*) as num from Car")
    LiveData<Integer> getCarCount();
}
