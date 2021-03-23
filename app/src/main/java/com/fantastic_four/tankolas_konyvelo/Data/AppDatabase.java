package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {
                Fuel.class,
                GasStation.class,
                Car.class,
                PersonalChalk.class
        },
        version = 8)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GasStationDao gasStationDao();

    public abstract FuelDao fuelDao();

    public abstract CarDao carDao();

    public abstract PersonalChalkDao personalChalkDao();


    private static volatile AppDatabase instance = null;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null)
                    instance = Room.databaseBuilder(context, AppDatabase.class, "car_db")
                            .fallbackToDestructiveMigration()
                            /*.addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(GSDatabaseWorker.class).build();
                                    WorkManager.getInstance().enqueue(workRequest);
                                }
                            })*/
                            .build();
            }

        }
        return instance;
    }


}