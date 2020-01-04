package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.JsonModelClasses.EligibilityJSONModel;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityRecruitmentBinding;
import edu.aku.hassannaqvi.slab.other.JSONUtilClass;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class RecruitmentActivity extends AppCompatActivity {
    ActivityRecruitmentBinding bi;
    DatabaseHelper db;
    String mrno, childName;
    String dateToday;
    String dtToday;
    FormsContract formsContract;
    EligibilityJSONModel elmodel;
    private static final String TAG = RecruitmentActivity.class.getName();
    int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bi = DataBindingUtil.setContentView(this, R.layout.activity_recruitment);
        bi.setCallback(this);
        dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
        dtToday = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime());
        bi.sen09t.setManager(getSupportFragmentManager());
        bi.sen09t.setTimeFormat("HH:mm");
        bi.sen09t.setIs24HourView(true);
        db = new DatabaseHelper(this);
        this.setTitle(getResources().getString(R.string.senheading));
        formsContract = new FormsContract();
        elmodel = new EligibilityJSONModel();
        setupView();
        ScrollView recr_scrollview = findViewById(R.id.recr_scrollview);
        validatorClass.setScrollViewFocus(recr_scrollview);
    }

    private void setupView() {
        bi.senmrno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                bi.senmrno.setInputType(InputType.TYPE_CLASS_NUMBER);
                length = charSequence.toString().length();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                clearFields();


                if (!bi.senmrno.getText().toString().isEmpty() && bi.senmrno.getText().toString().length() == 3) {
                    if (bi.senmrno.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 4) {
                            bi.senmrno.setText(bi.senmrno.getText().toString() + "-");
                            bi.senmrno.setSelection(bi.senmrno.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }
                if (!bi.senmrno.getText().toString().isEmpty() && bi.senmrno.getText().toString().length() == 6) {
                    if (bi.senmrno.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 7) {
                            bi.senmrno.setText(bi.senmrno.getText().toString() + "-");
                            bi.senmrno.setSelection(bi.senmrno.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
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
                    bi.fldGrpsen08.setVisibility(View.VISIBLE);

                } else {
                    bi.fldGrpsen09.setVisibility(View.VISIBLE);
                    bi.fldGrpsen08.setVisibility(View.GONE);
                    bi.sen08x.clearCheck();
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

    public String convertDateFormat(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = sdf.parse(dateStr);
            return new SimpleDateFormat("dd/MM/yyyy").format(d);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public void btnCheckMrno() {

        if (!bi.senmrno.getText().toString().trim().isEmpty()) {
            mrno = db.isMrnoFound(bi.senmrno.getText().toString());

            //  MainApp.fc.setsMrno(mrno);

            if (!mrno.isEmpty()) {
                if (!db.isInserted(bi.senmrno.getText().toString()).equals("1")) {
                    formsContract =  db.getelchild(mrno);
                    bi.fldGrpsen.setVisibility(View.VISIBLE);

                    bi.sen03.setMaxDate(dateToday);
                    bi.sen03.setMinDate(dateToday);

                    String currentdate = db.getDateBymrno(mrno);
                    bi.sen09d.setMinDate(convertDateFormat(currentdate));
                    bi.sen09d.setMaxDate(dateToday);

                } else {
                    Toast.makeText(this, "Child Already Enrolled!", Toast.LENGTH_SHORT).show();
                }
            } else {

                bi.fldGrpsen.setVisibility(View.GONE);

                Toast.makeText(this, "No MR Number found!", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "Not found.", Toast.LENGTH_SHORT).show();
        }


    }

    public boolean formValidation() {

//        Recruitment
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
            if (bi.sen10b.isChecked()) {
                return validatorClass.EmptyRadioButton(this, bi.sen11, bi.sen11a, getString(R.string.sen11));
            }
        }else {
            return validatorClass.EmptyRadioButton(this, bi.sen08x, bi.sen08x96, bi.sen08x96x, getString(R.string.sen08x));
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
                startActivity(new Intent(this, RecruitmentEndingActivity.class)
                        .putExtra("complete", true)
                        .putExtra("mothername",elmodel.getSel07())
                        .putExtra("birthdate",elmodel.getSel03()));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

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

    public void SaveDraft() throws JSONException {
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setDevicetagID(MainApp.getTagName(this));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setsMrno(mrno);
        MainApp.fc.setFormtype(MainApp.FORMTYPE_Recr);
        MainApp.fc.setIsEl("1");
        MainApp.fc.setsStudyid(bi.sen01.getText().toString());
        MainApp.fc.setIsinserted("1");
        setGPS(); //Set GPS
        elmodel = JSONUtilClass.getModelFromJSON(formsContract.getsEl(), EligibilityJSONModel.class);
        JSONObject rec = new JSONObject();
        rec.put("childName", elmodel.getSel02());
        rec.put("UUID", formsContract.getUID());
//        recruitment
        rec.put("sen01", bi.sen01.getText().toString());
        rec.put("sen02", bi.sen02.getText().toString());
        rec.put("sen03", bi.sen03.getText().toString());
        rec.put("sen04", bi.sen04.getText().toString());
        rec.put("sen05", bi.sen05.getText().toString());
        rec.put("sen06", bi.sen06.getText().toString());
        rec.put("sen07", bi.sen07.getText().toString());
        rec.put("sen08", bi.sen08a.isChecked() ? "1"
                : bi.sen08b.isChecked() ? "2"
                : "0");
        rec.put("sen08x", bi.sen08xa.isChecked() ? "1"
                : bi.sen08xb.isChecked() ? "2"
                : bi.sen08xc.isChecked() ? "3"
                : bi.sen08xd.isChecked() ? "4"
                : bi.sen08x96.isChecked() ? "96"
                : "0");
        rec.put("sen08x96x", bi.sen08x96x.getText().toString());
        rec.put("sen09d", bi.sen09d.getText().toString());
        rec.put("sen09t", bi.sen09t.getText().toString());
        rec.put("sen10", bi.sen10a.isChecked() ? "1"
                : bi.sen10b.isChecked() ? "2"
                : "0");
        rec.put("sen11", bi.sen11a.isChecked() ? "1"
                : bi.sen11b.isChecked() ? "2"
                : bi.sen11c.isChecked() ? "3"
                : "0");

        MainApp.fc.setsRecr(String.valueOf(rec));

    }

    private void setGPS() {
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

    public void BtnEnd() {
        onBackPressed();
    }
}

