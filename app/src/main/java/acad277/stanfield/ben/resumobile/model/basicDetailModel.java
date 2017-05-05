package acad277.stanfield.ben.resumobile.model;

import android.content.Context;

import java.util.ArrayList;
/**
 * Created by nayleenagda on 4/28/17.
 */

//the singleton array for job details
    //as it moves along the classes, make sures the class is not intialized

public class basicDetailModel {
    private Context context;
    private String name;

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getPublicProfile() {
        return publicProfile;
    }

    public void setPublicProfile(String publicProfile) {
        this.publicProfile = publicProfile;
    }

    private String headline;
    private String publicProfile;
    private String email;
    private String Country;


    private static basicDetailModel testBasicDetailModel;

    private basicDetailModel(Context context){
        this.context=context;
        name="";

    }

    public static basicDetailModel get (Context c){
        if(testBasicDetailModel==null){
            testBasicDetailModel= new basicDetailModel(c);
        }
        return testBasicDetailModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }



}

