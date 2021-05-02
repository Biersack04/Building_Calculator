package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.example.buildingcalculator.Authentication.LogInActivity;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.buildingcalculator.Constants.CREATE_PROFILE;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "AAAA";
    MaterialTextView personalInformation;
    MaterialTextView surname, name, patronymic, email, telephone, status, nameOfFirm, address, numberOfWorkers, numberOfCompletedProject;
    MaterialTextView IntroducedSurname, IntroducedName, IntroducedPatronymic, IntroducedEmail, IntroducedTelephone, IntroducedStatus,
            IntroducedNameOfFirm, IntroducedAddress, IntroducedNumberOfWorkers, IntroducedNumberOfCompletedProject;
    MaterialTextView menu, profile, project;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        layoutElementsInit();


    }

    public void layoutElementsInit(){
        menu = findViewById(R.id.menu_profile_layout);
        menu.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        project = findViewById(R.id.project_profile_layout);
        project.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        profile = findViewById(R.id.profile_profile_layout);
        profile.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        personalInformation = findViewById(R.id.personal_information_profile_layout);
        personalInformation.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        surname = findViewById(R.id.surname_profile_layout);
        surname.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        name = findViewById(R.id.name_profile_layout);
        name.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        patronymic = findViewById(R.id.patronymic_profile_layout);
        patronymic.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        email = findViewById(R.id.email_edit_text_profile_layout);
        email.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        telephone = findViewById(R.id.telephone_profile_layout);
        telephone.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        status = findViewById(R.id.status_profile_layout);
        status.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        nameOfFirm = findViewById(R.id.name_of_firm_profile_layout);
        nameOfFirm.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        address = findViewById(R.id.address_profile_layout);
        address.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        numberOfWorkers = findViewById(R.id.number_of_workers_profile_layout);
        numberOfWorkers.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        numberOfCompletedProject= findViewById(R.id.number_of_completed_project_profile_layout);
        numberOfCompletedProject.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        IntroducedSurname = findViewById(R.id.personal_surname_profile_layout);
        IntroducedSurname.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedName = findViewById(R.id.personal_name_profile_layout);
        IntroducedName.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedPatronymic = findViewById(R.id.personal_patronymic_profile_layout);
        IntroducedPatronymic.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedEmail = findViewById(R.id.personal_email_profile_layout);
        IntroducedEmail.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedTelephone = findViewById(R.id.personal_telephone_profile_layout);
        IntroducedTelephone.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedStatus = findViewById(R.id.personal_status_profile_layout);
        IntroducedStatus.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedNameOfFirm = findViewById(R.id.personal_name_of_firm_profile_layout);
        IntroducedNameOfFirm.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedAddress = findViewById(R.id.personal_address_profile_layout);
        IntroducedAddress.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedNumberOfWorkers = findViewById(R.id.personal_number_of_workers_profile_layout);
        IntroducedNumberOfWorkers.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        IntroducedNumberOfCompletedProject = findViewById(R.id.personal_number_of_completed_project_profile_layout);
        IntroducedNumberOfCompletedProject.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));
    }

    public void editProfile(View view) {
        intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra(CREATE_PROFILE, false);
        startActivity(intent);
    }

    public void menu(View view) {
        intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void myProject(View view) {
    }

    public void signOutProfile(View view) {

        FirebaseAuth.getInstance().signOut();
        intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
}