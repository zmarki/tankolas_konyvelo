package com.fantastic_four.tankolas_konyvelo.Data;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity /*(foreignKeys = {@ForeignKey()},{@ForeignKey()})*/
public class PersonalChalk {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int mileage;

    public int liter;

    public int price;

    public int fuelId;

    public Date date;
}
