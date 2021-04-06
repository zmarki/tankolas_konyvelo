package com.fantastic_four.tankolas_konyvelo.Data.Utils;

import com.fantastic_four.tankolas_konyvelo.Data.Converters;

import java.util.Date;

import androidx.room.TypeConverters;

public class CountSumMonth {
   // @TypeConverters({Converters.class})
    private String date;
    private int count;

    public CountSumMonth(String date, int count) {
        this.date = date;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
