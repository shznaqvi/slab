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

   private String _ID ="";
    private String _fuid ="";
    private String mrno ="";
    private String studyid="";
    private String childname="";
    private String mothername="";
    private String birthdate="";
    private String enrolmentdate="";
    private String fupround="";
    private String fuplocation="";
    private String dischargedate="";
    private String fupstatus="";
    private String lastfupdate="";

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_fuid() {
        return _fuid;
    }

    public void set_fuid(String _fuid) {
        this._fuid = _fuid;
    }

    public String getMrno() {
        return mrno;
    }

    public void setMrno(String mrno) {
        this.mrno = mrno;
    }

    public String getStudyid() {
        return studyid;
    }

    public void setStudyid(String studyid) {
        this.studyid = studyid;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEnrolmentdate() {
        return enrolmentdate;
    }

    public void setEnrolmentdate(String enrolmentdate) {
        this.enrolmentdate = enrolmentdate;
    }

    public String getFupround() {
        return fupround;
    }

    public void setFupround(String fupround) {
        this.fupround = fupround;
    }

    public String getFuplocation() {
        return fuplocation;
    }

    public void setFuplocation(String fuplocation) {
        this.fuplocation = fuplocation;
    }

    public String getDischargedate() {
        return dischargedate;
    }

    public void setDischargedate(String dischargedate) {
        this.dischargedate = dischargedate;
    }

    public String getFupstatus() {
        return fupstatus;
    }

    public void setFupstatus(String fupstatus) {
        this.fupstatus = fupstatus;
    }

    public String getLastfupdate() {
        return lastfupdate;
    }

    public void setLastfupdate(String lastfupdate) {
        this.lastfupdate = lastfupdate;
    }

    public FollowupListContract Sync(JSONObject jsonObject) throws JSONException {
        this._fuid= jsonObject.getString(FollowUpList.COLUMN__FUID);
        this.mrno= jsonObject.getString(FollowUpList.COLUMN_MRNO);
        this.studyid= jsonObject.getString(FollowUpList.COLUMN_STUDYID);
        this.childname= jsonObject.getString(FollowUpList.COLUMN_CHILDNAME);
        this.mothername= jsonObject.getString(FollowUpList.COLUMN_MOTHERNAME);
        this.birthdate= jsonObject.getString(FollowUpList.COLUMN_BIRTHDATE);
        this.enrolmentdate= jsonObject.getString(FollowUpList.COLUMN_ENROLMENTDATE);
        this.fupround= jsonObject.getString(FollowUpList.COLUMN_FUPROUND);
        this.fuplocation= jsonObject.getString(FollowUpList.COLUMN_FUPLOCATION);
        this.dischargedate= jsonObject.getString(FollowUpList.COLUMN_DISCHARGEDATE);
        this.fupstatus= jsonObject.getString(FollowUpList.COLUMN_FUPSTATUS);
        this.lastfupdate= jsonObject.getString(FollowUpList.COLUMN_LASTFUPDATE);


        return this;

    }
    public FollowupListContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN__ID));
        this._fuid = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN__FUID));
        this.mrno = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_MRNO));
        this.studyid = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_STUDYID));
        this.childname = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_CHILDNAME));
        this.mothername = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_MOTHERNAME));
        this.birthdate = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_BIRTHDATE));
        this.enrolmentdate = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_ENROLMENTDATE));
        this.fupround = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_FUPROUND));
        this.fuplocation = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_FUPLOCATION));
        this.dischargedate = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_DISCHARGEDATE));
        this.fupstatus = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_FUPSTATUS));
        this.lastfupdate = cursor.getString(cursor.getColumnIndex(FollowUpList.COLUMN_LASTFUPDATE));



        return this;
    }
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(FollowUpList.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(FollowUpList.COLUMN__FUID, this._fuid == null ? JSONObject.NULL : this._fuid);
        json.put(FollowUpList.COLUMN_MRNO, this.mrno == null ? JSONObject.NULL : this.mrno);
        json.put(FollowUpList.COLUMN_STUDYID, this.studyid == null ? JSONObject.NULL : this.studyid);
        json.put(FollowUpList.COLUMN_CHILDNAME, this.childname == null ? JSONObject.NULL : this.childname);
        json.put(FollowUpList.COLUMN_MOTHERNAME, this.mothername == null ? JSONObject.NULL : this.mothername);
        json.put(FollowUpList.COLUMN_BIRTHDATE, this.birthdate == null ? JSONObject.NULL : this.birthdate);
        json.put(FollowUpList.COLUMN_ENROLMENTDATE, this.enrolmentdate == null ? JSONObject.NULL : this.enrolmentdate);
        json.put(FollowUpList.COLUMN_FUPROUND, this.fupround == null ? JSONObject.NULL : this.fupround);
        json.put(FollowUpList.COLUMN_FUPLOCATION, this.fuplocation == null ? JSONObject.NULL : this.fuplocation);
        json.put(FollowUpList.COLUMN_DISCHARGEDATE, this.dischargedate == null ? JSONObject.NULL : this.dischargedate);
        json.put(FollowUpList.COLUMN_FUPSTATUS, this.fupstatus == null ? JSONObject.NULL : this.fupstatus);
        json.put(FollowUpList.COLUMN_LASTFUPDATE, this.lastfupdate == null ? JSONObject.NULL : this.lastfupdate);



        return json;
    }




    public FollowupListContract() {
    }



public static abstract class FollowUpList implements BaseColumns{

    public static final String TABLE_NAME = "followuplist";
    public static final String COLUMN_NAME_NULLABLE = "NULLHACK";

    public static final String COLUMN__ID = "_id";
    public static final String COLUMN__FUID = "_fuid";
    public static final String COLUMN_MRNO = "mrno";
    public static final String COLUMN_STUDYID = "studyid";
    public static final String COLUMN_CHILDNAME = "childname";
    public static final String COLUMN_MOTHERNAME = "mothername";
    public static final String COLUMN_BIRTHDATE = "birthdate";
    public static final String COLUMN_ENROLMENTDATE = "enrolmentdate";
    public static final String COLUMN_FUPROUND = "fupround";
    public static final String COLUMN_FUPLOCATION = "fuplocation";
    public static final String COLUMN_DISCHARGEDATE = "dischargeDate";
    public static final String COLUMN_FUPSTATUS = "fupstatus";
    public static final String COLUMN_LASTFUPDATE = "lastfupdate";

    public static String _URL = "followuplist.php";

}
}
