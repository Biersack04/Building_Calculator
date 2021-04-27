package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


public class ResetPasswordActivity extends AppCompatActivity {

    Intent intent;
    TextView resetPasswordText;
    Button buttonResetPassword;
    TextInputEditText inputEmail;
    String email = "";
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        resetPasswordText = findViewById(R.id.resetPasswordText);
        resetPasswordText.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        buttonResetPassword = findViewById(R.id.button_reset_password);
        buttonResetPassword.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));
        inputEmail = findViewById(R.id.email_edit_text);

        auth = FirebaseAuth.getInstance();
    }

    public void buttonResetPassword(View view) {

        email = inputEmail.getText().toString();

        if (!(email.isEmpty()))
        {
            resetPassword();
        }
        else if(inputEmail.getText().length() == 0)
        {
            inputEmail.requestFocus();
            inputEmail.setError(getString(R.string.email_error));
        }

    }

    public void resetPassword() {

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(ResetPasswordActivity.this, getString(R.string.password_change_instruction),Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(ResetPasswordActivity.this, getString(R.string.changing_password_error),Toast.LENGTH_SHORT).show();
                    }

                });
    }

    public void backLogIn(View view) {

        intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){

        intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}