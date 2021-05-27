package com.example.buildingcalculator.roomDataBase;

import androidx.room.TypeConverter;

public class UnitsConverter {

    @TypeConverter
    public String fromHobbies(Units unit) {
        return unit.name();
    }

    @TypeConverter
    public Units toHobbies(String unit) {
        return Units.valueOf(unit);
    }


}
