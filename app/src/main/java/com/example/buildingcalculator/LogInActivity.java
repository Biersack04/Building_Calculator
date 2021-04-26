package com.example.buildingcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.buildingcalculator.Constants.*;


public class LogInActivity extends AppCompatActivity {

    TextView logIn, registration, withoutRegistration, forgotPassword;
    Button buttonSignIn;
    Intent intent;
    FirebaseAuth auth;
    SharedPreferences sPref;
    SharedPreferences.Editor editor;
    Boolean flagWithoutRegistration;
    String email = "", password = "";
    ProgressBar progressBar;
    TextInputEditText inputEmail, inputPassword;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logIn = findViewById(R.id.logIn);
        logIn.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        registration = findViewById(R.id.registration);
        registration.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        buttonSignIn = findViewById(R.id.button_signIn);
        buttonSignIn.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        withoutRegistration = findViewById(R.id.without_registration);
        withoutRegistration.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_italic)));

        forgotPassword = findViewById(R.id.forgot_password);
        forgotPassword.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_italic)));

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null)
        {
            intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        }

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
                    if (!task.isSuccessful()){
                        Toast.makeText(LogInActivity.this, getString(R.string.error_log_in),Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);

                        flagWithoutRegistration = false;
                        editor = sPref.edit();
                        editor.putBoolean(PREFERENCES_FLAG_REGISTRATION, flagWithoutRegistration);
                        editor.apply();

                        intent = new Intent(LogInActivity.this, MainMenuActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
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

        flagWithoutRegistration = true;
        editor = sPref.edit();
        editor.putBoolean(PREFERENCES_FLAG_REGISTRATION, flagWithoutRegistration);
        editor.apply();

        intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}