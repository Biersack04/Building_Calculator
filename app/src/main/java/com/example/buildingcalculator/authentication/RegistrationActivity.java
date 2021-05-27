package com.example.buildingcalculator.authentication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.buildingcalculator.EditProfileActivity;
import com.example.buildingcalculator.MainMenuActivity;
import com.example.buildingcalculator.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.buildingcalculator.Constants.*;

public class RegistrationActivity extends AppCompatActivity {

    TextInputLayout roleList;
    AutoCompleteTextView roleItem;
    ArrayAdapter<String> arrayAdapter_role;
    String[] role_items;
    MaterialTextView logIn, registration, withoutRegistration;
    MaterialButton register;
    Intent intent;
    SharedPreferences sPref;
    SharedPreferences.Editor editor;
    FirebaseAuth auth;
    String email = "", password = "", chooseRole = "";
    private ProgressBar progressBar;
    TextInputEditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        layoutElementsInit();

        auth = FirebaseAuth.getInstance();

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);
    }

    public void layoutElementsInit(){

        inputEmail = findViewById(R.id.email_edit_text_registration_layout);
        inputPassword = findViewById(R.id.password_edit_text_registration_layout);
        progressBar = findViewById(R.id.progress_bar_registration_layout);

        roleList = findViewById(R.id.role_input_registration_layout);
        roleItem = findViewById(R.id.role_item_registration_layout);
        role_items = getResources().getStringArray(R.array.role_items);
        arrayAdapter_role = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_item, role_items);
        roleItem.setAdapter(arrayAdapter_role);
        roleItem.setOnItemClickListener(onItemClickListener);

        logIn = findViewById(R.id.logInTextView_registration_layout);
        logIn.setTypeface(Typeface.createFromAsset(getAssets(), getString(R.string.roboto_medium)));

        registration = findViewById(R.id.registration_text_view_registration_layout);
        registration.setTypeface(Typeface.createFromAsset(getAssets(), getString(R.string.roboto_medium)));

        register = findViewById(R.id.button_register_registration_layout);
        register.setTypeface(Typeface.createFromAsset(getAssets(), getString(R.string.roboto_medium)));

        withoutRegistration = findViewById(R.id.without_registration_text_view_registration_layout);
        withoutRegistration.setTypeface(Typeface.createFromAsset(getAssets(), getString(R.string.roboto_italic)));
    }

    private final AdapterView.OnItemClickListener onItemClickListener =
            (adapterView, view, i, l) -> {

                chooseRole = (String) adapterView.getItemAtPosition(i);
            };

    public void buttonRegister(View view) {

        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        if (!(email.isEmpty()) && !(password.isEmpty()) && !(chooseRole.isEmpty()))
        {
            register(chooseRole);
        }

        if (inputEmail.getText().length() == 0)
        {
            inputEmail.requestFocus();
            inputEmail.setError(getString(R.string.email_error));
        }
        else if (inputPassword.getText().length() < MIN_PASSWORD_LENGTH)
        {
            inputPassword.requestFocus();
            inputPassword.setError(getString(R.string.password_error));
        }
        else if (chooseRole.length() == 0)
        {
                roleList.setError(getString(R.string.role_error));
        }
    }

    public void register(String chooseRole) {

        progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegistrationActivity.this, task -> {

                    progressBar.setVisibility(View.GONE);

                    if(!task.isSuccessful())
                    {
                        Toast.makeText(RegistrationActivity.this,  getString(R.string.error_register),Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                            SharedPreferencesRole(chooseRole);
                            SharedPreferencesRegistrationFlag(false);

                            intentCreateProfile();
                        }
                });
    }

    public void buttonLogIn(View view) {

        intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void withoutRegistration(View view) {

        SharedPreferencesRegistrationFlag(true);

        intentMainMenu();
    }

    public void SharedPreferencesRole(String role){

        editor = sPref.edit();
        editor.putString(PREFERENCES_ROLE, role);
        editor.apply();
    }

    public void SharedPreferencesRegistrationFlag(boolean registrationFlag){

        editor = sPref.edit();
        editor.putBoolean(PREFERENCES_FLAG_REGISTRATION, registrationFlag);
        editor.apply();
    }

    public void intentMainMenu() {

        intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void intentCreateProfile() {

        intent = new Intent(this, EditProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(CREATE_PROFILE, true);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){

        intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}