package com.fantastic_four.tankolas_konyvelo.Data;

import com.fantastic_four.tankolas_konyvelo.Data.Relations.GasStationWithFuel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface FuelDao {

    @Query("SELECT * FROM Fuel")
    List<Fuel> getAll();

   /* @Query("SELECT 'FuelName' FROM Fuel WHERE GSid = :GSID")
    List<Fuel> findGS(int GSID);*/

    //1-n kapcsolat lekérdezés
    @Transaction
    @Query("SELECT * FROM GasStation WHERE gasStationId = :gasStationId")
    List<GasStationWithFuel> getGasStationWithFuel(int gasStationId);

}
