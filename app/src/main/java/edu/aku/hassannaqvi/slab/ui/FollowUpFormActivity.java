package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.databinding.ActivityFollowUpFormBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class FollowUpFormActivity extends AppCompatActivity {
    ActivityFollowUpFormBinding bi;
    DatabaseHelper db;

    private static final String TAG = FollowUpFormActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_follow_up_form);
        db = new DatabaseHelper(this);
        bi.setCallback(this);
        setupView();

    }

    private void setupView() {
        bi.sfu01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sfu01c){
                    bi.fldGrpsfu06.setVisibility(View.GONE);
                    bi.sfu06.setText(null);
                    bi.sfu07.setText(null);
                    bi.sfu08.setText(null);
                }
                else{
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
          /* if (UpdateDB()) {
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();
                finish();
                if (isEligibile() && (Double.valueOf(binding.sel01.getText().toString()) > 1.0
                        && Double.valueOf(binding.sel01.getText().toString()) < 2.5)
                        && (Integer.valueOf(binding.sel02w.getText().toString()) >= 28
                        && Integer.valueOf(binding.sel02w.getText().toString()) < 36)) {
                    startActivity(new Intent(this, BaselineActivity.class));
                } else {
                    startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
                }



            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }*/
        }
    }
    public void BtnEnd(){


    }

    private void SaveDraft() throws JSONException {

    }

    public void btnCheckMrno() {

    }
    public void btnStudyId(){

    }
}
