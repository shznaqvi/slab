package edu.aku.hassannaqvi.slab.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityLabInvestigationsBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class LabInvestigationsActivity extends Activity {

    private static final String TAG = LabInvestigationsActivity.class.getName();

    ActivityLabInvestigationsBinding binding;
    int check = 0;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_lab_investigations);
        db = new DatabaseHelper(this);

        binding.setCallback(this);
    }


    public Boolean formValidation() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();


        if (!validatorClass.EmptyRadioButton(this, binding.sfu19, binding.sfu19b, getString(R.string.sfu19))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1901, binding.sfu1901b, getString(R.string.sfu1901))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1902, binding.sfu1902b, getString(R.string.sfu1902))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu1903, binding.sfu1903b, getString(R.string.sfu1903))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sfu1904, getString(R.string.sfu1904))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sfu1905, getString(R.string.sfu1905))) {
            return false;
        }


        if (!validatorClass.EmptyCheckBox(this, binding.fldGrpcheck19, binding.sfu20a, binding.sfu2088x, getString(R.string.sfu1903))) {
            return false;
        }


        if (binding.sfu2088.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu2088x, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sfu21, getString(R.string.sfu21))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu22, binding.sfu2288, getString(R.string.sfu22))) {
            return false;
        }


        if (binding.sfu2288.isChecked() && !validatorClass.EmptyTextBox(this, binding.sfu2288x, getString(R.string.others))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sfu23, binding.sfu23c, getString(R.string.sfu23))) {
            return false;
        }


        return validatorClass.EmptyTextBox(this, binding.sfu2301, getString(R.string.sfu2301));
    }

    public void BtnContinue() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //if (UpdateDB()) {
                Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();
                finish();

            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));


            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            //}
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

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();

        MainApp.fc.setDevicetagID(sharedPref.getString("tagName", null));
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);


        JSONObject sa = new JSONObject();


        sa.put("sfu19", binding.sfu19a.isChecked() ? "1"
                : binding.sfu19b.isChecked() ? "2"
                : "0");


        sa.put("sfu1901", binding.sfu1901a.isChecked() ? "1"
                : binding.sfu1901b.isChecked() ? "2"
                : "0");


        sa.put("sfu1902", binding.sfu1902a.isChecked() ? "1"
                : binding.sfu1902b.isChecked() ? "2"
                : "0");


        sa.put("sfu1903", binding.sfu1903a.isChecked() ? "1"
                : binding.sfu1903b.isChecked() ? "2"
                : "0");


        sa.put("sfu1903", binding.sfu1903a.isChecked() ? "1"
                : binding.sfu1903b.isChecked() ? "2"
                : "0");


        sa.put("sfu1904", binding.sfu1904.getText().toString());

        sa.put("sfu1905", binding.sfu1905.getText().toString());


        sa.put("sfu20a", binding.sfu20a.isChecked() ? "1" : "0");
        sa.put("sfu20b", binding.sfu20b.isChecked() ? "2" : "0");
        sa.put("sfu2088", binding.sfu2088.isChecked() ? "88" : "0");

        sa.put("sfu2088x", binding.sfu2088x.getText().toString());
        sa.put("sfu21", binding.sfu21.getText().toString());


        sa.put("sfu22", binding.sfu22a.isChecked() ? "1"
                : binding.sfu22b.isChecked() ? "2"
                : binding.sfu2288.isChecked() ? "88"
                : "0");


        sa.put("sfu2288x", binding.sfu2288x.getText().toString());


        sa.put("sfu23", binding.sfu23a.isChecked() ? "1"
                : binding.sfu23b.isChecked() ? "2"
                : binding.sfu23c.isChecked() ? "3"
                : "0");


        sa.put("sfu2301", binding.sfu2301.getText().toString());


        MainApp.fc.setsA(String.valueOf(sa));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {

        Long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fc.setUID(
                    (MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFormID();

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }



}