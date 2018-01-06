package edu.aku.hassannaqvi.slab.ui.Outcome;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivitySectionOcBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class SectionOCActivity extends Activity {

    ActivitySectionOcBinding binding;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_section_oc);

        db = new DatabaseHelper(this);
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

//                startActivity(new Intent(this, SecRBActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnEnd() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

        MainApp.endActivity(this, this);

    }

    public Boolean formValidation() {


//       01
        if (!validatorClass.EmptyRadioButton(this, binding.oc01, binding.oc01b, getString(R.string.oc01))) {
            return false;
        }

//        02
        if (binding.oc01a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, binding.oc02w, getString(R.string.rc02w))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, binding.oc02d, getString(R.string.rc02d))) {
                return false;
            }
        }

//       03
        /*01*/
        if (!validatorClass.EmptyRadioButton(this, binding.oc0301, binding.oc0301b, getString(R.string.oc0301))) {
            return false;
        }
        /*02*/
        if (!validatorClass.EmptyRadioButton(this, binding.oc0302, binding.oc0302b, getString(R.string.oc0302))) {
            return false;
        }
        /*03*/
        if (!validatorClass.EmptyRadioButton(this, binding.oc0303, binding.oc0303b, getString(R.string.oc0303))) {
            return false;
        }

//      04
        if (!validatorClass.EmptyRadioButton(this, binding.oc04, binding.oc04b, getString(R.string.oc04))) {
            return false;
        }

//      05
        if (!validatorClass.EmptyRadioButton(this, binding.oc05, binding.oc0599, getString(R.string.oc05))) {
            return false;
        }

//        06
        if (!validatorClass.EmptyTextBox(this, binding.oc06, getString(R.string.oc06))) {
            return false;
        }

//        07
        if (!validatorClass.EmptyTextBox(this, binding.oc07, getString(R.string.oc07))) {
            return false;
        }

//      08
        if (!validatorClass.EmptyRadioButton(this, binding.oc08, binding.oc0899, getString(R.string.oc08))) {
            return false;
        }

//      09
        if (!validatorClass.EmptyRadioButton(this, binding.oc09, binding.oc0999, getString(R.string.oc09))) {
            return false;
        }

//      10
        if (!validatorClass.EmptyRadioButton(this, binding.oc10, binding.oc1099, getString(R.string.oc10))) {
            return false;
        }

//      11
        if (!validatorClass.EmptyTextBox(this, binding.oc11, getString(R.string.oc11))) {
            return false;
        }

//      12
        if (!validatorClass.EmptyRadioButton(this, binding.oc12, binding.oc12b, getString(R.string.oc12))) {
            return false;
        }

//      13
        if (!validatorClass.EmptyTextBox(this, binding.oc13, getString(R.string.oc13))) {
            return false;
        }

//      14
        if (!validatorClass.EmptyRadioButton(this, binding.oc14, binding.oc14b, getString(R.string.oc14))) {
            return false;
        }

//      15
        if (!validatorClass.EmptyTextBox(this, binding.oc15, getString(R.string.oc15))) {
            return false;
        }

//        16
        if (!validatorClass.EmptyRadioButton(this, binding.oc16, binding.oc1688, binding.oc1688x, getString(R.string.oc16))) {
            return false;
        }

//      17
        if (!validatorClass.EmptyRadioButton(this, binding.oc17, binding.oc17d, getString(R.string.oc17))) {
            return false;
        }

//      17
        return validatorClass.EmptyRadioButton(this, binding.oc18, binding.oc1866, getString(R.string.oc18));
    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);
        Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

        // 2. UPDATE FORM ROWID
        int updcount = db.updateSC();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        MainApp.fc = new FormsContract();

        JSONObject soc = new JSONObject();

        soc.put("oc01", binding.oc01a.isChecked() ? "1" : binding.oc01b.isChecked() ? "2" : "0");

        soc.put("oc02w", binding.oc02w.getText().toString());
        soc.put("oc02d", binding.oc02d.getText().toString());

        soc.put("oc0301", binding.oc0301a.isChecked() ? "1" : binding.oc0301b.isChecked() ? "2" : "0");
        soc.put("oc0302", binding.oc0302a.isChecked() ? "1" : binding.oc0302b.isChecked() ? "2" : "0");
        soc.put("oc0303", binding.oc0303a.isChecked() ? "1" : binding.oc0303b.isChecked() ? "2" : "0");

        soc.put("oc04", binding.oc04a.isChecked() ? "1" : binding.oc04b.isChecked() ? "2" : "0");
        soc.put("oc05", binding.oc05a.isChecked() ? "1" : binding.oc05b.isChecked() ? "2" : "0");
        soc.put("oc06", binding.oc06.getText().toString());
        soc.put("oc07", binding.oc07.getText().toString());
        soc.put("oc08", binding.oc08a.isChecked() ? "1" : binding.oc08b.isChecked() ? "2" : "0");
        soc.put("oc09", binding.oc09a.isChecked() ? "1" : binding.oc09b.isChecked() ? "2" : "0");
        soc.put("oc10", binding.oc10a.isChecked() ? "1" : binding.oc10b.isChecked() ? "2" : "0");
        soc.put("oc11", binding.oc11.getText().toString());
        soc.put("oc12", binding.oc12a.isChecked() ? "1" : binding.oc12b.isChecked() ? "2" : "0");
        soc.put("oc13", binding.oc13.getText().toString());
        soc.put("oc14", binding.oc14a.isChecked() ? "1" : binding.oc14b.isChecked() ? "2" : "0");
        soc.put("oc15", binding.oc15.getText().toString());

        soc.put("oc16", binding.oc16a.isChecked() ? "1" : binding.oc16b.isChecked() ? "2"
                : binding.oc16c.isChecked() ? "3"
                : binding.oc16d.isChecked() ? "4"
                : binding.oc16e.isChecked() ? "5"
                : binding.oc16f.isChecked() ? "6"
                : binding.oc16g.isChecked() ? "7"
                : binding.oc16h.isChecked() ? "8"
                : binding.oc1688.isChecked() ? "88"
                : "0");
        soc.put("oc1688x", binding.oc1688x.getText().toString());

        soc.put("oc17", binding.oc17a.isChecked() ? "1" : binding.oc17b.isChecked() ? "2"
                : binding.oc17c.isChecked() ? "3"
                : binding.oc17d.isChecked() ? "4"
                : "0");
        soc.put("oc18", binding.oc18a.isChecked() ? "1" : binding.oc18b.isChecked() ? "2"
                : binding.oc18c.isChecked() ? "3"
                : binding.oc18d.isChecked() ? "4"
                : binding.oc18e.isChecked() ? "5"
                : binding.oc1866.isChecked() ? "66"
                : "0");

        MainApp.fc.setsC(String.valueOf(soc));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

}