package com.example.buildingcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buildingcalculator.roomDataBase.AppDatabase;
import com.example.buildingcalculator.roomDataBase.AppDelegate;
import com.example.buildingcalculator.roomDataBase.Material;
import com.example.buildingcalculator.roomDataBase.MaterialDao;
import com.example.buildingcalculator.roomDataBase.Project;
import com.example.buildingcalculator.roomDataBase.ProjectDao;
import com.example.buildingcalculator.roomDataBase.Units;
import com.example.buildingcalculator.roomDataBase.WorkDao;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.buildingcalculator.Constants.APP_PREFERENCES_NAME;

public class ListOfMaterialsActivity extends AppCompatActivity {
    String selectButton;
    Intent intent;
    RecyclerView projectList;
    private StateAdapterMaterial adapter;
    private ArrayList<Material> listItems;
    String[] listNameColumn;

    SharedPreferences sPref;
    EditText editText;
    ArrayList<Material> projects = new ArrayList<Material>();
    List<Material> projectList2 = new ArrayList<Material>();
    private ViewModelMaterial mViewModelMaterial;
    Boolean chooseProject;
    String nameOfWork;
    Long WorkId;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_materials);


        Bundle arguments = getIntent().getExtras();
        //if (arguments != null) {
        selectButton = arguments.get("selectButton").toString();
        nameOfWork = arguments.get("nameOfWork").toString();

        AppDatabase db = AppDelegate.getInstance().getDatabase();
        WorkDao workDao = db.workDao();
        MaterialDao materialDao = db.materialDao();

        WorkId = workDao.getIdByName("name");

        Log.e("WORKID", String.valueOf(WorkId));
     //   materialDao.getMaterialsForOwner(WorkId);

        /*Если addMaterial - то все кнопки сохраняются - если просмотр то убрать добавить в проект и   */

        listNameColumn = getResources().getStringArray(R.array.material_report);



        listItems = new ArrayList<>();
       // sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_project);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        StateAdapterMaterial.OnStateClickListener stateClickListener = new StateAdapterMaterial.OnStateClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onStateClick(Material project, int position) {

                Toast.makeText(getApplicationContext(), "Был выбран пункт " + project.getName(),
                        Toast.LENGTH_SHORT).show();

                Log.e("ChooseMaterial","AAAAAAAAAAAaChooseMaterial");
              /*
                Log.e("ChooseProject", String.valueOf(chooseProject));
                if (chooseProject){
                    Log.e("ChooseProject","ЗАШЕЛ В ИФ");
                    intent = new Intent(getApplicationContext(),CalculationOfWorkActivity.class);
                    intent.putExtra("idOfChooseProject",project.getId());
                    intent.putExtra("ProjectChoosed",true);
                    intent.putExtra("nameOfWork",nameOfWork);
                    startActivity(intent);

                }*/
            }
        };
        // создаем адаптер
        StateAdapterMaterial adapter = new StateAdapterMaterial(this, projects, stateClickListener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

     //   long userId = sPref.getLong(PREFERENCES_USER_ID,0);

        mViewModelMaterial = ViewModelProviders.of(this).get(ViewModelMaterial.class);

        mViewModelMaterial.getCurrentData(WorkId).observe(ListOfMaterialsActivity.this, new Observer<List<Material>>() {
            @Override
            public void onChanged(@Nullable List<Material> currentList) {
                projectList2 = currentList;
                adapter.setProjects(currentList);
            }
        });



    }

    public void back(View view) {
    }

    public void saveAs(View view) {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog, null);

        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);

        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);

        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);

        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            public void onClick(DialogInterface dialog, int id) {
                                //Вводим текст и отображаем в строке ввода на основном экране:
                              //  final_text.setText(userInput.getText());
                                String nameFile=userInput.getText().toString();
                                Log.e("FILENAME",nameFile);
                                createReport(nameFile);
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();

        //и отображаем его:
        alertDialog.show();






    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createReport(String nameFile) {

        String name = nameFile+getString(R.string.file_extension);
        Workbook wb=new HSSFWorkbook();
        Cell cell;
        CellStyle cellStyle=wb.createCellStyle();
        Sheet sheet;
        sheet = wb.createSheet(getString(R.string.report_about_materials));

        Row row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue(getString(R.string.report_about_materials)+getString(R.string.space)+getString(R.string.word_work_in_order) + nameOfWork);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));

        int lastRow=0;
        int i;
        int length =listNameColumn.length;
        long totalAllPrice=0;
        row =sheet.createRow(1);
        Log.e("ArrayNameColumn", String.valueOf(listNameColumn.length));
        for (i=0;i<length-1;i++){
            cell=row.createCell(i);
            cell.setCellValue(listNameColumn[i]);
            cell.setCellStyle(cellStyle);
        }

        for(int k=0;k<projectList2.size();k++){
            Material material = new Material();
            material = projectList2.get(k);

            row =sheet.createRow(k+2);

            cell=row.createCell(0);
            cell.setCellValue(material.getName());
            cell.setCellStyle(cellStyle);

            cell=row.createCell(1);
            cell.setCellValue(material.getQuantity());
            cell.setCellStyle(cellStyle);

            cell=row.createCell(2);
            cell.setCellValue(material.getPriceForOne());
            cell.setCellStyle(cellStyle);

            cell=row.createCell(3);

            Units unit = material.getUnits();
            String nameUnit = unit.name();

            cell.setCellValue(nameUnit);
            cell.setCellStyle(cellStyle);

            cell=row.createCell(4);
            cell.setCellValue(material.getTotalPrice());
            cell.setCellStyle(cellStyle);

            totalAllPrice=totalAllPrice+material.getTotalPrice();
            lastRow=k+2;
        }

        row =sheet.createRow(lastRow+1);
        cell=row.createCell(0);
        cell.setCellValue(listNameColumn[length-1]);
        cell.setCellStyle(cellStyle);

        cell=row.createCell(1);
        cell.setCellValue(totalAllPrice);
        cell.setCellStyle(cellStyle);
        //

       // sheet.autoSizeColumn(length);

/*
        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*200));
*/
        File file = new File(getExternalFilesDir(null),name);
        FileOutputStream outputStream =null;

        try {
            outputStream=new FileOutputStream(file);
            wb.write(outputStream);
            Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
        } catch (java.io.IOException e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(),"NO OK",Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
}