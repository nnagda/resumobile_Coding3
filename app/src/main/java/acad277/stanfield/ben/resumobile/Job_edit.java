package acad277.stanfield.ben.resumobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import acad277.stanfield.ben.resumobile.model.JobDetails;
import acad277.stanfield.ben.resumobile.model.jobModel;

public class Job_edit extends AppCompatActivity {



    Button addJob;
    Button deleteJob;
    Button save;
    public static final String JOB_DETAIL="JOB_DETAIL";

    ListView jobs;

    private ArrayList<JobDetails> arrayJob;
    private JobAdapter jobAdapter;
    private jobModel myJobModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_edit);

        //for the list view
        myJobModel=jobModel.get(this);
        arrayJob = jobModel.get(this).getJobs();

        addJob=(Button)findViewById(R.id.buttonAddJob);
        deleteJob=(Button)findViewById(R.id.buttonDeleteJob);
        save=(Button)findViewById(R.id.buttonSaveJob);
        jobs=(ListView) findViewById(R.id.ListView_jobs);

        jobAdapter = new JobAdapter(arrayJob);

        jobs.setAdapter(jobAdapter);
        //Adding to the array // test values
        JobDetails testJob= new JobDetails();

        arrayJob.add(testJob);



        testJob.setPositionDescrption("test3");
        testJob.setPositionDescrption("Being lazy3");
        testJob.setPositionName("Best student3");






        addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Job_add.class);
                startActivityForResult(i,5);
            }
        });

        deleteJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Job_Delete.class);
                startActivityForResult(i,6);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    private class JobAdapter extends ArrayAdapter<JobDetails> {
        ArrayList<JobDetails> arrayJob= new ArrayList<>();

        public JobAdapter(ArrayList<JobDetails> arrayJob){
            super(getApplicationContext(), 0, arrayJob);
            this.arrayJob = arrayJob;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.list_row_jobs,null);
            }

            JobDetails testJob = arrayJob.get(position);



            //references to each view within the list row
            TextView jobName = (TextView) convertView.findViewById(R.id.textView_jobName);
            TextView positionName = (TextView) convertView.findViewById(R.id.textView_position);
            TextView jobDescription  =(TextView) convertView.findViewById(R.id.textView_description);


            // loads the data from the object into the view
            jobName.setText(testJob.getJobName());
            positionName.setText(testJob.getPositionName());
            jobDescription.setText(testJob.getPositionDescrption());

            return convertView;
        }

    }
}


