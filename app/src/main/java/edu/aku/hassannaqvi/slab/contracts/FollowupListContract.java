package edu.aku.hassannaqvi.slab.contracts;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ramsha.ahmed on 4/14/2018.
 */

public class FollowupListContract {
    String childname;
    String mothername;
    String mrNo;
    String StudyID;
    String DischargeDate;
    String type;
    Drawable typeimg;
    String status;
    String EnrolmentDate;
    String FollowupRound;

    public FollowupListContract Sync(JSONObject jsonObject) throws JSONException {
        this.childname = jsonObject.getString(FollowUpList.COLUMN_CHILDNAME);
        this.mothername = jsonObject.getString(FollowUpList.COLUMN_MOTHERNAME);
        this.mrNo = jsonObject.getString(FollowUpList.COLUMN_MRNO);
        this.StudyID = jsonObject.getString(FollowUpList.COLUMN_STUDYID);
        this.DischargeDate = jsonObject.getString(FollowUpList.COLUMN_DISCHARGEDATE);
        this.type = jsonObject.getString(FollowUpList.COLUMN_TYPE);
        this.status = jsonObject.getString(FollowUpList.COLUMN_STATUS);
        this.FollowupRound = jsonObject.getString(FollowUpList.COLUMN_FOLLOWUP_ROUND);
        return this;
    }
    public FollowupListContract Hydrate(Cursor cursor) {
        this.childname = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_CHILDNAME));
        this.mothername = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_MOTHERNAME));
        this.mrNo = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_MRNO));
        this.StudyID = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_STUDYID));
        this.DischargeDate = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_DISCHARGEDATE));
        this.type = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_TYPE));
        this.status = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_STATUS));
        this.FollowupRound = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_FOLLOWUP_ROUND));
        return this;
    }
    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(FollowUpList.COLUMN_CHILDNAME, this.childname == null ? JSONObject.NULL : this.childname);
        json.put(FollowUpList.COLUMN_MOTHERNAME, this.mothername == null ? JSONObject.NULL : this.mothername);
        json.put(FollowUpList.COLUMN_MRNO, this.mrNo == null ? JSONObject.NULL : this.mrNo);
        json.put(FollowUpList.COLUMN_STUDYID, this.StudyID == null ? JSONObject.NULL : this.StudyID);
        json.put(FollowUpList.COLUMN_DISCHARGEDATE, this.DischargeDate == null ? JSONObject.NULL : this.DischargeDate);
        json.put(FollowUpList.COLUMN_TYPE, this.type == null ? JSONObject.NULL : this.type);
        json.put(FollowUpList.COLUMN_STATUS, this.status == null ? JSONObject.NULL : this.status);
        json.put(FollowUpList.COLUMN_FOLLOWUP_ROUND, this.FollowupRound == null ? JSONObject.NULL : this.FollowupRound);


        return json;
    }


    public String getFollowupRound() {
        return FollowupRound;
    }

    public void setFollowupRound(String followupRound) {
        FollowupRound = followupRound;
    }

    public Drawable getTypeimg() {
        return typeimg;
    }

    public void setTypeimg(Drawable typeimg) {
        this.typeimg = typeimg;
    }

    public FollowupListContract() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getDischargeDate() {
        return DischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        DischargeDate = dischargeDate;
    }

    public String getEnrolmentDate() {
        return EnrolmentDate;
    }

    public void setEnrolmentDate(String enrolmentDate) {
        EnrolmentDate = enrolmentDate;
    }

public static abstract class FollowUpList implements BaseColumns{

    public static final String TABLE_NAME = "followuplist";
    public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
    public static final String COLUMN_PROJECT_NAME = "projectname";
    public static final String COLUMN_UID = "uid";
    public static final String COLUMN_FORMDATE = "formdate";
    public static final String COLUMN_USER = "user";
    public static final String COLUMN_FORMTYPE = "formtype";
    public static final String COLUMN_ISTATUS = "istatus";
    public static final String COLUMN_GPSLAT = "gpslat";
    public static final String COLUMN_GPSLNG = "gpslng";
    public static final String COLUMN_GPSDATE = "gpsdate";
    public static final String COLUMN_GPSACC = "gpsacc";
    public static final String COLUMN_DEVICEID = "deviceid";
    public static final String COLUMN_DEVICETAGID = "tagid";
    public static final String COLUMN_END_TIME = "endtime";
    public static final String COLUMN_SYNCED = "synced";
    public static final String COLUMN_SYNCED_DATE = "synced_date";
    public static final String COLUMN_APP_VERSION = "appversion";




    public static final String COLUMN_CHILDNAME = "childname";
    public static final String COLUMN_MOTHERNAME = "mothername";
    public static final String COLUMN_MRNO = "mrno";
    public static final String COLUMN_STUDYID = "studyid";
    public static final String COLUMN_DISCHARGEDATE = "dischargedate";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_FOLLOWUP_ROUND = "fround";

}


}
