package com.fantastic_four.tankolas_konyvelo.Data;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.LastFive;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

@Dao
public interface PersonalChalkDao {

    @Insert
    void insertAllChalk(PersonalChalk personalChalks);

    //Ez még nem jó lekérdezés
    @Query("SELECT date, liter, price FROM PersonalChalk ORDER BY date LIMIT 5")
    LiveData<List<LastFive>> lastFiveChalk();

    //Ez még nem jó lekérdezés CÉL Hány db tankolás volt havonta
    @TypeConverters({Converters.class})
    @Query("SELECT strftime('%Y-%m', date) as date, COUNT(id) as count FROM PersonalChalk GROUP BY strftime('%Y-%m', date)")
    LiveData<List<CountSumMonth>> countMonthChalk();

    //Hány liter lett tankolva havibontás
    @TypeConverters({Converters.class})
    @Query("SELECT strftime('%Y-%m', date) as date, SUM(liter) as count From PersonalChalk GROUP BY strftime('%Y-%m', date)")
    LiveData<List<CountSumMonth>> sumLiterMonthChalk();






}
