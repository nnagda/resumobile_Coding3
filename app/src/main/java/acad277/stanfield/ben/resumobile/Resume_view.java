package acad277.stanfield.ben.resumobile;
//Final view of the resume

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    TextView publicProfile;
    TextView headline;
    ImageView imageView;

    private int PICK_IMAGE_REQUEST = 1;



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
        publicProfile= (TextView)findViewById(R.id.TextView_publicProfile);
        headline=(TextView)findViewById(R.id.TextView_Headline);

        imageView=(ImageView)findViewById(R.id.imageView_selectPicture);

        //Intializing singelton arrays
        jobAdapter = new JobAdapter(arrayJob);
        jobs.setAdapter(jobAdapter);
        testBasicDetailModel= basicDetailModel.get(this);
        testCoverLetterModel=coverLetterModel.get(this);

        //Sets the fields to the Linkd In API data
        name.setText(testBasicDetailModel.getName());
        country.setText(testBasicDetailModel.getCountry());
        headline.setText(testBasicDetailModel.getHeadline());
        publicProfile.setText(testBasicDetailModel.getPublicProfile());
        coverLetter.setText(testCoverLetterModel.getCoverLetterText());


        //saves image the user selected
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);



        //loads it from shared preferences
        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
        String previouslyEncodedImage = shre.getString("image_data", "");

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            imageView.setImageBitmap(bitmap);
        }


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Resume_view.this,MainActivity.class);
                startActivity(in);
                finish();

            }
        });





    }

    //populates the list view with the job array
    private class JobAdapter extends ArrayAdapter<JobDetails>{


        public JobAdapter(ArrayList<JobDetails> arrayJob){
            super(getApplicationContext(), 0, arrayJob);
            //this.arrayJob = arrayJob;

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

    //gets the image from shared perferences
    //loads it into image view
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {


            Uri uri = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);


                //gets the image from shares preferences
                imageView.setImageBitmap(bitmap);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();

                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                //textEncode.setText(encodedImage);

                SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor edit=shre.edit();
                edit.putString("image_data",encodedImage);
                edit.commit();





            } catch (IOException e) {
                Log.d("there is an exception","3");

                e.printStackTrace();
            }
        }


    }
}
