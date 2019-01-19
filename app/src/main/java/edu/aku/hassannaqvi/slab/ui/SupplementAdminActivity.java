package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivitySupplementAdminBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class SupplementAdminActivity extends AppCompatActivity {
    ActivitySupplementAdminBinding bi;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_supplement_admin);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        setupView();
        ScrollView supp_scrollview = findViewById(R.id.supp_scrollview);
        validatorClass.setScrollViewFocus(supp_scrollview);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

    private void setupView() {

      /*  bi.sfu67.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sfu67a){
                    bi.fldGrpsfu68.setVisibility(View.GONE);
                    bi.sfu68a.clearCheck();
                    bi.sfu68b.clearCheck();
                    bi.sfu6896.clearCheck();
                    bi.sfu6896x.setText(null);
                    bi.sfu69.clearCheck();
                    bi.sfu6996x.setText(null);
                }else{
                    bi.fldGrpsfu68.setVisibility(View.VISIBLE);
                }
            }
        });*/
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
                startActivity(new Intent(this, LabInvestigationActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);
        int updcount = db.updateSSUP();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void BtnEnd() {

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

                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        JSONObject sa = new JSONObject();
        sa.put("sfu501", bi.sfu501a.isChecked() ? "1"
                : bi.sfu501b.isChecked() ? "2"
                : "0");
        sa.put("sfu502", bi.sfu502a.isChecked() ? "1"
                : bi.sfu502b.isChecked() ? "2"
                : bi.sfu502c.isChecked() ? "3"
                : bi.sfu502d.isChecked() ? "4"
                : bi.sfu50296.isChecked() ? "96"
                : "0");

        sa.put("sfu50296x", bi.sfu50296x.getText().toString());
        sa.put("sfu503", bi.sfu503.getText().toString());
        sa.put("sfu504", bi.sfu504.getText().toString());
        sa.put("sfu505", bi.sfu505.getText().toString());

        sa.put("sfu506", bi.sfu506a.isChecked() ? "1"
                : bi.sfu506b.isChecked() ? "2"
                : bi.sfu50696.isChecked() ? "96"
                : "0");

        sa.put("sfu50696x", bi.sfu50696x.getText().toString());
        sa.put("sfu507", bi.sfu507a.isChecked() ? "1"
                : bi.sfu50796.isChecked() ? "96"
                : "0");

        sa.put("sfu50796x", bi.sfu50796x.getText().toString());

        MainApp.fc.setsSup(String.valueOf(sa));
    }

    private boolean formValidation() {
        if (MainApp.fupLocation == 1) {
            if (!validatorClass.EmptyRadioButton(this, bi.sfu501, bi.sfu501b, getString(R.string.sfu501))) {
                return false;
            }
            if (bi.sfu501b.isChecked()) {
                if (!validatorClass.EmptyRadioButton(this, bi.sfu502, bi.sfu50296, bi.sfu50296x, getString(R.string.sfu502))) {
                    return false;
                }
            }

        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu503, getString(R.string.sfu503))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu504, getString(R.string.sfu504))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu505, getString(R.string.sfu505))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu506, bi.sfu50696, bi.sfu50696x, getString(R.string.sfu506))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu507, bi.sfu50796, bi.sfu50796x, getString(R.string.sfu507))) {
            return false;
        }


        return true;
    }
}
