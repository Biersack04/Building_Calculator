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
import io.reactivex.Single;

@Dao
public interface MaterialDao {

    @Query("SELECT * FROM material")
    Flowable<List<Material>> getAll();

    @Query("SELECT * FROM material WHERE material_id = :id")
    Material getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Material material);

    @Update
    void update(Material material);

    @Delete
    void delete(Material material);

    @Query("SELECT * FROM material WHERE parent_work_id IS :parentId")
    List<Material> getMaterialsForOwner(long parentId);

    @Query("SELECT * FROM material WHERE parent_work_id = :idWork")
    LiveData<List<Material>> listForWork(long idWork);
}
