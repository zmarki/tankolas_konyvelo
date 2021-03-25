package com.fantastic_four.tankolas_konyvelo.Data.Relations;

import com.fantastic_four.tankolas_konyvelo.Data.Fuel;
import com.fantastic_four.tankolas_konyvelo.Data.GasStation;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;


// 1-N Kapcsolat a Benzikút és az üzemanyag táblázattal
public class GasStationWithFuel {
    @Embedded
    public GasStation gasStation;

    @Relation(
            parentColumn = "gasStationId",
            entityColumn = "GSid"
    )
   public List<Fuel> fuels;
}

