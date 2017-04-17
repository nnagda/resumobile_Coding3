package acad277.stanfield.ben.resumobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Preferences extends AppCompatActivity {

    Button next;
    Button deletePic;
    Button galleryPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        next=(Button)findViewById(R.id.button_next);
        deletePic=(Button)findViewById(R.id.Button_delete_Pic);
        galleryPic=(Button)findViewById(R.id.Button_galleryPic);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Resume_view.class);
                startActivityForResult(i,8);
            }
        });
    }
}
