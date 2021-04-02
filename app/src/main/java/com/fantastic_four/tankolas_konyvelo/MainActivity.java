package com.fantastic_four.tankolas_konyvelo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.fantastic_four.tankolas_konyvelo.Data.Car;
import com.fantastic_four.tankolas_konyvelo.Data.Converters;
import com.fantastic_four.tankolas_konyvelo.Data.PersonalChalk;
import com.fantastic_four.tankolas_konyvelo.ViewModel.CarViewModel;
import com.fantastic_four.tankolas_konyvelo.ViewModel.PersonalChalkViewModel;
import com.fantastic_four.tankolas_konyvelo.Workers.WorkManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.TypeConverters;

@SuppressWarnings("ALL")
@TypeConverters(Converters.class)
public class MainActivity extends AppCompatActivity {

    private CarViewModel carViewModel;
    private PersonalChalkViewModel personalChalkViewModel;
    private WorkManager workManager = new WorkManager();
    // private GasStationRepository gasStationRepository;
    //private FuelRepository fuelRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Gomb megnyomva", Toast.LENGTH_LONG).show());

        carViewModel = ViewModelProviders.of(this).get(CarViewModel.class);
        personalChalkViewModel = ViewModelProviders.of(this).get(PersonalChalkViewModel.class);


        Car car = new Car("EDK579", "Toyota", "Prius", 1600, 80, "benzin", 478747);

       /* carViewModel.getInsertResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer result) {
                if (result==1) {
                    Toast.makeText(MainActivity.this, "Car Successfully save", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        PersonalChalk personalChalk;

        personalChalk = new PersonalChalk();
        try {
            personalChalk.date = sdf.parse("2021-05-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        personalChalk.fuelId = 1;
        personalChalk.liter = 15;
        personalChalk.mileage = 150;
        personalChalk.price = 415;


        carViewModel.insertCar(car);
        personalChalkViewModel.insertPersonalChalk(personalChalk);


    }
}