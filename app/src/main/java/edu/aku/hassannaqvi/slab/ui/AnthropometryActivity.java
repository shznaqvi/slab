package edu.aku.hassannaqvi.slab.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityAnthropometryBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;


public class AnthropometryActivity extends Activity {

    private static final String TAG = AnthropometryActivity.class.getName();

    ActivityAnthropometryBinding binding;
    int check = 0;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_anthropometry);
        db = new DatabaseHelper(this);

        binding.setCallback(this);

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

            if (MainApp.fupLocation == 1 || MainApp.fupLocation == 2) {
                startActivity(new Intent(this, PhysicalExaminationActivity.class));
            } else {
                startActivity(new Intent(this, LabInvestigationsActivity.class));
            }


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


    public Boolean formValidation() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyTextBox(this, binding.sfu06, getString(R.string.sfu06))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sfu07, getString(R.string.sfu07))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sfu08, getString(R.string.sfu08))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sfu09, getString(R.string.sfu09))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu10, binding.sfu1088, getString(R.string.sfu10))) {
            return false;
        }


        return !(binding.sfu1088.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu1088x, getString(R.string.others)));
    }


    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject sa = new JSONObject();


        sa.put("sfu06", binding.sfu06.getText().toString());
        sa.put("sfu07", binding.sfu07.getText().toString());
        sa.put("sfu08", binding.sfu08.getText().toString());
        sa.put("sfu09", binding.sfu09.getText().toString());


        sa.put("sfu10", binding.sfu10a.isChecked() ? "1"
                : binding.sfu10b.isChecked() ? "2"
                : binding.sfu10c.isChecked() ? "3"
                : binding.sfu10d.isChecked() ? "4"
                : binding.sfu1088.isChecked() ? "88"
                : "0");


        sa.put("sfu1088x", binding.sfu1088x.getText().toString());

        MainApp.fc.setsAnthro(String.valueOf(sa));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }
    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);


        int updcount = db.updateSAntrho();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


}