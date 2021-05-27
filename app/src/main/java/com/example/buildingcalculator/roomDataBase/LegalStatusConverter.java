package com.example.buildingcalculator.roomDataBase;

import androidx.room.TypeConverter;

public class LegalStatusConverter {

    @TypeConverter
    public String fromStatus(LegalStatus legalStatus) {
        return legalStatus.name();

    }


    @TypeConverter
    public LegalStatus toStatus(String status) {
        return LegalStatus.valueOf(status);
    }
}
