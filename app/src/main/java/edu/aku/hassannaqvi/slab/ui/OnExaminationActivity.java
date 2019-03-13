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

import static edu.aku.hassannaqvi.slab.ui.FollowUpFormActivity.FUPLOCATION_TAG;

public class OnExaminationActivity extends AppCompatActivity {
    ActivityOnExaminationBinding bi;
    DatabaseHelper db;
    String  childName , localmrno, localstudyID;
    int fupLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_on_examination);
        bi.setCallback(this);
        db = new DatabaseHelper(this);
        setupView();
        gettingIntents();
        ScrollView onexam_scrollview = findViewById(R.id.onexam_scrollview);
        validatorClass.setScrollViewFocus(onexam_scrollview);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

    private void setupView() {
        bi.sfu404.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu404b) {
                    bi.fldGrpsfu404.setVisibility(View.GONE);
                    bi.sfu405.clearCheck();
                } else {
                    bi.fldGrpsfu404.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void gettingIntents() {

        Intent intent = getIntent();
        if (intent.hasExtra(FUPLOCATION_TAG) && intent.hasExtra("childName") && intent.hasExtra("mrno") && intent.hasExtra("studyID")) {
            Bundle bundle = intent.getExtras();
            fupLocation = bundle.getInt(FUPLOCATION_TAG);
            childName = bundle.getString("childName");
            localmrno = bundle.getString("mrno");
            localstudyID = bundle.getString("studyID");
        } else {
            // Do something else
            Toast.makeText(this, "Restart your app or contact your support team!", Toast.LENGTH_SHORT);

        }

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
                startActivity(new Intent(this, SupplementAdminActivity.class).putExtra(FUPLOCATION_TAG, fupLocation)
                        .putExtra("childName", childName)
                        .putExtra("mrno", localmrno)
                        .putExtra("studyID", localstudyID));
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
//        if (formValidation()) {
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
//        }

    }

    private void SaveDraft() throws JSONException {

        JSONObject oe = new JSONObject();
        oe.put("sfu401", bi.sfu401.getText().toString());
            oe.put("sfu402", bi.sfu402.getText().toString());
        oe.put("sfu403", bi.sfu403.getText().toString());

        oe.put("sfu404", bi.sfu404a.isChecked() ? "1"
                : bi.sfu404b.isChecked() ? "2"
                : "0");
        oe.put("sfu405", bi.sfu405a.isChecked() ? "1"
                : bi.sfu405b.isChecked() ? "2"
                : bi.sfu405c.isChecked() ? "3"
                : bi.sfu405d.isChecked() ? "4"
                : bi.sfu405e.isChecked() ? "5"
                : "0");
        oe.put("sfu406", bi.sfu406a.isChecked() ? "1"
                : bi.sfu406b.isChecked() ? "2"
                : bi.sfu406c.isChecked() ? "3"
                : bi.sfu406d.isChecked() ? "4"
                : bi.sfu40696.isChecked() ? "96"
                : "0");
        oe.put("sfu40696x", bi.sfu40696x.getText().toString());
        oe.put("sfu407", bi.sfu407a.isChecked() ? "1"
                : bi.sfu407b.isChecked() ? "2"
                : bi.sfu40796.isChecked() ? "96"
                : "0");
        oe.put("sfu40796x", bi.sfu40796x.getText().toString());

        oe.put("sfu408", bi.sfu408a.isChecked() ? "1"
                : bi.sfu408b.isChecked() ? "2"
                : bi.sfu408c.isChecked() ? "3"
                : bi.sfu408d.isChecked() ? "4"
                : bi.sfu408e.isChecked() ? "5"
                : bi.sfu408f.isChecked() ? "6"
                : bi.sfu40896.isChecked() ? "96"
                : "0");
        oe.put("sfu40896x", bi.sfu40896x.getText().toString());

        oe.put("sfu409", bi.sfu409a.isChecked() ? "1"
                : bi.sfu409b.isChecked() ? "2"
                : "0");
        oe.put("sfu409bx", bi.sfu409bx.getText().toString());
        oe.put("sfu410", bi.sfu410a.isChecked() ? "1"
                : bi.sfu410b.isChecked() ? "2"
                : bi.sfu41096.isChecked() ? "96"
                : "0");
        oe.put("sfu41096x", bi.sfu41096x.getText().toString());
        oe.put("sfu411", bi.sfu411a.isChecked() ? "1"
                : bi.sfu411b.isChecked() ? "2"
                : bi.sfu411c.isChecked() ? "3"
                : bi.sfu411d.isChecked() ? "4"
                : "0");

        oe.put("sfu412", bi.sfu412a.isChecked() ? "1"
                : bi.sfu412b.isChecked() ? "2"
                : bi.sfu41296.isChecked() ? "96"
                : "0");
        oe.put("sfu41296x", bi.sfu41296x.getText().toString());
        MainApp.fc.setsExam(String.valueOf(oe));
    }

    private boolean formValidation() {
        String decimalRegex = "^\\d*\\.\\d|\\d+\\.\\d$";
        //String decimalRegex = "([0-9]+(\\.[0-9]))";
        if (!validatorClass.EmptyTextBox(this, bi.sfu401, getString(R.string.sfu401))) {
            return false;
        }
        if (!bi.sfu401.getText().toString().matches("\\d+(\\.\\d+)*")) {
            Toast.makeText(this, "Please enter correct decimal value!", Toast.LENGTH_SHORT).show();
            bi.sfu401.requestFocus();
            bi.sfu401.setError("Please enter correct decimal value!");
            return false;
        } else {
            bi.sfu401.clearFocus();
            bi.sfu401.setError(null);
            if (!validatorClass.RangeTextBox(this, bi.sfu401, 35.0, 40.9, getString(R.string.sfu401), (char) 0x00B0 + "C")) {
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
        if (!validatorClass.EmptyTextBox(this, bi.sfu402, getString(R.string.sfu402))) {
            return false;
        }
        if (!validatorClass.RangeTextBox(this, bi.sfu402, 30, 100, getString(R.string.sfu402), " minutes")) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu403, getString(R.string.sfu403))) {
            return false;
        }
        if (!validatorClass.RangeTextBox(this, bi.sfu403, 130, 200, getString(R.string.sfu403), " minutes")) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu404, bi.sfu404a, getString(R.string.sfu404))) {
            return false;
        }
        if (bi.sfu404a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bi.sfu405, bi.sfu405a, getString(R.string.sfu405))) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu406, bi.sfu40696, bi.sfu40696x, getString(R.string.sfu406))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu407, bi.sfu40796, bi.sfu40796x, getString(R.string.sfu407))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu408, bi.sfu40896, bi.sfu40896x, getString(R.string.sfu408))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu409, bi.sfu409b, bi.sfu409bx, getString(R.string.sfu409))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu410, bi.sfu41096, bi.sfu41096x, getString(R.string.sfu410))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu411, bi.sfu411a,getString(R.string.sfu411))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu412, bi.sfu41296, bi.sfu41296x, getString(R.string.sfu412))) {
            return false;
        }

        return true;
    }
}
