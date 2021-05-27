package com.example.buildingcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.buildingcalculator.authentication.LogInActivity;

import com.example.buildingcalculator.roomDataBase.AppDatabase;
import com.example.buildingcalculator.roomDataBase.AppDelegate;
import com.example.buildingcalculator.roomDataBase.Customer;
import com.example.buildingcalculator.roomDataBase.CustomerDao;
import com.example.buildingcalculator.roomDataBase.Executor;
import com.example.buildingcalculator.roomDataBase.ExecutorDao;
import com.example.buildingcalculator.roomDataBase.LegalStatus;
import com.example.buildingcalculator.roomDataBase.ProjectDao;
import com.example.buildingcalculator.roomDataBase.Status;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;


import static com.example.buildingcalculator.Constants.APP_PREFERENCES_NAME;
import static com.example.buildingcalculator.Constants.CREATE_PROFILE;
import static com.example.buildingcalculator.Constants.PREFERENCES_LEGAL_STATUS_USER;
import static com.example.buildingcalculator.Constants.PREFERENCES_ROLE;
import static com.example.buildingcalculator.Constants.PREFERENCES_USER_ID;
import static com.example.buildingcalculator.Constants.ROLE_CUSTOMER;
import static com.example.buildingcalculator.Constants.ROLE_EXECUTOR;
import static com.example.buildingcalculator.Constants.STATUS_INDIVIDUAL;
import static com.example.buildingcalculator.Constants.STATUS_LEGAL_ENTITY;
import static com.example.buildingcalculator.roomDataBase.LegalStatus.INDIVIDUAL;
import static com.example.buildingcalculator.roomDataBase.LegalStatus.JURISTIC_PERSON;


public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "AAAA";
    MaterialTextView personalInformation;
    MaterialTextView surname, name, patronymic, email, telephone, status, nameOfFirm, address, numberOfWorkers, numberOfCompletedProject;
    MaterialTextView IntroducedSurname, IntroducedName, IntroducedPatronymic, IntroducedEmail, IntroducedTelephone, IntroducedStatus,
            IntroducedNameOfFirm, IntroducedAddress, IntroducedNumberOfWorkers, IntroducedNumberOfCompletedProject;
    MaterialTextView menu, profile, project;
    Intent intent;
    SharedPreferences sPref;
    Status completedStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        layoutElementsInit();

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);
        String role = sPref.getString(PREFERENCES_ROLE," ");
        if (role.equals(ROLE_CUSTOMER))
        {
            numberOfCompletedProject.setVisibility(View.GONE);
            numberOfWorkers.setVisibility(View.GONE);
            IntroducedNumberOfWorkers.setVisibility(View.GONE);
            IntroducedNumberOfCompletedProject.setVisibility(View.GONE);
        }
        if (role.equals(ROLE_EXECUTOR))
        {
            telephone.setVisibility(View.GONE);
            IntroducedTelephone.setVisibility(View.GONE);
        }
        String legalStatus = sPref.getString(PREFERENCES_LEGAL_STATUS_USER," ");
        if (legalStatus.equals(STATUS_INDIVIDUAL))
        {
            nameOfFirm.setVisibility(View.GONE);
            address.setVisibility(View.GONE);
            IntroducedNameOfFirm.setVisibility(View.GONE);
            IntroducedAddress.setVisibility(View.GONE);
        }

        completedStatus = Status.valueOf("DONE");




    }

    public void layoutElementsInit(){

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


    @SuppressLint("CheckResult")
    @Override
    public void onResume(){
        super.onResume();
        AppDatabase db = AppDelegate.getInstance().getDatabase();
        long userId = sPref.getLong(PREFERENCES_USER_ID,0);
        Log.e("AAAAAAAAAA", String.valueOf(userId));
/*
        db.executorDao().getById(userId)
              //  .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Executor>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void accept(Executor executor) throws Exception {


                        IntroducedName.setText(executor.getName());
                        IntroducedSurname.setText(executor.getSurname());
                        IntroducedPatronymic.setText(executor.getPatronymic());
                        IntroducedEmail.setText(executor.getMailbox());

                        ///////////АААААААААА ПРОБЛЕМЫ СО СТАТУСОМ НЕ ТО ВЫВОДИТ
                     //   СДЕЛАТЬ ВЫВОД ПО НОРМАЛЬНОМУ ПО РУССКИ
                        LegalStatus status = executor.getLegalStatus();
                        String statusName = status.name();
                        IntroducedStatus.setText(statusName);
                        IntroducedNameOfFirm.setText(executor.getNameOfFirm());
                        IntroducedAddress.setText(executor.getAddressOfFirm());

                        assert executor.getNumberOfWorkers() != null;
                        IntroducedNumberOfWorkers.setText(executor.getNumberOfWorkers().toString());

                       long numberOfCompletedProject = executorDao.executorCompletedProject(userId, completedStatus);
                       Log.e("AAAAAAAAAAAAAAAAAAAA", String.valueOf(numberOfCompletedProject));
                        IntroducedNumberOfCompletedProject.setText(Long.toString(numberOfCompletedProject));

                    }});
*/

        String role = sPref.getString(PREFERENCES_ROLE," ");

        if (role.equals(ROLE_EXECUTOR))
        {
            ExecutorDao executorDao = db.executorDao();
            ProjectDao projectDao = db.projectDao();
            Executor  executor = executorDao.getById(userId);


            IntroducedName.setText(executor.getName());
            IntroducedSurname.setText(executor.getSurname());
            IntroducedPatronymic.setText(executor.getPatronymic());
            IntroducedEmail.setText(executor.getMailbox());
            LegalStatus status = executor.getLegalStatus();
            String statusName = status.name();
            Log.e("LEGAL STATUS",statusName);
            IntroducedStatus.setText(statusName);

            switch(statusName) {
                case "JURISTIC_PERSON":
                    IntroducedStatus.setText(STATUS_LEGAL_ENTITY);
                    break;
                case "INDIVIDUAL":
                    IntroducedStatus.setText(STATUS_INDIVIDUAL);
                    break;

                default:
                    break;
            }

                            IntroducedNameOfFirm.setText(executor.getNameOfFirm());
                            IntroducedAddress.setText(executor.getAddressOfFirm());

                            assert executor.getNumberOfWorkers() != null;
                            IntroducedNumberOfWorkers.setText(executor.getNumberOfWorkers().toString());
                            long numberOfCompletedProject = projectDao.executorCompletedProject(userId, completedStatus );
                            IntroducedNumberOfCompletedProject.setText(Long.toString(numberOfCompletedProject));



        }else if (role.equals(ROLE_CUSTOMER))
        {
            CustomerDao customerDao = db.customerDao();


            // Customer customer;
            Customer   customer = customerDao.getById(userId);


                            IntroducedName.setText(customer.getName());
                            IntroducedSurname.setText(customer.getSurname());
                            IntroducedPatronymic.setText(customer.getPatronymic());
                            IntroducedEmail.setText(customer.getMailbox());
                            LegalStatus status = customer.getLegalStatus();
                            String statusName = status.name();
                            IntroducedStatus.setText(statusName);
                            IntroducedNameOfFirm.setText(customer.getNameOfFirm());
                            IntroducedAddress.setText(customer.getAddressOfFirm());
                            IntroducedTelephone.setText(customer.getTelephone());

        }

    }

    @Override
    public void onBackPressed() {

        intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

}