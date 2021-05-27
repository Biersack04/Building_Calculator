package com.example.buildingcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buildingcalculator.roomDataBase.Project;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.buildingcalculator.Constants.APP_PREFERENCES_NAME;
import static com.example.buildingcalculator.Constants.CREATE_PROJECT;
import static com.example.buildingcalculator.Constants.PREFERENCES_USER_ID;

public class MyProjectsListActivity extends AppCompatActivity {

    Intent intent;
    RecyclerView projectList;
    private StateAdapter adapter;
    private ArrayList<Project> listItems;
    ArrayList<String> listProjects;
    SharedPreferences sPref;
    EditText editText;
    ArrayList<Project> projects = new ArrayList<Project>();
    List<Project> projectList2 = new ArrayList<Project>();
    private ViewModelProject mViewModelProject;
    Boolean chooseProject;
    String nameOfWork;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_projects_list);

        listItems = new ArrayList<>();
        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_project);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // RecyclerAdapter2.OnStateClickListener stateClickListener;
        Bundle arguments = getIntent().getExtras();
        if (arguments !=null)
        {
            chooseProject = arguments.getBoolean("ChooseProjectForWork");
        }
        Log.e("ChooseProject","ChooseProjectForWork");
        Log.e("ChooseProject", String.valueOf(chooseProject));
       /* if(chooseProject){
            nameOfWork = arguments.get("nameOfWork").toString();
        }

*/
        StateAdapter.OnStateClickListener stateClickListener = new StateAdapter.OnStateClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onStateClick(Project project, int position) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " + project.getName(),
                        Toast.LENGTH_SHORT).show();

                Log.e("ChooseProject","AAAAAAAAAAAaChooseProject");
                Log.e("ChooseProject", String.valueOf(chooseProject));
                if (chooseProject){
                    Log.e("ChooseProject","ЗАШЕЛ В ИФ");
                    intent = new Intent(getApplicationContext(),CalculationOfWorkActivity.class);
                    intent.putExtra("idOfChooseProject",project.getId());
                    intent.putExtra("ProjectChoosed",true);
                    intent.putExtra("nameOfWork",nameOfWork);
                    startActivity(intent);

                }
                /*
                intent = new Intent(getApplicationContext(), ViewProjectActivity.class);
                intent.putExtra("idOfProject",project.getId());
                startActivity(intent);*/
/*
                intent = new Intent(getApplicationContext(), ViewProjectActivity.class);
                intent.putExtra("nameOfProject",project.getName());
                startActivity(intent);*/
                Log.e("ProjectID",project.getId().toString());
            }
        };
        // создаем адаптер
        StateAdapter adapter = new StateAdapter(this, projects, stateClickListener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        long userId = sPref.getLong(PREFERENCES_USER_ID,0);

        mViewModelProject = ViewModelProviders.of(this).get(ViewModelProject.class);

        mViewModelProject.getCurrentData(userId).observe(MyProjectsListActivity.this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> currentList) {
                projectList2 = currentList;
                adapter.setProjects(currentList);
            }
        });


/*
        createExampleList();
        buildRecyclerView();
*/
        //Надо получить все названия проектов относящиеся к кастомеру или экзекьютору




     /*   createExampleList();
        buildRecyclerView();
*/


    }

/*
    private void buildRecyclerView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_project);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Set adapter
       
        adapter = new RecyclerAdapter2(listItems, this,stateClickListener);
        recyclerView.setAdapter(adapter);

    }
*/
    @SuppressLint("CheckResult")
    private void createExampleList() {



/*
        //Generate sample data

        AppDatabase db = AppDelegate.getInstance().getDatabase();
        long userId = sPref.getLong(PREFERENCES_EXECUTOR_ID,0);
        //Log.e("userId", String.valueOf(userId));
        //listProjects = new ArrayList<>();
       // listItems = new ArrayList<>();
        //listItems.add(new RecyclerItem("AAAA"));
        db.executorDao().executorProjects(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strings -> {
                    for(int i = 0;i<strings.size();i++){
                        Log.e("AAAAAAAAAAAAAAAa", strings.get(i));
                        this.listItems.add(new RecyclerItem(strings.get(i)));
                    }
                });
*/
       /* for (int i=0;i<listItems.size();i++){
            Log.e("AAAAAAAa", String.valueOf(listItems.get(i)));
        }*/
/*
        for(int i = 0;i<listProjects.size();i++){
            Log.e("AAAAAAAAAa",listProjects.get(i));
            //listProjects.add(strings.get(i));


        }
        //listItems.add(new RecyclerItem(strings.get(i).toString()));
        adapter = new RecyclerAdapter(listItems, this);
        projectList.setAdapter(adapter);

*/
    }



    public void myProject(View view) {
    }

    public void profile(View view) {
    }

    public void menu(View view) {
    }

    public void back(View view) {
    }

    public void viewProject(String string) {
        intent = new Intent(this, ViewProjectActivity.class);
        intent.putExtra("nameOfProject", string);
        startActivity(intent);
    }

    public void addProject(View view) {

        intent = new Intent(this, AddProjectActivity.class);
        intent.putExtra(CREATE_PROJECT, true);
        startActivity(intent);
        finish();
    }


    @SuppressLint("CheckResult")
    @Override
    public void onResume(){
        super.onResume();

        Bundle arguments = getIntent().getExtras();
        if (arguments !=null)
        {
            chooseProject = arguments.getBoolean("ChooseProjectForWork");
        }
        //createExampleList();
      //  buildRecyclerView();

    }
}