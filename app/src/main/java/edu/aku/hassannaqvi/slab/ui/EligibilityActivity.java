package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityEligibilityBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class EligibilityActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, TextWatcher {

    private static final String TAG = EligibilityActivity.class.getName();

    @BindViews({R.id.sel03, R.id.sel04, R.id.sel05, R.id.sel06, R.id.sel07, R.id.sel08, R.id.sel10})
    List<RadioGroup> eligibility;
    @BindViews({R.id.sel03a, R.id.sel04a, R.id.sel05a, R.id.sel06b, R.id.sel07b, R.id.sel08b, R.id.sel10a})
    List<RadioButton> eligibleYes;
    @BindViews({R.id.sel06b, R.id.sel07b, R.id.sel08b})
    List<RadioButton> eligibleNo;
    @BindViews({R.id.sel01, R.id.sel02w})
    List<EditText> eligibleEdit;


    ActivityEligibilityBinding binding;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_eligibility);
        db = new DatabaseHelper(this);
        ButterKnife.bind(this);

        binding.setCallback(this);
        setUpViews();


    }

    public void setUpViews() {

        // Eligibility check skip

        for (RadioGroup rg : eligibility) {
            rg.setOnCheckedChangeListener(this);
        }

        for (EditText ed : eligibleEdit) {
            ed.addTextChangedListener(this);
        }

    }

    public Boolean formValidation() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyTextBox(this, binding.sel01, getString(R.string.sel01))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, binding.sel02w, getString(R.string.sel02) + " - " + getString(R.string.weeks))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sel02d, getString(R.string.sel02) + " - " + getString(R.string.days))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, binding.sel02d, 0, 6, getString(R.string.sel02) + " - " + getString(R.string.days), " days")) {
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


        if (isEligibile() && (Double.valueOf(binding.sel01.getText().toString()) > 1.0
                && Double.valueOf(binding.sel01.getText().toString()) < 2.5)
                && (Integer.valueOf(binding.sel02w.getText().toString()) >= 28
                && Integer.valueOf(binding.sel02w.getText().toString()) < 36)) {

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
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();
                finish();
                if (isEligibile() && (Double.valueOf(binding.sel01.getText().toString()) > 1.0
                        && Double.valueOf(binding.sel01.getText().toString()) < 2.5)
                        && (Integer.valueOf(binding.sel02w.getText().toString()) >= 28
                        && Integer.valueOf(binding.sel02w.getText().toString()) < 36)) {
                    startActivity(new Intent(this, BaselineActivity.class));
                } else {
                    startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
                }



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
        if (isEligibile() && (Double.valueOf(binding.sel01.getText().toString()) > 1.0
                && Double.valueOf(binding.sel01.getText().toString()) < 2.5)
                && (Integer.valueOf(binding.sel02w.getText().toString()) >= 28
                && Integer.valueOf(binding.sel02w.getText().toString()) < 36)) {
            sa.put("sel09", "1");
        } else {
            sa.put("sel09", "2");
        }
        sa.put("sel10", binding.sel10a.isChecked() ? "1" : binding.sel10b.isChecked() ? "2" : "0");

        sa.put("sel11", binding.sel11.getText().toString());
        sa.put("sel12", binding.sel12.getText().toString());
        sa.put("sel13", new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
        sa.put("sel14", binding.sel14.getText().toString());
        sa.put("sel15", binding.sel15.getText().toString());
        sa.put("sel16", binding.sel16.getText().toString());
        sa.put("sel17", binding.sel17.getText().toString());
        sa.put("sel18a", binding.sel18a.getText().toString());
        sa.put("sel18b", binding.sel18b.getText().toString());
        sa.put("sel18c", binding.sel18c.getText().toString());


        MainApp.fc.setsEl(String.valueOf(sa));

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

    public boolean isEligibile() {
        int i = 0;

        for (RadioButton rb : eligibleYes) {
            if (rb.isChecked()) {
                i++;
            }
        }

        return i == eligibleYes.size();
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        if (isEligibile() && (Double.valueOf(binding.sel01.getText().toString()) > 1.0
                && Double.valueOf(binding.sel01.getText().toString()) < 2.5)
                && (Integer.valueOf(binding.sel02w.getText().toString()) >= 28
                && Integer.valueOf(binding.sel02w.getText().toString()) < 36)) {
            binding.fldGrpEligible.setVisibility(View.VISIBLE);
        } else {
            binding.fldGrpEligible.setVisibility(View.GONE);
            binding.sel11.setText(null);
            binding.sel12.setText(null);
            binding.sel14.setText(null);
            binding.sel15.setText(null);
            binding.sel16.setText(null);
            binding.sel17.setText(null);
            binding.sel18a.setText(null);
            binding.sel18b.setText(null);
            binding.sel18c.setText(null);
        }

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (!binding.sel01.getText().toString().isEmpty() && !binding.sel02w.getText().toString().isEmpty()) {
            if (isEligibile() && (Double.valueOf(binding.sel01.getText().toString()) > 1.0
                    && Double.valueOf(binding.sel01.getText().toString()) < 2.5)
                    && (Integer.valueOf(binding.sel02w.getText().toString()) >= 28
                    && Integer.valueOf(binding.sel02w.getText().toString()) < 36)) {
                binding.fldGrpEligible.setVisibility(View.VISIBLE);
            } else {
                binding.fldGrpEligible.setVisibility(View.GONE);
                binding.sel11.setText(null);
                binding.sel12.setText(null);
                binding.sel14.setText(null);
                binding.sel15.setText(null);
                binding.sel16.setText(null);
                binding.sel17.setText(null);
                binding.sel18a.setText(null);
                binding.sel18b.setText(null);
                binding.sel18c.setText(null);
            }
        }

    }





}