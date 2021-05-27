package com.example.buildingcalculator;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.buildingcalculator.roomDataBase.AppDatabase;
import com.example.buildingcalculator.roomDataBase.AppDelegate;
import com.example.buildingcalculator.roomDataBase.Material;
import com.example.buildingcalculator.roomDataBase.Project;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class ViewModelMaterial extends AndroidViewModel {
    private long userId;
    private long materialId;
    private LiveData<List<Material>> currentList;
    private Material currentMaterial;
    private AppDatabase db;

    public ViewModelMaterial(@NonNull Application application) {
        super(application);


        db = AppDelegate.getInstance().getDatabase();


    }

    public LiveData<List<Material>> getCurrentData(long userId){
        this.userId = userId;
        currentList = db.materialDao().listForWork(userId);
        return currentList;
    }

    public Material getCurrentMaterial(long materialId){

        this.materialId = materialId;
        currentMaterial = db.materialDao().getById(materialId);
        return currentMaterial;
    }


}