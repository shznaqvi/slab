package edu.aku.hassannaqvi.slab.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityEndingBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class EndingActivity extends Activity {

    private static final String TAG = EndingActivity.class.getSimpleName();
    ActivityEndingBinding bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_ending);
         ButterKnife.bind(this);*/

        bl = DataBindingUtil.setContentView(this, R.layout.activity_ending);
        bl.setCallback(this);
        Boolean check = getIntent().getExtras().getBoolean("complete");


        if (check) {
            bl.istatusa.setEnabled(true);
            bl.istatusb.setEnabled(false);

        } else {
            bl.istatusa.setEnabled(false);
            bl.istatusb.setEnabled(true);
        }

        bl.istatusb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bl.fldGrprsn.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrprsn.setVisibility(View.GONE);
                    bl.reason.clearCheck();
                    bl.reason88x.setText(null);
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

                MainApp.memFlag = 0;

                MainApp.TotalMembersCount = 0;
                MainApp.TotalMWRACount = 0;
                MainApp.mwraCount = 1;
                MainApp.TotalChildCount = 0;
                MainApp.imsCount = 1;
                MainApp.totalImsCount = 0;
                MainApp.lstChild.clear();
                MainApp.fupLocation = 0;

                MainApp.counter = 0;

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

        end.put("istatus", bl.istatusa.isChecked() ? "1" : bl.istatusb.isChecked() ? "2" : "0");
        end.put("reason", bl.reasona.isChecked() ? "1"
                : bl.reasonb.isChecked() ? "2"
                : bl.reasonc.isChecked() ? "3"
                : bl.reason88.isChecked() ? "88" : "0");
        end.put("reason88x", bl.reason88x.getText().toString());

        MainApp.fc.setIstatus(String.valueOf(end));



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

        if (!validatorClass.EmptyRadioButton(this, bl.istatus, bl.istatusa, getString(R.string.istatus))) {
            return false;
        }
        if (bl.istatusb.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bl.reason, bl.reasona, getString(R.string.reason))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.reason, bl.reason88, bl.reason88x, getString(R.string.reason) + " - " + getString(R.string.others))) {
                return false;
            }
        }



        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
