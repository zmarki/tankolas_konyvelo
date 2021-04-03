package com.fantastic_four.tankolas_konyvelo.Data.Utils;

import com.fantastic_four.tankolas_konyvelo.Data.Converters;

import java.util.Date;

import androidx.room.TypeConverters;

public class CountSumMonth {
    @TypeConverters({Converters.class})
    private Date date;
    private int count;

    public CountSumMonth(Date date, int count) {
        this.date = date;
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
