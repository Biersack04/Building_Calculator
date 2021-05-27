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

@Dao
public interface ProjectDao {

//РАБОТАЕТ
    @Query("SELECT * FROM project")
    LiveData<List<Project>> getAll();

    @Query("SELECT * FROM project WHERE project_id = :id")
    Project getById(long id);

    @Query("SELECT * FROM project WHERE executor_id = :idExecutor AND status = :status")
    long executorCompletedProject(long idExecutor,  @TypeConverters({StatusConverter.class}) Status status);
/*
    @Query("SELECT name FROM project WHERE executor_id = :idExecutor")
    LiveData<List<String>> executorProjects(long idExecutor);
*/
    @Query("SELECT * FROM project WHERE executor_id = :idExecutor")
    LiveData<List<Project>> executorProjects(long idExecutor);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Project project);

    @Update
    void update(Project project);

    @Delete
    void delete(Project project);

/*
    @Query("SELECT * FROM Work WHERE parent_project_id IS :parentId")
    List<Work> getWorksForOwner(long parentId);**/
}
