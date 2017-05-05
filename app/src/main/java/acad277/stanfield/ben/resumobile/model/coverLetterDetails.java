package acad277.stanfield.ben.resumobile.model;

import java.io.Serializable;

/**
 * Created by nayleenagda on 4/16/17.
 */




public class coverLetterDetails implements Serializable {
    String coverLetterText;


    public String getCoverLetterText() {
        return coverLetterText;
    }

    public void setCoverLetterText(String coverLetterText) {
        this.coverLetterText = coverLetterText;
    }

    @Override
    public String toString() {
        return "coverLetterDetails{" +
                "coverLetterText='" + coverLetterText + '\'' +
                '}';
    }
}
