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

import edu.aku.hassannaqvi.slab.JsonModelClasses.EligibilityJSONModel;
import edu.aku.hassannaqvi.slab.JsonModelClasses.FollowupJSONModel;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.ChildListContract;
import edu.aku.hassannaqvi.slab.contracts.FollowupListContract;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityFollowUpEndingBinding;
import edu.aku.hassannaqvi.slab.other.JSONUtilClass;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

import static edu.aku.hassannaqvi.slab.core.MainApp.fc;
import static edu.aku.hassannaqvi.slab.core.MainApp.hc;

public class FollowUpEndingActivity extends AppCompatActivity {
    ActivityFollowUpEndingBinding bi;
    FollowupJSONModel fupmodel;
    EligibilityJSONModel elmodel;
    FormsContract formsContract;
    String dateToday, currentDateToday;
    String lastfupdate;

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_follow_up_ending);
        bi.setCallback(this);

        fupmodel = new FollowupJSONModel();
        elmodel = new EligibilityJSONModel();
        formsContract = new FormsContract();
        MainApp.followuplist = new FollowupListContract();

        Boolean check = getIntent().getExtras().getBoolean("complete");
        currentDateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
        dateToday = new SimpleDateFormat("dd-MM-yyyy").format(new Date().getTime());
        lastfupdate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime());


        bi.sfu04.setManager(getSupportFragmentManager());
        bi.sfu04.setMaxDate(currentDateToday);

        if (check) {
            bi.istatusa.setEnabled(true);
            bi.istatusb.setEnabled(false);

        } else {
            bi.istatusa.setEnabled(false);
            bi.istatusb.setEnabled(true);
        }


        bi.istatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.istatusg) {
                    bi.fldGrpistatus.setVisibility(View.VISIBLE);
                } else {
                    bi.fldGrpistatus.setVisibility(View.GONE);
                    bi.sfu04.setText(null);
                    bi.sfu05.setText(null);
                }
            }
        });

    }


    public void BtnEnd() {

        //   Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
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
                MainApp.hc = null;
                MainApp.fc = null;
                MainApp.followuplist = null;
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
        DatabaseHelper db = new DatabaseHelper(this);
        end.put("istatus", bi.istatusa.isChecked() ? "1"
                : bi.istatusb.isChecked() ? "2"
                : bi.istatusc.isChecked() ? "3"
                : bi.istatusd.isChecked() ? "4"
                : bi.istatuse.isChecked() ? "5"
                : bi.istatusf.isChecked() ? "6"
                : bi.istatusg.isChecked() ? "7"
                : bi.istatus96.isChecked() ? "96"
                : "0");
        end.put("istatus96x", bi.istatus96x.getText().toString());
        fc.setIstatus(String.valueOf(end));
        int count = db.getMaxCount(MainApp.fc.getsMrno());
        if (bi.istatusa.isChecked() || bi.istatusg.isChecked()) {
            count++;
        } else {
            count = 0;
        }
        fc.setFupround(String.valueOf(count));
        hc.setround(fc.getFupround());
        if (bi.istatusg.isChecked()) {
            fc.setIsDischarged("true");
            fc.setDischargeDate(bi.sfu04.getText().toString());
            fc.setTotalsachgiven(bi.sfu05.getText().toString());
        } else {
            fc.setIsDischarged("");
            fc.setDischargeDate("");
            fc.setTotalsachgiven("");
        }

        fc.setEndtime(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);
        if (bi.istatusa.isChecked() || bi.istatusg.isChecked()) {
            MainApp.fc.setIsEl("1");
        } else {
            MainApp.fc.setIsEl("");
        }
        int updcount = db.updatefupEnding();
        for (int i = 1; i <= Integer.parseInt(hc.getcount()); i++) {
            db.updateHistory(String.valueOf(i));
        }

//This is for followup list

        fupmodel = JSONUtilClass.getModelFromJSON(MainApp.fc.getsFup(), FollowupJSONModel.class);
        MainApp.followuplist.set_fuid(MainApp.fc.getUID());
        MainApp.followuplist.setChildname(fupmodel.getChildName());
        MainApp.followuplist.setFuplocation(fupmodel.getSfu01());
        MainApp.followuplist.setDischargedate(MainApp.fc.getDischargeDate());
        MainApp.followuplist.setMrno(MainApp.fc.getsMrno());
        MainApp.followuplist.setStudyid(MainApp.fc.getsStudyid());
        MainApp.followuplist.setFupround(fc.getFupround());
        MainApp.followuplist.setLastfupdate(lastfupdate);
        MainApp.followuplist.setFupstatus(bi.istatusa.isChecked() ? "1"
                : bi.istatusb.isChecked() ? "2"
                : bi.istatusc.isChecked() ? "3"
                : bi.istatusd.isChecked() ? "4"
                : bi.istatuse.isChecked() ? "5"
                : bi.istatusf.isChecked() ? "6"
                : bi.istatusg.isChecked() ? "7"
                : bi.istatus96.isChecked() ? "96"
                : "0");
////        if (MainApp.fetchLocal) {
//        formsContract = db.getEl(fupmodel.getUuid());
//        elmodel = JSONUtilClass.getModelFromJSON(formsContract.getsEl(), EligibilityJSONModel.class);
        ChildListContract childListContract = db.getChildName(MainApp.fc.getsMrno(), MainApp.fc.getsStudyid());
        if (!childListContract.get_ID().isEmpty()) {
            MainApp.followuplist.setMothername(childListContract.getMothername());
            MainApp.followuplist.setEnrolmentdate(childListContract.getEnrolmentDate());
        }


   /* MainApp.followuplist.setEnrolmentDate(MainApp.fc.getFormDate());
    Date date = new Date(MainApp.fc.getFormDate());*/
          /* int round = db.getNextRoundCount(MainApp.followuplist.getMrno(), MainApp.followuplist.get_fuid());
           int totalrounds = 0;
            if (bi.istatusa.isChecked()||bi.istatusg.isChecked()){
               round++;
            }
            MainApp.followuplist.setFupround(String.valueOf(round));*/


//        } else {
        // MainApp.followuplist.set_fuid();
//        }
        db.addList(MainApp.followuplist);

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean formValidation() {
        //  Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyRadioButton(this, bi.istatus, bi.istatusa, getString(R.string.istatus))) {
            return false;
        }
        if (bi.istatusg.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu04, getString(R.string.sfu05))) {
                return false;
            }
            return validatorClass.EmptyTextBox(this, bi.sfu05, getString(R.string.sfu06));
        }

        return true;
    }


}
