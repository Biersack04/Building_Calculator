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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.buildingcalculator.Constants.*;

public class RegistrationActivity extends AppCompatActivity {

    TextInputLayout roleList;
    AutoCompleteTextView roleItem;

    ArrayAdapter<String> arrayAdapter_role;
    String[] role_items;

    TextView logIn, registration, withoutRegistration;
    Button register;
    Intent intent;

    SharedPreferences sPref;

    SharedPreferences.Editor editor;
    FirebaseAuth auth;

    String email = "", password = "";
    String chooseRole = "";
    private ProgressBar progressBar;

    TextInputEditText inputEmail, inputPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        inputEmail = findViewById(R.id.email_edit_text);
        inputPassword = findViewById(R.id.password_edit_text);
        progressBar = findViewById(R.id.progressBar);
        roleList = findViewById(R.id.role_input);
        roleItem = findViewById(R.id.role_item);

        role_items = getResources().getStringArray(R.array.role_items);
        arrayAdapter_role = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_item, role_items);
        roleItem.setAdapter(arrayAdapter_role);
        roleItem.setOnItemClickListener(onItemClickListener);

        logIn = findViewById(R.id.logInTextView);
        logIn.setTypeface(Typeface.createFromAsset(getAssets(), getString(R.string.roboto_medium)));

        registration = findViewById(R.id.registration);
        registration.setTypeface(Typeface.createFromAsset(getAssets(), getString(R.string.roboto_medium)));

        register = findViewById(R.id.button_register);
        register.setTypeface(Typeface.createFromAsset(getAssets(), getString(R.string.roboto_medium)));

        withoutRegistration = findViewById(R.id.without_registration_text_view);
        withoutRegistration.setTypeface(Typeface.createFromAsset(getAssets(), getString(R.string.roboto_italic)));

        auth = FirebaseAuth.getInstance();

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);
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

                            intent = new Intent(RegistrationActivity.this, MainMenuActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                });
    }

    public void buttonLogIn(View view) {

        intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void withoutRegistration(View view) {

        SharedPreferencesRegistrationFlag(true);

        intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void SharedPreferencesRole(String role){

        editor = sPref.edit();
        editor.putString(PREFERENCES_ROLE, role);
        editor.apply();
    }

    public void SharedPreferencesRegistrationFlag(boolean flag){

        editor = sPref.edit();
        editor.putBoolean(PREFERENCES_FLAG_REGISTRATION, flag);
        editor.apply();
    }

    @Override
    public void onBackPressed(){

        intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}