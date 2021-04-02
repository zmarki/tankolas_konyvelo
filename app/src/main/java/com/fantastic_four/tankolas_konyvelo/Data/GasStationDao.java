package com.fantastic_four.tankolas_konyvelo.Data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface GasStationDao {

    //Beilleszti az összes adatot, felülirja az ahol ütközés van
    @Insert(onConflict = REPLACE)
    void insertAll(List<GasStation> gasStations);


    @Query("SELECT * FROM GasStation")
    LiveData<List<GasStation>> getAllGS();


    //Fuel ID alapján szürt GasStation nevek
    @Query("SELECT name FROM GasStation INNER JOIN Fuel ON gasStationId= GSid WHERE GSid= :GSid")
    LiveData<List<String>> gsName(int GSid);

    //GasStation id alapján szürt Fuel nevek
    @Query("SELECT fuelName FROM Fuel INNER JOIN GasStation ON GSid = gasStationId WHERE gasStationId= :gasStationId AND name='Other'")
    LiveData<List<String>> fName(int gasStationId);



}
