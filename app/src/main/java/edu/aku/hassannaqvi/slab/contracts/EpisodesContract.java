package edu.aku.hassannaqvi.slab.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class EpisodesContract {
    private String projectName = "SLAB 2019";
    private String _ID = "";
    private String _UID = "";
    private String UUID = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String formtype = "";
    private String noofep = "";
    private String count = "";
    private String sMrno = "";     // Mr number
    private String sStudyid = "";     // Study ID
    private String sfuep = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String diseasetype = "";

    public String getprojectName() {
        return projectName;
    }

    public void setprojectName(String projectName) {
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

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getformDate() {
        return formDate;
    }

    public void setformDate(String formDate) {
        this.formDate = formDate;
    }

    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }

    public String getformtype() {
        return formtype;
    }

    public void setformtype(String formtype) {
        this.formtype = formtype;
    }

    public String getnoofep() {
        return noofep;
    }

    public void setnoofep(String noofep) {
        this.noofep = noofep;
    }

    public String getcount() {
        return count;
    }

    public void setcount(String count) {
        this.count = count;
    }

    public String getsMrno() {
        return sMrno;
    }

    public void setsMrno(String sMrno) {
        this.sMrno = sMrno;
    }

    public String getsStudyid() {
        return sStudyid;
    }

    public void setsStudyid(String sStudyid) {
        this.sStudyid = sStudyid;
    }

    public String getsfuep() {
        return sfuep;
    }

    public void setsfuep(String sfuep) {
        this.sfuep = sfuep;
    }

    public String getdeviceID() {
        return deviceID;
    }

    public void setdeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getdevicetagID() {
        return devicetagID;
    }

    public void setdevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getsynced() {
        return synced;
    }

    public void setsynced(String synced) {
        this.synced = synced;
    }

    public String getsynced_date() {
        return synced_date;
    }

    public void setsynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public String getappversion() {
        return appversion;
    }

    public void setappversion(String appversion) {
        this.appversion = appversion;
    }

    public String getdiseasetype() {
        return diseasetype;
    }

    public void setdiseasetype(String diseasetype) {
        this.diseasetype = diseasetype;
    }

    public EpisodesContract Sync(JSONObject jsonObject) throws JSONException {

        this.projectName = jsonObject.getString(EpisodesTable.COLUMN_PROJECTNAME);
        this._ID = jsonObject.getString(EpisodesTable.COLUMN__ID);
        this._UID = jsonObject.getString(EpisodesTable.COLUMN__UID);
        this.UUID = jsonObject.getString(EpisodesTable.COLUMN_UUID);
        this.formDate = jsonObject.getString(EpisodesTable.COLUMN_FORMDATE);
        this.user = jsonObject.getString(EpisodesTable.COLUMN_USER);
        this.formtype = jsonObject.getString(EpisodesTable.COLUMN_FORMTYPE);
        this.noofep = jsonObject.getString(EpisodesTable.COLUMN_NOOFEP);
        this.count = jsonObject.getString(EpisodesTable.COLUMN_COUNT);
        this.sMrno = jsonObject.getString(EpisodesTable.COLUMN_SMRNO);
        this.sStudyid = jsonObject.getString(EpisodesTable.COLUMN_SSTUDYID);
        this.sfuep = jsonObject.getString(EpisodesTable.COLUMN_SFUEP);
        this.deviceID = jsonObject.getString(EpisodesTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(EpisodesTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(EpisodesTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(EpisodesTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(EpisodesTable.COLUMN_APPVERSION);
        this.diseasetype = jsonObject.getString(EpisodesTable.COLUMN_DISEASETYPE);

        return this;
    }

    public EpisodesContract Hydrate(Cursor cursor) {

        this.projectName = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_PROJECTNAME));
        this._ID = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN__ID));
        this._UID = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN__UID));
        this.UUID = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_UUID));
        this.formDate = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_USER));
        this.formtype = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_FORMTYPE));
        this.noofep = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_NOOFEP));
        this.count = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_COUNT));
        this.sMrno = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_SMRNO));
        this.sStudyid = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_SSTUDYID));
        this.sfuep = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_SFUEP));
        this.deviceID = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_DEVICETAGID));
        this.synced = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_SYNCED_DATE));
        this.appversion = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_APPVERSION));
        this.diseasetype = cursor.getString(cursor.getColumnIndex(EpisodesTable.COLUMN_DISEASETYPE));

        return this;

    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(EpisodesTable.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(EpisodesTable.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(EpisodesTable.COLUMN__UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(EpisodesTable.COLUMN_UUID, this.UUID == null ? JSONObject.NULL : this.UUID);
        json.put(EpisodesTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(EpisodesTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(EpisodesTable.COLUMN_FORMTYPE, this.formtype == null ? JSONObject.NULL : this.formtype);
        json.put(EpisodesTable.COLUMN_COUNT, this.count == null ? JSONObject.NULL : this.count);
        json.put(EpisodesTable.COLUMN_SMRNO, this.sMrno == null ? JSONObject.NULL : this.sMrno);
        json.put(EpisodesTable.COLUMN_SSTUDYID, this.sStudyid == null ? JSONObject.NULL : this.sStudyid);
        json.put(EpisodesTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(EpisodesTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(EpisodesTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);
        json.put(EpisodesTable.COLUMN_DISEASETYPE, this.diseasetype == null ? JSONObject.NULL : this.diseasetype);


        if (!this.noofep.equals("")) {
            json.put(EpisodesTable.COLUMN_NOOFEP, this.noofep == null ? JSONObject.NULL : new JSONObject(this.noofep));
        }
        if (!this.sfuep.equals("")) {
            json.put(EpisodesTable.COLUMN_SFUEP, this.sfuep == null ? JSONObject.NULL : new JSONObject(this.sfuep));
        }

        return json;
    }


    public static abstract class EpisodesTable implements BaseColumns {

        public static final String TABLE_NAME = "episodes";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN__ID = "_id";
        public static final String COLUMN__UID = "_uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_FORMTYPE = "formtype";
        public static final String COLUMN_NOOFEP = "noofep";
        public static final String COLUMN_COUNT = "count";
        public static final String COLUMN_SMRNO = "smrno";
        public static final String COLUMN_SSTUDYID = "sstudyid";
        public static final String COLUMN_SFUEP = "sfuep";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_DISEASETYPE = "diseasetype";


        public static String _URL = "episodes.php";
    }


}
