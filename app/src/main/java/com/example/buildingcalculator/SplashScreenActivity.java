package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;

import com.example.buildingcalculator.authentication.LogInActivity;
import com.google.android.material.textview.MaterialTextView;

import static com.example.buildingcalculator.Constants.SPLASH_DISPLAY_LENGTH;

public class SplashScreenActivity extends AppCompatActivity {

    MaterialTextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initTextView();

        new Handler().postDelayed(() -> {

            Intent mainIntent = new Intent(SplashScreenActivity.this, LogInActivity.class);
            SplashScreenActivity.this.startActivity(mainIntent);
            SplashScreenActivity.this.finish();

        }, SPLASH_DISPLAY_LENGTH);
    }

    public void initTextView(){

        loadingText = findViewById(R.id.loading_text_vew);
        loadingText.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

}
