//Name: Naylee Nagda and Ben Stanfield
//Resumobile
//Due date: May 5th
//This our our Main Activity Log in page

//It establishes connection to LinkedIn


package acad277.stanfield.ben.resumobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.DeepLinkHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import org.json.JSONObject;
import java.util.ArrayList;
import acad277.stanfield.ben.resumobile.model.JobDetails;
import acad277.stanfield.ben.resumobile.model.basicDetailModel;
import acad277.stanfield.ben.resumobile.model.basicDetails;
import acad277.stanfield.ben.resumobile.model.jobModel;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button Override;
    private String testName;
    private basicDetailModel testBasicDetailModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        login=(Button)findViewById(R.id.buttonSignIn);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//Allows us to log into LinkedIn

                LISessionManager.getInstance(getApplicationContext()).init(MainActivity.this, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        Toast.makeText(getApplicationContext(),"You are logged in!",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onAuthError(LIAuthError error) {
                        Toast.makeText(getApplicationContext(),"You need to log in!",Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(getApplicationContext(),LinkedIn_data.class);
                        startActivityForResult(i,0);
                    }
                }, true);


//Allows us to get user information about the person who is logged in using a hash value

                String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name)";

                APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
                apiHelper.getRequest(MainActivity.this, url, new ApiListener() {


                    @Override
                    public void onApiSuccess(ApiResponse apiResponse) {
                        Intent i= new Intent(getApplicationContext(),LinkedIn_data.class);
                        startActivityForResult(i,2);

                    }

                    @Override
                    public void onApiError(LIApiError liApiError) {

                    }


                });

            }
        });


    }


    private static Scope buildScope() {
        //return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS);

    }

    //assigns us a session manager as we are logged into LinkedIn

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);
        Intent intent = new Intent(MainActivity.this, LinkedIn_data.class);
        startActivity(intent);

    }


    }



