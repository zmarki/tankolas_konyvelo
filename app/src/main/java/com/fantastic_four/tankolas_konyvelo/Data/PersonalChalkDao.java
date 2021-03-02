package com.fantastic_four.tankolas_konyvelo.Data;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface PersonalChalkDao {

    @Insert
    void insertAll(PersonalChalk... personalChalks);

}
