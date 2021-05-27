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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;


import com.example.buildingcalculator.roomDataBase.AppDatabase;
import com.example.buildingcalculator.roomDataBase.AppDelegate;
import com.example.buildingcalculator.roomDataBase.Customer;
import com.example.buildingcalculator.roomDataBase.CustomerDao;
import com.example.buildingcalculator.roomDataBase.Executor;
import com.example.buildingcalculator.roomDataBase.ExecutorDao;
import com.example.buildingcalculator.roomDataBase.LegalStatus;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;


import static com.example.buildingcalculator.Constants.APP_PREFERENCES_NAME;
import static com.example.buildingcalculator.Constants.CREATE_PROFILE;
import static com.example.buildingcalculator.Constants.EMPTY_STRING;
import static com.example.buildingcalculator.Constants.PREFERENCES_CUSTOMER_ID;
import static com.example.buildingcalculator.Constants.PREFERENCES_EXECUTOR_ID;
import static com.example.buildingcalculator.Constants.PREFERENCES_LEGAL_STATUS_USER;
import static com.example.buildingcalculator.Constants.PREFERENCES_ROLE;
import static com.example.buildingcalculator.Constants.PREFERENCES_USER_ID;
import static com.example.buildingcalculator.Constants.PROFILE_IS_COMPLETED;
import static com.example.buildingcalculator.Constants.ROLE_CUSTOMER;
import static com.example.buildingcalculator.Constants.ROLE_EXECUTOR;
import static com.example.buildingcalculator.Constants.STATUS_INDIVIDUAL;
import static com.example.buildingcalculator.Constants.STATUS_LEGAL_ENTITY;
import static com.example.buildingcalculator.roomDataBase.LegalStatus.INDIVIDUAL;
import static com.example.buildingcalculator.roomDataBase.LegalStatus.JURISTIC_PERSON;

