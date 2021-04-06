package com.fantastic_four.tankolas_konyvelo.Data;

import com.fantastic_four.tankolas_konyvelo.Data.Utils.CountSumMonth;
import com.fantastic_four.tankolas_konyvelo.Data.Utils.LastFive;
import com.fantastic_four.tankolas_konyvelo.PersonalChalk;
import com.fantastic_four.tankolas_konyvelo.PrevDataModel;
import com.fantastic_four.tankolas_konyvelo.StatThreeModel;

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

    //Utolsó tankolás adatai
    @Query("select date, price, liter, fuelName, g.name as GSName from PersonalChalk as p inner join Fuel as f on p.fuelID = f.id inner join GasStation as g on f.GSid=g.gasStationId ORDER BY date LIMIT 1")
    LiveData<LastFive> lastChalk();

    //Átlagpsan tankolt mennyiség
    @Query("SELECT AVG(liter) as avgLiter FROM PersonalChalk")
    LiveData<Float> avgLiter();

    //Átlag fogyasztás:
    @Query("SELECT SUM(liter)/((MAX(mileage)-MIN(mileage))/100) as avgCons FROM PersonalChalk")
    LiveData<Double> avgConsumption();

    //Összes adat lekérése:
    @Query("SELECT * from PersonalChalk order by date asc")
    LiveData<List<PersonalChalk>> getAllPersonalChalks();

    @Query("select p.date as date, p.mileage as mileage, p.price as price, p.liter as liter, f.fuelName as fuelName, g.name as gasstationName from PersonalChalk as p inner join Fuel as f on p.fuelID = f.id inner join GasStation as g on f.GSid = g.gasStationId order by date desc")
    LiveData<List<PrevDataModel>> getAllPersonalChalksWithGSFuelNames();

    @Query("select p1.date as date, p1.mileage - p2.mileage as mileageDiff from PersonalChalk as p1, PersonalChalk as p2 where p2.date = (select date from PersonalChalk where date < p1.date order by date desc limit 1) order by p1.date asc")
    LiveData<List<StatThreeModel>> statThreeData();

    //Átlag tankolási idököz
    @Query("SELECT (julianday(max(date)) - julianday(min(date))) / count(*) as avgfilling from PersonalChalk")
    LiveData<Double> avgFillingDuration();

    //Átlag km/tankolás:
    @Query("SELECT (max(mileage) - min(mileage)) / count(*)  as avgfilling from PersonalChalk")
    LiveData<Double> avgFillingKM();

    //Utolsó 5 tankolás adatai
    @Query("SELECT date, liter, price FROM PersonalChalk ORDER BY date asc LIMIT 5")
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
