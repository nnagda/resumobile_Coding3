package acad277.stanfield.ben.resumobile;

//adds a job

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;

import acad277.stanfield.ben.resumobile.model.JobDetails;
import acad277.stanfield.ben.resumobile.model.jobModel;

public class Job_add extends AppCompatActivity {
    EditText companyName;
    EditText positionName;
    EditText positionDescription;

    Button save;
    Button goBack;

    private ArrayList<JobDetails> arrayJob;
    private jobModel myJobModel;
    private JobAdapter jobAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_add);

        //details one will enter about their new job
        companyName=(EditText) findViewById(R.id.editCompanyName);
        positionName=(EditText) findViewById(R.id.editPositionName);
        positionDescription=(EditText) findViewById(R.id.editPositionDescription);

        save=(Button) findViewById(R.id.buttonSaveJob);
        goBack=(Button)findViewById(R.id.buttonGoBack);

        //loads already exisiting information about the Job details into the list view
        myJobModel=jobModel.get(this);
        arrayJob = jobModel.get(this).getJobs();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adding to the array // test values
                jobAdapter = new JobAdapter(arrayJob);
                JobDetails testJob= new JobDetails();


                testJob.setJobName(companyName.getText().toString());
                testJob.setPositionDescrption(positionDescription.getText().toString());
                testJob.setPositionName(positionName.getText().toString());

                //adds this new job to the job array
                arrayJob.add(testJob);


                finish();



            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    //populates the list view
    private class JobAdapter extends ArrayAdapter<JobDetails> {
        ArrayList<JobDetails> arrayJob= new ArrayList<>();

        public JobAdapter(ArrayList<JobDetails> arrayJob){
            super(getApplicationContext(), 0, arrayJob);
            this.arrayJob = arrayJob;

        }


    }


}
