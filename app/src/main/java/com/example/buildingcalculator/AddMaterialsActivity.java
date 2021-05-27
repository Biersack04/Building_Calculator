package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.buildingcalculator.roomDataBase.Material;
import com.example.buildingcalculator.roomDataBase.MaterialDao;
import com.example.buildingcalculator.roomDataBase.Priority;
import com.example.buildingcalculator.roomDataBase.Project;
import com.example.buildingcalculator.roomDataBase.ProjectDao;
import com.example.buildingcalculator.roomDataBase.Status;
import com.example.buildingcalculator.roomDataBase.Units;
import com.example.buildingcalculator.roomDataBase.Work;
import com.example.buildingcalculator.roomDataBase.WorkDao;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.buildingcalculator.Constants.PREFERENCES_USER_ID;

public class AddMaterialsActivity extends AppCompatActivity {
    TextInputLayout roleList;
    AutoCompleteTextView roleItem;
    ArrayAdapter<String> arrayAdapter_role;
    String[] role_items;
    String chooseUnits = "";
    String nameOfWork;
    EditText qty, pricePerOne;
    long qtyLong, pricePerOneLong;
    TextView title;
    TextView count;
    TextView priceTotalPerOne;
    TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_materials);
        Bundle arguments = getIntent().getExtras();
        //if (arguments != null) {
        nameOfWork = arguments.get("nameOfWork").toString();

        roleList = findViewById(R.id.units_input_layout);
        roleItem = findViewById(R.id.units_item);
        role_items = getResources().getStringArray(R.array.units);
        arrayAdapter_role = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_item, role_items);
        roleItem.setAdapter(arrayAdapter_role);
        roleItem.setOnItemClickListener(onItemClickListener);

        title = findViewById(R.id.edit_title);
        qty = findViewById(R.id.edit_count);
        pricePerOne = findViewById(R.id.edit_price);
       // roleList = findViewById(R.id.units_input_layout);



    }

    private final AdapterView.OnItemClickListener onItemClickListener =
            (adapterView, view, i, l) -> {

                chooseUnits = (String) adapterView.getItemAtPosition(i);
            };

    public void back(View view) {
    }

    /*Выбирать проект все равно придется*/

    public void saveMaterial(View view) {
        AppDatabase db = AppDelegate.getInstance().getDatabase();

        Material material = new Material();

        MaterialDao materialDao = db.materialDao();

      //  long userId = sPref.getLong(PREFERENCES_USER_ID,0);

        material.setName(title.getText().toString());
        String unitsName = "PIECE";
        Units units = Units.valueOf(unitsName);
        material.setUnits(units);
        material.setQuantity(Long.parseLong(String.valueOf(qty.getText())));
        material.setPriceForOne(Long.parseLong(String.valueOf(pricePerOne.getText())));
        material.setTotalPrice(Long.parseLong(String.valueOf(pricePerOne.getText())) * Long.parseLong(String.valueOf(qty.getText())));

       /* WorkDao workDao = db.workDao();
        Work work = new Work();
        work.setName("name");
        work.setQuantity(25);
        work.setPricePerOne(35);
        work.setTotalPrice(2555);
        String priorityName = "HIGH";
        Priority priority = Priority.valueOf(priorityName);
        work.setPriority(priority);
        String statusName = "CREATED";
        String unitsName = "PIECE";
        Units units = Units.valueOf(unitsName);
        work.setUnits(units);
        Status status = Status.valueOf(statusName);
        work.setParentId(1);
        work.setStatus(status);
        long idWork = workDao.insert(work);

        Log.e("IDWORK", String.valueOf(idWork));*/
        material.setParentId(1);

        materialDao.insert(material);

        Intent intent = new Intent(this, ListOfMaterialsActivity.class);
        intent.putExtra("selectButton","addMaterial");
        intent.putExtra("nameOfWork",nameOfWork);
        startActivity(intent);


    }

    public void listOfMaterials(View view) {


        Intent intent = new Intent(this, ListOfMaterialsActivity.class);
        intent.putExtra("selectButton","addMaterial");
        intent.putExtra("nameOfWork",nameOfWork);
        startActivity(intent);

    }
}