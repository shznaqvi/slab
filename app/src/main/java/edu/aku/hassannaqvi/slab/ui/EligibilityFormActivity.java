package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.ScrollView;
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
import edu.aku.hassannaqvi.slab.other.DateUtils;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class EligibilityFormActivity extends AppCompatActivity {
    ActivityEligibilityFormBinding bi;
    DatabaseHelper db;
    String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
    String dtToday = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime());
    String tToday = new SimpleDateFormat("HH:mm").format(new Date().getTime());
    int length = 0;
    private static final String TAG = EligibilityFormActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_eligibility_form);
        db = new DatabaseHelper(this);
        bi.setCallback(this);

        setupView();
        ScrollView el_scrollview = findViewById(R.id.el_scrollview);
//        validatorClass.setScrollViewFocus(bi.el_scrollview);
        validatorClass.setScrollViewFocus(el_scrollview);


    }

    private void setupView() {
        bi.sel03d.setManager(getSupportFragmentManager());
        bi.sel03d.setMaxDate(dateToday);
        bi.sel03d.setMinDate( DateUtils.getThreeDaysBack("dd/MM/yyyy",-3));
        bi.sel03t.setManager(getSupportFragmentManager());
        bi.sel03t.setTimeFormat("HH:mm");
        bi.sel03t.setIs24HourView(true);
        bi.sel01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                bi.sel01.setInputType(InputType.TYPE_CLASS_NUMBER);
                length = charSequence.toString().length();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                clearFields();


                if (!bi.sel01.getText().toString().isEmpty() && bi.sel01.getText().toString().length() == 3) {
                    if (bi.sel01.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 4) {
                            bi.sel01.setText(bi.sel01.getText().toString() + "-");
                            bi.sel01.setSelection(bi.sel01.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }
                if (!bi.sel01.getText().toString().isEmpty() && bi.sel01.getText().toString().length() == 6) {
                    if (bi.sel01.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 7) {
                            bi.sel01.setText(bi.sel01.getText().toString() + "-");
                            bi.sel01.setSelection(bi.sel01.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
//        bi.sel03t.setManager(getSupportFragmentManager());
        bi.sel12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sel12b && bi.sel13a.isChecked() && bi.sel14b.isChecked() && bi.sel15b.isChecked() && bi.sel16b.isChecked() && (bi.sel17b.isChecked() || bi.sel17c.isChecked())){
                    bi.sel18a.setChecked(true);
                }else{
                    bi.sel18b.setChecked(true);
                }
            }
        });
        bi.sel13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sel13a && bi.sel12b.isChecked() && bi.sel14b.isChecked() && bi.sel15b.isChecked() && bi.sel16b.isChecked() && (bi.sel17b.isChecked() || bi.sel17c.isChecked())){
                    bi.sel18a.setChecked(true);
                }else{
                    bi.sel18b.setChecked(true);
                }
            }
        });
        bi.sel14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sel14b && bi.sel12b.isChecked() && bi.sel13a.isChecked() && bi.sel15b.isChecked() && bi.sel16b.isChecked() && (bi.sel17b.isChecked() || bi.sel17c.isChecked())){
                    bi.sel18a.setChecked(true);
                }else{
                    bi.sel18b.setChecked(true);
                }
            }
        });
        bi.sel15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sel15b && bi.sel12b.isChecked() && bi.sel13a.isChecked() && bi.sel14b.isChecked() && bi.sel16b.isChecked() && (bi.sel17b.isChecked() || bi.sel17c.isChecked())){
                    bi.sel18a.setChecked(true);
                }else{
                    bi.sel18b.setChecked(true);
                }
            }
        });
        bi.sel16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sel16b && bi.sel12b.isChecked() && bi.sel13a.isChecked() && bi.sel14b.isChecked() && bi.sel15b.isChecked() && (bi.sel17b.isChecked() || bi.sel17c.isChecked())){
                    bi.sel18a.setChecked(true);
                }else{
                    bi.sel18b.setChecked(true);
                }
            }
        });
        bi.sel17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if((i == R.id.sel17b || i == R.id.sel17c) && bi.sel12b.isChecked() && bi.sel13a.isChecked() && bi.sel14b.isChecked() && bi.sel15b.isChecked() && bi.sel16b.isChecked()){
                    bi.sel18a.setChecked(true);
                }else{
                    bi.sel18b.setChecked(true);
                }
            }
        });


    }
    private boolean formValidation() {
        String regex = "^\\d*\\.\\d+|\\d+\\.\\d*$";
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

        if (!validatorClass.RangeTextBox(this, bi.sel08w, 28, 36, getString(R.string.sel08) + " - " + getString(R.string.weeks), " weeks")) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.sel08d, getString(R.string.sel08) + " - " + getString(R.string.days))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, bi.sel08d, 0, 6, getString(R.string.sel08) + " - " + getString(R.string.days), " days")) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.sel09, getString(R.string.sel09))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, bi.sel09, 1000, 2500, getString(R.string.sel09) ," Weight")) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.sel10, getString(R.string.sel10))) {
            return false;
        }
        if(!bi.sel10.getText().toString().contains(".")){
            Toast.makeText(this,"Length of neonate should be in decimal",Toast.LENGTH_SHORT);
            bi.sel10.setError("Length of neonate should be in decimal");
            return false;
        }else{
            bi.sel10.setError(null);
        }

        if(!bi.sel10.getText().toString().matches(regex)){
            Toast.makeText(this,"Invalid decimal number",Toast.LENGTH_SHORT);
            bi.sel10.setError("Invalid decimal number");
            return false;
        }else{
            bi.sel11.setError(null);
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel11, getString(R.string.sel11))) {
            return false;
        }
        /*if (!validatorClass.RangeTextBox(this, bi.sel11,1.0,4.0, getString(R.string.sel11)," cm")) {
            return false;
        }*/
        if(!bi.sel11.getText().toString().contains(".")){
            Toast.makeText(this,"Head of Circumference should be decimal",Toast.LENGTH_SHORT);
            bi.sel11.setError("Head of Circumference should be decimal");
            return false;
        }else{
            bi.sel11.setError(null);
        }
        if(!bi.sel11.getText().toString().matches(regex)){
            Toast.makeText(this,"Invalid decimal number",Toast.LENGTH_SHORT);
            bi.sel11.setError("Invalid decimal number");
            return false;
        }else{
            bi.sel11.setError(null);
        }

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
                Boolean consent = false;
                if(bi.sel19a.isChecked()){
                    consent = true;
                }else{
                    consent = false;
                }
                    startActivity(new Intent(this, EndingActivity.class).putExtra("complete", consent));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        Long newrowid = db.addForm(MainApp.fc);

        MainApp.fc.set_ID(String.valueOf(newrowid));

        if (newrowid != 0) {
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
      //  Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        onBackPressed();
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
        MainApp.fc.setsMrno(bi.sel01.getText().toString());
        MainApp.fc.setFormtype(MainApp.FORMTYPE_EL);
        if (bi.sel18a.isChecked()){
            MainApp.fc.setIsEl("1");
        }else{
            MainApp.fc.setIsEl("2");
        }
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
