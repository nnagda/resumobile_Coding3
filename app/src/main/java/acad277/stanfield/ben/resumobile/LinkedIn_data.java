//Shows the data gathered from the LinkedIn app

package acad277.stanfield.ben.resumobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
    //details that we would like from LinkedIn
    private static final String url =
            "https://" + host + "/v1/people/~:" +
                    "(email-address,formatted-name,positions,location:(name),public-profile-url,headline,summary)";


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
    TextView publicProfile;
    TextView headline;


    private ArrayList<JobDetails> arrayJob;
    private JobAdapter jobAdapter;
    private jobModel myJobModel;
    private coverLetterModel testCoverLetterModel;

    coverLetterDetails testCoverLetterDetails= new coverLetterDetails();
    basicDetails testBasicDetails= new basicDetails();



    //progress message as data from linkedIn loads in
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


        //Relationships between the code and the widgets
        name=(TextView)findViewById(R.id.TextView_Name);
        country=(TextView)findViewById(R.id.TextView_Country);
        coverLetter=(TextView)findViewById(R.id.TextView_coverLetter);
        TextView_Name=(TextView)findViewById(R.id.TextView_Name);
        TextView_Email=(TextView)findViewById(R.id.TextView_Email);
        publicProfile= (TextView)findViewById(R.id.TextView_publicProfile);
        headline=(TextView)findViewById(R.id.TextView_Headline);

        jobs=(ListView) findViewById(R.id.ListView_jobs);


        //Adding to the array // test values
        Intent i= new Intent(getApplicationContext(), Job_edit.class);

        //Intent to send information from the cover lette calss
        Intent ia= new Intent(getApplicationContext(),Cover_Letter_Edit.class);
        ia.putExtra(COVER_DETAILS,testCoverLetterDetails);

        //sets the cover letter text to the Cover Letter Details test
        String coverLetterString=(String)coverLetter.getText();
        testCoverLetterDetails.setCoverLetterText(coverLetterString);


        jobAdapter = new JobAdapter(arrayJob);
        jobs.setAdapter(jobAdapter);

        testCoverLetterModel= coverLetterModel.get(this);


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

        //sents it to the edit job class
        editJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Job_edit.class);
                startActivityForResult(i,2);
            }

        });

        //sends it to the next class
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Preferences.class);
                startActivityForResult(i,3);
            }

        });

    }

    //Linkedin Json data
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
            Log.d("json is ", response.toString());

            //get data from Json
            //sets it to the singleton array results

            //LinedIn profile link
            publicProfile.setText(response.get("publicProfileUrl").toString());
            basicDetailModel.get(getApplicationContext()).setPublicProfile(response.get("publicProfileUrl").toString());

            //headline
            headline.setText(response.get("headline").toString());
            basicDetailModel.get(getApplicationContext()).setHeadline(response.get("headline").toString());

            //nested json value : Location
            JSONObject locationObject= response.getJSONObject("location");
            String locationString = locationObject.getString("name");

            Log.d("location is: ", locationString);
            country.setText(locationString);

            basicDetailModel.get(getApplicationContext()).setCountry(locationString);

            //cover letter
            testCoverLetterDetails.setCoverLetterText(response.get("summary").toString());
            testCoverLetterModel.setCoverLetterText(response.get("summary").toString());
            coverLetter.setText(response.get("summary").toString());


            //email
            testBasicDetails.setEmail(response.get("emailAddress").toString());
            basicDetailModel.get(getApplicationContext()).setEmail(response.get("emailAddress").toString());

            //formatted name
            TextView_Name.setText(response.get("formattedName").toString());
            testBasicDetails.setName(response.get("formattedName").toString());
            basicDetailModel.get(getApplicationContext()).setName(response.get("formattedName").toString());



//           populates the list view and the singleton Job model
            String positionString =response.get("positions").toString();
            JSONObject positionJSON = new JSONObject(positionString);
            JSONArray valuesArray = positionJSON.getJSONArray("values");
            for (int i = 0; i < valuesArray.length(); i++) {
                JSONObject firstComp = (JSONObject)valuesArray.get(i);
                JSONObject comp = firstComp.getJSONObject("company");
                JSONObject date = firstComp.getJSONObject("startDate");

                //summary
                String companySummary = firstComp.getString("summary");

                String companyName = comp.getString("name");
                String startDate = date.getString("year");
                JobDetails testJob= new JobDetails();
                testJob.setJobName(companyName);
                testJob.setPositionName(startDate);
                testJob.setPositionDescrption(companySummary);
                 arrayJob.add(testJob);
            }


            String values = positionJSON.getString("values");

//updates the ilst view
            Log.d("the summary is ", values);
            jobAdapter.notifyDataSetChanged();


        } catch (Exception e){
            e.printStackTrace();
        }
    }




//loads data from the list view of Job array
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
                jobAdapter.notifyDataSetChanged();

            }

        }

        else if(requestCode==2){
            jobAdapter.notifyDataSetChanged();
        }


    }
}
