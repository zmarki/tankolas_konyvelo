package com.fantastic_four.tankolas_konyvelo.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Car {

    @PrimaryKey
    public int licensePlateNumber;

    public String brand;

    public String Type;

    public int ccm;

    public int kw;

    public String fuel;

    public int mileage;

}
