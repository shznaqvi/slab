package edu.aku.hassannaqvi.slab.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityVisitOutcomeBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

import static edu.aku.hassannaqvi.slab.other.JsonUtils.mergeJSONObjects;
import static edu.aku.hassannaqvi.slab.ui.SupplementAdminActivity.supAdmin;

public class VisitOutcome extends AppCompatActivity {
ActivityVisitOutcomeBinding bi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this,R.layout.activity_visit_outcome);
        bi.setCallback(this);

        bi.sfu601.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.sfu601a){
                    bi.fldGrpsfu601.setVisibility(View.GONE);
                    bi.sfu602a.clearCheck();
                    bi.sfu602b.clearCheck();
                    bi.sfu60296.clearCheck();
                    bi.sfu603.clearCheck();
                }else {
                    bi.fldGrpsfu601.setVisibility(View.VISIBLE);
                }
            }
        });


    }
    public void BtnEnd(){

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
    public void BtnContinue(){
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
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean formValidation() {
        if(MainApp.fupLocation == 1){
            if (!validatorClass.EmptyRadioButton(this, bi.sfu601, bi.sfu601b, getString(R.string.sfu601))) {
                return false;
            }
            if(bi.sfu601b.isChecked()){
                if (!validatorClass.EmptyRadioButton(this, bi.sfu602a, bi.sfu602a01, getString(R.string.sfu602a))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu602b, bi.sfu602b01, getString(R.string.sfu602b))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu60296, bi.sfu6029601,bi.sfu60296x, getString(R.string.sfu602)+" "+ getString(R.string.others))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu603, bi.sfu60396,bi.sfu60396x, getString(R.string.sfu603)+" "+ getString(R.string.others))) {
                    return false;
                }

            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu604, getString(R.string.sfu604))) {
                return false;
            }

        }

        return true;
    }

    private void SaveDraft() throws JSONException{
        JSONObject sa = new JSONObject();
        sa.put("sfu601",bi.sfu601a.isChecked() ? "1"
                : bi.sfu601b.isChecked() ? "2"
                : "0");
        sa.put("sfu602a",bi.sfu602a01.isChecked() ? "1"
                : bi.sfu602a02.isChecked() ? "2"
                : "0");
        sa.put("sfu602b",bi.sfu602b01.isChecked() ? "1"
                : bi.sfu602b02.isChecked() ? "2"
                : "0");
        sa.put("sfu60296",bi.sfu6029601.isChecked() ? "1"
                : bi.sfu6029602.isChecked() ? "2"
                : "0");

        sa.put("sfu60296x", bi.sfu60296x.getText().toString());
        sa.put("sfu603",bi.sfu603a.isChecked() ? "1"
                : bi.sfu603b.isChecked() ? "2"
                : bi.sfu603c.isChecked() ? "3"
                : bi.sfu60396.isChecked() ? "96"
                : "0");

        sa.put("sfu604", bi.sfu604.getText().toString());
        JSONObject localJson;

        localJson = mergeJSONObjects(supAdmin, sa);

        MainApp.fc.setsSup(String.valueOf(localJson));
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


}
