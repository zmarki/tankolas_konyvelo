package com.fantastic_four.tankolas_konyvelo.Data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface GasStationDao {

    @Query("SELECT * FROM GasStation")
    List<GasStation> getAll();


}
