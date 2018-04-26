package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
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
        bi = DataBindingUtil.setContentView(this,R.layout.activity_supplement_admin );
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        setupView();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"You can't go back",Toast.LENGTH_SHORT).show();
    }
    private void setupView() {
        bi.sfu67.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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

    private void SaveDraft() throws JSONException{
        JSONObject sa = new JSONObject();
        sa.put("sfu66",bi.sfu66a.isChecked() ? "1"
                : bi.sfu66b.isChecked() ? "2"
                : "0");
        sa.put("sfu66bx", bi.sfu66bx.getText().toString());

        sa.put("sfu67",bi.sfu67a.isChecked() ? "1"
                : bi.sfu67b.isChecked() ? "2"
                : "0");
        sa.put("sfu68a",bi.sfu68a01.isChecked() ? "1"
                : bi.sfu68a02.isChecked() ? "2"
                : "0");
        sa.put("sfu68b",bi.sfu68b01.isChecked() ? "1"
                : bi.sfu68b02.isChecked() ? "2"
                : "0");
        sa.put("sfu6896",bi.sfu689601.isChecked() ? "1"
                : bi.sfu689602.isChecked() ? "2"
                : "0");
        sa.put("sfu6896x", bi.sfu6896x.getText().toString());

        sa.put("sfu69",bi.sfu69a.isChecked() ? "1"
                : bi.sfu69b.isChecked() ? "2"
                : bi.sfu69c.isChecked() ? "3"
                : bi.sfu6996.isChecked() ? "96"
                : "0");
        sa.put("sfu6996x", bi.sfu6996x.getText().toString());

        MainApp.fc.setsSup(String.valueOf(sa));
    }

    private boolean formValidation() {
        if (!validatorClass.EmptyRadioButton(this, bi.sfu66, bi.sfu66b,bi.sfu66bx, getString(R.string.sfu66))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu67, bi.sfu67a, getString(R.string.sfu67))) {
            return false;
        }
        if(bi.sfu67b.isChecked()){
            if (!validatorClass.EmptyRadioButton(this, bi.sfu68a, bi.sfu68a01, getString(R.string.sfu68)+" - "+getString(R.string.sfu68a))) {
                return false;
            }
            if (!validatorClass.EmptyRadioButton(this, bi.sfu68b, bi.sfu68b01, getString(R.string.sfu68)+" - "+getString(R.string.sfu68b))) {
                return false;
            }
            if (!validatorClass.EmptyRadioButton(this, bi.sfu6896, bi.sfu689601,bi.sfu6896x, getString(R.string.sfu68)+" - "+getString(R.string.sfu6896))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bi.sfu69, bi.sfu6996,bi.sfu6996x, getString(R.string.sfu69)+" - "+getString(R.string.others))) {
                return false;
            }


        }

        return true;
    }
}
