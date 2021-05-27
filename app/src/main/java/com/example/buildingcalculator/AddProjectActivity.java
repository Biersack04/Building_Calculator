package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.buildingcalculator.roomDataBase.AppDatabase;
import com.example.buildingcalculator.roomDataBase.AppDelegate;
import com.example.buildingcalculator.roomDataBase.Executor;
import com.example.buildingcalculator.roomDataBase.ExecutorDao;
import com.example.buildingcalculator.roomDataBase.LegalStatus;
import com.example.buildingcalculator.roomDataBase.Project;
import com.example.buildingcalculator.roomDataBase.ProjectDao;
import com.example.buildingcalculator.roomDataBase.Status;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import static com.example.buildingcalculator.Constants.APP_PREFERENCES_NAME;
import static com.example.buildingcalculator.Constants.CREATE_PROFILE;
import static com.example.buildingcalculator.Constants.CREATE_PROJECT;
import static com.example.buildingcalculator.Constants.PREFERENCES_LEGAL_STATUS_USER;
import static com.example.buildingcalculator.Constants.PREFERENCES_USER_ID;
import static com.example.buildingcalculator.Constants.STATUS_INDIVIDUAL;
import static com.example.buildingcalculator.Constants.STATUS_LEGAL_ENTITY;
import static java.sql.Types.NULL;

public class AddProjectActivity extends AppCompatActivity {

    MaterialTextView titleTextView;
    MaterialButton addCustomer, addWork, save, delete;
    TextInputEditText title, address, createDate, endDate;
    TextInputLayout statusList;
    AutoCompleteTextView statusItem;
    ArrayAdapter<String> arrayAdapter_status;
    String[] status_items;
    String chooseStatus = "";
    SharedPreferences sPref;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        layoutElementsInit();
        Bundle arguments = getIntent().getExtras();

        Boolean createProject = (Boolean) arguments.get(CREATE_PROJECT);


        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);
        if (createProject){

            titleTextView.setText(getString(R.string.create_project));
            hideElements();


        }else {

            titleTextView.setText(getString(R.string.edit_project));
        }


    }

    private void layoutElementsInit() {

        titleTextView = findViewById(R.id.title_text_view_add_project_layout);
        titleTextView.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        addCustomer = findViewById(R.id.add_customer_add_project_layout);
        addWork = findViewById(R.id.add_work_add_project_layout);
        save = findViewById(R.id.save_project_add_project_layout);
        delete = findViewById(R.id.delete_project_add_project_layout);

        title  = findViewById(R.id.edit_title_add_project_layout);
        address  = findViewById(R.id.edit_address_add_project_layout);
        createDate = findViewById(R.id.edit_create_date_add_project_layout);
        endDate = findViewById(R.id.edit_end_date_add_project_layout);
        statusList = findViewById(R.id.status_input_add_project_layout);
        statusItem = findViewById(R.id.status_item_add_project_layout);
        status_items = getResources().getStringArray(R.array.status_items);
        arrayAdapter_status = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_item, status_items);
        statusItem.setAdapter(arrayAdapter_status);
        statusItem.setOnItemClickListener(onItemClickListener);

    }
    private final AdapterView.OnItemClickListener onItemClickListener =
            (adapterView, view, i, l) -> {
                chooseStatus = (String) adapterView.getItemAtPosition(i);

            };

    private void hideElements() {

        addCustomer.setVisibility(View.GONE);
        addWork.setVisibility(View.GONE);
        delete.setVisibility(View.GONE);
        statusList.setVisibility(View.GONE);
        statusItem.setVisibility(View.GONE);
    }

    public void back(View view) {
    }

    public void saveProject(View view) {

        Project project = new Project();
        AppDatabase db = AppDelegate.getInstance().getDatabase();
        ProjectDao projectDao = db.projectDao();
        ExecutorDao executorDao = db.executorDao();

        long userId = sPref.getLong(PREFERENCES_USER_ID,0);

        project.setName(title.getText().toString());
        project.setCreatedDate(Long.parseLong(createDate.getText().toString()));
        String statusName = "CREATED";
        Status status = Status.valueOf(statusName);
        project.setStatus(status);
        project.setExecutor_id(1);
/*
        Executor executor = new Executor();
        executor.setName("aaaaaaa");

        executor.setSurname("fffff");
        executor.setPatronymic("fffff");
        executor.setMailbox("fffffffff");
        String statusName2 = "JURISTIC_PERSON";
        LegalStatus legalStatus = LegalStatus.valueOf(statusName2);
        executor.setLegalStatus(legalStatus);
        executorDao.insert(executor);*/
        projectDao.insert(project);


        intent = new Intent(this, MyProjectsListActivity.class);
        startActivity(intent);

    }
}