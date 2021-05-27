package com.fantastic_four.tankolas_konyvelo;

//A statisztika oldalon található 3. grafikonhoz a model

public class StatThreeModel {

    private String date;
    private int mileageDiff;

    public StatThreeModel(String date, int mileageDiff) {
        this.date = date;
        this.mileageDiff = mileageDiff;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMileageDiff() {
        return mileageDiff;
    }

    public void setMileageDiff(int mileageDiff) {
        this.mileageDiff = mileageDiff;
    }
}
