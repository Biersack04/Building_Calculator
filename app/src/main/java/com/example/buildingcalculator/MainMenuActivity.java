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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.buildingcalculator.Authentication.RegistrationActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import static com.example.buildingcalculator.Constants.*;

public class MainMenuActivity extends AppCompatActivity {

    MaterialButton calculator, calculatingOfWorks, calculatingOfMaterials;
    MaterialTextView menu, profile, project;
    ImageView imageViewProject;
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

        layoutElementsInit();

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);

        flagWithoutRegistration = sPref.getBoolean(PREFERENCES_FLAG_REGISTRATION,true);

        if (flagWithoutRegistration)
        {
            hideElements();
        }
    }

    public void layoutElementsInit(){

        constraintLayout = findViewById(R.id.container);

        calculator = findViewById(R.id.calculator_main_menu_layout);
        calculator.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        calculatingOfWorks = findViewById(R.id.calculation_of_works_main_menu_layout);
        calculatingOfWorks.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        calculatingOfMaterials = findViewById(R.id.calculation_of_materials_main_menu_layout);
        calculatingOfMaterials.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        menu = findViewById(R.id.menu_main_menu_layout);
        menu.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        project = findViewById(R.id.project_main_menu_layout);
        project.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        profile = findViewById(R.id.profile_main_menu_layout);
        profile.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        imageViewProject = findViewById(R.id.image_project_main_menu_layout);
    }

    public void hideElements(){

        project.setVisibility(View.INVISIBLE);
        imageViewProject.setVisibility(View.GONE);
        ConstraintSet set = new ConstraintSet();

        set.clone(constraintLayout);

        set.connect(R.id.image_menu_main_menu_layout, ConstraintSet.END, R.id.vertical_center_percent_main_menu_layout, ConstraintSet.START);
        set.setHorizontalBias(R.id.image_menu_main_menu_layout, 0.5f);

        set.connect(R.id.menu_main_menu_layout, ConstraintSet.END, R.id.vertical_center_percent_main_menu_layout, ConstraintSet.START);
        set.setHorizontalBias(R.id.image_menu_main_menu_layout, 0.5f);

        set.connect(R.id.image_profile_main_menu_layout, ConstraintSet.START, R.id.vertical_center_percent_main_menu_layout, ConstraintSet.END);
        set.setHorizontalBias(R.id.image_menu_main_menu_layout, 0.5f);

        set.connect(R.id.profile_main_menu_layout, ConstraintSet.START, R.id.vertical_center_percent_main_menu_layout, ConstraintSet.END);
        set.setHorizontalBias(R.id.image_menu_main_menu_layout, 0.5f);

        set.applyTo(constraintLayout);
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

        intent = new Intent(this, SimpleCalculatorActivity.class);
        startActivity(intent);
    }

    public void myProject(View view) {

        intent = new Intent(this, MyProjectsListActivity.class);
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

        intent = new Intent(this, CategoryOfWorksOrMaterialsActivity.class);
        intent.putExtra(ACTIVITY_SELECTION,WORKS);
        startActivity(intent);
    }

    public void calculation_of_materials(View view) {

        intent = new Intent(this, CategoryOfWorksOrMaterialsActivity.class);
        intent.putExtra(ACTIVITY_SELECTION,MATERIALS);
        startActivity(intent);
    }
}