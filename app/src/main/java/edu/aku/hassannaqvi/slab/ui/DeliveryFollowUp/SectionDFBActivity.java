package edu.aku.hassannaqvi.slab.ui.DeliveryFollowUp;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivitySectionDfbBinding;
import edu.aku.hassannaqvi.slab.ui.Outcome.SectionOCActivity;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class SectionDFBActivity extends Activity {

    ActivitySectionDfbBinding bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_section_dfb);

        bl = DataBindingUtil.setContentView(this, R.layout.activity_section_dfb);
        setupViews();
        bl.setCallback(this);
    }

    public void setupViews() {

        // Q 2.1
        bl.dfb03c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bl.dfb03a.setEnabled(false);
                    bl.dfb03b.setEnabled(false);
                    bl.dfb0388.setEnabled(false);
                    bl.dfb03a.setChecked(false);
                    bl.dfb03b.setChecked(false);
                    bl.dfb0388.setChecked(false);
                    bl.dfb0388x.setText(null);
                    bl.fldGrpdfb04.setVisibility(View.GONE);
                    bl.dfb04.clearCheck();
                    bl.dfb05.clearCheck();
                } else {
                    bl.dfb03a.setEnabled(true);
                    bl.dfb03b.setEnabled(true);
                    bl.dfb0388.setEnabled(true);
                    bl.fldGrpdfb04.setVisibility(View.VISIBLE);
                }
            }
        });

        bl.dfb04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.dfb04a.isChecked()) {
                    bl.fldGrpdfb05.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpdfb05.setVisibility(View.GONE);
                    bl.dfb05.clearCheck();
                }
            }
        });


    }


    public Boolean formValidation() {

        // 2.1
        if (!validatorClass.EmptyRadioButton(this, bl.dfb01, bl.dfb01a, getString(R.string.dfb01))) {
            return false;
        }
        // 2.2
        if (!validatorClass.EmptyCheckBox(this, bl.fldGrpdfb02, bl.dfb02a, getString(R.string.dfb02))) {
            return false;
        }

        // 2.2x
        if (!validatorClass.EmptyCheckBox(this, bl.fldGrpdfb02, bl.dfb0288, bl.dfb0288x, getString(R.string.dfb02) + " - " + getString(R.string.other))) {
            return false;
        }


        if (!bl.dfb03c.isChecked()) {
            if (!validatorClass.EmptyCheckBox(this, bl.fldGrpdfb03, bl.dfb03a, getString(R.string.dfb03))) {
                return false;
            }

            // 2.2x
            if (!validatorClass.EmptyCheckBox(this, bl.fldGrpdfb03, bl.dfb0388, bl.dfb0388x, getString(R.string.dfb03) + " - " + getString(R.string.other))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.dfb04, bl.dfb04a, getString(R.string.dfb04))) {
                return false;
            }

            if (bl.dfb04a.isChecked()) {
                if (!validatorClass.EmptyRadioButton(this, bl.dfb05, bl.dfb05a, getString(R.string.dfb05))) {
                    return false;
                }
            }

        }
        // 2.3
        if (!validatorClass.EmptyRadioButton(this, bl.dfb06, bl.dfb06a, getString(R.string.dfb06))) {
            return false;
        }

        return validatorClass.EmptyRadioButton(this, bl.dfb06, bl.dfb0688, bl.dfb0688x, getString(R.string.dfb06) + " - " + getString(R.string.other));
    }


    public void BtnEnd() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

        MainApp.endActivity(this, this);

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
                Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                finish();

                startActivity(new Intent(this, SectionOCActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject sOb = new JSONObject();

        sOb.put("dfb01", bl.dfb01a.isChecked() ? "1"
                : bl.dfb01b.isChecked() ? "2"
                : bl.dfb01c.isChecked() ? "3"
                : bl.dfb0199.isChecked() ? "99" : "0");


        sOb.put("dfb02a", bl.dfb02a.isChecked() ? "1" : "0");
        sOb.put("dfb02b", bl.dfb02b.isChecked() ? "2" : "0");
        sOb.put("dfb02c", bl.dfb02a.isChecked() ? "3" : "0");
        sOb.put("dfb02d", bl.dfb02a.isChecked() ? "4" : "0");
        sOb.put("dfb02e", bl.dfb02a.isChecked() ? "5" : "0");
        sOb.put("dfb02f", bl.dfb02a.isChecked() ? "6" : "0");
        sOb.put("dfb02g", bl.dfb02a.isChecked() ? "7" : "0");
        sOb.put("dfb02h", bl.dfb02a.isChecked() ? "8" : "0");
        sOb.put("dfb0266", bl.dfb02a.isChecked() ? "66" : "0");
        sOb.put("dfb0288", bl.dfb02a.isChecked() ? "88" : "0");

        sOb.put("dfb0288x", bl.dfb0288x.getText().toString());

        sOb.put("dfb03a", bl.dfb03a.isChecked() ? "1" : "0");
        sOb.put("dfb03b", bl.dfb03b.isChecked() ? "2" : "0");
        sOb.put("dfb03c", bl.dfb03c.isChecked() ? "3" : "0");
        sOb.put("dfb0388", bl.dfb0388.isChecked() ? "88" : "0");
        sOb.put("dfb0388x", bl.dfb0388x.getText().toString());


        sOb.put("dfb04", bl.dfb04a.isChecked() ? "1"
                : bl.dfb04b.isChecked() ? "2"
                : bl.dfb0499.isChecked() ? "99" : "0");

        sOb.put("dfb05", bl.dfb05a.isChecked() ? "1"
                : bl.dfb05b.isChecked() ? "2"
                : bl.dfb0599.isChecked() ? "99" : "0");

        sOb.put("dfb06", bl.dfb06a.isChecked() ? "1"
                : bl.dfb06b.isChecked() ? "2"
                : bl.dfb06c.isChecked() ? "3"
                : bl.dfb06d.isChecked() ? "4"
                : bl.dfb06e.isChecked() ? "5"
                : bl.dfb06f.isChecked() ? "6"
                : bl.dfb0688.isChecked() ? "88" : "0");

        sOb.put("dfb0688x", bl.dfb0688x.getText().toString());


        MainApp.fc.setsB(String.valueOf(sOb));


        //sRc.put()


        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
//Long rowId;
        DatabaseHelper db = new DatabaseHelper(this);


        Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

        // 2. UPDATE FORM ROWID
        int updcount = db.updateSB();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }


        //return true;
    }

    public class checking {
        int check;

        public checking(int check) {
            this.check = check;
        }

        public int getCheck() {
            return check;
        }
    }

}
