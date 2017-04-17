package acad277.stanfield.ben.resumobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Job_add extends AppCompatActivity {
    EditText companyName;
    EditText positionName;
    EditText positionDescription;

    Button save;
    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_add);

        companyName=(EditText) findViewById(R.id.editCompanyName);
        positionName=(EditText) findViewById(R.id.editPositionName);
        positionDescription=(EditText) findViewById(R.id.editPositionDescription);

        save=(Button) findViewById(R.id.buttonSaveJob);
        goBack=(Button)findViewById(R.id.buttonGoBack);

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


}
