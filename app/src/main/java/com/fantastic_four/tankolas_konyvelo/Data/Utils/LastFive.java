package com.fantastic_four.tankolas_konyvelo.Data.Utils;

import com.fantastic_four.tankolas_konyvelo.Data.Converters;

import java.util.Date;

import androidx.room.TypeConverters;

@TypeConverters(Converters.class)
public class LastFive {
    private Date date;
    private int liter;
    private int price;

    public LastFive(Date date, int liter, int price) {
        this.date = date;
        this.liter = liter;
        this.price = price;
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
}
