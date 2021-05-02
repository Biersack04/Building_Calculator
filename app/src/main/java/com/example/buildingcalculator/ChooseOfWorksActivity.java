package com.example.buildingcalculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.buildingcalculator.Constants.ACTIVITY_SELECTION;
import static com.example.buildingcalculator.Constants.MATERIALS;
import static com.example.buildingcalculator.Constants.WORKS;

public class ChooseOfWorksActivity extends AppCompatActivity {

    private ArrayList<RecyclerItem> listItems;
    private RecyclerAdapter adapter;
    String activitySelection;
    TextView calculationOfWorksOrMaterials;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_of_works);
        Bundle arguments = getIntent().getExtras();
        if (arguments !=null)
        {
            activitySelection = arguments.get(ACTIVITY_SELECTION).toString();
        }
        calculationOfWorksOrMaterials = findViewById(R.id.calculation_of_works);
        editText = findViewById(R.id.search_of_works);

        calculationOfWorksOrMaterials.setText(getString(R.string.calculation_of_works));
        editText.setHint(getString(R.string.search_of_works));


        createExampleList();
        buildRecyclerView();


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String text) {
        ArrayList<RecyclerItem> filteredList = new ArrayList<>();

        for (RecyclerItem item : listItems) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }



    private void buildRecyclerView() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Set adapter
        adapter = new RecyclerAdapter(listItems, this);
        recyclerView.setAdapter(adapter);

    }

    private void createExampleList() {
        listItems = new ArrayList<>();
        //Generate sample data
        listItems.add(new RecyclerItem("Грунтовка стен"));
        listItems.add(new RecyclerItem("Шпаклевка стен"));
        listItems.add(new RecyclerItem("Покраска стен"));
        listItems.add(new RecyclerItem("Поклейка обоев"));

    }

    public void profile(View view) {
    }

    public void myProject(View view) {
    }

    public void menu(View view) {
    }

    public void back(View view) {
    }
}