package acad277.stanfield.ben.resumobile.model;

import android.content.Context;

import java.util.ArrayList;

//the singleton array for job arrays
//as it moves along the classes, make sures the class is not intialized

/**
 * Created by nayleenagda on 4/17/17.
 */

public class jobModel {

    private Context context;
    private ArrayList<JobDetails> jobs;
    private static jobModel testJobModel;

    private jobModel(Context context){
        this.context=context;
        jobs= new ArrayList<JobDetails>();

    }

    public static jobModel get (Context c){
        if(testJobModel==null){
            testJobModel= new jobModel(c.getApplicationContext());
        }
        return testJobModel;
    }

    public ArrayList<JobDetails> getJobs(){
        return jobs;
    }

    public JobDetails  getJobDetails(int position){
        return jobs.get(position);
    }

    public void addJobDetails(JobDetails jobDetail){
        jobs.add(jobDetail);

    }

    public void deleteJobDetails(int position){
        jobs.remove(position);
    }

}
