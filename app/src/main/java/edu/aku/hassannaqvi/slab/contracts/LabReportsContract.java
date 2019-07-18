package edu.aku.hassannaqvi.slab.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class LabReportsContract {

    private String projectName = "SLAB 2019";
    private String _ID= "";
    private String _UID= "";
    private String formDate= "";
    private String user= "";
    private String studyid= "";
    private String mrno= "";
    private String reportdate= "";
    private String reporttime = "";
    private String cbc= "";
    private String crp= "";
    private String blood= "";
    private String deviceID= "";
    private String devicetagID= "";
    private String synced= "";
    private String synced_date= "";
    private String appVersion= "";

    public LabReportsContract() {
    }

    public LabReportsContract Sync(JSONObject jsonObject) throws JSONException {
        this.projectName= jsonObject.getString(LabReportsTable.COLUMN_PROJECTNAME);
        this._ID = jsonObject.getString(LabReportsTable.COLUMN__ID );
        this._UID = jsonObject.getString(LabReportsTable.COLUMN__UID );
        this.formDate= jsonObject.getString(LabReportsTable.COLUMN_FORMDATE);
        this.user= jsonObject.getString(LabReportsTable.COLUMN_USER);
        this.studyid= jsonObject.getString(LabReportsTable.COLUMN_STUDYID);
        this.mrno= jsonObject.getString(LabReportsTable.COLUMN_MRNO);
        this.reportdate= jsonObject.getString(LabReportsTable.COLUMN_REPORTDATE);
        this.reporttime= jsonObject.getString(LabReportsTable.COLUMN_REPORTTIME);
        this.cbc= jsonObject.getString(LabReportsTable.COLUMN_CBC);
        this.crp= jsonObject.getString(LabReportsTable.COLUMN_CRP);
        this.blood= jsonObject.getString(LabReportsTable.COLUMN_BLOOD);
        this.deviceID = jsonObject.getString(LabReportsTable.COLUMN_DEVICEID );
        this.devicetagID = jsonObject.getString(LabReportsTable.COLUMN_DEVICETAGID );
        this.synced = jsonObject.getString(LabReportsTable.COLUMN_SYNCED );
        this.synced_date = jsonObject.getString(LabReportsTable.COLUMN_SYNCED_DATE );
        this.appVersion= jsonObject.getString(LabReportsTable.COLUMN_APPVERSION);
        return this;
    }

    public LabReportsContract Hydrate(Cursor cursor) {
        this.projectName = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_PROJECTNAME));
        this._ID  = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN__ID ));
        this._UID  = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN__UID ));
        this.formDate = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_USER));
        this.studyid = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_STUDYID));
        this.mrno = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_MRNO));
        this.reportdate = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_REPORTDATE));
        this.reporttime = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_REPORTTIME));
        this.cbc = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_CBC));
        this.crp = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_CRP));
        this.blood = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_BLOOD));
        this.deviceID  = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_DEVICEID ));
        this.devicetagID  = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_DEVICETAGID ));
        this.synced  = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_SYNCED ));
        this.synced_date  = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_SYNCED_DATE ));
        this.appVersion = cursor.getString(cursor.getColumnIndex(LabReportsTable.COLUMN_APPVERSION));
return this;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(LabReportsTable.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(LabReportsTable.COLUMN__ID , this._ID  == null ? JSONObject.NULL : this._ID );
        json.put(LabReportsTable.COLUMN__UID , this._UID  == null ? JSONObject.NULL : this._UID );
        json.put(LabReportsTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(LabReportsTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(LabReportsTable.COLUMN_STUDYID, this.studyid == null ? JSONObject.NULL : this.studyid);
        json.put(LabReportsTable.COLUMN_MRNO, this.mrno == null ? JSONObject.NULL : this.mrno);
        json.put(LabReportsTable.COLUMN_REPORTDATE, this.reportdate == null ? JSONObject.NULL : this.reportdate);
        json.put(LabReportsTable.COLUMN_REPORTTIME, this.reporttime == null ? JSONObject.NULL : this.reporttime);

        if (!this.cbc.equals("")) {
            json.put(LabReportsTable.COLUMN_CBC, this.cbc == null ? JSONObject.NULL : new JSONObject(this.cbc));
        }
        if (!this.crp.equals("")) {
            json.put(LabReportsTable.COLUMN_CRP, this.crp == null ? JSONObject.NULL : new JSONObject(this.crp));
        }
        if (!this.blood.equals("")) {
            json.put(LabReportsTable.COLUMN_BLOOD, this.blood == null ? JSONObject.NULL : new JSONObject(this.blood));
        }
        json.put(LabReportsTable.COLUMN_DEVICEID , this.deviceID  == null ? JSONObject.NULL : this.deviceID );
        json.put(LabReportsTable.COLUMN_DEVICETAGID , this.devicetagID  == null ? JSONObject.NULL : this.devicetagID );
        json.put(LabReportsTable.COLUMN_SYNCED , this.synced  == null ? JSONObject.NULL : this.synced );
        json.put(LabReportsTable.COLUMN_SYNCED_DATE , this.synced_date  == null ? JSONObject.NULL : this.synced_date );
        json.put(LabReportsTable.COLUMN_APPVERSION, this.appVersion == null ? JSONObject.NULL : this.appVersion);

        return json;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStudyid() {
        return studyid;
    }

    public void setStudyid(String studyid) {
        this.studyid = studyid;
    }

    public String getMrno() {
        return mrno;
    }

    public void setMrno(String mrno) {
        this.mrno = mrno;
    }

    public String getReportdate() {
        return reportdate;
    }

    public void setReportdate(String reportdate) {
        this.reportdate = reportdate;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    public String getCbc() {
        return cbc;
    }

    public void setCbc(String cbc) {
        this.cbc = cbc;
    }

    public String getCrp() {
        return crp;
    }

    public void setCrp(String crp) {
        this.crp = crp;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
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

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public static abstract class LabReportsTable implements BaseColumns{

        public static final String TABLE_NAME = "labreports";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN__ID  = "_id";
        public static final String COLUMN__UID  = "_uid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_STUDYID = "studyid";
        public static final String COLUMN_MRNO = "mrno";
        public static final String COLUMN_REPORTDATE = "reportdate";
        public static final String COLUMN_REPORTTIME = "reporttime";
        public static final String COLUMN_CBC = "cbc";
        public static final String COLUMN_CRP = "crp";
        public static final String COLUMN_BLOOD = "blood";
        public static final String COLUMN_DEVICEID  = "deviceid";
        public static final String COLUMN_DEVICETAGID  = "devicetagid";
        public static final String COLUMN_SYNCED  = "synced";
        public static final String COLUMN_SYNCED_DATE  = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";

        public static String _URL = "labreports.php";
    }
}
