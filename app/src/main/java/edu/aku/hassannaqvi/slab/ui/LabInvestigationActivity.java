package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.databinding.ActivityLabInvestigationBinding;
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

        setupView();
    }

    private void setupView() {
        bi.sli02.setManager(getSupportFragmentManager());
        bi.sli02.setMaxDate(dateToday);
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
                    bi.sli05.setText(null);
                    bi.sli0596.setChecked(false);
                } else {
                    bi.fldGrpsli02.setVisibility(View.VISIBLE);
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
                Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();
                finish();

                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));


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

    private boolean UpdateDB() {
        return false;
    }

    private void SaveDraft() throws JSONException {

    }

    private boolean formValidation() {
        if (!validatorClass.EmptyCheckBox(this, bi.fldGrpsli02, bi.sli01a, getString(R.string.sli01))) {
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
            if (!bi.sli0596.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bi.sli05, getString(R.string.sli05))) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyCheckBox(this, bi.fldGrpchecksli05, bi.sli0596, getString(R.string.sli05))) {
                    return false;
                }
            }

        }

        return true;
    }
}
