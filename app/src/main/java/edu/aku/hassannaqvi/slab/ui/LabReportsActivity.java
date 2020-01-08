package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.LabReportsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityLabReportsBinding;
import edu.aku.hassannaqvi.slab.other.DateUtils;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class LabReportsActivity extends AppCompatActivity {
    ActivityLabReportsBinding bi;
    Context context;
    private static final String TAG = LabReportsActivity.class.getName();
    String dtToday = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime());
    DatabaseHelper db;

    int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_lab_reports);
        bi.setCallback(this);
        context = LabReportsActivity.this;
        db = new DatabaseHelper(this);
        this.setTitle(getResources().getString(R.string.slrheading));
        bi.lbrMrno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                bi.lbrMrno.setInputType(InputType.TYPE_CLASS_NUMBER);
                length = charSequence.toString().length();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                clearFields();


                if (!bi.lbrMrno.getText().toString().isEmpty() && bi.lbrMrno.getText().toString().length() == 3) {
                    if (bi.lbrMrno.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 4) {
                            bi.lbrMrno.setText(bi.lbrMrno.getText().toString() + "-");
                            bi.lbrMrno.setSelection(bi.lbrMrno.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }
                if (!bi.lbrMrno.getText().toString().isEmpty() && bi.lbrMrno.getText().toString().length() == 6) {
                    if (bi.lbrMrno.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 7) {
                            bi.lbrMrno.setText(bi.lbrMrno.getText().toString() + "-");
                            bi.lbrMrno.setSelection(bi.lbrMrno.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        bi.reportdate.setManager(getSupportFragmentManager());
        bi.reportdate.setMaxDate(DateUtils.getThreeDaysBack("dd/MM/yyyy", 6));
        bi.reportdate.setMinDate(DateUtils.getThreeDaysBack("dd/MM/yyyy", -6));
        bi.reporttime.setManager(getSupportFragmentManager());
        bi.reporttime.setTimeFormat("HH:mm");
        bi.reporttime.setIs24HourView(true);
        validatorClass.setScrollViewFocus(bi.labrScrollview);

        bi.reports.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId != bi.blood.getId()) {
                    bi.growth.clearCheck();
                }
            }
        });
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
        if (!validatorClass.EmptyTextBox(context, bi.reportdate, getString(R.string.slrreportdate))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(context, bi.reporttime, getString(R.string.time))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(context, bi.reports, bi.cbc, getString(R.string.slrincbc))) {
            return false;
        }
        if (bi.cbc.isChecked()) {
            if (!validatorClass.EmptyTextBox(context, bi.slrhb, getString(R.string.slrhb))) {
                return false;
            }
            if (!bi.slrhb.getText().toString().matches("\\d+(\\.\\d+)*")) {
                Toast.makeText(this, "Please enter correct decimal value!", Toast.LENGTH_SHORT).show();
                bi.slrhb.requestFocus();
                bi.slrhb.setError("Please enter correct decimal value!");
                return false;
            } else {
                bi.slrhb.clearFocus();
                bi.slrhb.setError(null);
                if (!validatorClass.RangeTextBox(this, bi.slrhb, 1.0, 30.0, getString(R.string.slrhb), " units")) {
                    return false;
                }
            }
            if (!validatorClass.EmptyTextBox(context, bi.slrwbc, getString(R.string.slrwbc))) {
                return false;
            }

            if (!bi.slrwbc.getText().toString().matches("\\d+(\\.\\d+)*")) {
                Toast.makeText(this, "Please enter correct decimal value!", Toast.LENGTH_SHORT).show();
                bi.slrwbc.requestFocus();
                bi.slrwbc.setError("Please enter correct decimal value!");
                return false;
            } else {
                bi.slrwbc.clearFocus();
                bi.slrwbc.setError(null);
                if (!validatorClass.RangeTextBox(this, bi.slrwbc, 1.0, 30.0, getString(R.string.slrwbc), " units")) {
                    return false;
                }
            }
            if (!validatorClass.EmptyTextBox(context, bi.slrneu, getString(R.string.slrneu))) {
                return false;
            }
            if (!bi.slrneu.getText().toString().matches("\\d+(\\.\\d+)*")) {
                Toast.makeText(this, "Please enter correct decimal value!", Toast.LENGTH_SHORT).show();
                bi.slrneu.requestFocus();
                bi.slrneu.setError("Please enter correct decimal value!");
                return false;
            }
            if (!validatorClass.RangeTextBox(context, bi.slrneu, 1.0, 99.0, getString(R.string.slrneu), " units")) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(context, bi.slrlym, getString(R.string.slrlym))) {
                return false;
            }
            if (!bi.slrlym.getText().toString().matches("\\d+(\\.\\d+)*")) {
                Toast.makeText(this, "Please enter correct decimal value!", Toast.LENGTH_SHORT).show();
                bi.slrlym.requestFocus();
                bi.slrlym.setError("Please enter correct decimal value!");
                return false;
            }
            if (!validatorClass.RangeTextBox(context, bi.slrlym, 1.0, 99.0, getString(R.string.slrlym), " units")) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(context, bi.slrpla, getString(R.string.slrpla))) {
                return false;
            }
            if (!validatorClass.RangeTextBox(context, bi.slrpla, 1, 999, getString(R.string.slrpla), " units")) {
                return false;
            }
        }
        if (bi.crp.isChecked()) {
            if (!validatorClass.EmptyTextBox(context, bi.slrcrp, getString(R.string.slrcrp))) {
                return false;
            }
            if (!bi.slrcrp.getText().toString().matches("\\d+(\\.\\d+)*")) {
                Toast.makeText(this, "Please enter correct decimal value!", Toast.LENGTH_SHORT).show();
                bi.slrcrp.requestFocus();
                bi.slrcrp.setError("Please enter correct decimal value!");
                return false;
            } else {
                bi.slrcrp.clearFocus();
                bi.slrcrp.setError(null);
                if (!validatorClass.RangeTextBox(this, bi.slrcrp, 0.0, 20.0, getString(R.string.slrcrp), " units")) {
                    return false;
                }
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
                return validatorClass.EmptyTextBox(context, bi.slrorg2, getString(R.string.slrorg));
            }
        }
        return true;
    }

    public void BtnEnd() {
        //  Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Lab Report Submitted", Toast.LENGTH_SHORT).show();
                finish();

                //startActivity(new Intent(this, EndingActivity.class).putExtra("completed", true));

            } else {
                // Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(context);

        Long newrowid = db.addLabReport(MainApp.lr);

        MainApp.lr.set_ID(String.valueOf(newrowid));

        if (newrowid != 0) {
            //Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.lr.set_UID((MainApp.lr.getDeviceID() + MainApp.lr.get_ID()));
            db.updateLabReportID();

            return true;
        } else {
            //  Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {
        MainApp.lr = new LabReportsContract();
        MainApp.lr.setFormDate(dtToday);
        MainApp.lr.setUser(MainApp.userName);
        MainApp.lr.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.lr.setDevicetagID(MainApp.getTagName(context));
        MainApp.lr.setAppVersion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.lr.setMrno(bi.lbrMrno.getText().toString());
        MainApp.lr.setStudyid(bi.lbrStudyid.getText().toString());
        MainApp.lr.setReportdate(bi.reportdate.getText().toString());
        MainApp.lr.setReporttime(bi.reporttime.getText().toString());


        JSONObject cbc = new JSONObject();
        cbc.put("reporttype", bi.cbc.isChecked() ? "1" : bi.crp.isChecked() ? "2" : bi.blood.isChecked() ? "3" : "0");
        cbc.put("hb", bi.slrhb.getText().toString());
        cbc.put("wbc", bi.slrwbc.getText().toString());
        cbc.put("neutrophils", bi.slrneu.getText().toString());
        cbc.put("lymphocytes", bi.slrlym.getText().toString());
        cbc.put("platelets", bi.slrpla.getText().toString());
        MainApp.lr.setCbc(String.valueOf(cbc));

        JSONObject crp = new JSONObject();
        crp.put("crpcount", bi.slrcrp.getText().toString());
        MainApp.lr.setCrp(String.valueOf(crp));

        JSONObject blood = new JSONObject();
        blood.put("organismgrowth", bi.growthyes.isChecked() ? "1" : bi.growthno.isChecked() ? "2" : "0");
        blood.put("organismname1", bi.slrorg1.getText().toString());
        blood.put("organismname2", bi.slrorg2.getText().toString());
        MainApp.lr.setBlood(String.valueOf(blood));
    }


}
