package com.fantastic_four.tankolas_konyvelo.Data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FuelDao {

    @Insert(onConflict = REPLACE)
    void insertAllFuel(List<Fuel> fuel);


    @Query("SELECT * FROM Fuel")
    LiveData<List<Fuel>> getAllFuel();

   /* @Query("SELECT 'FuelName' FROM Fuel WHERE GSid = :GSID")
    List<Fuel> findGS(int GSID);*/

    /*//1-n kapcsolat lekérdezés
    @Transaction
    @Query("SELECT * FROM GasStation WHERE gasStationId = :gasStationId")
    List<GasStationWithFuel> getGasStationWithFuel(int gasStationId);*/

}
