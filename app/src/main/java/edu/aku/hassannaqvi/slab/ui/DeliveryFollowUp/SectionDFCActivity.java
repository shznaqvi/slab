package edu.aku.hassannaqvi.slab.ui.DeliveryFollowUp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivitySectionDfcBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class SectionDFCActivity extends AppCompatActivity {
    ActivitySectionDfcBinding bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_section_dfc);

        bl = DataBindingUtil.setContentView(this, R.layout.activity_section_dfc);
        setupViews();
        bl.setCallback(this);
    }

    public void setupViews() {

        bl.dfc06.setManager(getSupportFragmentManager());
        bl.dfc06.setMaxDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime()));

        bl.dfc02.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.dfc02a.isChecked()) {
                    bl.fldGrpdfc03.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpdfc03.setVisibility(View.GONE);
                    bl.dfc03.clearCheck();
                    bl.dfc04.clearCheck();
                }
            }
        });

        bl.dfc05.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.dfc05a.isChecked()) {
                    bl.fldGrpdfc08.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpdfc08.setVisibility(View.GONE);
                    bl.dfc08.clearCheck();
                    bl.dfc09.clearCheck();
                    bl.dfc0988x.setText(null);
                    bl.dfc10.clearCheck();
                    bl.dfc11.clearCheck();
                    bl.dfc1188x.setText(null);
                    bl.dfc12.clearCheck();
                    bl.dfc1288x.setText(null);
                    bl.dfc13.clearCheck();
                    bl.dfc14.setText(null);
                    bl.dfc15.clearCheck();
                    bl.dfc16.clearCheck();
                    bl.dfc17.clearCheck();
                    bl.dfc18.clearCheck();
                    bl.dfc19.clearCheck();
                    bl.dfc20.clearCheck();
                    bl.dfc2088x.setText(null);
                }
            }
        });

        bl.dfc08.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.dfc08a.isChecked()) {
                    bl.fldGrpdfc09.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpdfc09.setVisibility(View.GONE);
                    bl.dfc09.clearCheck();
                    bl.dfc0988x.setText(null);
                }
            }
        });

        bl.dfc10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.dfc10a.isChecked()) {
                    bl.fldGrpdfc11.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpdfc11.setVisibility(View.GONE);
                    bl.dfc11.clearCheck();
                    bl.dfc1188x.setText(null);
                    bl.dfc12.clearCheck();
                    bl.dfc1288x.setText(null);
                }
            }
        });

        bl.dfc13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.dfc13a.isChecked()) {
                    bl.fldGrpdfc14.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpdfc14.setVisibility(View.GONE);
                    bl.dfc14.setText(null);
                }
            }
        });

        bl.dfc1866.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bl.fldGrpdfc19.setVisibility(View.GONE);
                    bl.dfc19.clearCheck();
                    bl.dfc20.clearCheck();
                    bl.dfc2088x.setText(null);
                } else {
                    bl.fldGrpdfc19.setVisibility(View.VISIBLE);
                }
            }
        });

        bl.dfc19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.dfc19a.isChecked()) {
                    bl.fldGrpdfc20.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpdfc20.setVisibility(View.GONE);
                    bl.dfc20.clearCheck();
                    bl.dfc2088x.setText(null);
                }
            }
        });

    }


    public Boolean formValidation() {

        // 2.1
        if (!validatorClass.EmptyRadioButton(this, bl.dfc01, bl.dfc01a, getString(R.string.dfc01))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.dfc02, bl.dfc02a, getString(R.string.dfc02))) {
            return false;
        }

        if (bl.dfc02a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bl.dfc03, bl.dfc03a, getString(R.string.dfc03))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.dfc04, bl.dfc04a, getString(R.string.dfc04))) {
                return false;
            }

        }

        if (!validatorClass.EmptyRadioButton(this, bl.dfc05, bl.dfc05a, getString(R.string.dfc05))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bl.dfc06, getString(R.string.dfc06))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.dfc07, bl.dfc07a, getString(R.string.dfc07))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.dfc07, bl.dfc0788, bl.dfc0788x, getString(R.string.dfc07) + " - " + getString(R.string.other))) {
            return false;
        }

        if (bl.dfc05a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bl.dfc08, bl.dfc08a, getString(R.string.dfc08))) {
                return false;
            }

            if (bl.dfc08a.isChecked()) {
                if (!validatorClass.EmptyRadioButton(this, bl.dfc09, bl.dfc09a, getString(R.string.dfc09))) {
                    return false;
                }

                if (!validatorClass.EmptyRadioButton(this, bl.dfc09, bl.dfc0988, bl.dfc0988x, getString(R.string.dfc09) + " - " + getString(R.string.other))) {
                    return false;
                }


            }

            if (!validatorClass.EmptyRadioButton(this, bl.dfc10, bl.dfc10a, getString(R.string.dfc10))) {
                return false;
            }

            if (bl.dfc10a.isChecked()) {
                if (!validatorClass.EmptyRadioButton(this, bl.dfc11, bl.dfc11a, getString(R.string.dfc11))) {
                    return false;
                }

                if (!validatorClass.EmptyRadioButton(this, bl.dfc11, bl.dfc1188, bl.dfc1188x, getString(R.string.dfc11) + " - " + getString(R.string.other))) {
                    return false;
                }

                if (!validatorClass.EmptyRadioButton(this, bl.dfc12, bl.dfc12a, getString(R.string.dfc12))) {
                    return false;
                }

                if (!validatorClass.EmptyRadioButton(this, bl.dfc12, bl.dfc1288, bl.dfc1288x, getString(R.string.dfc12) + " - " + getString(R.string.other))) {
                    return false;
                }
            }
            if (!validatorClass.EmptyRadioButton(this, bl.dfc13, bl.dfc13a, getString(R.string.dfc13))) {
                return false;
            }

            if (bl.dfc13a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.dfc14, getString(R.string.dfc14))) {
                    return false;
                }

            }

            if (!validatorClass.EmptyRadioButton(this, bl.dfc15, bl.dfc15a, getString(R.string.dfc15))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.dfc16, bl.dfc16a, getString(R.string.dfc16))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.dfc17, bl.dfc17a, getString(R.string.dfc17))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.dfc18, bl.dfc18a, getString(R.string.dfc18))) {
                return false;
            }

            if (!bl.dfc1866.isChecked()) {
                if (!validatorClass.EmptyRadioButton(this, bl.dfc19, bl.dfc19a, getString(R.string.dfc19))) {
                    return false;
                }

                if (bl.dfc19a.isChecked()) {
                    if (!validatorClass.EmptyRadioButton(this, bl.dfc20, bl.dfc20a, getString(R.string.dfc20))) {
                        return false;
                    }
                    if (!validatorClass.EmptyRadioButton(this, bl.dfc20, bl.dfc2088, bl.dfc2088x, getString(R.string.dfc20) + " - " + getString(R.string.other))) {
                        return false;
                    }
                }
            }

        }

        return true;
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

                startActivity(new Intent(this, SectionDFDActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject sOb = new JSONObject();

        sOb.put("dfc01", bl.dfc01a.isChecked() ? "1"
                : bl.dfc01b.isChecked() ? "2"
                : bl.dfc01c.isChecked() ? "3"
                : bl.dfc0199.isChecked() ? "99" : "0");

        sOb.put("dfc02", bl.dfc02a.isChecked() ? "1"
                : bl.dfc02b.isChecked() ? "2" : "0");

        sOb.put("dfc03", bl.dfc03a.isChecked() ? "1"
                : bl.dfc03b.isChecked() ? "2"
                : bl.dfc03c.isChecked() ? "3"
                : bl.dfc03d.isChecked() ? "4"
                : bl.dfc03e.isChecked() ? "5"
                : bl.dfc03f.isChecked() ? "6"
                : bl.dfc03g.isChecked() ? "7" : "0");


        sOb.put("dfc04", bl.dfc04a.isChecked() ? "1"
                : bl.dfc04b.isChecked() ? "2"
                : bl.dfc0499.isChecked() ? "99" : "0");

        sOb.put("dfc05", bl.dfc05a.isChecked() ? "1"
                : bl.dfc05b.isChecked() ? "2" : "0");

        sOb.put("dfc06", bl.dfc06.getText().toString());

        sOb.put("dfc07", bl.dfc07a.isChecked() ? "1"
                : bl.dfc07b.isChecked() ? "2"
                : bl.dfc07c.isChecked() ? "3"
                : bl.dfc07d.isChecked() ? "4"
                : bl.dfc07e.isChecked() ? "5"
                : bl.dfc07f.isChecked() ? "6"
                : bl.dfc07g.isChecked() ? "7"
                : bl.dfc07h.isChecked() ? "8"
                : bl.dfc0788.isChecked() ? "88" : "0");
        sOb.put("dfc0788x", bl.dfc0788x.getText().toString());

        sOb.put("dfc08", bl.dfc08a.isChecked() ? "1"
                : bl.dfc08b.isChecked() ? "2"
                : bl.dfc0899.isChecked() ? "99" : "0");

        sOb.put("dfc09", bl.dfc09a.isChecked() ? "1"
                : bl.dfc09b.isChecked() ? "2"
                : bl.dfc09c.isChecked() ? "3"
                : bl.dfc09d.isChecked() ? "4"
                : bl.dfc09e.isChecked() ? "5"
                : bl.dfc09f.isChecked() ? "6"
                : bl.dfc09g.isChecked() ? "7"
                : bl.dfc09h.isChecked() ? "8"
                : bl.dfc0999.isChecked() ? "99"
                : bl.dfc0988.isChecked() ? "88" : "0");
        sOb.put("dfc0988x", bl.dfc0988x.getText().toString());


        sOb.put("dfc10", bl.dfc10a.isChecked() ? "1"
                : bl.dfc10b.isChecked() ? "2"
                : bl.dfc1099.isChecked() ? "99" : "0");

        sOb.put("dfc11", bl.dfc11a.isChecked() ? "1"
                : bl.dfc11b.isChecked() ? "2"
                : bl.dfc1188.isChecked() ? "88" : "0");
        sOb.put("dfc1188x", bl.dfc1188x.getText().toString());

        sOb.put("dfc12", bl.dfc12a.isChecked() ? "1"
                : bl.dfc12b.isChecked() ? "2"
                : bl.dfc1288.isChecked() ? "88" : "0");
        sOb.put("dfc1288x", bl.dfc1288x.getText().toString());

        sOb.put("dfc13", bl.dfc13a.isChecked() ? "1"
                : bl.dfc13b.isChecked() ? "2" : "0");

        sOb.put("dfc14", bl.dfc14.getText().toString());

        sOb.put("dfc15", bl.dfc15a.isChecked() ? "1"
                : bl.dfc15b.isChecked() ? "2"
                : bl.dfc15c.isChecked() ? "3"
                : bl.dfc1566.isChecked() ? "66" : "0");

        sOb.put("dfc16", bl.dfc16a.isChecked() ? "1"
                : bl.dfc16b.isChecked() ? "2"
                : bl.dfc16c.isChecked() ? "3"
                : bl.dfc1666.isChecked() ? "66" : "0");

        sOb.put("dfc17", bl.dfc17a.isChecked() ? "1"
                : bl.dfc17b.isChecked() ? "2"
                : bl.dfc17c.isChecked() ? "3"
                : bl.dfc17d.isChecked() ? "4"
                : bl.dfc17e.isChecked() ? "5"
                : bl.dfc17f.isChecked() ? "6"
                : bl.dfc1766.isChecked() ? "66" : "0");

        sOb.put("dfc18", bl.dfc18a.isChecked() ? "1"
                : bl.dfc18b.isChecked() ? "2"
                : bl.dfc18c.isChecked() ? "3"
                : bl.dfc18d.isChecked() ? "4"
                : bl.dfc18e.isChecked() ? "5"
                : bl.dfc18f.isChecked() ? "6"
                : bl.dfc1866.isChecked() ? "66" : "0");

        sOb.put("dfc19", bl.dfc19a.isChecked() ? "1"
                : bl.dfc19b.isChecked() ? "2" : "0");

        sOb.put("dfc20", bl.dfc20a.isChecked() ? "1"
                : bl.dfc20b.isChecked() ? "2"
                : bl.dfc20c.isChecked() ? "3"
                : bl.dfc20d.isChecked() ? "4"
                : bl.dfc2088.isChecked() ? "88" : "0");

        sOb.put("dfc2088x", bl.dfc2088x.getText().toString());


        MainApp.fc.setsC(String.valueOf(sOb));


        //sRc.put()


        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
//Long rowId;
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