public class EditProfileActivity extends AppCompatActivity {
    Intent intent;
    MaterialTextView titleTextView;
    TextInputEditText surname, name, patronymic, email, telephone, nameOfFirm, address;
    TextInputLayout statusList;
    AutoCompleteTextView statusItem;
    ArrayAdapter<String> arrayAdapter_status;
    String[] status_items;
    String chooseStatus = "";
    TextInputLayout nameFirmLayout,addressLayout;
    SharedPreferences sPref;
    MaterialTextView numberOfWorkers;
    SharedPreferences.Editor editor;
    EditText numberOfWorkersEditText;
    boolean checkSave=false;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);
        layoutElementsInit();

        Bundle arguments = getIntent().getExtras();

        Boolean createProfile = (Boolean) arguments.get(CREATE_PROFILE);

        if(createProfile){

            titleTextView.setText(getString(R.string.create_profile));
        }
        else {

            titleTextView.setText(getString(R.string.edit_profile));
            readDataBase();
        }
        checkSave=false;
        String role = sPref.getString(PREFERENCES_ROLE," ");

        if (role.equals(ROLE_CUSTOMER))
        {
            numberOfWorkersEditText.setVisibility(View.GONE);
            numberOfWorkers.setVisibility(View.GONE);
        }
        if (role.equals(ROLE_EXECUTOR))
        {
            telephone.setVisibility(View.GONE);

        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"SetTextI18n", "CheckResult"})
    private void readDataBase() {

        String role = sPref.getString(PREFERENCES_ROLE," ");

        if (role.equals(ROLE_EXECUTOR))
        {
            AppDatabase db = AppDelegate.getInstance().getDatabase();
            ExecutorDao executorDao = db.executorDao();
            long userId = sPref.getLong(PREFERENCES_USER_ID,0);
          Executor  executor = executorDao.getById(userId);


                            name.setText(executor.getName());
                            surname.setText(executor.getSurname());
                            patronymic.setText(executor.getPatronymic());
                            email.setText(executor.getMailbox());

                            if (chooseStatus.equals(STATUS_INDIVIDUAL)) {
                                nameOfFirm.setVisibility(View.GONE);
                                nameFirmLayout.setVisibility(View.GONE);

                                address.setVisibility(View.GONE);
                                addressLayout.setVisibility(View.GONE);
                            } else if (chooseStatus.equals(STATUS_LEGAL_ENTITY)) {
                                nameOfFirm.setVisibility(View.VISIBLE);
                                nameFirmLayout.setVisibility(View.VISIBLE);

                                address.setVisibility(View.VISIBLE);
                                addressLayout.setVisibility(View.VISIBLE);
                            }
                            nameOfFirm.setText(executor.getNameOfFirm());
                            address.setText(executor.getAddressOfFirm());
                            assert executor.getNumberOfWorkers() != null;
                            numberOfWorkersEditText.setText(executor.getNumberOfWorkers().toString());




        }else if (role.equals(ROLE_CUSTOMER))
        {
            AppDatabase db = AppDelegate.getInstance().getDatabase();
            CustomerDao customerDao = db.customerDao();




            long userId = sPref.getLong(PREFERENCES_USER_ID,0);
         Customer   customer = customerDao.getById(userId);


            name.setText(customer.getName());
            surname.setText(customer.getSurname());
            patronymic.setText(customer.getPatronymic());
            email.setText(customer.getMailbox());


            if (chooseStatus.equals(STATUS_INDIVIDUAL))
            {
                nameOfFirm.setVisibility(View.GONE);
                nameFirmLayout.setVisibility(View.GONE);

                address.setVisibility(View.GONE);
                addressLayout.setVisibility(View.GONE);
            }
            else if (chooseStatus.equals(STATUS_LEGAL_ENTITY) )
            {
                nameOfFirm.setVisibility(View.VISIBLE);
                nameFirmLayout.setVisibility(View.VISIBLE);

                address.setVisibility(View.VISIBLE);
                addressLayout.setVisibility(View.VISIBLE);
            }
            nameOfFirm.setText(customer.getNameOfFirm());
            address.setText(customer.getAddressOfFirm());
            telephone.setText(customer.getTelephone());
        }

    }

    public void layoutElementsInit(){

        titleTextView = findViewById(R.id.text_view_edit_profile_layout);
        titleTextView.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        surname = findViewById(R.id.edit_surname_edit_profile_layout);
        surname.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        name = findViewById(R.id.edit_name_edit_profile_layout);
        name.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        patronymic = findViewById(R.id.edit_patronymic_edit_profile_layout);
        patronymic.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        email = findViewById(R.id.edit_email_edit_profile_layout);
        email.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        telephone = findViewById(R.id.edit_telephone_edit_profile_layout);
        telephone.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        statusList = findViewById(R.id.status_input_edit_profile_layout);
        statusItem = findViewById(R.id.status_item_edit_profile_layout);
        status_items = getResources().getStringArray(R.array.status_items);
        arrayAdapter_status = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_item, status_items);
        statusItem.setAdapter(arrayAdapter_status);
        statusItem.setOnItemClickListener(onItemClickListener);

        nameFirmLayout = findViewById(R.id.name_of_firm_layout_edit_profile_layout);
        nameOfFirm = findViewById(R.id.edit_name_of_firm_edit_profile_layout);
        nameOfFirm.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        address = findViewById(R.id.edit_address_edit_profile_layout);
        addressLayout = findViewById(R.id.address_layout_edit_profile_layout);
        address.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        numberOfWorkers = findViewById(R.id.number_workers_edit_profile_layout);
        numberOfWorkers.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_medium)));

        numberOfWorkersEditText = findViewById(R.id.edit_number_of_workers_edit_profile_layout);
    }

    private final AdapterView.OnItemClickListener onItemClickListener =
            (adapterView, view, i, l) -> {
                chooseStatus = (String) adapterView.getItemAtPosition(i);

                if (chooseStatus.equals(STATUS_INDIVIDUAL))
                {
                    nameOfFirm.setVisibility(View.GONE);
                    nameFirmLayout.setVisibility(View.GONE);

                    address.setVisibility(View.GONE);
                    addressLayout.setVisibility(View.GONE);
                }
                else if (chooseStatus.equals(STATUS_LEGAL_ENTITY) )
                {
                    nameOfFirm.setVisibility(View.VISIBLE);
                    nameFirmLayout.setVisibility(View.VISIBLE);

                    address.setVisibility(View.VISIBLE);
                    addressLayout.setVisibility(View.VISIBLE);
                }
            };


    @SuppressLint("LongLogTag")
    public void saveProfile(View view) {
        Log.e("НАЖАЛИ НА СЕЙВ ПРОДЖЕКТ","AAAAAAAAAAAAAAAAAAaa");

        Log.e("ЧЕК МЕЙН ФИЛДС", String.valueOf(checkMainFields()));

       if(checkMainFields()) {
           sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);

           Bundle arguments = getIntent().getExtras();

           Boolean createProfile = (Boolean) arguments.get(CREATE_PROFILE);
           String role = sPref.getString(PREFERENCES_ROLE, EMPTY_STRING);
           Log.e("createProfile", createProfile.toString());

           Log.e("rol", role);

           if(createProfile){

               if (role.equals(ROLE_CUSTOMER))
               {
                   saveCustomer();
                   checkSave=true;

               }else if (role.equals(ROLE_EXECUTOR))
               {
                   Log.e("AAAAAAAAAAAAAAA","ЗАШЕЛ В SAVEPROFILE _ ROLE_EXECUTOR");
                   saveExecutor();
                   checkSave=true;
               }
               else
               {
                   Toast.makeText(EditProfileActivity.this, getString(R.string.save_people_error),Toast.LENGTH_SHORT).show();
               }
           }
           else {

               Log.e("По идее здесь редактирование профиля","FFFFFFFFFFFFFF");

               if (role.equals(ROLE_CUSTOMER))
               { Log.e("РЕДАКТИРОВАНИЕ КАСТОМЕРА","FFFFFFFFFFFFFF");
                   updateCustomer();

               }else if (role.equals(ROLE_EXECUTOR))
               { Log.e("РЕДАКТИРОВАНИЕ ЭКЗЕКБТОРА","FFFFFFFFFFFFFF");
                   updateExecutor();
               }
           }

           intent = new Intent(this, ProfileActivity.class);
           startActivity(intent);
           finish();

       }

    }

    @SuppressLint("LongLogTag")
    private void updateExecutor() {

        Log.e("Зашел в  Обновление экзекьютера","");
        long userId = sPref.getLong(PREFERENCES_USER_ID,0);
        Log.e("PREFERENCES_EXECUTOR_ID", String.valueOf(userId));
        AppDatabase db = AppDelegate.getInstance().getDatabase();
        ExecutorDao executorDao = db.executorDao();

        Executor executor = new Executor();
        executor.setId(userId);
        executor.setName(name.getText().toString());
        executor.setSurname(surname.getText().toString());
        executor.setPatronymic(patronymic.getText().toString());
        executor.setMailbox(email.getText().toString());
        executor.setAddressOfFirm(address.getText().toString());
        String statusName = checkStatus();
        editor = sPref.edit();
        editor.putString(PREFERENCES_LEGAL_STATUS_USER, statusName);
        editor.apply();
        LegalStatus legalStatus = LegalStatus.valueOf(statusName);
        executor.setLegalStatus(legalStatus);
        executor.setNameOfFirm(nameOfFirm.getText().toString());
        if (numberOfWorkersEditText.getText().toString().equals(""))
        {
            Log.e("ПРОВЕРОЧКА НА КОЛ_ВО РАБОТНИКОВ","AAAAAAAAAAAAAAAAAAA");
            executor.setNumberOfWorkers(0L);
        }else{
            executor.setNumberOfWorkers(Long.parseLong(numberOfWorkersEditText.getText().toString()));
        }

        Log.e("EXECUTOR",executor.toString());
        executorDao.update(executor);
    }

    private void updateCustomer() {

        AppDatabase db = AppDelegate.getInstance().getDatabase();
        CustomerDao customerDao = db.customerDao();
        long userId = sPref.getLong(PREFERENCES_USER_ID,0);
        Customer customer = new Customer();
        customer.setId(userId);
        customer.setName(name.getText().toString());
        customer.setSurname(surname.getText().toString());
        customer.setPatronymic(patronymic.getText().toString());
        customer.setMailbox(email.getText().toString());
        customer.setAddressOfFirm(address.getText().toString());
        String statusName = checkStatus();
        editor = sPref.edit();
        editor.putString(PREFERENCES_LEGAL_STATUS_USER, statusName);
        editor.apply();
        LegalStatus legalStatus = LegalStatus.valueOf(statusName);
        customer.setLegalStatus(legalStatus);
        customer.setNameOfFirm(nameOfFirm.getText().toString());
        customer.setTelephone(telephone.getText().toString());

        customerDao.update(customer);
    }

    private void saveExecutor() {

        Log.e("AAAAAAAAAAA","ЗАШЕЛ В ПАБЛИК ВОЙД СЕЙВ ЭКЗЕКЬЮТОР");
        AppDatabase db = AppDelegate.getInstance().getDatabase();
        ExecutorDao executorDao = db.executorDao();

        Executor executor = new Executor();
        executor.setName(name.getText().toString());
        executor.setSurname(surname.getText().toString());
        executor.setPatronymic(patronymic.getText().toString());
        executor.setMailbox(email.getText().toString());
        executor.setAddressOfFirm(address.getText().toString());
        String statusName = checkStatus();
        LegalStatus legalStatus = LegalStatus.valueOf(statusName);

        //LegalStatus legalStatus1 = LegalStatus.valueOf(statusName);
        editor = sPref.edit();
        editor.putString(PREFERENCES_LEGAL_STATUS_USER, statusName);
        editor.apply();

        executor.setLegalStatus(legalStatus);
        executor.setNameOfFirm(nameOfFirm.getText().toString());
        if (numberOfWorkersEditText.getText().toString().equals(""))
        {
            executor.setNumberOfWorkers(0L);
        }else{
            executor.setNumberOfWorkers(Long.parseLong(numberOfWorkersEditText.getText().toString()));
        }


        long userId = executorDao.insert(executor);
        sharedPreferencesUserId(userId);
        sharedPreferencesCreateProfile(true);
/*
        ExecutorHibernate executorHibernate = new ExecutorHibernate(name.getText().toString(), surname.getText().toString(),
                patronymic.getText().toString(),
                email.getText().toString(),legalStatus1,"adressOfFirm",address.getText().toString(),0L,
                Long.parseLong(numberOfWorkersEditText.getText().toString()));

        Call<Boolean> call = controller.addExecutor(executorHibernate);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean result=response.body();
                Log.e("Success","" + result);
                
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("FAIL","FAIL");
            }
        });
*/
    }

    private void saveCustomer() {

        AppDatabase db = AppDelegate.getInstance().getDatabase();
        CustomerDao customerDao = db.customerDao();

        Customer customer = new Customer();
        customer.setName(name.getText().toString());
        customer.setSurname(surname.getText().toString());
        customer.setPatronymic(patronymic.getText().toString());
        customer.setMailbox(email.getText().toString());
        customer.setAddressOfFirm(address.getText().toString());
        String statusName = checkStatus();
        LegalStatus legalStatus = LegalStatus.valueOf(statusName);
        customer.setLegalStatus(legalStatus);

        editor = sPref.edit();
        editor.putString(PREFERENCES_LEGAL_STATUS_USER, statusName);
        editor.apply();

        customer.setNameOfFirm(nameOfFirm.getText().toString());
        customer.setTelephone(telephone.getText().toString());

        long userId = customerDao.insert(customer);
        sharedPreferencesUserId(userId);
        sharedPreferencesCreateProfile(true);



    }

    private String checkStatus(){

        if (chooseStatus.equals(STATUS_LEGAL_ENTITY))
        {
            return String.valueOf(JURISTIC_PERSON);

        }else if (chooseStatus.equals(STATUS_INDIVIDUAL)){
            return String.valueOf(INDIVIDUAL);
        }else return EMPTY_STRING;

    }



    public void sharedPreferencesUserId(long userId){

        editor = sPref.edit();
        editor.putLong(PREFERENCES_USER_ID, userId);
        editor.apply();
    }

    public void sharedPreferencesCreateProfile(boolean createProfile){

        editor = sPref.edit();
        editor.putBoolean(PROFILE_IS_COMPLETED, createProfile);
        editor.apply();
    }

    private boolean checkMainFields() {

        String nameString = name.getText().toString();
        String surnameString = surname.getText().toString();
        String patronymicString = patronymic.getText().toString();


        if (!(nameString.isEmpty()) && !surnameString.isEmpty() && !(patronymicString.isEmpty()) && !(chooseStatus.isEmpty()))
        {
            return true;
        }

        if (name.getText().length() == 0)
        {
            name.requestFocus();
            name.setError("ИМЯ");
            return false;
        }
        else if (surname.getText().length() == 0)
        {
            surname.requestFocus();
            surname.setError("Фамилия");
            return false;
        }
        else if (patronymic.getText().length() == 0)
        {
            patronymic.requestFocus();
            patronymic.setError("ОТЧЕСТВО");
            return false;
        }
        else if (chooseStatus.length() == 0)
        {
            statusList.setError("Выберете статус");
            return false;
        }
        return false;

    }

    public void back(View view) {

        if (checkSave){
            intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }else
        {
            Toast.makeText(EditProfileActivity.this, "Вы точно не хотите сохранить изменения?",Toast.LENGTH_SHORT).show();
        }
    }


}