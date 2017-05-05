package acad277.stanfield.ben.resumobile.model;

import android.content.Context;

/**
 * Created by nayleenagda on 5/1/17.
 */

//the singleton array for cover letter
//as it moves along the classes, make sures the class is not intialized

public class coverLetterModel {
    private static coverLetterModel testCoverLetterModel;
    private String coverLetterText;
    private Context context;


    public String getCoverLetterText() {
        return coverLetterText;
    }

    public void setCoverLetterText(String coverLetterText) {
        this.coverLetterText = coverLetterText;
    }


    private coverLetterModel(Context context){
        this.context=context;


    }

    public static coverLetterModel get (Context c){
        if(testCoverLetterModel==null){
            testCoverLetterModel= new coverLetterModel(c);
        }
        return testCoverLetterModel;
    }




}




