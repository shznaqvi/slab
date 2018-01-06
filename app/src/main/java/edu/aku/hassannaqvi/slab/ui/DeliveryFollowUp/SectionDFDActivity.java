package edu.aku.hassannaqvi.slab.ui.DeliveryFollowUp;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivitySectionDfdBinding;
import edu.aku.hassannaqvi.slab.ui.Outcome.SectionOCActivity;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class SectionDFDActivity extends Activity {

    ActivitySectionDfdBinding bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bl = DataBindingUtil.setContentView(this, R.layout.activity_section_dfd);
        bl.setCallback(this);
    }

    public Boolean formValidation() {

        if (!validatorClass.EmptyTextBox(this, bl.dfd01, getString(R.string.dfd01))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bl.dfd02, getString(R.string.dfd02))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bl.dfd03, getString(R.string.dfd03))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.dfd04, bl.dfd04a, getString(R.string.dfd04))) {
            return false;
        }

        return validatorClass.EmptyTextBox(this, bl.dfd05, getString(R.string.dfd05));
    }

    public void BtnEnd() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

        MainApp.endActivity(this, this);

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
                Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                finish();

                startActivity(new Intent(this, SectionOCActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject sOb = new JSONObject();

        sOb.put("dfd01", bl.dfd01.getText().toString());
        sOb.put("dfd02", bl.dfd02.getText().toString());
        sOb.put("dfd03", bl.dfd03.getText().toString());
        sOb.put("dfd04", bl.dfd04a.isChecked() ? "1"
                : bl.dfd04b.isChecked() ? "2" : "0");
        sOb.put("dfd05", bl.dfd05.getText().toString());


        MainApp.fc.setsD(String.valueOf(sOb));


        //sRc.put()


        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
//Long rowId;
        DatabaseHelper db = new DatabaseHelper(this);


        Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

        // 2. UPDATE FORM ROWID
        int updcount = db.updateSD();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }


        //return true;
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
