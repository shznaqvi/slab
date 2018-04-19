package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
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
import edu.aku.hassannaqvi.slab.databinding.ActivityFollowUpEndingBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class FollowUpEndingActivity extends AppCompatActivity {
    ActivityFollowUpEndingBinding bi ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this,R.layout.activity_follow_up_ending);
        bi.setCallback(this);

        Boolean check = getIntent().getExtras().getBoolean("complete");
        String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());

        bi.sfu04.setManager(getSupportFragmentManager());
        bi.sfu04.setMaxDate(dateToday);

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
                if(i == R.id.istatusg){
                    bi.fldGrpistatus.setVisibility(View.VISIBLE);
                }else {
                    bi.fldGrpistatus.setVisibility(View.GONE);
                    bi.sfu04.setText(null);
                    bi.sfu05.setText(null);
                }
            }
        });

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

        MainApp.fc.setIstatus(String.valueOf(end));
        if(bi.istatusg.isChecked()) {

            MainApp.fc.setIsDischarged("true");
            MainApp.fc.setDischargeDate(bi.sfu04.getText().toString());
            MainApp.fc.setTotalsachgiven(bi.sfu05.getText().toString());
        }else{
            MainApp.fc.setIsDischarged("");
            MainApp.fc.setDischargeDate("");
            MainApp.fc.setTotalsachgiven("");
        }

        MainApp.fc.setEndtime(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));


        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);
        int updcount = db.updateEnding();

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

        if (!validatorClass.EmptyRadioButton(this, bi.istatus, bi.istatusa, getString(R.string.istatus))) {
            return false;
        }
        if(bi.istatusg.isChecked()){
            if (!validatorClass.EmptyTextBox(this, bi.sfu04, getString(R.string.sfu04))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu05, getString(R.string.sfu05))) {
                return false;
            }
        }

        return true;
    }


}
