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
    private String _ID = "";
    private String projectname ="SLAB";
    private String _UID ="";
    private String formdate ="";
    private String formtype ="";
    private String user ="";
    private String mrNo="";
    private String StudyID="";
    private String childname="";
    private String mothername="";
    private String birthdate="";
    private String EnrolmentDate="";
    private String FollowupRound="";
    private String fuplocation="";
    private String DischargeDate="";
    private String fupstatus="";
    private String lastfupdate="";

    public String getFormdate() {
        return formdate;
    }

    public void setFormdate(String formdate) {
        this.formdate = formdate;
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFuplocation() {
        return fuplocation;
    }

    public void setFuplocation(String fuplocation) {
        this.fuplocation = fuplocation;
    }

    public String getFupstatus() {
        return fupstatus;
    }

    public void setFupstatus(String fupstatus) {
        this.fupstatus = fupstatus;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getDevicetagid() {
        return devicetagid;
    }

    public void setDevicetagid(String devicetagid) {
        this.devicetagid = devicetagid;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    private String istatus="";
    private String deviceid = "";
    private String devicetagid = "";
    private String appversion = "";
    private String synced = "";
    private String synced_date = "";
    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getLastfupdate() {
        return lastfupdate;
    }

    public void setLastfupdate(String lastfupdate) {
        this.lastfupdate = lastfupdate;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }


    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public FollowupListContract Sync(JSONObject jsonObject) throws JSONException {

        this._ID= jsonObject.getString(FollowUpList.COLUMN__ID);
        this._UID= jsonObject.getString(FollowUpList.COLUMN__UID);
        this.childname= jsonObject.getString(FollowUpList.COLUMN_CHILDNAME);
        this.mothername= jsonObject.getString(FollowUpList.COLUMN_MOTHERNAME);
        this.mrNo= jsonObject.getString(FollowUpList.COLUMN_MRNO);
        this.StudyID= jsonObject.getString(FollowUpList.COLUMN_STUDYID);
        this.DischargeDate= jsonObject.getString(FollowUpList.COLUMN_DISCHARGEDATE);
        this.fuplocation= jsonObject.getString(FollowUpList.COLUMN_FUPLOCATION);
        this.fupstatus= jsonObject.getString(FollowUpList.COLUMN_FUPSTATUS);
        this.EnrolmentDate= jsonObject.getString(FollowUpList.COLUMN_ENROLMENTDATE);
        this.FollowupRound= jsonObject.getString(FollowUpList.COLUMN_FOLLOWUPROUND);
        this.birthdate= jsonObject.getString(FollowUpList.COLUMN_BIRTHDATE);
        this.lastfupdate= jsonObject.getString(FollowUpList.COLUMN_LASTFUPDATE);

        return this;

    }
    public FollowupListContract Hydrate(Cursor cursor) {

        this._ID = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN__ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN__UID));
        this.childname = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_CHILDNAME));
        this.mothername = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_MOTHERNAME));
        this.mrNo = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_MRNO));
        this.StudyID = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_STUDYID));
        this.DischargeDate = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_DISCHARGEDATE));
        this.fuplocation = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_FUPLOCATION));
        this.fupstatus = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_FUPSTATUS));
        this.EnrolmentDate = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_ENROLMENTDATE));
        this.FollowupRound = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_FOLLOWUPROUND));
        this.birthdate = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_BIRTHDATE));
        this.lastfupdate = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_LASTFUPDATE));

        return this;
    }
    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(FollowUpList.COLUMN_CHILDNAME, this.childname == null ? JSONObject.NULL : this.childname);
        json.put(FollowUpList.COLUMN_MOTHERNAME, this.mothername == null ? JSONObject.NULL : this.mothername);
        json.put(FollowUpList.COLUMN_MRNO, this.mrNo == null ? JSONObject.NULL : this.mrNo);
        json.put(FollowUpList.COLUMN_STUDYID, this.StudyID == null ? JSONObject.NULL : this.StudyID);
        json.put(FollowUpList.COLUMN_DISCHARGEDATE, this.DischargeDate == null ? JSONObject.NULL : this.DischargeDate);
        json.put(FollowUpList.COLUMN_FUPLOCATION, this.fuplocation == null ? JSONObject.NULL : this.fuplocation);
        json.put(FollowUpList.COLUMN_FUPSTATUS, this.fupstatus == null ? JSONObject.NULL : this.fupstatus);
        json.put(FollowUpList.COLUMN_ENROLMENTDATE, this.EnrolmentDate == null ? JSONObject.NULL : this.EnrolmentDate);
        json.put(FollowUpList.COLUMN_FOLLOWUPROUND, this.FollowupRound == null ? JSONObject.NULL : this.FollowupRound);
        json.put(FollowUpList.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(FollowUpList.COLUMN__UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(FollowUpList.COLUMN_BIRTHDATE, this.birthdate == null ? JSONObject.NULL : this.birthdate);
        json.put(FollowUpList.COLUMN_LASTFUPDATE, this.lastfupdate == null ? JSONObject.NULL : this.lastfupdate);

        return json;
    }


    public String getFollowupRound() {
        return FollowupRound;
    }

    public void setFollowupRound(String followupRound) {
        FollowupRound = followupRound;
    }


    public FollowupListContract() {
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
    public static final String COLUMN__ID = "_id";
    public static final String COLUMN_PROJECT_NAME = "projectname";
    public static final String COLUMN__UID = "_uid";
    public static final String COLUMN_FORMDATE = "formdate";
    public static final String COLUMN_FORMTYPE = "formtype";
    public static final String COLUMN_USER = "user";
    public static final String COLUMN_MRNO = "mrno";
    public static final String COLUMN_STUDYID = "studyid";
    public static final String COLUMN_CHILDNAME = "childname";
    public static final String COLUMN_MOTHERNAME = "mothername";
    public static final String COLUMN_BIRTHDATE = "birthdate";
    public static final String COLUMN_ENROLMENTDATE = "enrolmentdate";
    public static final String COLUMN_FOLLOWUPROUND = "followupround";
    public static final String COLUMN_FUPLOCATION = "fuplocation";
    public static final String COLUMN_DISCHARGEDATE = "dischargedate";
    public static final String COLUMN_FUPSTATUS = "fupstatus";
    public static final String COLUMN_LASTFUPDATE = "lastfupdate";
    public static final String COLUMN_ISTATUS = "istatus";

    public static final String COLUMN_DEVICEID = "deviceid";
    public static final String COLUMN_DEVICETAGID = "devicetagid";
    public static final String COLUMN_SYNCED = "synced";
    public static final String COLUMN_APP_VERSION = "appversion";

    public static final String COLUMN_SYNCED_DATE = "synced_date";



    public static String _URL = "childlist.php";

}
}
