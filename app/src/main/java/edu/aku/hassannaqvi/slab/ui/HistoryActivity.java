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

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HistoryActivity extends AppCompatActivity {
ActivityHistoryBinding bi;
DatabaseHelper db;
    String noofSachet, childName , localmrno, localstudyID;
    int fuplocation;
    String dtToday;
    Boolean nextExamSec;
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
        bi.sfu508a.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.sfu508a01){
                    bi.llfldgrppartial.setVisibility(VISIBLE);
                }else {
                    bi.llfldgrppartial.setVisibility(GONE);
                    bi.sfu508b.clearCheck();
                }
            }
        });
        bi.sfu508b.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.sfu508b1){
                    bi.partial.setVisibility(GONE);
                    bi.missed.setVisibility(GONE);
                    bi.sfu508c.clearCheck();
                    bi.sfu508d.clearCheck();
                }else if(checkedId == R.id.sfu508b2){
                    bi.partial.setVisibility(VISIBLE);
                    bi.missed.setVisibility(GONE);
//                    bi.sfu508c.clearCheck();
                    bi.sfu508d.clearCheck();
                }else if(checkedId == R.id.sfu508b3){
                    bi.partial.setVisibility(GONE);
                    bi.missed.setVisibility(VISIBLE);
                    bi.sfu508c.clearCheck();
//                    bi.sfu508d.clearCheck();
                }
            }
        });


      /*  bi.sfu11a1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        });*/
    }
   /* private void gettingIntents() {

        Intent intent = getIntent();
        if (intent.hasExtra(FollowUpFormActivity.FUPLOCATION_TAG) && intent.hasExtra("childName") && intent.hasExtra("mrno") && intent.hasExtra("studyID")) {
            Bundle bundle = intent.getExtras();
            fupLocation = bundle.getInt(FollowUpFormActivity.FUPLOCATION_TAG);
            childName = bundle.getString("childName");
            localmrno = bundle.getString("mrno");
            localstudyID = bundle.getString("studyID");
        } else {
            // Do something else
            Toast.makeText(this, "Restart your app or contact your support team!", Toast.LENGTH_SHORT);

        }

    }*/
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
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                //Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
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



/*
                if (MainApp.hiCount == Integer.parseInt(noofSachet)) {
                    MainApp.hiCount = 1;
                    startActivity(new Intent(this, FeedingPracticeActivity.class)
                            .putExtra(FollowUpFormActivity.FUPLOCATION_TAG, fuplocation)
                            .putExtra("childName", bi.ChildName.getText().toString())
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
                */

                if (MainApp.hiCount < Integer.parseInt(noofSachet)) {
                    MainApp.hiCount++;
                    startActivity(new Intent(this, HistoryActivity.class)
                            .putExtra(FollowUpFormActivity.FUPLOCATION_TAG, fuplocation)
                            .putExtra("childName", bi.ChildName.getText().toString())
                            .putExtra("noofSachet",noofSachet)
                            .putExtra("mrno",localmrno)
                            .putExtra("studyID",localstudyID));
                }
                else{
                    MainApp.hiCount = 1;//reset counter
                    startActivity(new Intent(this, VisitOutcome.class)
                            .putExtra(FollowUpFormActivity.FUPLOCATION_TAG, fuplocation)
                            .putExtra("childName", bi.ChildName.getText().toString())
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
//        MainApp.hc.setFormDate(dtToday);  // this date has been changed by hassan bhai after data analysis.
        MainApp.hc.setFormDate(MainApp.fc.getFormDate());
        MainApp.hc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.hc.setDevicetagID(MainApp.getTagName(this));
        MainApp.hc.setUUID(MainApp.fc.getUID());/// should be uid
        MainApp.hc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.hc.setsMrno(localmrno);
        MainApp.hc.setFormtype(MainApp.FORMTYPE_Fup);
        MainApp.hc.setIsEl("1");
        MainApp.hc.setsStudyid(localstudyID);
        MainApp.hc.setNoofDays(noofSachet);
        MainApp.hc.setNoofSachet(noofSachet);
        MainApp.hc.setIsinserted("1");
       // MainApp.hc.setround(MainApp.fc.getFupround());
        MainApp.hc.setcount(String.valueOf(MainApp.hiCount));

        JSONObject his = new JSONObject();
        his.put("sfu508a", bi.sfu508a01.isChecked() ? "1"
                : bi.sfu508a02.isChecked() ? "2"
                : "0");
        his.put("sfu508b", bi.sfu508b1.isChecked() ? "1"
                : bi.sfu508b2.isChecked() ? "2"
                : bi.sfu508b3.isChecked() ? "3"
                : "0");
        his.put("sfu508c", bi.sfu508c1.isChecked() ? "1"
                : bi.sfu508c2.isChecked() ? "2"
                : bi.sfu508c3.isChecked() ? "3"
                : "0");
        his.put("sfu508d", bi.sfu508d1.isChecked() ? "1"
                : bi.sfu508d2.isChecked() ? "2"
                : bi.sfu508d3.isChecked() ? "3"
                : bi.sfu508d4.isChecked() ? "4"
                : bi.sfu508d96.isChecked() ? "96"
                : "0");
        his.put("sfu508d96x", bi.sfu508d96x.getText().toString());
        MainApp.hc.setSfu11(String.valueOf(his));
    }

    private boolean formValidation() {
        if (!validatorClass.EmptyRadioButton(this, bi.sfu508a, bi.sfu508a01, getString(R.string.sfu508a))) {
            return false;
        }
        if(bi.sfu508a01.isChecked()){
            if (!validatorClass.EmptyRadioButton(this, bi.sfu508b, bi.sfu508b1, getString(R.string.sfu508b))) {
                return false;
            }
            if (bi.sfu508b2.isChecked()){
                if (!validatorClass.EmptyRadioButton(this, bi.sfu508c, bi.sfu508c1, getString(R.string.sfu508c))) {
                    return false;
                }
            }
            if (bi.sfu508b3.isChecked()){
                if (!validatorClass.EmptyRadioButton(this, bi.sfu508d, bi.sfu508d1, bi.sfu508d96x, getString(R.string.sfu508d))) {
                    return false;
                }
            }
        }

        return true;
    }


}
