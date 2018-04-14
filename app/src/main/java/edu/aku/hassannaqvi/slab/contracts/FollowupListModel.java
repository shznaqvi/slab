package edu.aku.hassannaqvi.slab.contracts;

/**
 * Created by ramsha.ahmed on 4/14/2018.
 */

public class FollowupListModel {
    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getMrNo() {
        return mrNo;
    }

    public void setMrNo(String mrNo) {
        this.mrNo = mrNo;
    }

    public String getStudyID() {
        return StudyID;
    }

    public void setStudyID(String studyID) {
        StudyID = studyID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    String childname , mothername, mrNo, StudyID, Date;
}
