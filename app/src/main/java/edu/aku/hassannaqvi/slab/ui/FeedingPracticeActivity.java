package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityFeedingPracticeBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class FeedingPracticeActivity extends AppCompatActivity {
    ActivityFeedingPracticeBinding bi;
    DatabaseHelper db;
    String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
    Boolean nextExamSec;
    String childName, localmrno, localstudyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_feeding_practice);
        db = new DatabaseHelper(this);
        bi.setCallback(this);
        gettingIntents();
        setupView();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"You can't go back",Toast.LENGTH_SHORT).show();
    }
    private void gettingIntents() {

        Intent intent = getIntent();
        if (intent.hasExtra("openExamSec") && intent.hasExtra("childName") && intent.hasExtra("mrno") && intent.hasExtra("studyID")) {
            Bundle bundle = intent.getExtras();
            nextExamSec = bundle.getBoolean("openExamSec");
            childName = bundle.getString("childName");
            localmrno = bundle.getString("mrno");
            localstudyID = bundle.getString("studyID");
        } else {
            // Do something else
            Toast.makeText(this, "Restart your app or contact your support team!", Toast.LENGTH_SHORT);

        }
    }

    private void setupView() {
        bi.sfu49.setManager(getSupportFragmentManager());
        bi.sfu49.setMaxDate(dateToday);
        bi.childname.setText(childName);

        bi.sfu12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu12b) {
                    bi.fldGrpsfu13.setVisibility(View.GONE);
                    bi.sfu13.clearCheck();
                    bi.sfu14.clearCheck();
                    bi.sfu1496x.setText(null);
                } else {
                    bi.fldGrpsfu13.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.sfu13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu13a) {
                    bi.fldGrpsfu14.setVisibility(View.GONE);
                    bi.sfu14.clearCheck();
                    bi.sfu1496x.setText(null);
                } else {
                    bi.fldGrpsfu14.setVisibility(View.VISIBLE);
                }
            }
        });
        bi.sfu40.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu40b) {
                    bi.fldGrpsfu40.setVisibility(View.GONE);
                    bi.sfu41.clearCheck();
                    bi.sfu42.setText(null);
                } else {
                    bi.fldGrpsfu40.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.sfu48.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu48b) {
                    bi.fldGrpsfu50.setVisibility(View.GONE);
                    bi.sfu50.clearCheck();
                    bi.sfu5096x.setText(null);
                    bi.sfu51.setText(null);
                    bi.sfu52.setText(null);
                    bi.sfu52a.setChecked(false);

                } else {
                    bi.fldGrpsfu50.setVisibility(View.VISIBLE);
                }
            }
        });
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
                if (nextExamSec == false)
                    startActivity(new Intent(this, SupplementAdminActivity.class));
                else
                    startActivity(new Intent(this, OnExaminationActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {


        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSFEED();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void BtnEnd() {

     //   Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
               // Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                finish();

                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        MainApp.fc.setsMrno(localmrno);
        MainApp.fc.setsStudyid(localstudyID);
        JSONObject fp = new JSONObject();
        fp.put("sfu12", bi.sfu12a.isChecked() ? "1"
                : bi.sfu12b.isChecked() ? "2"
                : bi.sfu12c.isChecked() ? "3"
                : "0");
        fp.put("sfu13", bi.sfu13a.isChecked() ? "1"
                : bi.sfu12b.isChecked() ? "2"
                : "0");
        fp.put("sfu14", bi.sfu14a.isChecked() ? "1"
                : bi.sfu14b.isChecked() ? "2"
                : bi.sfu14c.isChecked() ? "3"
                : bi.sfu1496.isChecked() ? "96"
                : "0");

        fp.put("sfu1496x", bi.sfu1496x.getText().toString());

        fp.put("sfu15", bi.sfu15a.isChecked() ? "1"
                : bi.sfu15b.isChecked() ? "2"
                : bi.sfu15c.isChecked() ? "3"
                : bi.sfu1596.isChecked() ? "96"
                : "0");
        fp.put("sfu1596x", bi.sfu1596x.getText().toString());

        fp.put("sfu16", bi.sfu16a.isChecked() ? "1"
                : bi.sfu16b.isChecked() ? "2"
                : "0");
        fp.put("sfu17", bi.sfu17.getText().toString());


        fp.put("sfu18", bi.sfu18a.isChecked() ? "1"
                : bi.sfu18b.isChecked() ? "2"
                : "0");
        fp.put("sfu19", bi.sfu19.getText().toString());


        fp.put("sfu20", bi.sfu20a.isChecked() ? "1"
                : bi.sfu20b.isChecked() ? "2"
                : "0");
        fp.put("sfu21", bi.sfu21.getText().toString());


        fp.put("sfu22", bi.sfu22a.isChecked() ? "1"
                : bi.sfu22b.isChecked() ? "2"
                : "0");
        fp.put("sfu23", bi.sfu23.getText().toString());


        fp.put("sfu24", bi.sfu24a.isChecked() ? "1"
                : bi.sfu24b.isChecked() ? "2"
                : "0");
        fp.put("sfu25", bi.sfu25.getText().toString());


        fp.put("sfu26", bi.sfu26a.isChecked() ? "1"
                : bi.sfu26b.isChecked() ? "2"
                : "0");
        fp.put("sfu27", bi.sfu27.getText().toString());


        fp.put("sfu28", bi.sfu28a.isChecked() ? "1"
                : bi.sfu28b.isChecked() ? "2"
                : "0");
        fp.put("sfu29", bi.sfu29.getText().toString());


        fp.put("sfu30", bi.sfu30a.isChecked() ? "1"
                : bi.sfu30b.isChecked() ? "2"
                : "0");
        fp.put("sfu31", bi.sfu31.getText().toString());


        fp.put("sfu32", bi.sfu32a.isChecked() ? "1"
                : bi.sfu32b.isChecked() ? "2"
                : "0");
        fp.put("sfu33", bi.sfu33.getText().toString());


        fp.put("sfu34", bi.sfu34a.isChecked() ? "1"
                : bi.sfu34b.isChecked() ? "2"
                : "0");
        fp.put("sfu35", bi.sfu35.getText().toString());


        fp.put("sfu36", bi.sfu36a.isChecked() ? "1"
                : bi.sfu36b.isChecked() ? "2"
                : "0");
        fp.put("sfu37s", bi.sfu37s.getText().toString());
        fp.put("sfu37m", bi.sfu37m.getText().toString());
        fp.put("sfu37d", bi.sfu37d.getText().toString());


        fp.put("sfu38", bi.sfu38a.isChecked() ? "1"
                : bi.sfu38b.isChecked() ? "2"
                : "0");
        fp.put("sfu39", bi.sfu39.getText().toString());

        fp.put("sfu40", bi.sfu40a.isChecked() ? "1"
                : bi.sfu40b.isChecked() ? "2"
                : "0");
        fp.put("sfu41", bi.sfu41a.isChecked() ? "1"
                : bi.sfu41b.isChecked() ? "2"
                : bi.sfu41c.isChecked() ? "3"
                : "0");
        fp.put("sfu42", bi.sfu42.getText().toString());


        fp.put("sfu43", bi.sfu43a.isChecked() ? "1"
                : bi.sfu43b.isChecked() ? "2"
                : "0");
        fp.put("sfu44d", bi.sfu44d.getText().toString());
        fp.put("sfu44h", bi.sfu44h.getText().toString());


        fp.put("sfu45", bi.sfu45a.isChecked() ? "1"
                : bi.sfu45b.isChecked() ? "2"
                : "0");
        fp.put("sfu46", bi.sfu46.getText().toString());


        fp.put("sfu47", bi.sfu47.getText().toString());


        fp.put("sfu48", bi.sfu48a.isChecked() ? "1"
                : bi.sfu48b.isChecked() ? "2"
                : "0");

        fp.put("sfu49", bi.sfu49.getText().toString());

        fp.put("sfu50", bi.sfu50a.isChecked() ? "1"
                : bi.sfu5096.isChecked() ? "96"
                : "0");
        fp.put("sfu5096x", bi.sfu5096x.getText().toString());

        fp.put("sfu51", bi.sfu51.getText().toString());

        fp.put("sfu52", bi.sfu52.getText().toString());
        fp.put("sfu52a", bi.sfu52a.isChecked() ? "1"
                : "0");
        MainApp.fc.setsFeed(String.valueOf(fp));
    }

    private boolean formValidation() {
        if (!validatorClass.EmptyRadioButton(this, bi.sfu12, bi.sfu12a, getString(R.string.sfu12))) {
            return false;
        }
        if (!bi.sfu12b.isChecked()) {

            if (!validatorClass.EmptyRadioButton(this, bi.sfu13, bi.sfu13a, getString(R.string.sfu13))) {
                return false;
            }
            if (bi.sfu13b.isChecked()) {
                if (!validatorClass.EmptyRadioButton(this, bi.sfu14, bi.sfu1496, bi.sfu1496x, getString(R.string.sfu14))) {
                    return false;
                }
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu15, bi.sfu1596, bi.sfu1596x, getString(R.string.sfu15))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sfu16, bi.sfu16a, getString(R.string.sfu16))) {
            return false;
        }
        if (bi.sfu16a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu17, getString(R.string.howlong))) {
                return false;
            }
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sfu18, bi.sfu18a, getString(R.string.sfu18))) {
            return false;
        }
        if (bi.sfu18a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu19, getString(R.string.howlong))) {
                return false;
            }
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sfu20, bi.sfu20a, getString(R.string.sfu20))) {
            return false;
        }
        if (bi.sfu20a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu21, getString(R.string.howlong))) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu22, bi.sfu22a, getString(R.string.sfu22))) {
            return false;
        }
        if (bi.sfu22a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu23, getString(R.string.howlong))) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu24, bi.sfu24a, getString(R.string.sfu24))) {
            return false;
        }
        if (bi.sfu24a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu25, getString(R.string.howlong))) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu26, bi.sfu26a, getString(R.string.sfu26))) {
            return false;
        }
        if (bi.sfu26a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu27, getString(R.string.howlong))) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu28, bi.sfu28a, getString(R.string.sfu28))) {
            return false;
        }
        if (bi.sfu28a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu29, getString(R.string.howlong))) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu30, bi.sfu30a, getString(R.string.sfu30))) {
            return false;
        }
        if (bi.sfu30a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu31, getString(R.string.howlong))) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu32, bi.sfu32a, getString(R.string.sfu32))) {
            return false;
        }
        if (bi.sfu32a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu33, getString(R.string.howlong))) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu34, bi.sfu34a, getString(R.string.sfu34))) {
            return false;
        }
        if (bi.sfu34a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu35, getString(R.string.howlong))) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu36, bi.sfu36a, getString(R.string.sfu36))) {
            return false;
        }
        if (bi.sfu36a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu37s, getString(R.string.howlongsmd))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu37m, getString(R.string.howlongsmd))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu37d, getString(R.string.howlongsmd))) {
                return false;
            }


        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu38, bi.sfu38a, getString(R.string.sfu38))) {
            return false;
        }
        if (bi.sfu38a.isChecked()) {

            if (!validatorClass.EmptyTextBox(this, bi.sfu39, getString(R.string.howlong))) {
                return false;
            }

        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu40, bi.sfu40a, getString(R.string.sfu40))) {
            return false;
        }
        if (bi.sfu40a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bi.sfu41, bi.sfu41a, getString(R.string.sfu41))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu42, getString(R.string.times))) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu43, bi.sfu43a, getString(R.string.sfu43))) {
            return false;
        }
        if (bi.sfu43a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu44d, getString(R.string.howlongdh))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu44h, getString(R.string.howlongdh))) {
                return false;
            }

        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu45, bi.sfu45a, getString(R.string.sfu45))) {
            return false;
        }
        if (bi.sfu45a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu46, getString(R.string.times))) {
                return false;
            }
        }
/*
        if (!validatorClass.EmptyRadioButton(this, bi.sfu46, bi.sfu46a, getString(R.string.sfu46))) {
            return false;
        }
        if(bi.sfu46a.isChecked()){
            if (!validatorClass.EmptyTextBox(this, bi.sfu47, getString(R.string.times) )) {
                return false;
            }
        }*/
        if (!validatorClass.EmptyTextBox(this, bi.sfu47, getString(R.string.sfu47))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu48, bi.sfu48a, getString(R.string.sfu48))) {
            return false;
        }
        if (bi.sfu48a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu49, getString(R.string.sfu49))) {
                return false;
            }
            if (!validatorClass.EmptyRadioButton(this, bi.sfu50, bi.sfu5096, bi.sfu5096x, getString(R.string.sfu51))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu51, getString(R.string.sfu51))) {
                return false;
            }
            if (!bi.sfu52a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu52, getString(R.string.sfu52))) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyCheckBox(this, bi.fldGrpchecksfu53, bi.sfu52a, getString(R.string.sfu52) + " - " + getString(R.string.sfu52a))) {
                    return false;
                }
            }
        }
        return true;
    }

}
