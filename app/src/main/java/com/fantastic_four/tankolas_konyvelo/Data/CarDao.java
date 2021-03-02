package com.fantastic_four.tankolas_konyvelo.Data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CarDao {

    @Query("SELECT * FROM Car")
    List<Car> getAll();

    @Insert
    void insertAll(Car... cars);

    @Delete
    void delete(Car cars);
}
