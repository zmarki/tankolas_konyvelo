package com.fantastic_four.tankolas_konyvelo.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


//Benzinkút tábla két oszlopa, egy ID és a kút neve
//Ezek a táblák fixen fel lesznek töltve a benzikutakkal pl: OMW, Shell, MOL
@Entity
public class GasStation {

    @PrimaryKey
    public int gasStationId;

    public String name;

    public GasStation(int gasStationId, String name) {
        this.gasStationId = gasStationId;
        this.name = name;
    }

    public int getId() {
        return gasStationId;
    }

    public void setId(int id) {
        this.gasStationId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
