package com.example.buildingcalculator;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingcalculator.roomDataBase.Project;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.buildingcalculator.Constants.APP_PREFERENCES_NAME;
import static com.example.buildingcalculator.Constants.PREFERENCES_USER_ID;

public class MainActivity extends AppCompatActivity {

    ArrayList<Project> projects = new ArrayList<Project>();
    private ViewModelProject mViewModelProject;
    SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // начальная инициализация списка
        //setInitialData();
        RecyclerView recyclerView = findViewById(R.id.list);
        // определяем слушателя нажатия элемента в списке
        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);

        StateAdapter.OnStateClickListener stateClickListener = new StateAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Project project, int position) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " + project.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        // создаем адаптер
        StateAdapter adapter = new StateAdapter(this, projects, stateClickListener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        long userId = sPref.getLong(PREFERENCES_USER_ID,0);

        mViewModelProject = ViewModelProviders.of(this).get(ViewModelProject.class);

        mViewModelProject.getCurrentData(userId).observe(MainActivity.this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> currentList) {
                adapter.setProjects(currentList);
            }
        });
    }

}