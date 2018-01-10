package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
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
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class EligibilityActivity extends AppCompatActivity {

    private static final String TAG = EligibilityActivity.class.getName();

    ActivityEligibilityBinding binding;
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
        checking ch = new checking(check);
        //binding.setCheckFlag(ch);
        binding.setCallback(this);

//        Setting DATETIME picker and spinners

//        Main Working from here
//        Skip Patterns

    }

    public Boolean formValidation() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyTextBox(this, binding.sel01, getString(R.string.sel01))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel02w, getString(R.string.weeks))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel02d, getString(R.string.days))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sel03, binding.sel03b, getString(R.string.sel03))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sel04, binding.sel04b, getString(R.string.sel04))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sel05, binding.sel05b, getString(R.string.sel05))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sel06, binding.sel06b, getString(R.string.sel06))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sel07, binding.sel07b, getString(R.string.sel07))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sel08, binding.sel08b, getString(R.string.sel08))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sel10, binding.sel10b, getString(R.string.sel10))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel11, getString(R.string.sel11))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel14, getString(R.string.sel14))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel15, getString(R.string.sel15))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel16, getString(R.string.sel16))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel17, getString(R.string.sel17))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel18a, getString(R.string.sel18a))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel18b, getString(R.string.sel18b))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel18c, getString(R.string.sel18c))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel18c, getString(R.string.sel18c))) {
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


        sa.put("sel01", binding.sel01.getText().toString());
        sa.put("sel02w", binding.sel02w.getText().toString());
        sa.put("sel02d", binding.sel02d.getText().toString());

        sa.put("sel03", binding.sel03a.isChecked() ? "1" : binding.sel03b.isChecked() ? "2" : "0");
        sa.put("sel04", binding.sel04a.isChecked() ? "1" : binding.sel04b.isChecked() ? "2" : "0");
        sa.put("sel05", binding.sel05a.isChecked() ? "1" : binding.sel05b.isChecked() ? "2" : "0");
        sa.put("sel06", binding.sel06a.isChecked() ? "1" : binding.sel06b.isChecked() ? "2" : "0");
        sa.put("sel07", binding.sel07a.isChecked() ? "1" : binding.sel07b.isChecked() ? "2" : "0");
        sa.put("sel08", binding.sel08a.isChecked() ? "1" : binding.sel08b.isChecked() ? "2" : "0");
        sa.put("sel10", binding.sel10a.isChecked() ? "1" : binding.sel10b.isChecked() ? "2" : "0");

        sa.put("sel11", binding.sel11.getText().toString());
        sa.put("sel12", binding.sel12.getText().toString());
        sa.put("sel14", binding.sel14.getText().toString());
        sa.put("sel15", binding.sel15.getText().toString());
        sa.put("sel16", binding.sel16.getText().toString());
        sa.put("sel17", binding.sel17.getText().toString());
        sa.put("sel18a", binding.sel18a.getText().toString());
        sa.put("sel18b", binding.sel18b.getText().toString());
        sa.put("sel18c", binding.sel18c.getText().toString());


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