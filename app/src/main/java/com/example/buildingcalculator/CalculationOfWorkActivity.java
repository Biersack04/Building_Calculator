package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.buildingcalculator.roomDataBase.AppDatabase;
import com.example.buildingcalculator.roomDataBase.AppDelegate;
import com.example.buildingcalculator.roomDataBase.Executor;
import com.example.buildingcalculator.roomDataBase.ExecutorDao;
import com.example.buildingcalculator.roomDataBase.LegalStatus;
import com.example.buildingcalculator.roomDataBase.Priority;
import com.example.buildingcalculator.roomDataBase.Status;
import com.example.buildingcalculator.roomDataBase.Work;
import com.example.buildingcalculator.roomDataBase.WorkDao;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.buildingcalculator.Constants.ACTIVITY_SELECTION;
import static com.example.buildingcalculator.Constants.APP_PREFERENCES_NAME;
import static com.example.buildingcalculator.Constants.PREFERENCES_LEGAL_STATUS_USER;
import static com.example.buildingcalculator.Constants.PREFERENCES_ROLE;
import static com.example.buildingcalculator.Constants.PREFERENCES_USER_ID;

public class
CalculationOfWorkActivity extends AppCompatActivity {

    TextInputLayout roleList;
    AutoCompleteTextView roleItem;
    ArrayAdapter<String> arrayAdapter_role;
    String[] role_items;

    String nameOfWork;
    String email = "", password = "", chooseRole = "";

    EditText qty, pricePerOne;
    long qtyLong, pricePerOneLong;
    TextView title;
    TextView count;
    TextView priceTotalPerOne;
    TextView totalPrice;
    Intent intent;
    long idWork;
    SharedPreferences sPref;
    SharedPreferences.Editor editor;
    Work work;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_of_work);

        Bundle arguments = getIntent().getExtras();
        //if (arguments != null) {
            nameOfWork = arguments.get("nameOfWork").toString();
       // }
        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);
        Log.e("AAAAAAAAAAAAAAAA", nameOfWork);

      /*  roleList = findViewById(R.id.role_input_registration_layout);
        roleItem = findViewById(R.id.role_item_registration_layout);
        role_items = getResources().getStringArray(R.array.units);
        arrayAdapter_role = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_item, role_items);
        roleItem.setAdapter(arrayAdapter_role);
        roleItem.setOnItemClickListener(onItemClickListener);
*/
        qty = findViewById(R.id.enter_units_layout);
        pricePerOne = findViewById(R.id.enter_unit_price_layout);


        title = findViewById(R.id.title_text);
        count = findViewById(R.id.qty_total_text);
        priceTotalPerOne = findViewById(R.id.unit_price_total_text);
        totalPrice = findViewById(R.id.total_price_text);

    }

    private final AdapterView.OnItemClickListener onItemClickListener =
            (adapterView, view, i, l) -> {

                chooseRole = (String) adapterView.getItemAtPosition(i);
            };

    public void myProject(View view) {
    }

    public void profile(View view) {
    }

    public void menu(View view) {
    }

    public void back(View view) {
    }

    public void calculateWork(View view) {

        qtyLong = Long.parseLong(qty.getText().toString());
        pricePerOneLong = Long.parseLong(pricePerOne.getText().toString());

        title.setText(nameOfWork);
        count.setText(String.valueOf(qtyLong));
        priceTotalPerOne.setText(String.valueOf(pricePerOneLong));
        totalPrice.setText(String.valueOf(qtyLong * pricePerOneLong));

    }

    public void addToProject(View view) {

        //Проверка что проекты вообще существуют или не надо
        editor = sPref.edit();
        editor.putString("work_name", nameOfWork);
        editor.putLong("work_quantity", qtyLong);
        editor.putLong("work_price_per_one", pricePerOneLong);
        editor.putLong("work_total_price", qtyLong * pricePerOneLong);
        editor.putString("work_priority", "HIGH");
        editor.putString("work_status", "CREATED");
        editor.apply();
/*
        AppDatabase db = AppDelegate.getInstance().getDatabase();
        WorkDao workDao = db.workDao();

        work = new Work();
        work.setName(nameOfWork);
        work.setQuantity(qtyLong);
        work.setPricePerOne(pricePerOneLong);
        work.setTotalPrice(qtyLong * pricePerOneLong);
        String priorityName = "HIGH";
        Priority priority = Priority.valueOf(priorityName);
        work.setPriority(priority);
        String statusName = "CREATED";
        Status status = Status.valueOf(statusName);
        work.setStatus(status);


        */


        /*
        intent = new Intent(this, MyProjectsListActivity.class);
        startActivity(intent);
*/
        //Сохранить бин в базу данных
        //перейти на  вкладку со всеми проектами
        //Выбрав проект добавить в работу его ай ди
        //

        intent = new Intent(this, MyProjectsListActivity.class);
        intent.putExtra("ChooseProjectForWork", true);
        intent.putExtra("nameOfWork",nameOfWork);
        startActivity(intent);
/*
        Intent intent = new Intent(this, MyProjectsListActivity.class);
        startActivityForResult(intent, 1);
*/

    }



    @Override
    protected void onResume() {
        // запишем в лог значения requestCode и resultCode
        long    projectId =0;
        boolean projectChoosed = false;
        super.onResume();

        Bundle arguments = getIntent().getExtras();
        if (arguments !=null)
        {
            projectId = arguments.getLong("idOfChooseProject");
            projectChoosed = arguments.getBoolean("ProjectChoosed");
        }

        Log.e("PROJECTID", String.valueOf(projectId));
        editor = sPref.edit();
        editor.putString("work_name", nameOfWork);
        editor.putLong("work_quantity", qtyLong);
        editor.putLong("work_price_per_one", pricePerOneLong);
        editor.putLong("work_total_price", qtyLong * pricePerOneLong);
        editor.putString("work_priority", "HIGH");
        editor.putString("work_status", "CREATED");
        editor.apply();

        if(projectChoosed){
            AppDatabase db = AppDelegate.getInstance().getDatabase();
            WorkDao workDao = db.workDao();
            long userId = sPref.getLong(PREFERENCES_USER_ID,0);
            work = new Work();
            work.setName(sPref.getString("work_name",""));
            work.setQuantity(sPref.getLong("work_quantity",0));
            work.setPricePerOne(sPref.getLong("work_price_per_one",0));
            work.setTotalPrice(sPref.getLong("work_total_price",0));
            String priorityName = sPref.getString("work_priority","");
            Priority priority = Priority.valueOf(priorityName);
            work.setPriority(priority);
            String statusName = sPref.getString("work_status","");
            Status status = Status.valueOf(statusName);
            work.setParentId(projectId);
            work.setStatus(status);
            idWork = workDao.insert(work);

            intent = new Intent(this, CategoryOfWorksOrMaterialsActivity.class);
            startActivity(intent);

        }


        /*
        Work workInProject = workDao.getById(idWork);
        workInProject.setParentId(projectId);
        */
       // work.setParentId(projectId);
        //idWork = workDao.insert(work);
    }
}