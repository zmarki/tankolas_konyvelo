package com.fantastic_four.tankolas_konyvelo.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Fuel.class, GasStation.class, }, version = 1)

public abstract class AppDatavase extends RoomDatabase {
    public abstract FuelDao fuelDao();
    public abstract GasStationDao gasStationDao();
    public abstract CarDao carDao();
    public abstract PersonalChalkDao personalChalkDao();

}