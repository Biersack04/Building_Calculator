package com.example.buildingcalculator;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.buildingcalculator.roomDataBase.AppDatabase;
import com.example.buildingcalculator.roomDataBase.AppDelegate;
import com.example.buildingcalculator.roomDataBase.Project;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class ViewModelProject extends AndroidViewModel {
    private long userId;
    private long projectId;
    private LiveData<List<Project>> currentList;
    private Project currentProject;
    private AppDatabase db;

    public ViewModelProject(@NonNull Application application) {
        super(application);


        db = AppDelegate.getInstance().getDatabase();


    }

    public LiveData<List<Project>> getCurrentData(long userId){
        this.userId = userId;
        currentList = db.projectDao().executorProjects(userId);
        return currentList;
    }

    public Project getCurrentProject(long projectId){

        this.projectId = projectId;
        currentProject = db.projectDao().getById(projectId);
        return currentProject;
    }


}