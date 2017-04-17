package acad277.stanfield.ben.resumobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

import acad277.stanfield.ben.resumobile.model.JobDetails;
import acad277.stanfield.ben.resumobile.model.coverLetterDetails;


public class LinkedIn_data extends AppCompatActivity {

    public static final String COVER_DETAILS=Cover_Letter_Edit.COVER_LETTER;
    public static final String JOB_DETAIL=Job_edit.JOB_DETAIL;

    Button editCoverLetter;
    Button editJob;
    Button next;
    TextView name;
    TextView country;
    TextView education;
    TextView coverLetter;
    ListView jobs;

    private ArrayList<JobDetails> arrayJob;
    private JobAdapter jobAdapter;






    coverLetterDetails testCoverLetterDetails= new coverLetterDetails();
    JobDetails testJob= new JobDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_in_data);

        //Setting up the list view
        arrayJob = new ArrayList<>();





        editCoverLetter=(Button)findViewById(R.id.buttonEditCoverLetter);
        editJob=(Button)findViewById(R.id.buttonEditJobs);
        next=(Button)findViewById(R.id.buttonNext);


        //API will fill these out
        name=(TextView)findViewById(R.id.TextView_Name);
        country=(TextView)findViewById(R.id.TextView_Country);
        education=(TextView)findViewById(R.id.TextView_Education);
        coverLetter=(TextView)findViewById(R.id.TextView_coverLetter);

        jobs=(ListView) findViewById(R.id.ListView_jobs);


        //Adding to the array // test values
        Intent i= new Intent(getApplicationContext(), Job_edit.class);

        testJob.setJobName("Student");
        testJob.setPositionDescrption("Being lazy");
        testJob.setPositionName("Best student");

        i.putExtra(JOB_DETAIL,testJob);
        //String jobString=(String)jobs.ge();




        //how to put list view in intent




        Intent i= new Intent(getApplicationContext(),Cover_Letter_Edit.class);
        i.putExtra(COVER_DETAILS,testCoverLetterDetails);

        String coverLetterString=(String)coverLetter.getText();
        testCoverLetterDetails.setCoverLetterText(coverLetterString);

        startActivityForResult(i,1);








        jobAdapter = new JobAdapter(arrayJob);
        jobs.setAdapter(jobAdapter);
        arrayJob.add(testJob);
        //Toast.makeText(getApplicationContext(), "Length is" + arrayJob.size(), Toast.LENGTH_SHORT).show();



        //Set  LinkedIn text.
        //Currently setting dummy text
        coverLetter.setText("TEST");



        // sends an Intent with the cover letter Information to the Cover_Letter_Edit class
        editCoverLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Cover_Letter_Edit.class);
                i.putExtra(COVER_DETAILS,testCoverLetterDetails);

                String coverLetterString=(String)coverLetter.getText();
                testCoverLetterDetails.setCoverLetterText(coverLetterString);

                startActivityForResult(i,1);
            }

        });


        editJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Job_edit.class);
                startActivityForResult(i,2);
            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Preferences.class);
                startActivityForResult(i,3);
            }

        });

    }

    private class JobAdapter extends ArrayAdapter<JobDetails>{
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








    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                testCoverLetterDetails=(coverLetterDetails) data.getSerializableExtra(COVER_DETAILS);
                testCoverLetterDetails.setCoverLetterText(testCoverLetterDetails.getCoverLetterText());
                coverLetter.setText(testCoverLetterDetails.getCoverLetterText());

            }
        }


    }
}
