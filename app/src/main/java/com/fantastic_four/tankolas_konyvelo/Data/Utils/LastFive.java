package com.fantastic_four.tankolas_konyvelo.Data.Utils;

import com.fantastic_four.tankolas_konyvelo.Data.Converters;

import java.util.Date;

import androidx.room.TypeConverters;

@TypeConverters(Converters.class)
public class LastFive {
    private Date date;
    private int liter;
    private int price;
    private String GSName;
    private String fuelName;

    public LastFive(Date date, int liter, int price, String GSName, String fuelName) {
        this.date = date;
        this.liter = liter;
        this.price = price;
        this.GSName = GSName;
        this.fuelName = fuelName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getGSName() {
        return GSName;
    }

    public void setGSName(String GSName) {
        this.GSName = GSName;
    }

    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }
}
