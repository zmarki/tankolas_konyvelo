package com.fantastic_four.tankolas_konyvelo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Car {

    @NonNull
    @PrimaryKey
    public String licensePlateNumber;

    @NonNull
    public String brand;

    @NonNull
    public String type;

    @NonNull
    public int ccm;

    @NonNull
    public int kw;

    @NonNull
    public String fuel;


    public Car(@NonNull String licensePlateNumber, @NonNull String brand, @NonNull String type, int ccm, int kw, @NonNull String fuel) {
        this.licensePlateNumber = licensePlateNumber;
        this.brand = brand;
        this.type = type;
        this.ccm = ccm;
        this.kw = kw;
        this.fuel = fuel;
    }

    public Car() {

    }

    @NonNull
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(@NonNull String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCcm() {
        return ccm;
    }

    public void setCcm(int ccm) {
        this.ccm = ccm;
    }

    public int getKw() {
        return kw;
    }

    public void setKw(int kw) {
        this.kw = kw;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}
