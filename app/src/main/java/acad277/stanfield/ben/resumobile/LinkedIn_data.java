package acad277.stanfield.ben.resumobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.DeepLinkHelper;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIDeepLinkError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.DeepLinkListener;

import org.json.JSONArray;
import org.json.JSONObject;

import acad277.stanfield.ben.resumobile.model.JobDetails;
import acad277.stanfield.ben.resumobile.model.basicDetailModel;
import acad277.stanfield.ben.resumobile.model.basicDetails;
import acad277.stanfield.ben.resumobile.model.coverLetterDetails;
import acad277.stanfield.ben.resumobile.model.coverLetterModel;
import acad277.stanfield.ben.resumobile.model.jobModel;


public class LinkedIn_data extends AppCompatActivity {

    public static final String COVER_DETAILS=Cover_Letter_Edit.COVER_LETTER;
    private static final String host = "api.linkedin.com";
    private ProgressDialog progress;
    private static final String url =
            "https://" + host + "/v1/people/~:" +
                    "(email-address,formatted-name,positions)";


    Button editCoverLetter;
    Button editJob;
    Button next;
    TextView name;
    TextView country;
    TextView education;
    TextView coverLetter;
    ListView jobs;
    TextView TextView_Name;
    TextView TextView_Email;

    private ArrayList<JobDetails> arrayJob;
    private JobAdapter jobAdapter;
    private jobModel myJobModel;

    coverLetterDetails testCoverLetterDetails= new coverLetterDetails();
    basicDetails testBasicDetails= new basicDetails();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progress= new ProgressDialog(this);
        progress.setMessage("Retrieving data from LinkedIn...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        linkededinApiHelper();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_in_data);


        myJobModel=jobModel.get(this);

        //Setting up the list view
        arrayJob = jobModel.get(this).getJobs();


        editCoverLetter=(Button)findViewById(R.id.buttonEditCoverLetter);
        editJob=(Button)findViewById(R.id.buttonEditJobs);
        next=(Button)findViewById(R.id.buttonNext);


        //API will fill these out
        name=(TextView)findViewById(R.id.TextView_Name);
        country=(TextView)findViewById(R.id.TextView_Country);
        coverLetter=(TextView)findViewById(R.id.TextView_coverLetter);
        TextView_Name=(TextView)findViewById(R.id.TextView_Name);
        TextView_Email=(TextView)findViewById(R.id.TextView_Email);

        jobs=(ListView) findViewById(R.id.ListView_jobs);


        //Adding to the array // test values
        Intent i= new Intent(getApplicationContext(), Job_edit.class);

        Intent ia= new Intent(getApplicationContext(),Cover_Letter_Edit.class);
        ia.putExtra(COVER_DETAILS,testCoverLetterDetails);

        String coverLetterString=(String)coverLetter.getText();
        testCoverLetterDetails.setCoverLetterText(coverLetterString);

        jobAdapter = new JobAdapter(arrayJob);
        jobs.setAdapter(jobAdapter);




        //Set  LinkedIn text.
        //Currently setting dummy text
        coverLetter.setText("Sample cover letter text.");




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

    public void linkededinApiHelper(){
        Log.d("URL is ", "1");
        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
        Log.d("URL is ", "2");
        apiHelper.getRequest(LinkedIn_data.this, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse result) {
                Log.d("URL is ", "3");
                try {
                    showResult(result.getResponseDataAsJson());
                    progress.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onApiError(LIApiError error) {
                Log.d("error is ", error.toString());

            }
        });
    }

    public  void  showResult(JSONObject response){

        try {
            TextView_Email.setText(response.get("emailAddress").toString());
            testBasicDetails.setEmail(response.get("emailAddress").toString());
            basicDetailModel.get(getApplicationContext()).setEmail(response.get("emailAddress").toString());



            TextView_Name.setText(response.get("formattedName").toString());
            testBasicDetails.setName(response.get("formattedName").toString());
            basicDetailModel.get(getApplicationContext()).setName(response.get("formattedName").toString());



//            country.setText(response.get("positions").toString());
            String positionString =response.get("positions").toString();
            JSONObject positionJSON = new JSONObject(positionString);
            JSONArray valuesArray = positionJSON.getJSONArray("values");
            for (int i = 0; i < valuesArray.length(); i++) {
                JSONObject firstComp = (JSONObject)valuesArray.get(i);
                JSONObject comp = firstComp.getJSONObject("company");
                JSONObject date = firstComp.getJSONObject("startDate");

//                JSONObject summary = firstComp.getJSONObject("summary");
                String companyName = comp.getString("name");
                String startDate = date.getString("year");
                Log.d("the company name is ", companyName);
//                Log.d("the company summary is ", summary.toString());
                JobDetails testJob= new JobDetails();
                testJob.setJobName(companyName);
                testJob.setPositionName(startDate);
                testJob.setPositionDescrption("Sample job description");
                arrayJob.add(testJob);
            }


            String values = positionJSON.getString("values");


            Log.d("the summary is ", values);
//            testBasicDetails.setName(response.get("numConnections").toString());

//            Picasso.with(this).load(response.getString("pictureUrl"))
//                    .into(profile_picture);

        } catch (Exception e){
            e.printStackTrace();
        }
    }





    private class JobAdapter extends ArrayAdapter<JobDetails>{
        //ArrayList<JobDetails> arrayJob= new ArrayList<>();

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








    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                testCoverLetterDetails=(coverLetterDetails) data.getSerializableExtra(COVER_DETAILS);
                testCoverLetterDetails.setCoverLetterText(testCoverLetterDetails.getCoverLetterText());
                coverLetter.setText(testCoverLetterDetails.getCoverLetterText());

                coverLetterModel.get(getApplicationContext()).setCoverLetterText(testCoverLetterDetails.getCoverLetterText());




            }

        }

        else if(requestCode==2){
            jobAdapter.notifyDataSetChanged();
        }


    }
}
