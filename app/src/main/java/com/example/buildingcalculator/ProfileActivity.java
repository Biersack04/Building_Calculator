package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.buildingcalculator.Authentication.LogInActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "AAAA";
    TextView personalInformation;
    TextView surname, name, patronymic, email, telephone, status, nameOfFirm, address, numberOfWorkers;
    TextView IntroducedSurname, IntroducedName, IntroducedPatronymic, IntroducedEmail, IntroducedTelephone, IntroducedStatus,
            IntroducedNameOfFirm, IntroducedAddress, IntroducedNumberOfWorkers, IntroducedAdditionalField;
    TextView menu, profile, project;
    Intent intent;

    private FirebaseAuth mAuth;
// [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;

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

        email = findViewById(R.id.email_edit_text);
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


        mAuth = FirebaseAuth.getInstance();
// [END initialize_auth]

// [START auth_state_listener]

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

    public void signOutProfile(View view) {

        FirebaseAuth.getInstance().signOut();
        intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
}