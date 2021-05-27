package com.fantastic_four.tankolas_konyvelo;

import java.util.Date;

public class PrevDataModel {

    private Date date;
    private int liter;
    private int mileage;
    private int price;
    private String fuelName;
    private String gasstationName;

    public PrevDataModel(Date date, int liter, int mileage, int price, String fuelName, String gasstationName) {
        this.date = date;
        this.liter = liter;
        this.mileage = mileage;
        this.price = price;
        this.fuelName = fuelName;
        this.gasstationName = gasstationName;
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

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }

    public String getGasstationName() {
        return gasstationName;
    }

    public void setGasstationName(String gasstationName) {
        this.gasstationName = gasstationName;
    }
}
