package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.JsonModelClasses.RecruitmentJSONModel;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.ChildListContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityRecruitmentEndingBinding;
import edu.aku.hassannaqvi.slab.other.JSONUtilClass;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class RecruitmentEndingActivity extends AppCompatActivity {

    private static final String TAG = RecruitmentEndingActivity.class.getSimpleName();
    ActivityRecruitmentEndingBinding bl;
    RecruitmentJSONModel recmodel;
    String mothername, birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recmodel = new RecruitmentJSONModel();
        MainApp.R_CHILDLIST = new ChildListContract();
        bl = DataBindingUtil.setContentView(this, R.layout.activity_recruitment_ending);
        bl.setCallback(this);
        Boolean check = getIntent().getExtras().getBoolean("complete");
        String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());

        bl.sfu04.setManager(getSupportFragmentManager());
        bl.sfu04.setMaxDate(dateToday);

        if (check) {
            bl.istatusa.setEnabled(true);
            bl.istatusb.setEnabled(false);
            bl.istatusc.setEnabled(false);
            bl.istatusd.setEnabled(false);
            bl.istatuse.setEnabled(false);
            bl.istatusf.setEnabled(false);
            bl.istatus96.setEnabled(false);
        } else {
            bl.istatusa.setEnabled(false);
            bl.istatusb.setEnabled(true);
            bl.istatusc.setEnabled(true);
            bl.istatusd.setEnabled(true);
            bl.istatuse.setEnabled(true);
            bl.istatusf.setEnabled(true);
            bl.istatus96.setEnabled(true);
        }
        gettingintents();

        bl.istatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.istatusg) {
                    bl.fldGrpistatus.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpistatus.setVisibility(View.GONE);
                    bl.sfu04.setText(null);
                    bl.sfu05.setText(null);
                }
            }
        });

    }

    private void gettingintents() {
        Intent intent = getIntent();
        if (intent.hasExtra("mothername") && intent.hasExtra("birthdate")) {
            Bundle bundle = intent.getExtras();
            birthdate = bundle.getString("birthdate");
            mothername = bundle.getString("mothername");
        } else {
            // Do something else
            Toast.makeText(this, "Restart your app or contact your support team!", Toast.LENGTH_SHORT);

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


                MainApp.fupLocation = 0;


                MainApp.selectedPos = -1;

                MainApp.randID = 1;

                MainApp.isRsvp = false;
                MainApp.isHead = false;
                MainApp.flag = true;

                finish();
                Intent endSec = new Intent(this, MainActivity.class);
                endSec.putExtra("complete", false);
                startActivity(endSec);
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject end = new JSONObject();

        end.put("istatus", bl.istatusa.isChecked() ? "1"
                : bl.istatusb.isChecked() ? "2"
                : bl.istatusc.isChecked() ? "3"
                : bl.istatusd.isChecked() ? "4"
                : bl.istatuse.isChecked() ? "5"
                : bl.istatusf.isChecked() ? "6"
                : bl.istatusg.isChecked() ? "7"
                : bl.istatus96.isChecked() ? "96"
                : "0");
        end.put("istatus96x", bl.istatus96x.getText().toString());

        MainApp.fc.setIstatus(String.valueOf(end));

        MainApp.fc.setEndtime(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));


        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);
        int updcount = db.updateEnding();
        if (bl.istatusa.isChecked()) {

            //TODO : updating child list...
            recmodel = JSONUtilClass.getModelFromJSON(MainApp.fc.getsRecr(), RecruitmentJSONModel.class);
            MainApp.R_CHILDLIST.set_ruid(MainApp.fc.getUID());
            MainApp.R_CHILDLIST.setBirthdate(birthdate);
            MainApp.R_CHILDLIST.setChildname(recmodel.getChildName());
            MainApp.R_CHILDLIST.setMothername(mothername);
            MainApp.R_CHILDLIST.setEnrolmentDate(recmodel.getSen03());
            MainApp.R_CHILDLIST.setMrNo(MainApp.fc.getsMrno());
            MainApp.R_CHILDLIST.setStudyID(MainApp.fc.getsStudyid());
            db.addChildList(MainApp.R_CHILDLIST);
        }


        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyRadioButton(this, bl.istatus, bl.istatusa, getString(R.string.istatus))) {
            return false;
        }
        if (bl.istatusg.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bl.sfu04, getString(R.string.sfu05))) {
                return false;
            }
            return validatorClass.EmptyTextBox(this, bl.sfu05, getString(R.string.sfu06));
        }

        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
