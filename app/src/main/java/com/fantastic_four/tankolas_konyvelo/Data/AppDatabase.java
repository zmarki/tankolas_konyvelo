package com.fantastic_four.tankolas_konyvelo.Data;

import android.content.Context;

import com.fantastic_four.tankolas_konyvelo.Car;
import com.fantastic_four.tankolas_konyvelo.PersonalChalk;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile AppDatabase instance = null;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null)
                    instance = Room.databaseBuilder(context, AppDatabase.class, "car_db")
                            /*  .fallbackToDestructiveMigration()
                              .addCallback(new Callback() {
                                  @Override
                                  public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                      super.onCreate(db);
                                      OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(GSDatabaseWorker.class).build();
                                      WorkManager.getInstance().enqueue(workRequest);
                                  }
                              })*/
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            InputStream inputStream = null;

                                            //insert GasStations
                                            try {
                                                inputStream = context.getAssets().open("GasStation.json");
                                                int size = inputStream.available();
                                                byte[] buffer = new byte[size];
                                                inputStream.read(buffer);
                                                inputStream.close();
                                                String json = new String(buffer, "UTF-8");
                                                List<GasStation> gasStationList = new Gson().fromJson(json, new TypeToken<List<GasStation>>() {
                                                }.getType());
                                                instance.gasStationDao().insertAll(gasStationList);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }


                                            //insert Fuels
                                            try {
                                                inputStream = context.getAssets().open("Fuel.json");
                                                int size = inputStream.available();
                                                byte[] buffer = new byte[size];
                                                inputStream.read(buffer);
                                                inputStream.close();
                                                String json = new String(buffer, "UTF-8");
                                                List<Fuel> fuelList = new Gson().fromJson(json, new TypeToken<List<Fuel>>() {
                                                }.getType());
                                                instance.fuelDao().insertAllFuel(fuelList);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            })
                            .build();
            }

        }
        return instance;
    }


}