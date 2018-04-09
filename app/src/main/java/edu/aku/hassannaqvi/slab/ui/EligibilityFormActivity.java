package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityEligibilityFormBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class EligibilityFormActivity extends AppCompatActivity {
    ActivityEligibilityFormBinding bi;
    DatabaseHelper db;
    String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    private static final String TAG = EligibilityFormActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_eligibility_form);
        db = new DatabaseHelper(this);
        bi.setCallback(this);

        setupView();
    }

    private void setupView() {
        bi.sel03d.setManager(getSupportFragmentManager());
        bi.sel03d.setMaxDate(dateToday);

        bi.sel03t.setManager(getSupportFragmentManager());

        bi.sen03.setManager(getSupportFragmentManager());
        bi.sen03.setMaxDate(dateToday);

        bi.sen09d.setManager(getSupportFragmentManager());
        bi.sen09d.setMaxDate(dateToday);

        bi.sen09t.setManager(getSupportFragmentManager());

        bi.sen08.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sen08b) {
                    bi.fldGrpsen09.setVisibility(View.GONE);
                    bi.sen10.clearCheck();
                    bi.sen11.clearCheck();
                } else {
                    bi.fldGrpsen09.setVisibility(View.VISIBLE);
                }
            }
        });
        bi.sen10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != R.id.sen10b) {
                    bi.fldGrpsen11.setVisibility(View.GONE);
                   bi.sen11.clearCheck();
                } else {
                    bi.fldGrpsen11.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private boolean formValidation() {

        if (!validatorClass.EmptyTextBox(this, bi.sel01, getString(R.string.mrno))) {
            bi.sel01err.setVisibility(View.VISIBLE);

            return false;
        }
        if (bi.sel01.getText().toString().length() == 9) {
            String[] str = bi.sel01.getText().toString().split("-");
            if (str.length > 3 || bi.sel01.getText().toString().charAt(3) != '-' || bi.sel01.getText().toString().charAt(6) != '-') {
                bi.sel01.setError("Wrong presentation!!");
                return false;
            }
        } else {
            Toast.makeText(this, "Invalid length: " + getString(R.string.mrno), Toast.LENGTH_SHORT).show();
            bi.sel01.setError("Invalid length");
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel02, getString(R.string.childname))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.sel03d, getString(R.string.sel03) + " - " + getString(R.string.date))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel03t, getString(R.string.sel03) + " - " + getString(R.string.time))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel04, bi.sel04b, getString(R.string.sel04))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel05, bi.sel0596, bi.sel0596x, getString(R.string.sel05))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel06, getString(R.string.sel06))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel07, getString(R.string.sel07))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, bi.sel08w, getString(R.string.sel08) + " - " + getString(R.string.weeks))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, bi.sel08d, getString(R.string.sel08) + " - " + getString(R.string.days))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, bi.sel08d, 0, 6, getString(R.string.sel08) + " - " + getString(R.string.days), " days")) {
            return false;
        }
      /*  double kg = Double.parseDouble(bi.sel09.getText().toString());
        if (!validatorClass.EmptyTextBox(this, bi.sel09, getString(R.string.sel09))) {
            if (kg > 1.0 && kg < 2.5) {
                return false;
            } else {
                Toast.makeText(this, "Values Must be between 1 to 2.5: " + getString(R.string.sel09), Toast.LENGTH_SHORT).show();
                bi.sel09.setError("Invalid length");
                return false;
            }

        }*/
        if (!validatorClass.EmptyRadioButton(this, bi.sel12, bi.sel12a, getString(R.string.sel12))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel13, bi.sel13a, getString(R.string.sel13))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel14, bi.sel14a, getString(R.string.sel14))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel15, bi.sel15a, getString(R.string.sel15))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel16, bi.sel16a, getString(R.string.sel16))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel17, bi.sel17a, getString(R.string.sel17))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sel18, bi.sel18a, getString(R.string.sel18))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sel19, bi.sel19b, bi.sel19bx, getString(R.string.sel19))) {
            return false;
        }

        if (bi.sel12a.isChecked()) {
            if (bi.sel15b.isChecked() && bi.sel16b.isChecked() && bi.sel17b.isChecked()) {
                Toast.makeText(this, "Success!!! " + getString(R.string.sel12), Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(this, "Error at " + getString(R.string.sel15), Toast.LENGTH_SHORT).show();
                return false;
            }
        }


//        Enrolment
        if (!validatorClass.EmptyTextBox(this, bi.sen01, getString(R.string.studyid))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sen02, getString(R.string.sen02))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sen03, getString(R.string.sen03) + " - " + getString(R.string.date))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.sen04, getString(R.string.sen04))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sen05, getString(R.string.sen05))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sen06, getString(R.string.sen06))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.sen07, getString(R.string.sen07))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sen08, bi.sen08a, getString(R.string.sen08))) {
            return false;
        }
        if (bi.sen08a.isChecked()) {

            if (!validatorClass.EmptyTextBox(this, bi.sen09d, getString(R.string.sen09) + " - " + getString(R.string.date))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sen09t, getString(R.string.sen09) + " - " + getString(R.string.time))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bi.sen10, bi.sen10a, getString(R.string.sen10))) {
                return false;
            }
            if (bi.sen10a.isChecked()) {
                if (!validatorClass.EmptyRadioButton(this, bi.sen11, bi.sen11a, getString(R.string.sen11))) {
                    return false;
                }
            }
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
                finish();
                    startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {



        Long updcount = db.addForm(MainApp.fc);

        MainApp.fc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fc.setUID((MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFormID();

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
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

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setDevicetagID(MainApp.getTagName(this));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        setGPS(); //Set GPS

        JSONObject ef = new JSONObject();
        ef.put("sel01", bi.sel01.getText().toString());

        ef.put("sel02", bi.sel02.getText().toString());

        ef.put("sel03d", bi.sel03d.getText().toString());
        ef.put("sel03t", bi.sel03t.getText().toString());

        ef.put("sel04", bi.sel04a.isChecked() ? "1"
                : bi.sel04b.isChecked() ? "2"
                : bi.sel04c.isChecked() ? "3"
                : "0");
        ef.put("sel05", bi.sel05a.isChecked() ? "1"
                : bi.sel05b.isChecked() ? "2"
                : bi.sel0596.isChecked() ? "96"
                : "0");
        ef.put("sel0596x", bi.sel0596x.getText().toString());

        ef.put("sel06", bi.sel06.getText().toString());
        ef.put("sel07", bi.sel07.getText().toString());

        ef.put("sel08w", bi.sel08w.getText().toString());
        ef.put("sel08d", bi.sel08d.getText().toString());


        ef.put("sel09", bi.sel09.getText().toString());
        ef.put("sel10", bi.sel10.getText().toString());
        ef.put("sel11", bi.sel11.getText().toString());

        ef.put("sel12", bi.sel12a.isChecked() ? "1"
                : bi.sel12b.isChecked() ? "2"
                : "0");
        ef.put("sel13", bi.sel13a.isChecked() ? "1"
                : bi.sel13b.isChecked() ? "2"
                : "0");
        ef.put("sel14", bi.sel14a.isChecked() ? "1"
                : bi.sel14b.isChecked() ? "2"
                : "0");
        ef.put("sel15", bi.sel15a.isChecked() ? "1"
                : bi.sel15b.isChecked() ? "2"
                : "0");
        ef.put("sel16", bi.sel16a.isChecked() ? "1"
                : bi.sel16b.isChecked() ? "2"
                : "0");
        ef.put("sel17", bi.sel17a.isChecked() ? "1"
                : bi.sel17b.isChecked() ? "2"
                : bi.sel17c.isChecked() ? "3"
                : "0");
        ef.put("sel18", bi.sel18a.isChecked() ? "1"
                : bi.sel18b.isChecked() ? "2"
                : "0");
        ef.put("sel19", bi.sel19a.isChecked() ? "1"
                : bi.sel19b.isChecked() ? "2"
                : "0");
        ef.put("sel20", bi.sel19bx.getText().toString());


//        sen starts here
        ef.put("sen01", bi.sen01.getText().toString());
        ef.put("sen02", bi.sen02.getText().toString());
        ef.put("sen03", bi.sen03.getText().toString());
        ef.put("sen04", bi.sen04.getText().toString());
        ef.put("sen05", bi.sen05.getText().toString());
        ef.put("sen06", bi.sen06.getText().toString());
        ef.put("sen07", bi.sen07.getText().toString());
        ef.put("sen08", bi.sen08a.isChecked() ? "1"
                : bi.sen08b.isChecked() ? "2"
                : "0");
        ef.put("sen09d", bi.sen09d.getText().toString());
        ef.put("sen09t", bi.sen09t.getText().toString());
        ef.put("sen10", bi.sen10a.isChecked() ? "1"
                : bi.sen10b.isChecked() ? "2"
                : "0");
        ef.put("sen11", bi.sen11a.isChecked() ? "1"
                : bi.sen11b.isChecked() ? "2"
                : bi.sen11c.isChecked() ? "3"
                : "0");
        MainApp.fc.setsEl(String.valueOf(ef));
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

}
