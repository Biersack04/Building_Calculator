package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.buildingcalculator.roomDataBase.AppDatabase;
import com.example.buildingcalculator.roomDataBase.AppDelegate;
import com.example.buildingcalculator.roomDataBase.Project;
import com.example.buildingcalculator.roomDataBase.ProjectDao;
import com.example.buildingcalculator.roomDataBase.Status;
import com.google.android.material.textview.MaterialTextView;

import static com.example.buildingcalculator.Constants.CREATED_STATUS;
import static com.example.buildingcalculator.Constants.DONE_STATUS;
import static com.example.buildingcalculator.Constants.IN_PROGRESS_STATUS;

public class ViewProjectActivity extends AppCompatActivity {

    MaterialTextView IntroducedTitle,IntroducedNumberOfWorks,IntroducedNumberOfMaterials,IntroducedCreateDate,IntroducedCustomer,
            IntroducedAddress,IntroducedStatus,IntroducedTotalPrice,IntroducedEndDate;
    private ViewModelProject mViewModelProject;
    long projectId;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        layoutElementsInit();

        Bundle arguments = getIntent().getExtras();
        if (arguments !=null)
        {
            projectId = arguments.getLong("idOfProject");
        }

        AppDatabase db = AppDelegate.getInstance().getDatabase();
        ProjectDao projectDao = db.projectDao();
        Project getProject = projectDao.getById(projectId);
        //  executor = executorDao.getById(userId);

        IntroducedTitle.setText(getProject.getName());
        IntroducedNumberOfWorks.setText(Long.toString(getProject.getNumberOfWorks()));
        IntroducedNumberOfMaterials.setText(Long.toString(getProject.getNumberOfMaterials()));
        IntroducedCreateDate.setText(Long.toString(getProject.getCreatedDate()));

       // IntroducedCustomer.setText();
        IntroducedAddress.setText(getProject.getProjectAddress());
      //  IntroducedStatus.setText();
        Status status = getProject.getStatus();
        String statusName = status.name();

       // IntroducedStatus.setText(statusName);
        switch(statusName) {
            case "CREATED":
                IntroducedStatus.setText(CREATED_STATUS);
                break;
            case "IN_PROGRESS":
                IntroducedStatus.setText(IN_PROGRESS_STATUS);
                break;
            case "DONE":
                IntroducedStatus.setText(DONE_STATUS);
                break;

            default:
                break;
        }

     //   IntroducedTotalPrice.setText();
        IntroducedEndDate.setText(Long.toString(getProject.getEndDate()));




/*
        mViewModel = ViewModelProviders.of(this).get(ViewModel.class);

        mViewModel.getCurrentProject(projectId).observe(ViewProjectActivity.this, new Observer<Project>() {
            @Override
            public void onChanged(@Nullable Project currentProject) {
                adapter.setProjects(currentProject);
            }
        });
*/
    }
    public void layoutElementsInit(){
        IntroducedTitle = findViewById(R.id.personal_title);
        IntroducedNumberOfWorks = findViewById(R.id.personal_number_of_works);
        IntroducedNumberOfMaterials = findViewById(R.id.personal_number_of_materials);
        IntroducedCreateDate = findViewById(R.id.personal_create_date);
        IntroducedCustomer = findViewById(R.id.personal_customer);
        IntroducedAddress = findViewById(R.id.personal_address);
        IntroducedStatus = findViewById(R.id.personal_status);
        IntroducedTotalPrice = findViewById(R.id.personal_total_price);
        IntroducedEndDate = findViewById(R.id.personal_end_date);


    }
    public void editProject(View view) {
    }

    public void createReport(View view) {
    }

    public void lookCustomerProfile(View view) {
    }
}