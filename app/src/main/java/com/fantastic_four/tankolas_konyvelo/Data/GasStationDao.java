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

}
