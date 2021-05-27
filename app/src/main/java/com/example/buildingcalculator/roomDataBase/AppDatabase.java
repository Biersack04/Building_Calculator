package com.example.buildingcalculator.roomDataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {People.class, Customer.class, Executor.class, Project.class, Work.class, Material.class}, version = 1, exportSchema=false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CustomerDao customerDao();
    public abstract ExecutorDao executorDao();
    public abstract ProjectDao projectDao();
    public abstract WorkDao workDao();
    public abstract MaterialDao materialDao();


    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Поскольку мы не изменяли таблицу, здесь больше ничего не нужно делать.
        }
    };
}