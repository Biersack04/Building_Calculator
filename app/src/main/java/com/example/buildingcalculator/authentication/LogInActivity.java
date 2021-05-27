package com.example.buildingcalculator.authentication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.buildingcalculator.MainMenuActivity;
import com.example.buildingcalculator.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.buildingcalculator.Constants.*;


public class LogInActivity extends AppCompatActivity {

    MaterialTextView logIn, registration, withoutRegistration, forgotPassword;
    MaterialButton buttonSignIn;
    Intent intent;
    FirebaseAuth auth;
    SharedPreferences sPref;
    SharedPreferences.Editor editor;
    String email = "", password = "";
    ProgressBar progressBar;
    TextInputEditText inputEmail, inputPassword;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        layoutElementsInit();

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null)
        {
            Log.e("AUTH", String.valueOf(auth));
            intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void layoutElementsInit(){

        logIn = findViewById(R.id.logIn_text_view_log_in_layout);
        logIn.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        registration = findViewById(R.id.registration_text_view_log_in_layout);
        registration.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        buttonSignIn = findViewById(R.id.button_sign_in_log_in_layout);
        buttonSignIn.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        withoutRegistration = findViewById(R.id.without_registration_text_view_log_in_layout);
        withoutRegistration.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_italic)));

        forgotPassword = findViewById(R.id.forgot_password_text_view_log_in_layout);
        forgotPassword.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_italic)));

        inputEmail = findViewById(R.id.email_edit_text_log_in_layout);
        inputPassword = findViewById(R.id.password_edit_text_log_in_layout);
        progressBar = findViewById(R.id.progress_bar_log_in_layout);
    }

    public void buttonSignIn(View view) {

        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        if (!(email.isEmpty()) && !(password.isEmpty()))
        {
            signIn();
        }

        if (inputEmail.getText().length() == 0)
        {
            inputEmail.requestFocus();
            inputEmail.setError(getString(R.string.email_error));

        }
        else if (inputPassword.getText().length() == 0)
        {
            inputPassword.requestFocus();
            inputPassword.setError(getString(R.string.password_error));
        }
    }

    public void signIn() {
        progressBar.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(LogInActivity.this, task -> {

                    if (!task.isSuccessful())
                    {
                        Toast.makeText(LogInActivity.this, getString(R.string.error_log_in),Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);

                        sharedPreferencesRegistrationFlag(false);
                        intentMainMenu();
                    }

                });
    }

    public void registration(View view) {

        intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void buttonResetPassword(View view) {

        intent = new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
    }

    public void withoutRegistration(View view) {

        sharedPreferencesRegistrationFlag(true);

        intentMainMenu();
    }

    public void intentMainMenu() {

        intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void sharedPreferencesRegistrationFlag(boolean registrationFlag){

        editor = sPref.edit();
        editor.putBoolean(PREFERENCES_FLAG_REGISTRATION, registrationFlag);
        editor.apply();
    }

}