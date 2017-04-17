package acad277.stanfield.ben.resumobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Job_Delete extends AppCompatActivity {
    Button save;
    Button goBack;
    ListView jobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job__delete);

        save=(Button) findViewById(R.id.buttonSaveJobs);
        goBack=(Button)findViewById(R.id.buttonGoBack);
        jobs=(ListView)findViewById(R.id.list_jobs);


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
