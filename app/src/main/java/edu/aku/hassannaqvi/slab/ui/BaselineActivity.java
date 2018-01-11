package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityBaselineBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class BaselineActivity extends AppCompatActivity {

    private static final String TAG = BaselineActivity.class.getName();

    ActivityBaselineBinding binding;
    String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_baseline);
        db = new DatabaseHelper(this);

        binding.setCallback(this);
        setUpViews();

    }

    public void setUpViews() {

        binding.sbl01.setManager(getSupportFragmentManager());
        binding.sbl12.setManager(getSupportFragmentManager());
        binding.sbl01.setMaxDate(dateToday);
        binding.sbl12.setMaxDate(dateToday);
        binding.sbl08.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.sbl08a.isChecked()) {
                    binding.fldGrpsbl09.setVisibility(View.VISIBLE);
                } else {
                    binding.fldGrpsbl09.setVisibility(View.GONE);
                    binding.sbl09.clearCheck();
                    binding.sbl10.clearCheck();
                    binding.sbl1088x.setText(null);
                    binding.sbl11.clearCheck();
                    binding.sbl12.setText(null);
                    binding.sbl13.setText(null);
                    binding.sbl14.clearCheck();
                }
            }
        });

        binding.sbl09.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.sbl09a.isChecked()) {
                    binding.fldGrpsbl10.setVisibility(View.GONE);
                    binding.sbl10.clearCheck();
                    binding.sbl1088x.setText(null);
                } else {
                    binding.fldGrpsbl10.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.sbl11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.sbl11a.isChecked()) {
                    binding.fldGrpsbl12.setVisibility(View.VISIBLE);
                    binding.fldGrpsbl14.setVisibility(View.GONE);
                    binding.sbl14.clearCheck();
                } else {
                    binding.fldGrpsbl12.setVisibility(View.GONE);
                    binding.fldGrpsbl14.setVisibility(View.VISIBLE);
                    binding.sbl12.setText(null);
                    binding.sbl13.setText(null);
                }
            }
        });
    }


    public Boolean formValidation() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyTextBox(this, binding.sbl01, getString(R.string.sbl01))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sbl02hr, getString(R.string.hours))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sbl02min, getString(R.string.min))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sbl03, getString(R.string.sbl03))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sbl04, getString(R.string.sbl04))) {
            return false;
        }


        if (!validatorClass.EmptyTextBox(this, binding.sbl05, getString(R.string.sbl05))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sbl06, binding.sbl06c, getString(R.string.sbl06))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sbl07, binding.sbl0788, getString(R.string.sbl07))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sbl07, binding.sbl0788, binding.sbl0788x, getString(R.string.sbl07))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, binding.sbl08, binding.sbl08b, getString(R.string.sbl08))) {
            return false;
        }


        if (binding.sbl08a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, binding.sbl09, binding.sbl09b, getString(R.string.sbl09))) {
                return false;
            }


            if (binding.sbl09a.isChecked()) {

                if (!validatorClass.EmptyRadioButton(this, binding.sbl10, binding.sbl10c, getString(R.string.sbl10))) {
                    return false;
                }
            }


            if (!validatorClass.EmptyRadioButton(this, binding.sbl11, binding.sbl11b, getString(R.string.sbl11))) {
                return false;
            }


            if (binding.sbl11a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, binding.sbl12, getString(R.string.sbl12))) {
                    return false;
                }


                if (!validatorClass.EmptyTextBox(this, binding.sbl13, getString(R.string.sbl13))) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyRadioButton(this, binding.sbl14, binding.sbl14b, getString(R.string.sbl14))) {
                    return false;
                }
            }


        }


        return true;
    }


    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();

        MainApp.fc.setDevicetagID(sharedPref.getString("tagName", null));
        MainApp.fc.setFormDate(new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);


        JSONObject sa = new JSONObject();


        sa.put("sbl01", binding.sbl01.getText().toString());
        sa.put("sbl02hr", binding.sbl02hr.getText().toString());
        sa.put("sbl02min", binding.sbl02min.getText().toString());
        sa.put("sbl03", binding.sbl03.getText().toString());
        sa.put("sbl04", binding.sbl04.getText().toString());
        sa.put("sbl05", binding.sbl05.getText().toString());

        sa.put("sbl06", binding.sbl06a.isChecked() ? "1"
                : binding.sbl06b.isChecked() ? "2"
                : binding.sbl06c.isChecked() ? "3" : "0");


        sa.put("sbl07", binding.sbl07a.isChecked() ? "1"
                : binding.sbl07b.isChecked() ? "2"
                : binding.sbl0788.isChecked() ? "88" : "0");


        sa.put("sbl0788x", binding.sbl0788x.getText().toString());


        sa.put("sbl08", binding.sbl08a.isChecked() ? "1"
                : binding.sbl08b.isChecked() ? "2"
                : "0");


        sa.put("sbl09", binding.sbl09a.isChecked() ? "1"
                : binding.sbl09b.isChecked() ? "2"
                : "0");


        sa.put("sbl10", binding.sbl10a.isChecked() ? "1"
                : binding.sbl10b.isChecked() ? "2"
                : binding.sbl10c.isChecked() ? "3" : "0");


        sa.put("sbl11", binding.sbl11a.isChecked() ? "1"
                : binding.sbl11b.isChecked() ? "2"
                : "0");


        sa.put("sbl12", binding.sbl12.getText().toString());
        sa.put("sbl13", binding.sbl13.getText().toString());


        sa.put("sbl14", binding.sbl14a.isChecked() ? "1"
                : binding.sbl14b.isChecked() ? "2"
                : "0");


        MainApp.fc.setsA(String.valueOf(sa));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    public void BtnEnd() {
        finish();
        Toast.makeText(this, "complete Section", Toast.LENGTH_SHORT).show();
        Intent endSec = new Intent(this, EndingActivity.class);
        endSec.putExtra("complete", false);
        startActivity(endSec);
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


        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
        // }
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