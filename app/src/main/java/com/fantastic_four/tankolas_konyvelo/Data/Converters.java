package com.fantastic_four.tankolas_konyvelo.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.room.TypeConverter;

public class Converters {
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    @TypeConverter
    public static Date fromTimestamp(String timestamp){
        if (timestamp != null) {
            try {
                return df.parse(timestamp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
       // return timestamp == null ? null : new df.parse(timestamp);
    }

    @TypeConverter
    public static String toTimestamp(Date date){
        return date == null ? null : df.format(date);
    }
}
