package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityFollowUpFormBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class FollowUpFormActivity extends AppCompatActivity {
    ActivityFollowUpFormBinding bi;
    DatabaseHelper db;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    private static final String TAG = FollowUpFormActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_follow_up_form);
        db = new DatabaseHelper(this);
        bi.setCallback(this);
        setupView();
        Log.d("a1", dtToday);

    }

    private void setupView() {
        bi.sfu01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu01c) {
                    bi.fldGrpsfu06.setVisibility(View.GONE);
                    bi.sfu06.setText(null);
                    bi.sfu07.setText(null);
                    bi.sfu08.setText(null);
                } else {
                    bi.fldGrpsfu06.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    private boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyTextBox(this, bi.sfu001, getString(R.string.mrno))) {
            return false;
        }
        if (bi.sfu001.getText().toString().length() == 9) {
            String[] str = bi.sfu001.getText().toString().split("-");
            if (str.length > 3 || bi.sfu001.getText().toString().charAt(3) != '-' || bi.sfu001.getText().toString().charAt(6) != '-') {
                bi.sfu001.setError("Wrong presentation!!");
                return false;
            }
        } else {
            Toast.makeText(this, "Invalid length: " + getString(R.string.mrno), Toast.LENGTH_SHORT).show();
            bi.sfu001.setError("Invalid length");
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.sfu05, getString(R.string.sfu05))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu06, getString(R.string.sfu06))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu07, getString(R.string.sfu07))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu08, getString(R.string.sfu08))) {
            return false;
        }
        return true;
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
                if (bi.sfu01c.isChecked()) {
                    startActivity(new Intent(this, SupplementAdminActivity.class).putExtra("complete", true));
                } else {
                    startActivity(new Intent(this, OnExaminationActivity.class).putExtra("complete", true));

                }


            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
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

    public void BtnEnd() {


    }

    private void SaveDraft() throws JSONException {
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);

        JSONObject fu = new JSONObject();
        fu.put("sfudatetime", dtToday);
        fu.put("sfu01", bi.sfu01a.isChecked() ? "1"
                : bi.sfu01b.isChecked() ? "2"
                : bi.sfu01c.isChecked() ? "3"
                : "0");
        fu.put("sfu06", bi.sfu06.getText().toString());
        fu.put("sfu07", bi.sfu07.getText().toString());
        fu.put("sfu08", bi.sfu08.getText().toString());

    }

    public void btnCheckMrno() {

    }

    public void btnStudyId() {

    }
}
