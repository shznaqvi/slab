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
import edu.aku.hassannaqvi.slab.databinding.ActivityOnExaminationBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class OnExaminationActivity extends AppCompatActivity {
    ActivityOnExaminationBinding bi;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_on_examination);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        setupView();
        ScrollView onexam_scrollview = findViewById(R.id.onexam_scrollview);
        validatorClass.setScrollViewFocus(onexam_scrollview);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

    private void setupView() {
        bi.sfu57.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu57b) {
                    bi.fldGrpsfu58.setVisibility(View.GONE);
                    bi.sfu58.clearCheck();
                } else {
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
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(this, SupplementAdminActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
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

        JSONObject oe = new JSONObject();
        oe.put("sfu54", bi.sfu54.getText().toString());
        oe.put("sfu55", bi.sfu55.getText().toString());
        oe.put("sfu56", bi.sfu56.getText().toString());

        oe.put("sfu57", bi.sfu57a.isChecked() ? "1"
                : bi.sfu57b.isChecked() ? "2"
                : "0");
        oe.put("sfu58", bi.sfu58a.isChecked() ? "1"
                : bi.sfu58b.isChecked() ? "2"
                : bi.sfu58c.isChecked() ? "3"
                : bi.sfu58d.isChecked() ? "4"
                : bi.sfu58e.isChecked() ? "5"
                : "0");
        oe.put("sfu59", bi.sfu59a.isChecked() ? "1"
                : bi.sfu59b.isChecked() ? "2"
                : bi.sfu59c.isChecked() ? "3"
                : bi.sfu59d.isChecked() ? "4"
                : bi.sfu5996.isChecked() ? "96"
                : "0");
        oe.put("sfu5996x", bi.sfu5996x.getText().toString());
        oe.put("sfu60", bi.sfu60a.isChecked() ? "1"
                : bi.sfu60b.isChecked() ? "2"
                : bi.sfu6096.isChecked() ? "96"
                : "0");
        oe.put("sfu6096x", bi.sfu6096x.getText().toString());

        oe.put("sfu61", bi.sfu61a.isChecked() ? "1"
                : bi.sfu61b.isChecked() ? "2"
                : bi.sfu61c.isChecked() ? "3"
                : bi.sfu61d.isChecked() ? "4"
                : bi.sfu61e.isChecked() ? "5"
                : bi.sfu61f.isChecked() ? "6"
                : bi.sfu6196.isChecked() ? "96"
                : "0");
        oe.put("sfu6196x", bi.sfu6196x.getText().toString());

        oe.put("sfu62", bi.sfu62a.isChecked() ? "1"
                : bi.sfu62b.isChecked() ? "2"
                : "0");
        oe.put("sfu62bx", bi.sfu62bx.getText().toString());
        oe.put("sfu63", bi.sfu63a.isChecked() ? "1"
                : bi.sfu63b.isChecked() ? "2"
                : bi.sfu6396.isChecked() ? "96"
                : "0");
        oe.put("sfu6396x", bi.sfu6396x.getText().toString());
        oe.put("sfu64", bi.sfu64a.isChecked() ? "1"
                : bi.sfu64b.isChecked() ? "2"
                : bi.sfu64c.isChecked() ? "3"
                : bi.sfu64d.isChecked() ? "4"
                : "0");

        oe.put("sfu65", bi.sfu65a.isChecked() ? "1"
                : bi.sfu65b.isChecked() ? "2"
                : bi.sfu6596.isChecked() ? "96"
                : "0");
        oe.put("sfu6596x", bi.sfu6596x.getText().toString());
        MainApp.fc.setsExam(String.valueOf(oe));
    }

    private boolean formValidation() {
        String decimalRegex = "^\\d*\\.\\d|\\d+\\.\\d$";
        //String decimalRegex = "([0-9]+(\\.[0-9]))";
        if (!validatorClass.EmptyTextBox(this, bi.sfu54, getString(R.string.sfu54))) {
            return false;
        }
        if (!bi.sfu54.getText().toString().matches("\\d+(\\.\\d+)*")) {
            Toast.makeText(this, "Please enter correct decimal value!", Toast.LENGTH_SHORT).show();
            bi.sfu54.requestFocus();
            bi.sfu54.setError("Please enter correct decimal value!");
            return false;
        } else {
            bi.sfu54.clearFocus();
            bi.sfu54.setError(null);
            if (!validatorClass.RangeTextBox(this, bi.sfu54, 35.0, 40.9, getString(R.string.sfu54), (char) 0x00B0 + "C")) {
                return false;
            }

        }
       /* if (!validatorClass.RangeTextBox(this, bi.sfu54, 35.0, 40.9, getString(R.string.sfu54), (char) 0x00B0 +"C" )) {
            return false;
        }*/
      /*  if (bi.sfu54.getText().toString().contains(".")) {
            if (!bi.sfu54.getText().toString().matches(decimalRegex)) {
                Toast.makeText(this, "Invalid decimal number", Toast.LENGTH_SHORT);
                bi.sfu54.setError("Invalid decimal number");
                bi.sfu54.requestFocus();
              return false;
            } else {
                bi.sfu54.setError(null);
                bi.sfu54.clearFocus();
                return false;
            }
          //  return false;
        }
*/
        if (!validatorClass.EmptyTextBox(this, bi.sfu55, getString(R.string.sfu55))) {
            return false;
        }
        if (!validatorClass.RangeTextBox(this, bi.sfu55, 30, 100, getString(R.string.sfu55), " minutes")) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu56, getString(R.string.sfu56))) {
            return false;
        }
        if (!validatorClass.RangeTextBox(this, bi.sfu56, 130, 200, getString(R.string.sfu55), " minutes")) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu57, bi.sfu57a, getString(R.string.sfu57))) {
            return false;
        }
        if (bi.sfu57a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bi.sfu58, bi.sfu58a, getString(R.string.sfu58))) {
                return false;
            }

        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu59, bi.sfu5996, bi.sfu5996x, getString(R.string.sfu59))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu60, bi.sfu6096, bi.sfu6096x, getString(R.string.sfu60))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu61, bi.sfu6196, bi.sfu6196x, getString(R.string.sfu61))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu61, bi.sfu6196, bi.sfu6196x, getString(R.string.sfu61))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu62, bi.sfu62b, bi.sfu62bx, getString(R.string.sfu62))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu63, bi.sfu6396, bi.sfu6396x, getString(R.string.sfu63))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu64, bi.sfu64a, getString(R.string.sfu64))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu65, bi.sfu6596, bi.sfu6596x, getString(R.string.sfu65))) {
            return false;
        }
        return true;
    }
}
