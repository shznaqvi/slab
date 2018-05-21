package edu.aku.hassannaqvi.slab.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityLabInvestigationBinding;
import edu.aku.hassannaqvi.slab.databinding.ActivityLabReportsBinding;
import edu.aku.hassannaqvi.slab.other.DateUtils;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class LabReportsActivity extends AppCompatActivity {
    ActivityLabReportsBinding bi;
    Context context;
    private static final String TAG = LabReportsActivity.class.getName();
    String dtToday = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime());
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_lab_reports);
        bi.setCallback(this);
        context = LabReportsActivity.this;
        db = new DatabaseHelper(this);
        bi.reportdate.setManager(getSupportFragmentManager());
        // bi.reportdate.setMaxDate(dateToday);
        // bi.reportdate.setMinDate( DateUtils.getThreeDaysBack("dd/MM/yyyy",-3));
        bi.reporttime.setManager(getSupportFragmentManager());
        bi.reporttime.setTimeFormat("HH:mm");
        bi.reporttime.setIs24HourView(true);
//        validatorClass.setScrollViewFocus(labr_scrollview);
        // setContentView(R.layout.activity_lab_reports);
    }

    public void btnCheckMrno() {
        if (!bi.lbrMrno.getText().toString().trim().isEmpty() && !bi.lbrStudyid.getText().toString().trim().isEmpty()) {

            //TODO : Checking mrno is recruited on server or not!!!
//            if ((!childListContract.getMrNo().isEmpty() && !childListContract.getStudyID().isEmpty()) || db.isChildFound(bi.sfu001.getText().toString(), bi.sfu002.getText().toString())) {
            if (db.isChildFound(bi.lbrMrno.getText().toString(), bi.lbrStudyid.getText().toString())) {
                bi.fldGrplabreport.setVisibility(View.VISIBLE);
            } else {
                bi.fldGrplabreport.setVisibility(View.GONE);
                Toast.makeText(this, "Child not Recruited yet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean formValidation() {
        // Toast.makeText(context, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyRadioButton(context, bi.reports, bi.cbc, getString(R.string.slrincbc))) {
            return false;
        }
        if (bi.cbc.isChecked()) {
            if (!validatorClass.EmptyTextBox(context, bi.slrhb, getString(R.string.slrhb))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(context, bi.slrwbc, getString(R.string.slrwbc))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(context, bi.slrneu, getString(R.string.slrneu))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(context, bi.slrlym, getString(R.string.slrlym))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(context, bi.slrpla, getString(R.string.slrpla))) {
                return false;
            }
        }
        if (bi.crp.isChecked()) {
            if (!validatorClass.EmptyTextBox(context, bi.slrcrp, getString(R.string.slrcrp))) {
                return false;
            }
        }
        if (bi.blood.isChecked()) {
            if (!validatorClass.EmptyRadioButton(context, bi.growth, bi.growthyes, getString(R.string.slrorg))) {
                return false;
            }
            if (bi.growthyes.isChecked()) {
                if (!validatorClass.EmptyTextBox(context, bi.slrorg1, getString(R.string.slrorg))) {
                    return false;
                }
                if (!validatorClass.EmptyTextBox(context, bi.slrorg2, getString(R.string.slrorg))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void BtnContinue() {
        //  Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                //  Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();
                finish();

                startActivity(new Intent(this, EndingActivity.class).putExtra("completed", true));

            } else {
                // Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(context);

        Long newrowid = db.addForm(MainApp.fc);

        MainApp.fc.set_ID(String.valueOf(newrowid));

        if (newrowid != 0) {
            //Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fc.setUID((MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFormID();

            return true;
        } else {
            //  Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void BtnEnd() {
        final Intent endingIntent = new Intent(context, EndingActivity.class).putExtra("complete", false);
//Completed (5): Add a prompt "Do you really want to exit?Yes/No" on click on exit button
        // Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder
                .setMessage("Do you want to Exit??")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
//                                    if (formValidation()) {
                                try {
                                    SaveDraft();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (UpdateDB()) {
                                    finish();
                                    startActivity(endingIntent);
                                } else {
                                    Toast.makeText(context, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
                                }
//                                    }
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    private void SaveDraft() throws JSONException {
        MainApp.fc = new FormsContract();
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setDevicetagID(MainApp.getTagName(context));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setsMrno(bi.lbrMrno.getText().toString());
        MainApp.fc.setsStudyid(bi.lbrStudyid.getText().toString());
        MainApp.fc.setFormtype(MainApp.FORMTYPE_LAB);
        setGPS(); //Set GPS

        JSONObject labr = new JSONObject();
        // labr.put("studyid", bi.lbr_studyid.getText().toString());
//        labr.put("mrno", bi.lbr_mrno.getText().toString());
        labr.put("reportdate", dtToday);

        labr.put("hb", bi.slrhb.getText().toString());
        labr.put("wbc", bi.slrwbc.getText().toString());
        labr.put("neutrophils", bi.slrneu.getText().toString());
        labr.put("lymphocytes", bi.slrlym.getText().toString());
        labr.put("platelets", bi.slrpla.getText().toString());
        labr.put("crpcount", bi.slrcrp.getText().toString());
        labr.put("organismname1", bi.slrorg1.getText().toString());
        labr.put("organismname2", bi.slrorg2.getText().toString());


        MainApp.fc.setsFup(String.valueOf(labr));

    }

    private void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");

            if (lat == "0" && lang == "0") {
                // Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                //  Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            MainApp.fc.setGpsLat(lat);
            MainApp.fc.setGpsLng(lang);
            MainApp.fc.setGpsAcc(acc);
            MainApp.fc.setGpsDT(date); // Timestamp is converted to date above

            //Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }

}
