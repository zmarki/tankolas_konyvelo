package com.fantastic_four.tankolas_konyvelo;

import com.fantastic_four.tankolas_konyvelo.Data.Converters;
import com.fantastic_four.tankolas_konyvelo.Data.Fuel;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(foreignKeys = {@ForeignKey(entity = Fuel.class,
        parentColumns = "id",
        childColumns = "fuelId",
        onDelete = ForeignKey.CASCADE)}/*, {@ForeignKey()}*/)

public class PersonalChalk {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int mileage;

    @NonNull
    public int liter;

    @NonNull
    public int price;

    @NonNull
    public int fuelId;

    // @NonNull
    @TypeConverters({Converters.class})
    public Date date;

    public PersonalChalk() {

    }

    public PersonalChalk(int mileage, int liter, int price, int fuelId, Date date) {
        this.mileage = mileage;
        this.liter = liter;
        this.price = price;
        this.fuelId = fuelId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getLiter() {
        return liter;
    }

    public void setLiter(int liter) {
        this.liter = liter;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFuelId() {
        return fuelId;
    }

    public void setFuelId(int fuelId) {
        this.fuelId = fuelId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
