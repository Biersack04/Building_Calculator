package com.example.buildingcalculator.roomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CustomerDao {


    @Query("SELECT * FROM people_customer")
    LiveData<List<Customer>> getAll();

    @Query("SELECT * FROM people_customer WHERE id = :id")
    Customer getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Customer materials);

    @Update
    void update(Customer materials);

    @Delete
    void delete(Customer materials);

    @Query("SELECT * FROM people_customer WHERE id IS :parentId")
    LiveData<List<Customer>> getMaterialsForOwner(long parentId);
}
