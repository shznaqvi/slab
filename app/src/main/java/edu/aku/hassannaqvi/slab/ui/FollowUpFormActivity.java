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

import edu.aku.hassannaqvi.slab.JsonModelClasses.EligibilityJSONModel;
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
    FormsContract fc, fc_1;
    EligibilityJSONModel elmodel;
    String localMrno;
    String localStudyID ;
    String childName ;
    int noofsachet ;


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
        elmodel = new EligibilityJSONModel();
    }

    private void checkIntents() {
            Intent intent = getIntent();
            if(intent.hasExtra(MainApp.MRNO_TAG) && intent.hasExtra(MainApp.STUDYID_TAG) && intent.hasExtra(MainApp.CHILDNAME_TAG)){
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

            }else{
                bi.sfu001.setEnabled(true);
                bi.btnCheckMrno.setVisibility(View.VISIBLE);
                bi.fldGrpA.setVisibility(View.GONE);
                bi.fldGrpB.setVisibility(View.GONE);

                // Do something else
                Toast.makeText(this,"Restart your app or contact your support team!",Toast.LENGTH_SHORT);

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
                } else {
                    bi.fldGrpsfu06.setVisibility(View.VISIBLE);

                }
            }
        });
        bi.sfu10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try{
                    noofsachet = Integer.parseInt(bi.sfu10.getText().toString());
                }catch (Exception e){
                }

                if (bi.sfu10.getText().toString().equals("1")){
                    bi.sfu11a.setVisibility(View.VISIBLE);
                    bi.sfu11b.setVisibility(View.GONE);
                    bi.sfu11c.setVisibility(View.GONE);
                    bi.sfu11d.setVisibility(View.GONE);
                    bi.sfu11e.setVisibility(View.GONE);
                    bi.sfu11f.setVisibility(View.GONE);
                    bi.sfu11g.setVisibility(View.GONE);
                }
                else if(bi.sfu10.getText().toString().equals("2")){

                    bi.sfu11a.setVisibility(View.VISIBLE);
                    bi.sfu11b.setVisibility(View.VISIBLE);
                    bi.sfu11c.setVisibility(View.GONE);
                    bi.sfu11d.setVisibility(View.GONE);
                    bi.sfu11e.setVisibility(View.GONE);
                    bi.sfu11f.setVisibility(View.GONE);
                    bi.sfu11g.setVisibility(View.GONE);
                }
                else if(bi.sfu10.getText().toString().equals("3")){

                    bi.sfu11a.setVisibility(View.VISIBLE);
                    bi.sfu11b.setVisibility(View.VISIBLE);
                    bi.sfu11c.setVisibility(View.VISIBLE);
                    bi.sfu11d.setVisibility(View.GONE);
                    bi.sfu11e.setVisibility(View.GONE);
                    bi.sfu11f.setVisibility(View.GONE);
                    bi.sfu11g.setVisibility(View.GONE);
                }
                else if(bi.sfu10.getText().toString().equals("4")){

                    bi.sfu11a.setVisibility(View.VISIBLE);
                    bi.sfu11b.setVisibility(View.VISIBLE);
                    bi.sfu11c.setVisibility(View.VISIBLE);
                    bi.sfu11d.setVisibility(View.VISIBLE);
                    bi.sfu11e.setVisibility(View.GONE);
                    bi.sfu11f.setVisibility(View.GONE);
                    bi.sfu11g.setVisibility(View.GONE);
                }
                else if(bi.sfu10.getText().toString().equals("5")){

                    bi.sfu11a.setVisibility(View.VISIBLE);
                    bi.sfu11b.setVisibility(View.VISIBLE);
                    bi.sfu11c.setVisibility(View.VISIBLE);
                    bi.sfu11d.setVisibility(View.VISIBLE);
                    bi.sfu11e.setVisibility(View.VISIBLE);
                    bi.sfu11f.setVisibility(View.GONE);
                    bi.sfu11g.setVisibility(View.GONE);
                }
                else if(bi.sfu10.getText().toString().equals("6")){

                    bi.sfu11a.setVisibility(View.VISIBLE);
                    bi.sfu11b.setVisibility(View.VISIBLE);
                    bi.sfu11c.setVisibility(View.VISIBLE);
                    bi.sfu11d.setVisibility(View.VISIBLE);
                    bi.sfu11e.setVisibility(View.VISIBLE);
                    bi.sfu11f.setVisibility(View.VISIBLE);
                    bi.sfu11g.setVisibility(View.GONE);
                }

                else if(bi.sfu10.getText().toString().equals("7")){

                    bi.sfu11a.setVisibility(View.VISIBLE);
                    bi.sfu11b.setVisibility(View.VISIBLE);
                    bi.sfu11c.setVisibility(View.VISIBLE);
                    bi.sfu11d.setVisibility(View.VISIBLE);
                    bi.sfu11e.setVisibility(View.VISIBLE);
                    bi.sfu11f.setVisibility(View.VISIBLE);
                    bi.sfu11g.setVisibility(View.VISIBLE);
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
        if (!validatorClass.EmptyRadioButton(this, bi.sfu01,bi.sfu01a, getString(R.string.sfu01c))) {
            return false;
        }

        if (!bi.sfu01c.isChecked()) {

            if (!validatorClass.EmptyTextBox(this, bi.sfu06, getString(R.string.sfu06))) {
                return false;
            }
            if (!validatorClass.RangeTextBox(this, bi.sfu06, 1000, 2500, getString(R.string.sfu06) ," Weight")) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu07, getString(R.string.sfu07))) {
                return false;
            }
            if(!bi.sfu07.getText().toString().contains(".")){
                Toast.makeText(this,"Length of neonate should be in decimal",Toast.LENGTH_SHORT);
                bi.sfu07.setError("Length of neonate should be in decimal");
                return false;
            }else{
                bi.sfu07.setError(null);
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu08, getString(R.string.sfu08))) {
                return false;
            }
            if(!bi.sfu08.getText().toString().contains(".")){
                Toast.makeText(this,"Head of Circumference should be decimal",Toast.LENGTH_SHORT);
                bi.sfu08.setError("Head of Circumference should be decimal");
                return false;
            }else{
                bi.sfu08.setError(null);
            }
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu10, getString(R.string.sfu10))) {
            return false;
        }
        switch (noofsachet){
            case 1:
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11a1,bi.sfu11a11, getString(R.string.sfuifqty))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11a2,bi.sfu11a21, getString(R.string.sfuifpartial))) {
                    return false;
                }
                break;
            case 2:
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11a1,bi.sfu11a11, getString(R.string.sfuifqty))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11a2,bi.sfu11a21, getString(R.string.sfuifpartial))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11b1,bi.sfu11b11, getString(R.string.sfuifqty))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11b2,bi.sfu11b21, getString(R.string.sfuifpartial))) {
                    return false;
                }
                break;
            case 3:
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11a1,bi.sfu11a11, getString(R.string.sfuifqty))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11a2,bi.sfu11a21, getString(R.string.sfuifpartial))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11b1,bi.sfu11b11, getString(R.string.sfuifqty))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11b2,bi.sfu11b21, getString(R.string.sfuifpartial))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11c1,bi.sfu11c11, getString(R.string.sfuifqty))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu11c2,bi.sfu11c21, getString(R.string.sfuifpartial))) {
                    return false;
                }
                break;

                case 4:
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11a1,bi.sfu11a11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11a2,bi.sfu11a21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11b1,bi.sfu11b11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11b2,bi.sfu11b21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11c1,bi.sfu11c11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11c2,bi.sfu11c21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11d1,bi.sfu11d11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11d2,bi.sfu11d21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    break;

                case 5:
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11a1,bi.sfu11a11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11a2,bi.sfu11a21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11b1,bi.sfu11b11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11b2,bi.sfu11b21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11c1,bi.sfu11c11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11c2,bi.sfu11c21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11d1,bi.sfu11d11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11d2,bi.sfu11d21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11e1,bi.sfu11e11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11e2,bi.sfu11e21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    break;

                case 6:
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11a1,bi.sfu11a11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11a2,bi.sfu11a21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11b1,bi.sfu11b11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11b2,bi.sfu11b21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11c1,bi.sfu11c11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11c2,bi.sfu11c21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11d1,bi.sfu11d11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11d2,bi.sfu11d21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11e1,bi.sfu11e11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11e2,bi.sfu11e21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11f1,bi.sfu11f11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11f2,bi.sfu11f21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    break;

                case 7:
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11a1,bi.sfu11a11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11a2,bi.sfu11a21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11b1,bi.sfu11b11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11b2,bi.sfu11b21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11c1,bi.sfu11c11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11c2,bi.sfu11c21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11d1,bi.sfu11d11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11d2,bi.sfu11d21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11e1,bi.sfu11e11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11e2,bi.sfu11e21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11f1,bi.sfu11f11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11f2,bi.sfu11f21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11g1,bi.sfu11g11, getString(R.string.sfuifqty))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu11g2,bi.sfu11g21, getString(R.string.sfuifpartial))) {
                        return false;
                    }
                    break;

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
                Boolean defaultValue = true;
                if (bi.sfu01c.isChecked()) {
                    defaultValue = false;
                } else {
                    defaultValue = true;
                }
                startActivity(new Intent(this, FeedingPracticeActivity.class).putExtra("openExamSec", defaultValue).putExtra("childName",bi.ChildName.getText().toString()));


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

    public void BtnEnd() {


    }

    private void SaveDraft() throws JSONException {
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setsMrno(localMrno);
        MainApp.fc.setsStudyid(localStudyID);
        MainApp.fc.setFormtype(MainApp.FORMTYPE_Fup);
        MainApp.fc.setIsinserted("1");
        setGPS(); //Set GPS

        JSONObject fu = new JSONObject();
        fu.put("sfudatetime", dtToday);
        fu.put("sfu01", bi.sfu01a.isChecked() ? "1"
                : bi.sfu01b.isChecked() ? "2"
                : bi.sfu01c.isChecked() ? "3"
                : "0");
        fu.put("sfu06", bi.sfu06.getText().toString());
        fu.put("sfu07", bi.sfu07.getText().toString());
        fu.put("sfu08", bi.sfu08.getText().toString());
//        a
        fu.put("sfu10", bi.sfu10.getText().toString());
        fu.put("sfu11a1", bi.sfu11a11.isChecked() ? "1"
                : bi.sfu11a12.isChecked() ? "2"
                : "0");
        fu.put("sfu11a2", bi.sfu11a21.isChecked() ? "1"
                : bi.sfu11a22.isChecked() ? "2"
                : bi.sfu11a23.isChecked() ? "3"
                : "0");


//      b

        fu.put("sfu11b1", bi.sfu11b11.isChecked() ? "1"
                : bi.sfu11b12.isChecked() ? "2"
                : "0");
        fu.put("sfu11b2", bi.sfu11b21.isChecked() ? "1"
                : bi.sfu11b22.isChecked() ? "2"
                : bi.sfu11b23.isChecked() ? "3"
                : "0");


//      c

        fu.put("sfu11c1", bi.sfu11c11.isChecked() ? "1"
                : bi.sfu11c12.isChecked() ? "2"
                : "0");
        fu.put("sfu11c2", bi.sfu11c21.isChecked() ? "1"
                : bi.sfu11c22.isChecked() ? "2"
                : bi.sfu11c23.isChecked() ? "3"
                : "0");

//        d

        fu.put("sfu11d1", bi.sfu11d11.isChecked() ? "1"
                : bi.sfu11d12.isChecked() ? "2"
                : "0");
        fu.put("sfu11d2", bi.sfu11d21.isChecked() ? "1"
                : bi.sfu11d22.isChecked() ? "2"
                : bi.sfu11d23.isChecked() ? "3"
                : "0");
//        e
        fu.put("sfu11e1", bi.sfu11e11.isChecked() ? "1"
                : bi.sfu11e12.isChecked() ? "2"
                : "0");
        fu.put("sfu11e2", bi.sfu11e21.isChecked() ? "1"
                : bi.sfu11e22.isChecked() ? "2"
                : bi.sfu11e23.isChecked() ? "3"
                : "0");
//        f
        fu.put("sfu11f1", bi.sfu11f11.isChecked() ? "1"
                : bi.sfu11f12.isChecked() ? "2"
                : "0");
        fu.put("sfu11f2", bi.sfu11f21.isChecked() ? "1"
                : bi.sfu11f22.isChecked() ? "2"
                : bi.sfu11f23.isChecked() ? "3"
                : "0");
//        g
        fu.put("sfu11g1", bi.sfu11g11.isChecked() ? "1"
                : bi.sfu11g12.isChecked() ? "2"
                : "0");
        fu.put("sfu11g2", bi.sfu11g21.isChecked() ? "1"
                : bi.sfu11g22.isChecked() ? "2"
                : bi.sfu11g23.isChecked() ? "3"
                : "0");
        MainApp.fc.setsFup(String.valueOf(fu));

    }

    public void btnCheckMrno() {

        if (!bi.sfu001.getText().toString().trim().isEmpty()) {

            fc = db.getStudyID(bi.sfu001.getText().toString());
            fc_1 = db.getChildName(bi.sfu001.getText().toString());
            fc.setsEl(fc_1.getsEl());

            if (!fc.getsMrno().isEmpty()) {

                Toast.makeText(this, "MR Number found!", Toast.LENGTH_SHORT).show();
                bi.fldGrpA.setVisibility(View.VISIBLE);
                elmodel = JSONUtilClass.getModelFromJSON(fc.getsEl(), EligibilityJSONModel.class);

                bi.sfu002.setText(fc.getsStudyid());
                bi.ChildName.setText(elmodel.getSel02());

            } else {

                bi.fldGrpA.setVisibility(View.GONE);

                Toast.makeText(this, "No MR Number found!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Not found.", Toast.LENGTH_SHORT).show();
        }

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


}
