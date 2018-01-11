package edu.aku.hassannaqvi.slab.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityEligibilityBinding;
import edu.aku.hassannaqvi.slab.databinding.ActivityPhysicalExaminationBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class PhysicalExaminationActivity extends Activity {

    private static final String TAG = PhysicalExaminationActivity.class.getName();

    ActivityPhysicalExaminationBinding binding;
    int check = 0;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_eligibility);
        db = new DatabaseHelper(this);

//        Get data from Main Activity
        check = getIntent().getExtras().getInt("check");


//        Assigning data to UI binding
        PhysicalExaminationActivity.checking ch = new PhysicalExaminationActivity.checking(check);
        //binding.setCheckFlag(ch);
        binding.setCallback(this);

//        Setting DATETIME picker and spinners

//        Main Working from here
//        Skip Patterns
    }


    public Boolean formValidation() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyTextBox(this, binding.sfu14, getString(R.string.sfu14))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sfu15, getString(R.string.sfu15))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sfu16, getString(R.string.sfu16))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu17, binding.sfu17b, getString(R.string.sfu17))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1701, binding.sfu1701e, getString(R.string.sfu1701))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1801, binding.sfu180188, getString(R.string.sfu1801))) {
            return false;
        }


        if (binding.sfu180188.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu180188x, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1802, binding.sfu180288, getString(R.string.sfu1802))) {
            return false;
        }


        if (binding.sfu180288.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu180288x, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1803, binding.sfu1803b, getString(R.string.sfu1803))) {
            return false;
        }


        if (binding.sfu1803b.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu1803bx, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1804, binding.sfu180488, getString(R.string.sfu1804))) {
            return false;
        }


        if (binding.sfu180488.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu180488x, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1805, binding.sfu1805c, getString(R.string.sfu1805))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1806, binding.sfu180688, getString(R.string.sfu1806))) {
            return false;
        }


        if (binding.sfu180688.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu180688x, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1807, binding.sfu180788, getString(R.string.sfu1807))) {
            return false;
        }


        if (binding.sfu180788.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu180788x, getString(R.string.others))) {
            return false;
        }


        return true;
    }

    public void BtnContinue() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                finish();


            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnEnd() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                finish();

                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();

        MainApp.fc.setDevicetagID(sharedPref.getString("tagName", null));
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        setGPS(); //Set GPS

        JSONObject sa = new JSONObject();


        sa.put("sfu14", binding.sfu14.getText().toString());
        sa.put("sfu15", binding.sfu15.getText().toString());
        sa.put("sfu16", binding.sfu16.getText().toString());

        sa.put("sfu17", binding.sfu17a.isChecked() ? "1"
                : binding.sfu17b.isChecked() ? "2"
                : "0");


        sa.put("sfu1701", binding.sfu1701a.isChecked() ? "1"
                : binding.sfu1701b.isChecked() ? "2"
                : binding.sfu1701c.isChecked() ? "3"
                : binding.sfu1701d.isChecked() ? "4"
                : binding.sfu1701e.isChecked() ? "5"
                : "0");


        sa.put("sfu1801", binding.sfu1801a.isChecked() ? "1"
                : binding.sfu1801b.isChecked() ? "2"
                : binding.sfu1801c.isChecked() ? "3"
                : binding.sfu1801d.isChecked() ? "4"
                : binding.sfu180188.isChecked() ? "88"
                : "0");

        sa.put("sfu180188x", binding.sfu180188x.getText().toString());


        sa.put("sfu1802", binding.sfu1802a.isChecked() ? "1"
                : binding.sfu1802b.isChecked() ? "2"
                : binding.sfu1802c.isChecked() ? "3"
                : binding.sfu1802d.isChecked() ? "4"
                : binding.sfu1802e.isChecked() ? "5"
                : binding.sfu1802f.isChecked() ? "6"
                : binding.sfu180188.isChecked() ? "88"
                : "0");


        sa.put("sfu180188x", binding.sfu180188x.getText().toString());


        sa.put("sfu1803", binding.sfu1803a.isChecked() ? "1"
                : binding.sfu1803b.isChecked() ? "2"
                : "0");


        sa.put("sfu1803bx", binding.sfu1803bx.getText().toString());


        sa.put("sfu1804", binding.sfu1804a.isChecked() ? "1"
                : binding.sfu1804b.isChecked() ? "2"
                : binding.sfu180488.isChecked() ? "88"
                : "0");

        sa.put("sfu180488x", binding.sfu180488x.getText().toString());


        sa.put("sfu1805", binding.sfu1805a.isChecked() ? "1"
                : binding.sfu1805b.isChecked() ? "2"
                : binding.sfu1805c.isChecked() ? "3"
                : "0");


        sa.put("sfu1806", binding.sfu1806a.isChecked() ? "1"
                : binding.sfu1806b.isChecked() ? "2"
                : binding.sfu180688.isChecked() ? "88"
                : "0");

        sa.put("sfu180688x", binding.sfu180688x.getText().toString());


        sa.put("sfu1807", binding.sfu1807a.isChecked() ? "1"
                : binding.sfu1807b.isChecked() ? "2"
                : binding.sfu180788.isChecked() ? "88"
                : "0");


        sa.put("sfu180788x", binding.sfu180788x.getText().toString());


        MainApp.fc.setsA(String.valueOf(sa));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {

        Long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fc.setUID(
                    (MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFormID();

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            MainApp.fc.setGpsLat(lat);
            MainApp.fc.setGpsLng(lang);
            MainApp.fc.setGpsAcc(acc);
            MainApp.fc.setGpsDT(date); // Timestamp is converted to date above

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }


    public class checking {
        int check;

        public checking(int check) {
            this.check = check;
        }

        public int getCheck() {
            return check;
        }
    }

}