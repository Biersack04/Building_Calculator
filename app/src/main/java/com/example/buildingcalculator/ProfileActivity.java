package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView personalInformation;
    TextView surname, name, patronymic, email, telephone, status, nameOfFirm, address, numberOfWorkers, additionalField;
    TextView IntroducedSurname, IntroducedName, IntroducedPatronymic, IntroducedEmail, IntroducedTelephone, IntroducedStatus,
            IntroducedNameOfFirm, IntroducedAddress, IntroducedNumberOfWorkers, IntroducedAdditionalField;
    TextView menu, profile, project;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        menu = findViewById(R.id.menu);
        menu.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        project = findViewById(R.id.project);
        project.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        profile = findViewById(R.id.profile);
        profile.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        personalInformation = findViewById(R.id.personal_information);
        profile.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        surname = findViewById(R.id.surname);
        surname.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        name = findViewById(R.id.name);
        name.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        patronymic = findViewById(R.id.patronymic);
        patronymic.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        email = findViewById(R.id.email);
        email.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        telephone = findViewById(R.id.telephone);
        telephone.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        status = findViewById(R.id.status);
        status.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        nameOfFirm = findViewById(R.id.name_of_firm);
        nameOfFirm.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        address = findViewById(R.id.address);
        address.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        numberOfWorkers = findViewById(R.id.number_of_workers);
        numberOfWorkers.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        additionalField = findViewById(R.id.additional_field);
        additionalField.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedSurname = findViewById(R.id.personal_surname);
        IntroducedSurname.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedName = findViewById(R.id.personal_name);
        IntroducedName.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedPatronymic = findViewById(R.id.personal_patronymic);
        IntroducedPatronymic.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedEmail = findViewById(R.id.personal_email);
        IntroducedEmail.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedTelephone = findViewById(R.id.personal_telephone);
        IntroducedTelephone.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedStatus = findViewById(R.id.personal_status);
        IntroducedStatus.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedNameOfFirm = findViewById(R.id.personal_name_of_firm);
        IntroducedNameOfFirm.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedAddress = findViewById(R.id.personal_address);
        IntroducedAddress.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedNumberOfWorkers = findViewById(R.id.personal_number_of_workers);
        IntroducedNumberOfWorkers.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedAdditionalField = findViewById(R.id.personal_additional_field);
        IntroducedAdditionalField.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));


    }

    public void editProfile(View view) {
        intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void menu(View view) {
        intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void myProject(View view) {
    }
}