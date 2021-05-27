package com.example.buildingcalculator.roomDataBase;

import android.app.Application;

import androidx.room.Room;

public class AppDelegate extends Application {

    public static AppDelegate instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "user_database")
                .allowMainThreadQueries()
                .build();

    }

    public static AppDelegate getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}