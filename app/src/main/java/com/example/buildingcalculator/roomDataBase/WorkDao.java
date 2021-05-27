package com.example.buildingcalculator.roomDataBase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkDao {

    @Query("SELECT * FROM work")
    List<Work> getAll();

    @Query("SELECT * FROM work WHERE work_id = :id")
    Work getById(long id);

    @Query("SELECT work_id FROM work WHERE name = :name")
    long getIdByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Work work);

    @Update
    void update(Work work);

    @Delete
    void delete(Work work);



/*
//А вот это пригодится для списка материалов
    @Query("SELECT * FROM material WHERE parent_work_id IS :parentId")
    List<Materials> getMaterialsForOwner(long parentId);
*/
}


