package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.HistoryContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityHistoryBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class HistoryActivity extends AppCompatActivity {
ActivityHistoryBinding bi;
DatabaseHelper db;
    String noofSachet, childName , localmrno, localstudyID;
    int fuplocation;
    String dtToday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_history);
        db = new DatabaseHelper(this);
        bi.setCallback(this);
        dtToday = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime());
        gettingIntents();
        setUpView();
    }

    private void setUpView() {
        bi.ChildName.setText(childName);
        bi.sfudaytext.setText("Day "+MainApp.hiCount+" (have you given the supplement)?");
        bi.sfu11a1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sfu11a11){
                    bi.llfldgrppartial.setVisibility(View.GONE);
                    bi.sfu11a2.clearCheck();
                }
                else{
                    bi.llfldgrppartial.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
       Toast.makeText(this,"You can't go back",Toast.LENGTH_SHORT).show();
    }

    private void gettingIntents() {
        Intent intent = getIntent();
        if (intent.hasExtra(FollowUpFormActivity.FUPLOCATION_TAG) && intent.hasExtra("childName") && intent.hasExtra("noofSachet") && intent.hasExtra("mrno") && intent.hasExtra("studyID")) {
            Bundle bundle = intent.getExtras();
            fuplocation = bundle.getInt(FollowUpFormActivity.FUPLOCATION_TAG);
            childName = bundle.getString("childName");
            noofSachet = bundle.getString("noofSachet");
            localmrno = bundle.getString("mrno");
            localstudyID = bundle.getString("studyID");
        } else {
            // Do something else
            Toast.makeText(this, "Restart your app or contact your support team!", Toast.LENGTH_SHORT);
        }
    }
    public void BtnEnd() {

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
           //     Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();
                finish();

                if (MainApp.hiCount == Integer.parseInt(noofSachet)) {
                    MainApp.hiCount = 1;
                    startActivity(new Intent(this, FeedingPracticeActivity.class).putExtra(FollowUpFormActivity.FUPLOCATION_TAG, fuplocation).putExtra("childName", bi.ChildName.getText().toString())
                            .putExtra("mrno",localmrno)
                            .putExtra("studyID",localstudyID));
                }
                else{
                    MainApp.hiCount++;
                    startActivity(new Intent(this, HistoryActivity.class)
                            .putExtra(FollowUpFormActivity.FUPLOCATION_TAG, fuplocation)
                            .putExtra("childName", bi.ChildName.getText().toString())
                            .putExtra("noofSachet",noofSachet)
                            .putExtra("mrno",localmrno)
                            .putExtra("studyID",localstudyID));
                }
                //startActivity(new Intent(this, SachetActivity.class).putExtra("openExamSec", defaultValue).putExtra("childName", bi.ChildName.getText().toString()).putExtra("noofSachet",bi.sfu10.getText().toString()));

//                startActivity(new Intent(this, FeedingPracticeActivity.class).putExtra("openExamSec", nextExamSec).putExtra("childName", bi.ChildName.getText().toString()));
                //startActivity(new Intent(this, SachetActivity.class).putExtra("openExamSec", defaultValue).putExtra("childName", bi.ChildName.getText().toString()).putExtra("noofSachet",bi.sfu10.getText().toString()));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        Long newrowid = db.addHistory(MainApp.hc);

        MainApp.hc.set_ID(String.valueOf(newrowid));

        if (newrowid != 0) {
         //   Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.hc.set_UID((MainApp.hc.getDeviceID() + MainApp.hc.get_ID()));
            db.updateHistoryID();




            return true;
        } else {
            //Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() throws JSONException{
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.hc = new HistoryContract();
        MainApp.hc.setUser(MainApp.userName);
        MainApp.hc.setFormDate(dtToday);
        MainApp.hc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.hc.setDevicetagID(MainApp.getTagName(this));
        MainApp.hc.setUUID(MainApp.fc.get_ID());
        MainApp.hc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.hc.setsMrno(localmrno);
        MainApp.hc.setFormtype(MainApp.FORMTYPE_Fup);
        MainApp.hc.setIsEl("1");
        MainApp.hc.setsStudyid(localstudyID);
        MainApp.hc.setNoofDays(noofSachet);
        MainApp.hc.setNoofSachet(noofSachet);
        MainApp.hc.setIsinserted("1");
        setGPS(); //Set GPS
        JSONObject his = new JSONObject();
        his.put("sfuqty", bi.sfu11a11.isChecked() ? "1"
                : bi.sfu11a12.isChecked() ? "2"
                : "0");
            his.put("sfuifpartial", bi.sfu11a21.isChecked() ? "1"
                    : bi.sfu11a22.isChecked() ? "2"
                    : "0");
        MainApp.hc.setSfu11(String.valueOf(his));
    }
    private void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");

            if (lat == "0" && lang == "0") {
                //Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
               // Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            MainApp.fc.setGpsLat(lat);
            MainApp.fc.setGpsLng(lang);
            MainApp.fc.setGpsAcc(acc);
            MainApp.fc.setGpsDT(date); // Timestamp is converted to date above

            //Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            //Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }
    private boolean formValidation() {
        if (!validatorClass.EmptyRadioButton(this, bi.sfu11a1, bi.sfu11a11, getString(R.string.sfuifqty))) {
            return false;
        }
        if (!bi.sfu11a11.isChecked()){
            if (!validatorClass.EmptyRadioButton(this, bi.sfu11a2, bi.sfu11a21, getString(R.string.sfuifpartial))) {
                return false;
            }
        }
        return true;
    }


}
