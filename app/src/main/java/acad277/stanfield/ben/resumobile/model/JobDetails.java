package acad277.stanfield.ben.resumobile.model;

import java.util.ArrayList;

/**
 * Created by nayleenagda on 4/16/17.
 */

public class JobDetails {
    String jobName;
    String positionName;
    String positionDescrption;
    int startYear;
    int endYear;




    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionDescrption() {
        return positionDescrption;
    }

    public void setPositionDescrption(String positionDescrption) {
        this.positionDescrption = positionDescrption;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @Override
    public String toString() {
        return "JobDetails{" +
                "jobName='" + jobName + '\'' +
                ", positionName='" + positionName + '\'' +
                ", positionDescrption='" + positionDescrption + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                '}';
    }

}
