package com.example.buildingcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.buildingcalculator.Constants.APP_PREFERENCES_NAME;
import static com.example.buildingcalculator.Constants.PREFERENCES_FLAG_REGISTRATION;

public class MainMenuActivity extends AppCompatActivity {

    Button calculator, calculatingOfWorks, calculatingOfMaterials;
    TextView menu, profile, project;
    ImageView imageViewProject, imageViewProfile, imageViewMenu;
    Intent intent;
    private long backPressedTime;
    private Toast backToast;
    Boolean flagWithoutRegistration;
    SharedPreferences sPref;
    ConstraintLayout constraintLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        constraintLayout = findViewById(R.id.container);

        calculator = findViewById(R.id.calculator);
        calculator.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        calculatingOfWorks = findViewById(R.id.calculation_of_works);
        calculatingOfWorks.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        calculatingOfMaterials = findViewById(R.id.calculation_of_materials);
        calculatingOfMaterials.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        menu = findViewById(R.id.menu);
        menu.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        project = findViewById(R.id.project);
        project.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        profile = findViewById(R.id.profile);
        profile.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        imageViewProject = findViewById(R.id.image_project);
        imageViewProfile = findViewById(R.id.image_profile);
        imageViewMenu = findViewById(R.id.image_menu);

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);

        flagWithoutRegistration = sPref.getBoolean(PREFERENCES_FLAG_REGISTRATION,true);

        if (flagWithoutRegistration)
        {
            project.setVisibility(View.INVISIBLE);
            imageViewProject.setVisibility(View.GONE);
            ConstraintSet set = new ConstraintSet();

            set.clone(constraintLayout);

            set.connect(R.id.image_menu, ConstraintSet.END, R.id.vertical_center_percent, ConstraintSet.START);
            set.setHorizontalBias(R.id.image_menu, 0.5f);

            set.connect(R.id.menu, ConstraintSet.END, R.id.vertical_center_percent, ConstraintSet.START);
            set.setHorizontalBias(R.id.image_menu, 0.5f);

            set.connect(R.id.image_profile, ConstraintSet.START, R.id.vertical_center_percent, ConstraintSet.END);
            set.setHorizontalBias(R.id.image_menu, 0.5f);

            set.connect(R.id.profile, ConstraintSet.START, R.id.vertical_center_percent, ConstraintSet.END);
            set.setHorizontalBias(R.id.image_menu, 0.5f);

            set.applyTo(constraintLayout);
        }

    }

    public void profile(View view) {

        if (flagWithoutRegistration)
        {
            intent = new Intent(this, RegistrationActivity.class);
        }
        else
        {
            intent = new Intent(this, ProfileActivity.class);
        }
        startActivity(intent);
    }

    public void calculator(View view) {

        intent = new Intent(this, SimpleCalculator.class);
        startActivity(intent);
    }

    public void myProject(View view) {

        intent = new Intent(this, MyProject.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed(){

        if (backPressedTime + 2000 > System.currentTimeMillis())
        {

            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else
            {
                backToast = Toast.makeText(getBaseContext(),getString(R.string.exit_message),Toast.LENGTH_SHORT);
                backToast.show();
            }

        backPressedTime = System.currentTimeMillis();
    }

    public void calculation_of_works(View view) {
/*
        intent = new Intent(this, CategoryOfWorksOrMaterials.class);
        intent.putExtra("activity_selection","Works");
        startActivity(intent);*/

        intent = new Intent(this, CalculationOfWork.class);
        startActivity(intent);
    }

    public void calculation_of_materials(View view) {

        intent = new Intent(this, CategoryOfWorksOrMaterials.class);
        intent.putExtra("activity_selection","Materials");
        startActivity(intent);
    }
}