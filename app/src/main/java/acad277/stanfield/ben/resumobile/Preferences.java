package acad277.stanfield.ben.resumobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import acad277.stanfield.ben.resumobile.model.basicDetailModel;
import acad277.stanfield.ben.resumobile.model.basicDetails;
import acad277.stanfield.ben.resumobile.model.jobModel;

import static android.R.attr.baseline;
import static android.R.attr.bitmap;
import static android.R.attr.data;


public class Preferences extends AppCompatActivity {

    Button next;
    Button deletePic;
    Button galleryPic;
    private int PICK_IMAGE_REQUEST = 1;
    File album;
    ImageView imageView;


    private basicDetailModel testBasicDetailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        next=(Button)findViewById(R.id.button_next);
        deletePic=(Button)findViewById(R.id.Button_delete_Pic);
        galleryPic=(Button)findViewById(R.id.Button_galleryPic);
        imageView = (ImageView) findViewById(R.id.imageView_selectPicture);

        testBasicDetailModel= basicDetailModel.get(this);





        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),Resume_view.class);
                startActivityForResult(i,8);



            }
        });

        //album= new File("/storage/emulated/0/DCIM/Camera/IMG_20170428_134625.jpg");

        galleryPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

        SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(this);
        String previouslyEncodedImage = shre.getString("image_data", "");

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            imageView.setImageBitmap(bitmap);
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);


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
