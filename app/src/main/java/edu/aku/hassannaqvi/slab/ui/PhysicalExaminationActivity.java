package edu.aku.hassannaqvi.slab.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import edu.aku.hassannaqvi.slab.databinding.ActivityPhysicalExaminationBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class PhysicalExaminationActivity extends Activity {

    private static final String TAG = PhysicalExaminationActivity.class.getName();

    ActivityPhysicalExaminationBinding binding;
    int check = 0;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_physical_examination);
        db = new DatabaseHelper(this);

        binding.setCallback(this);
        setUpViews();
    }


    public void setUpViews() {
        binding.sfu17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.sfu17a.isChecked()) {
                    binding.fldGrpsfu1701.setVisibility(View.VISIBLE);
                } else {
                    binding.fldGrpsfu1701.setVisibility(View.GONE);
                    binding.sfu17.clearCheck();
                }
            }
        });
    }

    public Boolean formValidation() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyTextBox(this, binding.sfu14, getString(R.string.sfu14))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, binding.sfu14, 35.0, 40.0, getString(R.string.sfu14), " 0F")) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sfu15, getString(R.string.sfu15))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, binding.sfu15, 30, 100, getString(R.string.sfu15), " /minutes")) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sfu16, getString(R.string.sfu16))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, binding.sfu16, 130, 200, getString(R.string.sfu16), " /minutes")) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu17, binding.sfu17b, getString(R.string.sfu17))) {
            return false;
        }


        if (binding.sfu17a.isChecked()) {

            if (!validatorClass.EmptyRadioButton(this, binding.sfu1701, binding.sfu1701e, getString(R.string.sfu1701))) {
                return false;
            }
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1801, binding.sfu180188, getString(R.string.sfu1801))) {
            return false;
        }


        if (binding.sfu180188.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu180188x, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1802, binding.sfu180288, getString(R.string.sfu1802))) {
            return false;
        }


        if (binding.sfu180288.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu180288x, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1803, binding.sfu1803b, getString(R.string.sfu1803))) {
            return false;
        }


        if (binding.sfu1803b.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu1803bx, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1804, binding.sfu180488, getString(R.string.sfu1804))) {
            return false;
        }


        if (binding.sfu180488.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu180488x, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1805, binding.sfu1805c, getString(R.string.sfu1805))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1806, binding.sfu180688, getString(R.string.sfu1806))) {
            return false;
        }


        if (binding.sfu180688.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu180688x, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1807, binding.sfu180788, getString(R.string.sfu1807))) {
            return false;
        }


        return !(binding.sfu180788.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu180788x, getString(R.string.others)));
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

            startActivity(new Intent(this, LabInvestigationsActivity.class));


            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
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
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject sa = new JSONObject();


        sa.put("sfu14", binding.sfu14.getText().toString());
        sa.put("sfu15", binding.sfu15.getText().toString());
        sa.put("sfu16", binding.sfu16.getText().toString());

        sa.put("sfu17", binding.sfu17a.isChecked() ? "1"
                : binding.sfu17b.isChecked() ? "2"
                : "0");


        sa.put("sfu1701", binding.sfu1701a.isChecked() ? "1"
                : binding.sfu1701b.isChecked() ? "2"
                : binding.sfu1701c.isChecked() ? "3"
                : binding.sfu1701d.isChecked() ? "4"
                : binding.sfu1701e.isChecked() ? "5"
                : "0");


        sa.put("sfu1801", binding.sfu1801a.isChecked() ? "1"
                : binding.sfu1801b.isChecked() ? "2"
                : binding.sfu1801c.isChecked() ? "3"
                : binding.sfu1801d.isChecked() ? "4"
                : binding.sfu180188.isChecked() ? "88"
                : "0");

        sa.put("sfu180188x", binding.sfu180188x.getText().toString());


        sa.put("sfu1802", binding.sfu1802a.isChecked() ? "1"
                : binding.sfu1802b.isChecked() ? "2"
                : binding.sfu1802c.isChecked() ? "3"
                : binding.sfu1802d.isChecked() ? "4"
                : binding.sfu1802e.isChecked() ? "5"
                : binding.sfu1802f.isChecked() ? "6"
                : binding.sfu180188.isChecked() ? "88"
                : "0");


        sa.put("sfu180188x", binding.sfu180188x.getText().toString());


        sa.put("sfu1803", binding.sfu1803a.isChecked() ? "1"
                : binding.sfu1803b.isChecked() ? "2"
                : "0");


        sa.put("sfu1803bx", binding.sfu1803bx.getText().toString());


        sa.put("sfu1804", binding.sfu1804a.isChecked() ? "1"
                : binding.sfu1804b.isChecked() ? "2"
                : binding.sfu180488.isChecked() ? "88"
                : "0");

        sa.put("sfu180488x", binding.sfu180488x.getText().toString());


        sa.put("sfu1805", binding.sfu1805a.isChecked() ? "1"
                : binding.sfu1805b.isChecked() ? "2"
                : binding.sfu1805c.isChecked() ? "3"
                : "0");


        sa.put("sfu1806", binding.sfu1806a.isChecked() ? "1"
                : binding.sfu1806b.isChecked() ? "2"
                : binding.sfu180688.isChecked() ? "88"
                : "0");

        sa.put("sfu180688x", binding.sfu180688x.getText().toString());


        sa.put("sfu1807", binding.sfu1807a.isChecked() ? "1"
                : binding.sfu1807b.isChecked() ? "2"
                : binding.sfu180788.isChecked() ? "88"
                : "0");


        sa.put("sfu180788x", binding.sfu180788x.getText().toString());


        MainApp.fc.setsAnthro(String.valueOf(sa));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);


        int updcount = db.updateSExam();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


}