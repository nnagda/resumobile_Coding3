package acad277.stanfield.ben.resumobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;


import acad277.stanfield.ben.resumobile.model.coverLetterDetails;

public class Cover_Letter_Edit extends AppCompatActivity {

    Button save;
    Button clearText;
    EditText coverLetterText;
    public static final String COVER_LETTER="COVER_LETTER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover__letter__edit);

        save=(Button)findViewById(R.id.buttonSaveCoverLetter);
        clearText=(Button)findViewById(R.id.button_clearText);
        coverLetterText=(EditText)findViewById(R.id.EditText_CoverLetter);



        //Receiving the cover letter intent from LinkedIn_data
        Intent i= getIntent();
        final coverLetterDetails testCoverLetterDetails=(coverLetterDetails) i.getSerializableExtra(LinkedIn_data.COVER_DETAILS);
        coverLetterText.setText(testCoverLetterDetails.getCoverLetterText());






        //clears the already existing text incase the user wants to retype from scratch
        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coverLetterText.setText("");
            }
        });



        //saves the edited cover letter Text
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Cover_Letter_Edit.this,LinkedIn_data.class);
                i.putExtra(COVER_LETTER,(Serializable) testCoverLetterDetails);

                //sets the cover text in LinkedIn_data to the new edited cover text
                String test=coverLetterText.getText().toString();
                testCoverLetterDetails.setCoverLetterText(test);

                setResult(RESULT_OK,i);



                //Toast.makeText(getApplicationContext(),testCoverLetterDetails.getCoverLetterText(),Toast.LENGTH_SHORT).show();



                finish();
            }
        });
    }
}
