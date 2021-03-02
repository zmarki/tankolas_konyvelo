package com.fantastic_four.tankolas_konyvelo.Data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface FuelDao {

    @Query("SELECT * FROM Fuel")
    List<Fuel> getAll();

    @Query("SELECT 'FuelName' FROM Fuel WHERE GSid = :GSID")
    List<Fuel> findGS(int GSID);

}
