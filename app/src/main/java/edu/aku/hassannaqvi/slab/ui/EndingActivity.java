package edu.aku.hassannaqvi.slab.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityEndingBinding;

public class EndingActivity extends Activity {

    private static final String TAG = EndingActivity.class.getSimpleName();
    ActivityEndingBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);
        ButterKnife.bind(this);

        Boolean check = getIntent().getExtras().getBoolean("complete");

        if (check) {
            b.istatusa.setEnabled(true);
            b.istatusb.setEnabled(false);

        } else {
            //fldGrpmn0823Reason.setVisibility(View.GONE);
            //istatus1.setEnabled(false);
        }


    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {

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

//    Total No of Alive members got from Section B

/*                MainApp.currentStatusCount = 0;
                MainApp.currentDeceasedCheck = 0;
                MainApp.currentMotherCheck = 0;*/

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

        MainApp.fc.setIstatus(b.istatusa.isChecked() ? "1"
                : b.istatusb.isChecked() ? "2"
                : "0");



        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateEnding();
        /*if (MainApp.memFlag != 0) {
            db.updateFamilyMember();
        }
        if (MainApp.currentDeceasedCheck != 0) {
            db.updateDeceasedMother();
        }
        if (MainApp.currentMotherCheck != 0) {
            db.updateMother();
        }
        if (MainApp.totalChild != 0) {
            db.updateIM();
        }
*/
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

        if (b.istatus.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Not Selected): " + getString(R.string.istatus), Toast.LENGTH_LONG).show();
            b.istatusa.setError("Please Select One");    // Set Error on last radio button
            Log.i(TAG, "istatus: This data is Required!");
            return false;
        } else {
            b.istatusa.setError(null);
        }



        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
