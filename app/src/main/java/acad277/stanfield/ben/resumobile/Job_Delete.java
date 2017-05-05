//Delete the job from the job array

package acad277.stanfield.ben.resumobile;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import acad277.stanfield.ben.resumobile.model.JobDetails;
import acad277.stanfield.ben.resumobile.model.jobModel;

public class Job_Delete extends AppCompatActivity {
    //intializing buttons
    Button save;
    Button goBack;
    ListView jobs;

    private ArrayList<JobDetails> arrayJob;
    private jobModel myJobModel;
    private JobAdapter jobAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job__delete);

        //buttons that will be used in this class
        save=(Button) findViewById(R.id.buttonSaveJobs);
        goBack=(Button)findViewById(R.id.buttonGoBack);
        jobs=(ListView)findViewById(R.id.list_jobs);

        myJobModel=jobModel.get(this);
        arrayJob = jobModel.get(this).getJobs();


        //initlaizes the list view array
        jobAdapter = new JobAdapter(arrayJob);
        jobs.setAdapter(jobAdapter);



        //deleting the row
        jobs.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                //this is row that user clicked
                arrayJob.remove(position);
                jobAdapter.notifyDataSetChanged();

            }
        });



//if no changes were made, maintain that. If changes were made save those too
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    //populates the job array with the exisitng jobs
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
