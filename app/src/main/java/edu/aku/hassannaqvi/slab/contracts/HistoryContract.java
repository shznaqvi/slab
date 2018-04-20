package edu.aku.hassannaqvi.slab.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ramsha.ahmed on 4/19/2018.
 */

public class HistoryContract {

    private String projectName = "SLAB";
    private String _ID = "";
    private String _UID = "";
    private String UUID = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String formtype = "";

    private String sMrno = "";     // Mr number
    private String sStudyid = "";     // Study ID
    private String isEl = "";     // isEligible
    private String noofSachet = "";
    private String noofDays = "";
    private String sfu11 = "";
    private String isinserted = "";     // Child inserted
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";

    public HistoryContract() {
    }


    public HistoryContract Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(HistoryTable.COLUMN__ID );
        this.projectName= jsonObject.getString(HistoryTable.COLUMN_PROJECTNAME);
        this._UID = jsonObject.getString(HistoryTable.COLUMN__UID );
        this.UUID= jsonObject.getString(HistoryTable.COLUMN_UUID);
        this.formDate= jsonObject.getString(HistoryTable.COLUMN_FORMDATE);
        this.user= jsonObject.getString(HistoryTable.COLUMN_USER);
        this.formtype= jsonObject.getString(HistoryTable.COLUMN_FORMTYPE);
        this.sMrno= jsonObject.getString(HistoryTable.COLUMN_SMRNO);
        this.sStudyid= jsonObject.getString(HistoryTable.COLUMN_SSTUDYID);
        this.isEl= jsonObject.getString(HistoryTable.COLUMN_ISEL);
        this.noofSachet= jsonObject.getString(HistoryTable.COLUMN_NOOFSACHET);
        this.noofDays= jsonObject.getString(HistoryTable.COLUMN_NOOFDAYS);
        this.sfu11= jsonObject.getString(HistoryTable.COLUMN_SFU11);
        this.isinserted= jsonObject.getString(HistoryTable.COLUMN_ISINSERTED);
        this.deviceID = jsonObject.getString(HistoryTable.COLUMN_DEVICEID );
        this.devicetagID = jsonObject.getString(HistoryTable.COLUMN_DEVICETAGID );
        this.synced = jsonObject.getString(HistoryTable.COLUMN_SYNCED );
        this.synced_date = jsonObject.getString(HistoryTable.COLUMN_SYNCED_DATE );
        this.appversion= jsonObject.getString(HistoryTable.COLUMN_APPVERSION);

        return this;
    }
    public HistoryContract Hydrate(Cursor cursor) {
        this._ID  = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN__ID ));
        this.projectName = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_PROJECTNAME));
        this._UID  = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN__UID ));
        this.UUID = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_UUID));
        this.formDate = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_USER));
        this.formtype = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_FORMTYPE));
        this.sMrno = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_SMRNO));
        this.sStudyid = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_SSTUDYID));
        this.isEl = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_ISEL));
        this.noofSachet = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_NOOFSACHET));
        this.noofDays = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_NOOFDAYS));
        this.sfu11 = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_SFU11));
        this.isinserted = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_ISINSERTED));
        this.deviceID  = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_DEVICEID ));
        this.devicetagID  = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_DEVICETAGID ));
        this.synced  = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_SYNCED ));
        this.synced_date  = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_SYNCED_DATE ));
        this.appversion = cursor.getString(cursor.getColumnIndex(HistoryTable.COLUMN_APPVERSION));

        // TODO:

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
       // json.put(HistoryTable.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);

        json.put(HistoryTable.COLUMN__ID , this._ID  == null ? JSONObject.NULL : this._ID );
        json.put(HistoryTable.COLUMN_PROJECTNAME, this.projectName);
        json.put(HistoryTable.COLUMN__UID , this._UID  == null ? JSONObject.NULL : this._UID );
        json.put(HistoryTable.COLUMN_UUID, this.UUID == null ? JSONObject.NULL : this.UUID);
        json.put(HistoryTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(HistoryTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(HistoryTable.COLUMN_FORMTYPE, this.formtype == null ? JSONObject.NULL : this.formtype);
        json.put(HistoryTable.COLUMN_SMRNO, this.sMrno == null ? JSONObject.NULL : this.sMrno);
        json.put(HistoryTable.COLUMN_SSTUDYID, this.sStudyid == null ? JSONObject.NULL : this.sStudyid);
        json.put(HistoryTable.COLUMN_ISEL, this.isEl == null ? JSONObject.NULL : this.isEl);
        json.put(HistoryTable.COLUMN_NOOFSACHET, this.noofSachet == null ? JSONObject.NULL : this.noofSachet);
        json.put(HistoryTable.COLUMN_NOOFDAYS, this.noofDays == null ? JSONObject.NULL : this.noofDays);
        json.put(HistoryTable.COLUMN_ISINSERTED, this.isinserted == null ? JSONObject.NULL : this.isinserted);
        json.put(HistoryTable.COLUMN_DEVICEID , this.deviceID  == null ? JSONObject.NULL : this.deviceID );
        json.put(HistoryTable.COLUMN_DEVICETAGID , this.devicetagID  == null ? JSONObject.NULL : this.devicetagID );
        json.put(HistoryTable.COLUMN_SYNCED , this.synced  == null ? JSONObject.NULL : this.synced );
        json.put(HistoryTable.COLUMN_SYNCED_DATE , this.synced_date  == null ? JSONObject.NULL : this.synced_date );
        json.put(HistoryTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);



        if (!this.sfu11.equals("")) {
            json.put(HistoryTable.COLUMN_SFU11, this.sfu11 == null ? JSONObject.NULL : new JSONObject(this.sfu11));
        }

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

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
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

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
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

    public String getIsEl() {
        return isEl;
    }

    public void setIsEl(String isEl) {
        this.isEl = isEl;
    }

    public String getNoofSachet() {
        return noofSachet;
    }

    public void setNoofSachet(String noofSachet) {
        this.noofSachet = noofSachet;
    }

    public String getNoofDays() {
        return noofDays;
    }

    public void setNoofDays(String noofDays) {
        this.noofDays = noofDays;
    }

    public String getSfu11() {
        return sfu11;
    }

    public void setSfu11(String sfu11) {
        this.sfu11 = sfu11;
    }

    public String getIsinserted() {
        return isinserted;
    }

    public void setIsinserted(String isinserted) {
        this.isinserted = isinserted;
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

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }


    public static abstract class HistoryTable implements BaseColumns {

        public static final String TABLE_NAME = "history";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN__ID  = "_id";
        public static final String COLUMN__UID  = "_uid";
        public static final String COLUMN_UUID = "uuid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_FORMTYPE = "formtype";
        public static final String COLUMN_SMRNO = "smrno";
        public static final String COLUMN_SSTUDYID = "sstudyid";
        public static final String COLUMN_ISEL = "isel";
        public static final String COLUMN_NOOFSACHET = "noofsachet";
        public static final String COLUMN_NOOFDAYS = "noofdays";
        public static final String COLUMN_SFU11 = "sfu11";
        public static final String COLUMN_ISINSERTED = "isinserted";
        public static final String COLUMN_DEVICEID  = "deviceid";
        public static final String COLUMN_DEVICETAGID  = "devicetagid";
        public static final String COLUMN_SYNCED  = "synced";
        public static final String COLUMN_SYNCED_DATE  = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";


        public static String _URL = "history.php";
    }


}
