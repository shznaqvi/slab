package edu.aku.hassannaqvi.slab.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.slab.contracts.DistrictsContract;
import edu.aku.hassannaqvi.slab.contracts.DistrictsContract.singleDistrict;
import edu.aku.hassannaqvi.slab.contracts.FollowupListContract.FollowUpList;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.slab.contracts.TalukasContract;
import edu.aku.hassannaqvi.slab.contracts.TalukasContract.TalukasTable;
import edu.aku.hassannaqvi.slab.contracts.UCsContract;
import edu.aku.hassannaqvi.slab.contracts.UCsContract.UCsTable;
import edu.aku.hassannaqvi.slab.contracts.UsersContract;
import edu.aku.hassannaqvi.slab.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.slab.contracts.VillagesContract;
import edu.aku.hassannaqvi.slab.contracts.VillagesContract.singleVillage;
import edu.aku.hassannaqvi.slab.ui.EligibilityFormActivity;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersContract.UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.ROW_USERNAME + " TEXT,"
            + UsersTable.ROW_PASSWORD + " TEXT,"
            + UsersTable.FULL_NAME + " TEXT"
            + " );";
    public static final String DATABASE_NAME = "slab.db";
    public static final String DB_NAME = DATABASE_NAME.replace(".", "_copy.");
    public static final String PROJECT_NAME = "DMU-SRCPREG";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT," +
            FormsTable.COLUMN_FORMDATE + " TEXT," +
            FormsTable.COLUMN_USER + " TEXT," +
            FormsTable.COLUMN_FORMTYPE + " TEXT," +
            FormsTable.COLUMN_MRNO + " TEXT," +
            FormsTable.COLUMN_STUDYID + " TEXT," +
            FormsTable.COLUMN_isINSERTED + " TEXT," +
            FormsTable.COLUMN_SEL + " TEXT," +
            FormsTable.COLUMN_isEL + " TEXT," +
            //FormsTable.COLUMN_SBL + " TEXT," +
            FormsTable.COLUMN_SRECR + " TEXT," +
            FormsTable.COLUMN_SFUP + " TEXT," +
            FormsTable.COLUMN_SANTHRO + " TEXT," +
            FormsTable.COLUMN_SEXAM + " TEXT," +
            FormsTable.COLUMN_SLAB + " TEXT," +
            FormsTable.COLUMN_SSUP + " TEXT," +
            FormsTable.COLUMN_SFEED + " TEXT," +
            FormsTable.COLUMN_ISTATUS + " TEXT," +
            FormsTable.COLUMN_GPSLAT + " TEXT," +
            FormsTable.COLUMN_GPSLNG + " TEXT," +
            FormsTable.COLUMN_GPSDATE + " TEXT," +
            FormsTable.COLUMN_GPSACC + " TEXT," +
            FormsTable.COLUMN_DEVICEID + " TEXT," +
            FormsTable.COLUMN_DEVICETAGID + " TEXT," +
            FormsTable.COLUMN_APP_VERSION + " TEXT," +
            FormsTable.COLUMN_END_TIME + " TEXT," +
            FormsTable.COLUMN_SYNCED + " TEXT," +
            FormsTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";

    private static final String SQL_CREATE_FOLLOWUPLIST = "CREATE TABLE "
            + FollowUpList.TABLE_NAME + "(" +
            FollowUpList._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FollowUpList.COLUMN_PROJECT_NAME + " TEXT," +
            FollowUpList.COLUMN_UID + " TEXT," +
            FollowUpList.COLUMN_FORMDATE + " TEXT," +
            FollowUpList.COLUMN_USER + " TEXT," +
            FollowUpList.COLUMN_FORMTYPE + " TEXT," +
            FollowUpList.COLUMN_CHILDNAME + " TEXT," +
            FollowUpList.COLUMN_MOTHERNAME + " TEXT," +
            FollowUpList.COLUMN_MRNO + " TEXT," +
            FollowUpList.COLUMN_STUDYID + " TEXT," +
            FollowUpList.COLUMN_DISCHARGEDATE + " TEXT," +
            FollowUpList.COLUMN_TYPE + " TEXT," +
            FollowUpList.COLUMN_STATUS + " TEXT, " +

            FollowUpList.COLUMN_ISTATUS + " TEXT," +
            FollowUpList.COLUMN_GPSLAT + " TEXT," +
            FollowUpList.COLUMN_GPSLNG + " TEXT," +
            FollowUpList.COLUMN_GPSDATE + " TEXT," +
            FollowUpList.COLUMN_GPSACC + " TEXT," +
            FollowUpList.COLUMN_DEVICEID + " TEXT," +
            FollowUpList.COLUMN_DEVICETAGID + " TEXT," +
            FollowUpList.COLUMN_APP_VERSION + " TEXT," +
            FollowUpList.COLUMN_END_TIME + " TEXT," +
            FollowUpList.COLUMN_SYNCED + " TEXT," +
            FollowUpList.COLUMN_SYNCED_DATE + " TEXT"
            + " );";
    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + UsersContract.UsersTable.TABLE_NAME;
    private static final String SQL_DELETE_FORMS =
            "DROP TABLE IF EXISTS " + FormsTable.TABLE_NAME;
    private static final String SQL_DELETE_DISTRICTS = "DROP TABLE IF EXISTS " + singleDistrict.TABLE_NAME;
    private static final String SQL_DELETE_VILLAGES = "DROP TABLE IF EXISTS " + singleVillage.TABLE_NAME;
    final String SQL_CREATE_DISTRICT = "CREATE TABLE " + singleDistrict.TABLE_NAME + " (" +
            singleDistrict._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            singleDistrict.COLUMN_DISTRICT_CODE + " TEXT, " +
            singleDistrict.COLUMN_DISTRICT_NAME + " TEXT " +
            ");";
    final String SQL_CREATE_VILLAGE = "CREATE TABLE " + singleVillage.TABLE_NAME + " (" +
            singleVillage._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            singleVillage.COLUMN_VILLAGE_CODE + " TEXT, " +
            singleVillage.COLUMN_VILLAGE_NAME + " TEXT, " +
            singleVillage.COLUMN_DISTRICT_CODE + " TEXT " +
            ");";

    private final String TAG = "DatabaseHelper";


    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);
       db.execSQL(SQL_CREATE_FOLLOWUPLIST);
        db.execSQL(SQL_CREATE_DISTRICT);
        db.execSQL(SQL_CREATE_VILLAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_USERS);
        db.execSQL(SQL_DELETE_FORMS);
        db.execSQL(SQL_CREATE_FOLLOWUPLIST);
        db.execSQL(SQL_DELETE_DISTRICTS);
        db.execSQL(SQL_DELETE_VILLAGES);
    }

    public void syncVillages(JSONArray pcList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleVillage.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = pcList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectPSU = jsonArray.getJSONObject(i);

                VillagesContract vc = new VillagesContract();
                vc.sync(jsonObjectPSU);
                Log.i(TAG, "syncVillages: " + jsonObjectPSU.toString());

                ContentValues values = new ContentValues();

                values.put(singleVillage.COLUMN_VILLAGE_CODE, vc.getVillageCode());
                values.put(singleVillage.COLUMN_VILLAGE_NAME, vc.getVillageName());
                values.put(singleVillage.COLUMN_DISTRICT_CODE, vc.getDistrictCode());

                db.insert(singleVillage.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncTalukas(JSONArray Talukaslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TalukasTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Talukaslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                TalukasContract Vc = new TalukasContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(TalukasTable.COLUMN_TALUKA_CODE, Vc.getTalukacode());
                values.put(TalukasTable.COLUMN_TALUKA, Vc.getTaluka());

                db.insert(TalukasTable.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncUCs(JSONArray UCslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UCsTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = UCslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                UCsContract Vc = new UCsContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(UCsTable.COLUMN_UCCODE, Vc.getUccode());
                values.put(UCsTable.COLUMN_UCS, Vc.getUcs());
                values.put(UCsTable.COLUMN_TALUKA_CODE, Vc.getTaluka_code());

                db.insert(UCsTable.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncDistricts(JSONArray dcList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleDistrict.TABLE_NAME, null, null);

        try {
            JSONArray jsonArray = dcList;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectDistrict = jsonArray.getJSONObject(i);

                DistrictsContract dc = new DistrictsContract();
                dc.sync(jsonObjectDistrict);

                ContentValues values = new ContentValues();

                values.put(singleDistrict.COLUMN_DISTRICT_CODE, dc.getDistrictCode());
                values.put(singleDistrict.COLUMN_DISTRICT_NAME, dc.getDistrictName());

                db.insert(singleDistrict.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public Collection<DistrictsContract> getAllDistricts() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleDistrict._ID,
                singleDistrict.COLUMN_DISTRICT_CODE,
                singleDistrict.COLUMN_DISTRICT_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                singleDistrict._ID + " ASC";

        Collection<DistrictsContract> allDC = new ArrayList<DistrictsContract>();
        try {
            c = db.query(
                    singleDistrict.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                DistrictsContract dc = new DistrictsContract();
                allDC.add(dc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<VillagesContract> getAllPSUsByDistrict(String district_code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleVillage._ID,
                singleVillage.COLUMN_VILLAGE_CODE,
                singleVillage.COLUMN_VILLAGE_NAME,
                singleVillage.COLUMN_DISTRICT_CODE
        };

        String whereClause = singleVillage.COLUMN_DISTRICT_CODE + " = ?";
        String[] whereArgs = {district_code};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleVillage.COLUMN_VILLAGE_CODE + " ASC";

        Collection<VillagesContract> allPC = new ArrayList<VillagesContract>();
        try {
            c = db.query(
                    singleVillage.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                VillagesContract pc = new VillagesContract();
                allPC.add(pc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allPC;
    }

    public void syncUser(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                UsersContract user = new UsersContract();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersContract.UsersTable.ROW_USERNAME, user.getUserName());
                values.put(UsersTable.ROW_PASSWORD, user.getPassword());
                values.put(UsersTable.FULL_NAME, user.getFULL_NAME());
                db.insert(UsersTable.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }

    public boolean Login(String username, String password) throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        String[] columns = {
                UsersTable._ID
        };

// Which row to update, based on the ID
        String selection = UsersContract.UsersTable.ROW_USERNAME + " = ?" + " AND " + UsersContract.UsersTable.ROW_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(UsersContract.UsersTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;
    }

    public List<FormsContract> getFormsByDSS(String dssID) {
        List<FormsContract> formList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FormsTable.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FormsContract fc = new FormsContract();
                formList.add(fc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public String isMrnoFound(String mrNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                FormsTable.COLUMN_PROJECT_NAME,
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_MRNO,
                FormsTable.COLUMN_STUDYID,
                FormsTable.COLUMN_isINSERTED,
                FormsTable.COLUMN_SEL,
                FormsTable.COLUMN_isEL,
                //FormsTable.COLUMN_SBL,
                FormsTable.COLUMN_SRECR,
                FormsTable.COLUMN_SFUP,
                FormsTable.COLUMN_SANTHRO,
                FormsTable.COLUMN_SEXAM,
                FormsTable.COLUMN_SLAB,
                FormsTable.COLUMN_SSUP,
                FormsTable.COLUMN_SFEED,

                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
                FormsTable.COLUMN_END_TIME

        };

        String whereClause = FormsTable.COLUMN_MRNO + " =? AND " +
                FormsTable.COLUMN_isEL + " =?  ";

        String[] whereArgs = new String[]{mrNo, "1"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " DESC LIMIT 1";
        FormsContract fc = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                fc.Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return fc.getsMrno();
    }

    public FormsContract getStudyID(String mrNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                FormsTable.COLUMN_PROJECT_NAME,
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_MRNO,
                FormsTable.COLUMN_STUDYID,
                FormsTable.COLUMN_isINSERTED,
                FormsTable.COLUMN_SEL,
                FormsTable.COLUMN_isEL,
                //FormsTable.COLUMN_SBL,
                FormsTable.COLUMN_SRECR,
                FormsTable.COLUMN_SFUP,
                FormsTable.COLUMN_SANTHRO,
                FormsTable.COLUMN_SEXAM,
                FormsTable.COLUMN_SLAB,
                FormsTable.COLUMN_SSUP,
                FormsTable.COLUMN_SFEED,

                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
                FormsTable.COLUMN_END_TIME

        };

        String whereClause = FormsTable.COLUMN_MRNO + " =? AND " +
                FormsTable.COLUMN_isEL + " =? AND " +
                FormsTable.COLUMN_FORMTYPE + " =? ";
        String[] whereArgs = new String[]{mrNo, "1", "2"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " DESC LIMIT 1";
        FormsContract fc = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                fc.Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return fc;
    }

    public FormsContract getChildName(String mrNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                FormsTable.COLUMN_PROJECT_NAME,
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_MRNO,
                FormsTable.COLUMN_STUDYID,
                FormsTable.COLUMN_isINSERTED,
                FormsTable.COLUMN_SEL,
                FormsTable.COLUMN_isEL,
                //FormsTable.COLUMN_SBL,
                FormsTable.COLUMN_SRECR,
                FormsTable.COLUMN_SFUP,
                FormsTable.COLUMN_SANTHRO,
                FormsTable.COLUMN_SEXAM,
                FormsTable.COLUMN_SLAB,
                FormsTable.COLUMN_SSUP,
                FormsTable.COLUMN_SFEED,

                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
                FormsTable.COLUMN_END_TIME

        };

        String whereClause = FormsTable.COLUMN_MRNO + " =? AND " +
                FormsTable.COLUMN_isEL + " =? AND " +
                FormsTable.COLUMN_FORMTYPE + " =? ";
        String[] whereArgs = new String[]{mrNo, "1", "1"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " DESC LIMIT 1";
        FormsContract fc = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                fc.Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return fc;
    }

    public String getDateBymrno(String mrNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                FormsTable.COLUMN_PROJECT_NAME,
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_MRNO,
                FormsTable.COLUMN_STUDYID,
                FormsTable.COLUMN_isINSERTED,
                FormsTable.COLUMN_SEL,
                FormsTable.COLUMN_isEL,
                //FormsTable.COLUMN_SBL,
                FormsTable.COLUMN_SRECR,
                FormsTable.COLUMN_SFUP,
                FormsTable.COLUMN_SANTHRO,
                FormsTable.COLUMN_SEXAM,
                FormsTable.COLUMN_SLAB,
                FormsTable.COLUMN_SSUP,
                FormsTable.COLUMN_SFEED,

                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
                FormsTable.COLUMN_END_TIME

        };

        String whereClause = FormsTable.COLUMN_MRNO + " =? AND " +
                FormsTable.COLUMN_isEL + " =?  ";

        String[] whereArgs = new String[]{mrNo, "1"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " DESC LIMIT 1";
        FormsContract fc = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                fc.Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return fc.getFormDate();
    }

    public String isInserted(String mrno) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {

                FormsTable.COLUMN_PROJECT_NAME,
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_MRNO,
                FormsTable.COLUMN_STUDYID,
                FormsTable.COLUMN_isINSERTED,
                FormsTable.COLUMN_SEL,
                FormsTable.COLUMN_isEL,
                //FormsTable.COLUMN_SBL,
                FormsTable.COLUMN_SRECR,
                FormsTable.COLUMN_SFUP,
                FormsTable.COLUMN_SANTHRO,
                FormsTable.COLUMN_SEXAM,
                FormsTable.COLUMN_SLAB,
                FormsTable.COLUMN_SSUP,
                FormsTable.COLUMN_SFEED,

                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
                FormsTable.COLUMN_END_TIME

        };

        String whereClause = FormsTable.COLUMN_MRNO + " =? AND " +
                FormsTable.COLUMN_isEL + " =? ";

        String[] whereArgs = new String[]{mrno, "1"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " DESC LIMIT 1";
        FormsContract fc = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                fc.Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return fc.getIsinserted();
    }

    public FormsContract isStudyIDFound(String mrNo, String studyID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_PROJECT_NAME,
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_MRNO,
                FormsTable.COLUMN_STUDYID,
                FormsTable.COLUMN_isINSERTED,
                FormsTable.COLUMN_SEL,
                FormsTable.COLUMN_isEL,
                //FormsTable.COLUMN_SBL,
                FormsTable.COLUMN_SRECR,
                FormsTable.COLUMN_SFUP,
                FormsTable.COLUMN_SANTHRO,
                FormsTable.COLUMN_SEXAM,
                FormsTable.COLUMN_SLAB,
                FormsTable.COLUMN_SSUP,
                FormsTable.COLUMN_SFEED,

                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
                FormsTable.COLUMN_END_TIME
        };

        String whereClause = FormsTable.COLUMN_MRNO + "=? AND " +
                FormsTable.COLUMN_STUDYID + "=? AND " +
                FormsTable.COLUMN_isEL + "=?";
        String[] whereArgs = new String[]{mrNo, studyID, "1"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " DESC LIMIT 1";
        FormsContract fc = new FormsContract();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                fc.Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return fc;
    }

    public Long addForm(FormsContract fc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECT_NAME, fc.getProjectName());
        values.put(FormsTable.COLUMN_UID, fc.getUID());
        values.put(FormsTable.COLUMN_FORMDATE, fc.getFormDate());
        values.put(FormsTable.COLUMN_USER, fc.getUser());
        values.put(FormsTable.COLUMN_FORMTYPE, fc.getFormtype());
        values.put(FormsTable.COLUMN_ISTATUS, fc.getIstatus());
        values.put(FormsTable.COLUMN_MRNO, fc.getsMrno());
        values.put(FormsTable.COLUMN_STUDYID, fc.getsStudyid());
        values.put(FormsTable.COLUMN_isINSERTED, fc.getIsinserted());
        values.put(FormsTable.COLUMN_SEL, fc.getsEl());
        values.put(FormsTable.COLUMN_isEL, fc.getIsEl());
        //values.put(FormsTable.COLUMN_SBL, fc.getsBl());
        values.put(FormsTable.COLUMN_SRECR, fc.getsRecr());
        values.put(FormsTable.COLUMN_SFUP, fc.getsFup());
        values.put(FormsTable.COLUMN_SANTHRO, fc.getsAnthro());
        values.put(FormsTable.COLUMN_SEXAM, fc.getsExam());
        values.put(FormsTable.COLUMN_SLAB, fc.getsLab());
        values.put(FormsTable.COLUMN_SSUP, fc.getsSup());
        values.put(FormsTable.COLUMN_SFEED, fc.getsFeed());
        values.put(FormsTable.COLUMN_GPSLAT, fc.getGpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, fc.getGpsLng());
        values.put(FormsTable.COLUMN_GPSDATE, fc.getGpsDT());
        values.put(FormsTable.COLUMN_GPSACC, fc.getGpsAcc());
        values.put(FormsTable.COLUMN_DEVICETAGID, fc.getDevicetagID());
        values.put(FormsTable.COLUMN_DEVICEID, fc.getDeviceID());
        values.put(FormsTable.COLUMN_SYNCED, fc.getSynced());
        values.put(FormsTable.COLUMN_SYNCED_DATE, fc.getSynced_date());
        values.put(FormsTable.COLUMN_APP_VERSION, fc.getAppversion());
        values.put(FormsTable.COLUMN_END_TIME, fc.getEndtime());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, MainApp.fc.getUID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Collection<FormsContract> getAllForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_SEL,
                // FormsTable.COLUMN_SBL,
                FormsTable.COLUMN_SRECR,
                FormsTable.COLUMN_SFUP,
                FormsTable.COLUMN_SANTHRO,
                FormsTable.COLUMN_SEXAM,
                FormsTable.COLUMN_SLAB,
                FormsTable.COLUMN_SSUP,
                FormsTable.COLUMN_SFEED,

                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
                FormsTable.COLUMN_END_TIME

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_PROJECT_NAME,
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_MRNO,
                FormsTable.COLUMN_STUDYID,
                FormsTable.COLUMN_isINSERTED,
                FormsTable.COLUMN_SEL,
                FormsTable.COLUMN_isEL,
                //FormsTable.COLUMN_SBL,
                FormsTable.COLUMN_SRECR,
                FormsTable.COLUMN_SFUP,
                FormsTable.COLUMN_SANTHRO,
                FormsTable.COLUMN_SEXAM,
                FormsTable.COLUMN_SLAB,
                FormsTable.COLUMN_SSUP,
                FormsTable.COLUMN_SFEED,

                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE,
                FormsTable.COLUMN_APP_VERSION,
                FormsTable.COLUMN_END_TIME
        };
        String whereClause = FormsTable.COLUMN_SYNCED + " is null OR " + FormsTable.COLUMN_SYNCED + "=''";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getTodayForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                //FormsTable.COLUMN_DSSID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_FORMDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                fc.set_ID(c.getString(c.getColumnIndex(FormsTable._ID)));
                fc.setFormDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                fc.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                fc.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allFC.add(fc);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    public int updateSEl() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SEL, MainApp.fc.getsEl());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    /*
        public int updateSBL() {
            SQLiteDatabase db = this.getReadableDatabase();

    // New value for one column
            ContentValues values = new ContentValues();
            values.put(FormsTable.COLUMN_SBL, MainApp.fc.getsBl());

    // Which row to update, based on the ID
            String selection = FormsTable._ID + " = ?";
            String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

            int count = db.update(FormsTable.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);
            return count;
        }
        */
    public int updateSRecr() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SRECR, MainApp.fc.getsRecr());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSSUP() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SSUP, MainApp.fc.getsSup());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSFEED() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SFEED, MainApp.fc.getsFeed());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSFup() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SFUP, MainApp.fc.getsFup());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSAntrho() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SANTHRO, MainApp.fc.getsAnthro());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSExam() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SEXAM, MainApp.fc.getsExam());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSLab() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SLAB, MainApp.fc.getsLab());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.fc.getIstatus());
        values.put(FormsTable.COLUMN_END_TIME, MainApp.fc.getEndtime());


// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

}