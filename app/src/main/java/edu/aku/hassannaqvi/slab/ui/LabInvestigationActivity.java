package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityLabInvestigationBinding;
import edu.aku.hassannaqvi.slab.other.DateUtils;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class LabInvestigationActivity extends AppCompatActivity {
    ActivityLabInvestigationBinding bi;
    DatabaseHelper db;
    String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_investigation);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_lab_investigation);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        this.setTitle(getResources().getString(R.string.sliheading));

        setupView();
        ScrollView lab_scrollview = findViewById(R.id.lab_scrollview);
        validatorClass.setScrollViewFocus(lab_scrollview);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"You can't go back",Toast.LENGTH_SHORT).show();
    }
    private void setupView() {

        bi.sli02.setManager(getSupportFragmentManager());
        bi.sli02.setMaxDate(dateToday);
        bi.sli02.setMinDate( DateUtils.getThreeDaysBack("dd/MM/yyyy",-7));
// Completed (2): Change the date of blood collection to 7 days backward
        bi.sli03.setManager(getSupportFragmentManager());
        bi.sli03.setTimeFormat("HH:mm");
        bi.sli03.setIs24HourView(true);

        bi.sli01d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.sli01a.setChecked(false);
                    bi.sli01b.setChecked(false);
                    bi.sli01c.setChecked(false);

                    bi.sli01a.setEnabled(false);
                    bi.sli01b.setEnabled(false);
                    bi.sli01c.setEnabled(false);
                    bi.fldGrpsli02.setVisibility(View.GONE);

                    bi.sli04a.setChecked(false);
                    bi.sli04b.setChecked(false);
                    bi.sli0496.setChecked(false);
                    bi.sli0496x.setText(null);
                 /*   bi.sli05.setText(null);
                    bi.sli0598.setChecked(false);*/
                } else {
                    bi.fldGrpsli02.setVisibility(View.VISIBLE);
                    bi.sli01a.setEnabled(true);
                    bi.sli01b.setEnabled(true);
                    bi.sli01c.setEnabled(true);
                }
            }
        });
        bi.sli04d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bi.sli04a.setChecked(false);
                    bi.sli04b.setChecked(false);
//                    bi.sli04c.setChecked(false);
                    bi.sli0496.setChecked(false);
                    bi.sli0496x.setText(null);

                    bi.sli04a.setEnabled(false);
                    bi.sli04b.setEnabled(false);
//                    bi.sli04c.setEnabled(false);
                    bi.sli0496.setEnabled(false);
                } else {
                    bi.sli04a.setEnabled(true);
                    bi.sli04b.setEnabled(true);
//                    bi.sli04c.setEnabled(true);
                    bi.sli0496.setEnabled(true);
                }
            }
        });
    }

    public void BtnContinue() {

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

                startActivity(new Intent(this, FollowUpEndingActivity.class).putExtra("complete", true));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void BtnEnd() {

 //       Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                //Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);
        int updcount = db.updateSLab();
        if (updcount == 1) {
            //Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            finish();

            startActivity(new Intent(this, FollowUpEndingActivity.class).putExtra("complete", false));

            return true;
        } else {
          // Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {
        JSONObject li = new JSONObject();
        li.put("sli01a",bi.sli01a.isChecked() ? "1" : "0");
        li.put("sli01b",bi.sli01b.isChecked() ? "2" : "0");
        li.put("sli01c",bi.sli01c.isChecked() ? "3" : "0");
        li.put("sli01d",bi.sli01d.isChecked() ? "4" : "0");

        li.put("sli02", bi.sli02.getText().toString());
        li.put("sli03", bi.sli03.getText().toString());

        li.put("sli04a",bi.sli04a.isChecked() ? "1" : "0");
        li.put("sli04b",bi.sli04b.isChecked() ? "2" : "0");
//        li.put("sli04c",bi.sli04c.isChecked() ? "3" : "0");
        li.put("sli04d",bi.sli04d.isChecked() ? "3" : "0");
        li.put("sli0496",bi.sli0496.isChecked() ? "96" : "0");

      /*  li.put("sli05", bi.sli05.getText().toString());
        li.put("sli0598",bi.sli0598.isChecked() ? "98" : "0");*/
//Completed (3): Add SBR and none options in other investigations question.
      MainApp.fc.setsLab(String.valueOf(li));
    }

    private boolean formValidation() {
        if (!validatorClass.EmptyCheckBox(this, bi.sli01, bi.sli01a, getString(R.string.sli01))) {
            return false;
        }
        if (!bi.sli01d.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sli02, getString(R.string.sli02))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sli03, getString(R.string.sli03))) {
                return false;
            }
            if (!validatorClass.EmptyCheckBox(this, bi.sli04, bi.sli0496, bi.sli0496x, getString(R.string.sli04))) {
                return false;
            }
           /* if (!bi.sli0598.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bi.sli05, getString(R.string.sli05))) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyCheckBox(this, bi.fldGrpchecksli05, bi.sli0598, getString(R.string.sli05))) {
                    return false;
                }
            }*/

        }

        return true;
    }
}
