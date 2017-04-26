package acad277.stanfield.ben.resumobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;

public class MainActivity extends AppCompatActivity {
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.buttonSignIn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LISessionManager.getInstance(getApplicationContext()).init(MainActivity.this, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        Toast.makeText(getApplicationContext(),"You need to log in!",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onAuthError(LIAuthError error) {
                        Toast.makeText(getApplicationContext(),"You are logged in!",Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(getApplicationContext(),LinkedIn_data.class);
                        startActivityForResult(i,0);
                    }
                }, true);


                String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name)";

                APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
                apiHelper.getRequest(MainActivity.this, url, new ApiListener() {
                    @Override
                    public void onApiSuccess(ApiResponse apiResponse) {
                        Toast.makeText(getApplicationContext(),"Ysss!",Toast.LENGTH_SHORT).show();

                    }


                    @Override
                    public void onApiError(LIApiError liApiError) {
                        Toast.makeText(getApplicationContext(),"Ysss!",Toast.LENGTH_SHORT).show();
                    }
                });






            }
        });






    }
    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing onActivityResult() method
        LISessionManager.getInstance(getApplicationContext()).onActivityResult(this, requestCode, resultCode, data);




    }




    }



