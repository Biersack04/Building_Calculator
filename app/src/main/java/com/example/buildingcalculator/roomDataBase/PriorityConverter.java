package com.example.buildingcalculator.roomDataBase;

import androidx.room.TypeConverter;

public class PriorityConverter {

    @TypeConverter
    public String fromHobbies(Priority priority) {
        return priority.name();
    }

    @TypeConverter
    public Priority toHobbies(String priority) {
        return Priority.valueOf(priority);
    }

}
