package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.JsonModelClasses.RecruitmentJSONModel;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityFollowUpFormBinding;
import edu.aku.hassannaqvi.slab.other.JSONUtilClass;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class FollowUpFormActivity extends AppCompatActivity {
    ActivityFollowUpFormBinding bi;
    DatabaseHelper db;
    String dtToday = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime());
    String todaysDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date().getTime());
    FormsContract fc, fc_1;
    RecruitmentJSONModel recmodel;
    public String localMrno;
    public String localStudyID;
    String childName;
    int noofsachet;


    private static final String TAG = FollowUpFormActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_follow_up_form);
        db = new DatabaseHelper(this);
        bi.setCallback(this);

        checkIntents();
        setupView();
        fc = new FormsContract();
        fc_1 = new FormsContract();
        recmodel = new RecruitmentJSONModel();
    }

    private void checkIntents() {
        Intent intent = getIntent();
        if (intent.hasExtra(MainApp.MRNO_TAG) && intent.hasExtra(MainApp.STUDYID_TAG) && intent.hasExtra(MainApp.CHILDNAME_TAG)) {
            Bundle bundle = intent.getExtras();
            localMrno = bundle.getString(MainApp.MRNO_TAG);
            localStudyID = bundle.getString(MainApp.STUDYID_TAG);
            childName = bundle.getString(MainApp.CHILDNAME_TAG);
            bi.sfu001.setText(localMrno);
            bi.sfu002.setText(localStudyID);
            bi.ChildName.setText(childName);

            bi.sfu001.setEnabled(false);
            bi.btnCheckMrno.setVisibility(View.GONE);
            bi.fldGrpA.setVisibility(View.VISIBLE);
            bi.fldGrpB.setVisibility(View.VISIBLE);

        } else {
            bi.sfu001.setEnabled(true);
            bi.btnCheckMrno.setVisibility(View.VISIBLE);
            bi.fldGrpA.setVisibility(View.GONE);
            bi.fldGrpB.setVisibility(View.GONE);

            // Do something else
            Toast.makeText(this, "Restart your app or contact your support team!", Toast.LENGTH_SHORT);

        }

    }

    private void setupView() {
        bi.sfu01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu01c) {
                    bi.fldGrpsfu06.setVisibility(View.GONE);
                    bi.sfu06.setText(null);
                    bi.sfu07.setText(null);
                    bi.sfu08.setText(null);
                } else if (i == R.id.sfu01b) {
                    bi.fldGrpsfu07.setVisibility(View.VISIBLE);
                    bi.fldGrpsfu06.setVisibility(View.VISIBLE);
                } else if (i == R.id.sfu01a) {
                    bi.fldGrpsfu07.setVisibility(View.GONE);
                    bi.sfu07.setText(null);
                    bi.sfu08.setText(null);
                    bi.fldGrpsfu06.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    private boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyTextBox(this, bi.sfu001, getString(R.string.mrno))) {
            return false;
        }
        if (bi.sfu001.getText().toString().length() == 9) {
            String[] str = bi.sfu001.getText().toString().split("-");
            if (str.length > 3 || bi.sfu001.getText().toString().charAt(3) != '-' || bi.sfu001.getText().toString().charAt(6) != '-') {
                bi.sfu001.setError("Wrong presentation!!");
                return false;
            }
        } else {
            Toast.makeText(this, "Invalid length: " + getString(R.string.mrno), Toast.LENGTH_SHORT).show();
            bi.sfu001.setError("Invalid length");
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu01, bi.sfu01a, getString(R.string.sfu01c))) {
            return false;
        }

        if (!bi.sfu01c.isChecked()) {

            if (!validatorClass.EmptyTextBox(this, bi.sfu06, getString(R.string.sfu06))) {
                return false;
            }
            if (!validatorClass.RangeTextBox(this, bi.sfu06, 1000, 2500, getString(R.string.sfu06), " Weight")) {
                return false;
            }
            if (!bi.sfu01a.isChecked()) {

                if (!validatorClass.EmptyTextBox(this, bi.sfu07, getString(R.string.sfu07))) {
                    return false;
                }
                if (!bi.sfu07.getText().toString().contains(".")) {
                    Toast.makeText(this, "Length of neonate should be in decimal", Toast.LENGTH_SHORT);
                    bi.sfu07.setError("Length of neonate should be in decimal");
                    return false;
                } else {
                    bi.sfu07.setError(null);
                }
                if (!validatorClass.EmptyTextBox(this, bi.sfu08, getString(R.string.sfu08))) {
                    return false;
                }
                if (!bi.sfu08.getText().toString().contains(".")) {
                    Toast.makeText(this, "Head of Circumference should be decimal", Toast.LENGTH_SHORT);
                    bi.sfu08.setError("Head of Circumference should be decimal");
                    return false;
                } else {
                    bi.sfu08.setError(null);
                }

            }
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu10, getString(R.string.sfu10))) {
            return false;
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
                Boolean defaultValue = true;
                if (bi.sfu01c.isChecked()) {
                    defaultValue = false;
                } else {
                    defaultValue = true;
                }
                //startActivity(new Intent(this, FeedingPracticeActivity.class).putExtra("openExamSec", defaultValue).putExtra("childName", bi.ChildName.getText().toString()));
                if (!bi.sfu10.getText().toString().equals("0")) {
                    startActivity(new Intent(this, HistoryActivity.class)
                            .putExtra("openExamSec", defaultValue)
                            .putExtra("childName", bi.ChildName.getText().toString())
                            .putExtra("noofSachet", bi.sfu10.getText().toString())
                            .putExtra("mrno", bi.sfu001.getText().toString())
                            .putExtra("studyID", bi.sfu002.getText().toString()));
                } else {
                    startActivity(new Intent(this, FeedingPracticeActivity.class)
                            .putExtra("openExamSec", defaultValue)
                            .putExtra("childName", bi.ChildName.getText().toString())
                            .putExtra("mrno", bi.sfu001.getText().toString())
                            .putExtra("studyID", bi.sfu002.getText().toString()));
                }
            } else {
               // Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

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
        // Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                //     Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                finish();

                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void SaveDraft() throws JSONException {
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setDevicetagID(MainApp.getTagName(this));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setsMrno(bi.sfu001.getText().toString());
        MainApp.fc.setsStudyid(bi.sfu002.getText().toString());
        MainApp.fc.setFormtype(MainApp.FORMTYPE_Fup);
        MainApp.fc.setIsinserted("1");
        setGPS(); //Set GPS

        JSONObject fu = new JSONObject();
        recmodel = JSONUtilClass.getModelFromJSON(fc_1.getsRecr(), RecruitmentJSONModel.class);
        fu.put("sfudatetime", dtToday);
        fu.put("uuid", recmodel.getUUID());
        fu.put("childName", recmodel.getChildName());
        fu.put("sfu01", bi.sfu01a.isChecked() ? "1"
                : bi.sfu01b.isChecked() ? "2"
                : bi.sfu01c.isChecked() ? "3"
                : "0");
        fu.put("sfu06", bi.sfu06.getText().toString());
        fu.put("sfu07", bi.sfu07.getText().toString());
        fu.put("sfu08", bi.sfu08.getText().toString());

        MainApp.fc.setsFup(String.valueOf(fu));

    }

    public void btnCheckMrno() {

        if (!bi.sfu001.getText().toString().trim().isEmpty() && !bi.sfu002.getText().toString().trim().isEmpty()) {

            // fc = db.getStudyID(bi.sfu001.getText().toString());
            fc_1 = db.getChildName(bi.sfu001.getText().toString(), bi.sfu002.getText().toString());


            if (!fc_1.getsMrno().isEmpty() && !fc_1.getsStudyid().isEmpty()) {

                //TODO() : checking followup already inserted today or not!!

                if (!db.iffupexist(bi.sfu001.getText().toString(), bi.sfu002.getText().toString(), todaysDate+" 00:00", dtToday)) {
                    recmodel = JSONUtilClass.getModelFromJSON(fc_1.getsRecr(), RecruitmentJSONModel.class);
                   // Toast.makeText(this, "MR Number found!", Toast.LENGTH_SHORT).show();
                    bi.fldGrpA.setVisibility(View.VISIBLE);
                    bi.fldGrpB.setVisibility(View.VISIBLE);
                    bi.sfu002.setText(fc_1.getsStudyid());
                    bi.ChildName.setText(recmodel.getChildName());
                    MainApp.fetchLocal = true;
                } else {
                    Toast.makeText(this, "You have already inserted a followup Today!", Toast.LENGTH_SHORT).show();
                }


            } else {
                bi.fldGrpA.setVisibility(View.GONE);
                bi.fldGrpB.setVisibility(View.GONE);

                Toast.makeText(this, "No MR No or Study ID found!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Please Enter correct MrNo and Study ID", Toast.LENGTH_SHORT).show();
        }

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
