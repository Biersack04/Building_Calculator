package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.buildingcalculator.Authentication.ResetPasswordActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import static com.example.buildingcalculator.Constants.APP_PREFERENCES_NAME;
import static com.example.buildingcalculator.Constants.CREATE_PROFILE;
import static com.example.buildingcalculator.Constants.EMPTY_STRING;
import static com.example.buildingcalculator.Constants.MIN_PASSWORD_LENGTH;
import static com.example.buildingcalculator.Constants.PREFERENCES_FLAG_REGISTRATION;
import static com.example.buildingcalculator.Constants.PREFERENCES_ROLE;
import static com.example.buildingcalculator.Constants.ROLE_CUSTOMER;
import static com.example.buildingcalculator.Constants.ROLE_EXECUTOR;
import static com.example.buildingcalculator.Constants.STATUS_INDIVIDUAL;
import static com.example.buildingcalculator.Constants.STATUS_LEGAL_ENTITY;

public class EditProfileActivity extends AppCompatActivity {
    Intent intent;
    MaterialTextView titleTextView;
    TextInputEditText surname, name, patronymic, email, telephone, nameOfFirm, address;
    MaterialTextView menu, profile, project;
    TextInputLayout statusList;
    AutoCompleteTextView statusItem;
    ArrayAdapter<String> arrayAdapter_status;
    String[] status_items;
    String chooseStatus = "";
    TextInputLayout nameFirmLayout;
    SharedPreferences sPref;
    MaterialTextView numberOfWorkers,numberOfCompletedProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        layoutElementsInit();

        Bundle arguments = getIntent().getExtras();

        Boolean createProfile = (Boolean) arguments.get(CREATE_PROFILE);

        if(createProfile){

            titleTextView.setText(getString(R.string.create_profile));
        }
        else {

            titleTextView.setText(getString(R.string.edit_profile));
            readDataBase();
        }

    }

    private void readDataBase() {
    }

    public void layoutElementsInit(){

        menu = findViewById(R.id.menu_edit_profile_layout);
        menu.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        project = findViewById(R.id.project_edit_profile_layout);
        project.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        profile = findViewById(R.id.profile_edit_profile_layout);
        profile.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        titleTextView = findViewById(R.id.text_view_edit_profile_layout);
        titleTextView.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        surname = findViewById(R.id.edit_surname_edit_profile_layout);
        surname.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        name = findViewById(R.id.edit_name_edit_profile_layout);
        name.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        patronymic = findViewById(R.id.edit_patronymic_edit_profile_layout);
        patronymic.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        email = findViewById(R.id.edit_email_edit_profile_layout);
        email.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        telephone = findViewById(R.id.edit_telephone_edit_profile_layout);
        telephone.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        statusList = findViewById(R.id.status_input_edit_profile_layout);
        statusItem = findViewById(R.id.status_item_edit_profile_layout);
        status_items = getResources().getStringArray(R.array.status_items);
        arrayAdapter_status = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_item, status_items);
        statusItem.setAdapter(arrayAdapter_status);
        statusItem.setOnItemClickListener(onItemClickListener);

        nameFirmLayout = findViewById(R.id.name_of_firm_layout_edit_profile_layout);
        nameOfFirm = findViewById(R.id.edit_name_of_firm_edit_profile_layout);
        nameOfFirm.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        address = findViewById(R.id.edit_address_edit_profile_layout);
        address.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        numberOfWorkers = findViewById(R.id.number_workers_edit_profile_layout);
        numberOfWorkers.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        numberOfCompletedProjects = findViewById(R.id.enter_number_completed_projects_edit_profile_layout);
        numberOfCompletedProjects.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

    }

    private final AdapterView.OnItemClickListener onItemClickListener =
            (adapterView, view, i, l) -> {
                chooseStatus = (String) adapterView.getItemAtPosition(i);

                if (chooseStatus.equals(STATUS_INDIVIDUAL))
                {
                    nameOfFirm.setVisibility(View.GONE);
                    nameFirmLayout.setVisibility(View.GONE);
                }
                else if (chooseStatus.equals(STATUS_LEGAL_ENTITY) )
                {
                    nameOfFirm.setVisibility(View.VISIBLE);
                    nameFirmLayout.setVisibility(View.VISIBLE);
                }
            };


    public void saveProfile(View view) {

       if(checkMainFields()){
           sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);

           String role = sPref.getString(PREFERENCES_ROLE,EMPTY_STRING);

           if (role.equals(ROLE_CUSTOMER))
           {
               saveCustomer();

           }else if (role.equals(ROLE_EXECUTOR))
           {
               saveExecutor();
           }
           else
           {
               Toast.makeText(EditProfileActivity.this, getString(R.string.save_people_error),Toast.LENGTH_SHORT).show();
           }
       }
    }

    private void saveExecutor() {

    }

    private void saveCustomer() {

    }

    private boolean checkMainFields() {

        String nameString = name.getText().toString();
        String surnameString = surname.getText().toString();
        String patronymicString = patronymic.getText().toString();


        if (!(nameString.isEmpty()) && !surnameString.isEmpty() && !(patronymicString.isEmpty()) && !(chooseStatus.isEmpty()))
        {
            return true;
        }

        if (name.getText().length() == 0)
        {
            name.requestFocus();
            name.setError(getString(R.string.email_error));
            return false;
        }
        else if (surname.getText().length() == 0)
        {
            surname.requestFocus();
            surname.setError(getString(R.string.password_error));
            return false;
        }
        else if (chooseStatus.length() == 0)
        {
            statusList.setError(getString(R.string.role_error));
            return false;
        }
        return false;

    }

    public void back(View view) {

    }

    public void menu(View view) {
        intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void project(View view) {
    }

}