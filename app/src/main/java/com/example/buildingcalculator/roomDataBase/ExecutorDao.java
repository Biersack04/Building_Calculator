package com.example.buildingcalculator.roomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ExecutorDao {


    @Query("SELECT * FROM people_executor")
    LiveData<List<Executor>> getAll();

    @Query("SELECT * FROM people_executor WHERE id = :id")
    Executor getById(long id);

    @Query("SELECT * FROM people_executor WHERE id = :id")
    long executorCompletedProject(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Executor executor);

    @Update
    void update(Executor executor);

    @Delete
    void delete(Executor executor);

    @Query("SELECT * FROM people_executor WHERE id IS :parentId")
    LiveData<List<Executor>> getMaterialsForOwner(long parentId);
}
