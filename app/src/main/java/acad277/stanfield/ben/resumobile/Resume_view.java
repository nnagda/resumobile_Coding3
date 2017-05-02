package acad277.stanfield.ben.resumobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import acad277.stanfield.ben.resumobile.model.JobDetails;
import acad277.stanfield.ben.resumobile.model.basicDetailModel;
import acad277.stanfield.ben.resumobile.model.coverLetterModel;
import acad277.stanfield.ben.resumobile.model.jobModel;

public class Resume_view extends AppCompatActivity {


    Button logout;
    TextView name;
    TextView country;
    TextView education;
    TextView coverLetter;
    ListView jobs;
    TextView TextView_Name;
    TextView TextView_Email;
    TextView email;

    ImageView imageView;



    private ArrayList<JobDetails> arrayJob;
    private JobAdapter jobAdapter;
    private jobModel myJobModel;

    private basicDetailModel testBasicDetailModel;
    private coverLetterModel testCoverLetterModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_view);

        myJobModel=jobModel.get(this);

        //Setting up the list view
        arrayJob = jobModel.get(this).getJobs();






        logout=(Button)findViewById(R.id.buttonLogout);

        //API will fill these out
        name=(TextView)findViewById(R.id.TextView_Name);
        email=(TextView)findViewById(R.id.TextView_Email);
        country=(TextView)findViewById(R.id.TextView_Country);
        coverLetter=(TextView)findViewById(R.id.TextView_coverLetter);
        TextView_Name=(TextView)findViewById(R.id.TextView_Name);
        TextView_Email=(TextView)findViewById(R.id.TextView_Email);
        jobs=(ListView) findViewById(R.id.ListView_jobs);

        imageView=(ImageView)findViewById(R.id.imageView_selectPicture);

        jobAdapter = new JobAdapter(arrayJob);
        jobs.setAdapter(jobAdapter);


        testBasicDetailModel= basicDetailModel.get(this);
        testCoverLetterModel=coverLetterModel.get(this);

        //Sets the fields to the API data
        name.setText(testBasicDetailModel.getName());
        email.setText(testBasicDetailModel.getEmail());
        country.setText(testBasicDetailModel.getCountry());
        coverLetter.setText(testCoverLetterModel.getCoverLetterText());


//        Intent intent = getIntent();
//        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");

//        if(bitmap == null)
//            Log.d("..", "bitmap  null");

//        Toast.makeText(getApplicationContext(),"test:"+bitmap,Toast.LENGTH_SHORT).show();
//        Log.d("test",bitmap.toString());


        if(getIntent().hasExtra("BitmapImage")) {
            imageView = new ImageView(this);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(
//                    getIntent().getByteArrayExtra("BitmapImage"),0,getIntent()
//                            .getByteArrayExtra("BitmapImage").length);
            //imageView.setImageBitmap(bitmap);
            //String bitmap= getIntent().getStringExtra("BitmapImage");
            //Toast.makeText(getApplicationContext(),"test:"+bitmap.toString(),Toast.LENGTH_SHORT).show();
        }







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
