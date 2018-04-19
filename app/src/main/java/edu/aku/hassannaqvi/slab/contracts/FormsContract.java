package edu.aku.hassannaqvi.slab.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FormsContract {

    private String projectName = "SLAB";
    //private final String surveyType = "SN";
    private String _ID = "";
    private String _UID = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String formtype = "";
    private String endtime = "";
    private String istatus = ""; // Interview Status
    private String sMrno = "";     // Mr number
    private String sStudyid = "";     // Study ID
    private String isEl = "";     // isEligible
    private String sEl = "";     // Eligible
    //private String sBl = ""; // sB
    private String sRecr = ""; // sRecruitment
    private String sFup = ""; //
    private String sAnthro = "";
    private String sExam = "";
    private String sLab = "";
    private String sSup = "";
    private String sFeed = "";
    private String isinserted = "";     // Child inserted
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String isDischarged = "";
    private String dischargeDate = "";
    private String totalsachgiven = "";

    public String getIsDischarged() {
        return isDischarged;
    }

    public void setIsDischarged(String isDischarged) {
        this.isDischarged = isDischarged;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getTotalsachgiven() {
        return totalsachgiven;
    }

    public void setTotalsachgiven(String totalsachgiven) {
        this.totalsachgiven = totalsachgiven;
    }

    public FormsContract() {
    }


    public FormsContract Sync(JSONObject jsonObject) throws JSONException {
        this.projectName = jsonObject.getString(FormsTable.COLUMN_PROJECT_NAME);
        this._ID = jsonObject.getString(FormsTable._ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.formDate = jsonObject.getString(FormsTable.COLUMN_FORMDATE);
        this.user = jsonObject.getString(FormsTable.COLUMN_USER);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.formtype = jsonObject.getString(FormsTable.COLUMN_FORMTYPE);
        this.sMrno = jsonObject.getString(FormsTable.COLUMN_MRNO);
        this.sStudyid = jsonObject.getString(FormsTable.COLUMN_STUDYID);
        this.sEl = jsonObject.getString(FormsTable.COLUMN_SEL);
        this.isEl = jsonObject.getString(FormsTable.COLUMN_isEL);
        //this.sBl = jsonObject.getString(FormsTable.COLUMN_SBL);
        this.sRecr = jsonObject.getString(FormsTable.COLUMN_SRECR);
        this.sFup = jsonObject.getString(FormsTable.COLUMN_SFUP);
        this.sAnthro = jsonObject.getString(FormsTable.COLUMN_SANTHRO);
        this.sExam = jsonObject.getString(FormsTable.COLUMN_SEXAM);
        this.sLab = jsonObject.getString(FormsTable.COLUMN_SLAB);
        this.sSup= jsonObject.getString(FormsTable.COLUMN_SSUP);
        this.sFeed= jsonObject.getString(FormsTable.COLUMN_SFEED);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDATE);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_APP_VERSION);
        this.endtime = jsonObject.getString(FormsTable.COLUMN_END_TIME);
        this.isinserted = jsonObject.getString(FormsTable.COLUMN_isINSERTED);
        this.isDischarged = jsonObject.getString(FormsTable.COLUMN_ISDISCHARGED);
        this.dischargeDate = jsonObject.getString(FormsTable.COLUMN_DISCHARGEDATE);
        this.totalsachgiven = jsonObject.getString(FormsTable.COLUMN_TOTALSACHGIVEN);


        return this;

    }

    public FormsContract Hydrate(Cursor cursor) {
        this.projectName = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_PROJECT_NAME));
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable._ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.formDate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USER));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.formtype = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMTYPE));
        this.sMrno = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MRNO));
        this.sStudyid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_STUDYID));
        this.sEl = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SEL));
        this.isEl = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_isEL));
        //this.sBl = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SBL));
        this.sRecr = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SRECR));
        this.sFup = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SFUP));
        this.sAnthro = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SANTHRO));
        this.sExam = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SEXAM));
        this.sLab = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SLAB));
        this.sSup = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SSUP));
        this.sFeed = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SFEED));

        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.synced = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED_DATE));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APP_VERSION));
        this.endtime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_END_TIME));
        this.isinserted = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_isINSERTED));
        this.isDischarged = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISDISCHARGED));
        this.dischargeDate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DISCHARGEDATE));
        this.totalsachgiven = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_TOTALSACHGIVEN));




        // TODO:

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(FormsTable.COLUMN_PROJECT_NAME, this.projectName);
        json.put(FormsTable._ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(FormsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(FormsTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(FormsTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(FormsTable.COLUMN_FORMTYPE, this.formtype == null ? JSONObject.NULL : this.formtype);
        json.put(FormsTable.COLUMN_MRNO, this.sMrno == null ? JSONObject.NULL : this.sMrno);
        json.put(FormsTable.COLUMN_STUDYID, this.sStudyid == null ? JSONObject.NULL : this.sStudyid);
        json.put(FormsTable.COLUMN_isEL, this.isEl == null ? JSONObject.NULL : this.isEl);
        json.put(FormsTable.COLUMN_isINSERTED, this.isinserted == null ? JSONObject.NULL : this.isinserted);
        json.put(FormsTable.COLUMN_ISDISCHARGED, this.isDischarged == null ? JSONObject.NULL : this.isDischarged);
        json.put(FormsTable.COLUMN_DISCHARGEDATE, this.dischargeDate == null ? JSONObject.NULL : this.dischargeDate);
        json.put(FormsTable.COLUMN_TOTALSACHGIVEN, this.totalsachgiven == null ? JSONObject.NULL : this.totalsachgiven);


        if (!this.sEl.equals("")) {
            json.put(FormsTable.COLUMN_SEL, this.sEl == null ? JSONObject.NULL : new JSONObject(this.sEl));
        }

        if (!this.istatus.equals("")) {
            json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : new JSONObject(this.istatus));
        }
        /*
        if (!this.sBl.equals("")) {

            json.put(FormsTable.COLUMN_SBL, this.sBl.equals("") ? JSONObject.NULL : new JSONObject(this.sBl));

        }*/
        if (!this.sRecr.equals("")) {
            json.put(FormsTable.COLUMN_SRECR, this.sRecr.equals("") ? JSONObject.NULL : new JSONObject(this.sRecr));
        }

        if (!this.sFup.equals("")) {
           // json.put(FormsTable.COLUMN_SBL, this.sBl.equals("") ? JSONObject.NULL : new JSONObject(this.sBl));

            json.put(FormsTable.COLUMN_SFUP, this.sFup.equals("")? JSONObject.NULL : new JSONObject(this.sFup));

            // }
        }
        if (!this.sExam.equals("")) {

            json.put(FormsTable.COLUMN_SEXAM,this.sExam.equals("")? JSONObject.NULL : new JSONObject(this.sExam));

        }


        if (!this.sAnthro.equals("")) {

            json.put(FormsTable.COLUMN_SANTHRO, this.sAnthro.equals("")? JSONObject.NULL : new JSONObject(this.sAnthro));

        }

        if (!this.sLab.equals("")) {
            json.put(FormsTable.COLUMN_SLAB, this.sLab.equals("")? JSONObject.NULL : new JSONObject(this.sLab));

        }

        if (!this.sSup.equals("")) {
            json.put(FormsTable.COLUMN_SSUP, this.sSup.equals("") ? JSONObject.NULL : new JSONObject(this.sSup));

        }
        if (!this.sFeed.equals("")) {
            json.put(FormsTable.COLUMN_SFEED, this.sFeed.equals("") ? JSONObject.NULL : new JSONObject(this.sFeed));

        }


        json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(FormsTable.COLUMN_GPSDATE, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
        json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(FormsTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(FormsTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(FormsTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);
        json.put(FormsTable.COLUMN_APP_VERSION, this.appversion == null ? JSONObject.NULL : this.appversion);
        json.put(FormsTable.COLUMN_END_TIME, this.endtime == null ? JSONObject.NULL : this.endtime);

        return json;
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

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getsRecr() {
        return sRecr;
    }

    public void setsRecr(String sRecr) {
        this.sRecr = sRecr;
    }

    public String getProjectName() {
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getUID() {
        return _UID;
    }

    public void setUID(String _UID) {
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

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getsEl() {
        return sEl;
    }

    public void setsEl(String sEl) {
        this.sEl = sEl;
    }
/*
    public String getsBl() {
        return sBl;
    }

    public void setsBl(String sBl) {
        this.sBl = sBl;
    }*/

    public String getsFup() {
        return sFup;
    }

    public void setsFup(String sFup) {
        this.sFup = sFup;
    }

    public String getsAnthro() {
        return sAnthro;
    }

    public void setsAnthro(String sAnthro) {
        this.sAnthro = sAnthro;
    }

    public String getsExam() {
        return sExam;
    }

    public void setsExam(String sExam) {
        this.sExam = sExam;
    }

    public String getsLab() {
        return sLab;
    }

    public void setsLab(String sLab) {
        this.sLab = sLab;
    }

    public String getsSup() {
        return sSup;
    }

    public void setsSup(String sSup) {
        this.sSup = sSup;
    }

    public String getsFeed() {
        return sFeed;
    }

    public void setsFeed(String sFeed) {
        this.sFeed = sFeed;
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }

    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
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
    public String getIsinserted() {
        return isinserted;
    }

    public void setIsinserted(String isinserted) {
        this.isinserted = isinserted;
    }



    public String getIsEl() {
        return isEl;
    }

    public void setIsEl(String isEl) {
        this.isEl = isEl;
    }

    public static abstract class FormsTable implements BaseColumns {

        public static final String TABLE_NAME = "forms";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectname";
        public static final String _ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_FORMTYPE = "formtype";
        public static final String COLUMN_MRNO = "mrno";
        public static final String COLUMN_STUDYID = "studyid";
        public static final String COLUMN_SEL = "sel";
        public static final String COLUMN_isEL = "isEl";
       // public static final String COLUMN_SBL = "sbl";
        public static final String COLUMN_SRECR = "sRecr";
        public static final String COLUMN_SFUP = "sfup";
        public static final String COLUMN_SANTHRO = "santhro";
        public static final String COLUMN_SEXAM = "sexam";
        public static final String COLUMN_SLAB = "slab";
        public static final String COLUMN_SSUP = "ssup";
        public static final String COLUMN_SFEED = "sfeed";
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
        public static final String COLUMN_isINSERTED = "isinserted";

        public static final String COLUMN_ISDISCHARGED = "isDischarged";
        public static final String COLUMN_DISCHARGEDATE = "dischargeDate";
        public static final String COLUMN_TOTALSACHGIVEN = "totalsachgiven";
        public static String _URL = "forms.php";
    }
}
