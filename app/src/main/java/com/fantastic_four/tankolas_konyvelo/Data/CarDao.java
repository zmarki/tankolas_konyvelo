package com.fantastic_four.tankolas_konyvelo.Data;

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

    @Query("SELECT * FROM Car")
    LiveData<List<Car>> getAllCar();
}
