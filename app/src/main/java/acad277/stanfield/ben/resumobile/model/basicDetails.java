package acad277.stanfield.ben.resumobile.model;

import java.io.Serializable;

/**
 * Created by nayleenagda on 4/16/17.
 */

public class basicDetails implements Serializable {
    String name;
    String location;
    String education;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "basicDetails{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", education='" + education + '\'' +
                '}';
    }
}
