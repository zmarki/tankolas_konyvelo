package com.fantastic_four.tankolas_konyvelo.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//Az üzemanyag tipusok táblája, idegen kulcs összekötéssel a benzinkút táblával
//Ezek a táblák fixen fel lesznek töltve adattal pl: MaxMotion 95, OMW kuttal összekötve
@Entity (foreignKeys = {@ForeignKey(entity = GasStation.class,
        parentColumns = "gasStationId",
        childColumns = "GSid",
        onDelete = ForeignKey.CASCADE)
})
public class Fuel {

    @PrimaryKey (autoGenerate = false)
    public int id;

    @ColumnInfo
    public String FuelName;

    public int GSid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuelName() {
        return FuelName;
    }

    public void setFuelName(String fuelName) {
        FuelName = fuelName;
    }

    public int getGSid() {
        return GSid;
    }

    public void setGSid(int GSid) {
        this.GSid = GSid;
    }
}
