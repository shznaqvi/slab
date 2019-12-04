package edu.aku.hassannaqvi.slab.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ramsha.ahmed on 4/25/2018.
 */

public class ChildListContract {

    private String _ID = "";
    private String _ruid = "";
    private String mrNo = "";
    private String StudyID = "";
    private String childname = "";
    private String mothername = "";
    private String birthdate = "";
    private String EnrolmentDate = "";
    private String lastFupDT = "";

    public ChildListContract() {
    }

    public ChildListContract Sync(JSONObject jsonObject) throws JSONException {
        this._ruid = jsonObject.getString(ChildListTable.COLUMN__RUID);
        this.mrNo = jsonObject.getString(ChildListTable.COLUMN_MRNO);
        this.StudyID = jsonObject.getString(ChildListTable.COLUMN_STUDYID);
        this.childname = jsonObject.getString(ChildListTable.COLUMN_CHILDNAME);
        this.mothername = jsonObject.getString(ChildListTable.COLUMN_MOTHERNAME);
        this.birthdate = jsonObject.getString(ChildListTable.COLUMN_BIRTHDATE);
        this.EnrolmentDate = jsonObject.getString(ChildListTable.COLUMN_ENROLMENTDATE);
        this.lastFupDT = jsonObject.getString(ChildListTable.COLUMN_LAST_FUPDT);

        return this;
    }

    public ChildListContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(ChildListTable.COLUMN__ID));
        this._ruid = cursor.getString(cursor.getColumnIndex(ChildListTable.COLUMN__RUID));
        this.mrNo = cursor.getString(cursor.getColumnIndex(ChildListTable.COLUMN_MRNO));
        this.StudyID = cursor.getString(cursor.getColumnIndex(ChildListTable.COLUMN_STUDYID));
        this.childname = cursor.getString(cursor.getColumnIndex(ChildListTable.COLUMN_CHILDNAME));
        this.mothername = cursor.getString(cursor.getColumnIndex(ChildListTable.COLUMN_MOTHERNAME));
        this.birthdate = cursor.getString(cursor.getColumnIndex(ChildListTable.COLUMN_BIRTHDATE));
        this.EnrolmentDate = cursor.getString(cursor.getColumnIndex(ChildListTable.COLUMN_ENROLMENTDATE));
        this.lastFupDT = cursor.getString(cursor.getColumnIndex(ChildListTable.COLUMN_LAST_FUPDT));
        return this;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(ChildListTable.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(ChildListTable.COLUMN__RUID, this._ruid == null ? JSONObject.NULL : this._ruid);
        json.put(ChildListTable.COLUMN_MRNO, this.mrNo == null ? JSONObject.NULL : this.mrNo);
        json.put(ChildListTable.COLUMN_STUDYID, this.StudyID == null ? JSONObject.NULL : this.StudyID);
        json.put(ChildListTable.COLUMN_CHILDNAME, this.childname == null ? JSONObject.NULL : this.childname);
        json.put(ChildListTable.COLUMN_MOTHERNAME, this.mothername == null ? JSONObject.NULL : this.mothername);
        json.put(ChildListTable.COLUMN_BIRTHDATE, this.birthdate == null ? JSONObject.NULL : this.birthdate);
        json.put(ChildListTable.COLUMN_ENROLMENTDATE, this.EnrolmentDate == null ? JSONObject.NULL : this.EnrolmentDate);
        json.put(ChildListTable.COLUMN_LAST_FUPDT, this.lastFupDT == null ? JSONObject.NULL : this.lastFupDT);


        return json;
    }

    public String get_ruid() {
        return _ruid;
    }

    public void set_ruid(String _ruid) {
        this._ruid = _ruid;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
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

    public String getEnrolmentDate() {
        return EnrolmentDate;
    }

    public void setEnrolmentDate(String enrolmentDate) {
        EnrolmentDate = enrolmentDate;
    }

    public String getLastFupDT() {
        return lastFupDT;
    }

    public void setLastFupDT(String lastFupDT) {
        this.lastFupDT = lastFupDT;
    }

    public static abstract class ChildListTable implements BaseColumns {

        public static final String TABLE_NAME = "childlist";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN__ID = "_id";
        public static final String COLUMN__RUID = "ruid";
        public static final String COLUMN_LAST_FUPDT = "last_fupdate";
        public static final String COLUMN_MRNO = "mrno";
        public static final String COLUMN_STUDYID = "studyid";
        public static final String COLUMN_CHILDNAME = "childname";
        public static final String COLUMN_MOTHERNAME = "mothername";
        public static final String COLUMN_BIRTHDATE = "birthdate";
        public static final String COLUMN_ENROLMENTDATE = "enrolmentdate";


        public static String _URL = "childlist.php";

    }

}
