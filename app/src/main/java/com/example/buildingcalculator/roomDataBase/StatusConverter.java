package com.example.buildingcalculator.roomDataBase;

import androidx.room.TypeConverter;

public class StatusConverter {

    @TypeConverter
    public String fromHobbies(Status status) {
        return status.name();
    }

    @TypeConverter
    public Status toHobbies(String status) {
        return Status.valueOf(status);
    }
}
