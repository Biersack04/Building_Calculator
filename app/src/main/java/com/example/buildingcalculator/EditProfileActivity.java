package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EditProfileActivity extends AppCompatActivity {
    Intent intent;
    TextView personalInformation;
    TextView surname, name, patronymic, email, telephone, status, nameOfFirm, address, numberOfWorkers, numberOfCompletedProjects;
    TextView menu, profile, project;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        menu = findViewById(R.id.menu);
        menu.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        project = findViewById(R.id.project);
        project.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        profile = findViewById(R.id.profile);
        profile.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        personalInformation = findViewById(R.id.personal_information);
        profile.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        surname = findViewById(R.id.edit_surname);
        surname.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        name = findViewById(R.id.edit_name);
        name.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        patronymic = findViewById(R.id.edit_patronymic);
        patronymic.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        email = findViewById(R.id.edit_email);
        email.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        telephone = findViewById(R.id.edit_telephone);
        telephone.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        status = findViewById(R.id.status_input);
        status.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        nameOfFirm = findViewById(R.id.edit_name_of_firm);
        nameOfFirm.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        address = findViewById(R.id.edit_address);
        address.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        numberOfWorkers = findViewById(R.id.edit_number_of_workers);
        numberOfWorkers.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        numberOfCompletedProjects = findViewById(R.id.enter_number_completed_projects);
        numberOfCompletedProjects.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

    }

    public void saveProfile(View view) {
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