package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.databinding.ActivityLabInvestigationsBinding;
import edu.aku.hassannaqvi.slab.databinding.ActivityOnExaminationBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class OnExaminationActivity extends AppCompatActivity {
ActivityOnExaminationBinding bi;
DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this,R.layout.activity_on_examination);
       bi.setCallback(this);
        db = new DatabaseHelper(this);
        setupView();
    }

    private void setupView() {
        bi.sfu57.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sfu57b){
                    bi.fldGrpsfu58.setVisibility(View.GONE);
                    bi.sfu57.clearCheck();
                }else{
                    bi.fldGrpsfu58.setVisibility(View.VISIBLE);
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
          /* if (UpdateDB()) {
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
            }*/
        }
    }

    private boolean UpdateDB() {
        return false;
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

    }

    private boolean formValidation() {
        if (!validatorClass.EmptyTextBox(this, bi.sfu54, getString(R.string.sfu54) )) {
            return false;
        }
        if (!validatorClass.RangeTextBox(this, bi.sfu54, 35 , 40 ,  getString(R.string.sfu54), "&deg; C" )) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu55, getString(R.string.sfu55) )) {
            return false;
        }
        if (!validatorClass.RangeTextBox(this, bi.sfu55, 30 , 100 ,  getString(R.string.sfu55), " minutes" )) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu56, getString(R.string.sfu56) )) {
            return false;
        }
        if (!validatorClass.RangeTextBox(this, bi.sfu56, 130 , 200 ,  getString(R.string.sfu55), " minutes" )) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu57, bi.sfu57a, getString(R.string.sfu57))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu58, bi.sfu58a, getString(R.string.sfu58))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu59, bi.sfu5996,bi.sfu5996x, getString(R.string.sfu59))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu60, bi.sfu6096,bi.sfu6096x, getString(R.string.sfu60))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu61, bi.sfu6196,bi.sfu6196x, getString(R.string.sfu61))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu61, bi.sfu6196,bi.sfu6196x, getString(R.string.sfu61))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu62, bi.sfu62b,bi.sfu62bx, getString(R.string.sfu62))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu63, bi.sfu6396,bi.sfu6396x, getString(R.string.sfu63))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu64, bi.sfu64a, getString(R.string.sfu64))) {
            return false;
        }
        return !validatorClass.EmptyRadioButton(this, bi.sfu65, bi.sfu6596,bi.sfu6596x, getString(R.string.sfu65));
    }
}
