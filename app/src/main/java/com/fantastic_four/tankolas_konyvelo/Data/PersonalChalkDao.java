package com.fantastic_four.tankolas_konyvelo.Data;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.LastFive;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

@Dao
public interface PersonalChalkDao {

    @Insert
    void insertAllChalk(PersonalChalk personalChalks);

    //Nem használjuk mert nem engedélyezett hogy törölni lehessen már rögzitett bejegyzést
    @Delete
    void deleteChalk(PersonalChalk personalChalk);

    //Töröljük az összes adatot
    @Query("DELETE FROM personalchalk")
    void deleteALL();

    //Átlagpsan tankolt mennyiség
    @Query("SELECT AVG(liter) FROM PersonalChalk")
    LiveData<Integer> avgLiter();

    //Átlag fogyasztás:
    @Query("SELECT SUM(liter)/((MAX(mileage)-MIN(mileage))/100) FROM PersonalChalk")
    LiveData<Double> avgConsumption();


    //Átlag tankolási idököz


    //Átlag km/tankolás:


    //Utolsó 5 tankolás adatai
    @Query("SELECT date, liter, price FROM PersonalChalk ORDER BY date LIMIT 5")
    LiveData<List<LastFive>> lastFiveChalk();

    //Hány tankolás volt havonta
    @TypeConverters({Converters.class})
    @Query("SELECT strftime('%Y-%m', date) as date, COUNT(id) as count FROM PersonalChalk GROUP BY strftime('%Y-%m', date)")
    LiveData<List<CountSumMonth>> countMonthChalk();

    //Hány liter volt tankolva havonta
    @TypeConverters({Converters.class})
    @Query("SELECT strftime('%Y-%m', date) as date, SUM(liter) as count From PersonalChalk GROUP BY strftime('%Y-%m', date)")
    LiveData<List<CountSumMonth>> sumLiterMonthChalk();

    //egy Tankolással hány Km lett megtéve



}
