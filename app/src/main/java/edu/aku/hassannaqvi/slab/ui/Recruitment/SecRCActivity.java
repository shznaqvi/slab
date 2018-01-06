package edu.aku.hassannaqvi.slab.ui.Recruitment;

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
import java.util.Calendar;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivitySecRcBinding;
import edu.aku.hassannaqvi.slab.ui.EndingActivity;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class SecRCActivity extends AppCompatActivity {

    ActivitySecRcBinding bl;
    String dateToday;
    String maxDate9Monthsback;
    String maxDate9Months;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sec_rc);

        bl = DataBindingUtil.setContentView(this, R.layout.activity_sec_rc);

        dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
        maxDate9Monthsback = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTimeInMillis() - ((MainApp.MILLISECONDS_IN_9MONTH) + MainApp.MILLISECONDS_IN_DAY));
        maxDate9Months = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTimeInMillis() + ((MainApp.MILLISECONDS_IN_9MONTH) + MainApp.MILLISECONDS_IN_DAY));

        setupViews();
        bl.setCallback(this);


    }

    public void setupViews() {
        bl.rc01.setManager(getSupportFragmentManager());
        bl.rc03.setManager(getSupportFragmentManager());
        bl.rc01.setMaxDate(dateToday);
        bl.rc01.setMinDate(maxDate9Monthsback);
        bl.rc03.setMaxDate(maxDate9Months);
        bl.rc03.setMinDate(dateToday);


        bl.rc04.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.rc04a.isChecked()) {
                    bl.fldGrprc05.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrprc05.setVisibility(View.GONE);
                    bl.rc05a.setChecked(false);
                    bl.rc05b.setChecked(false);
                    bl.rc05c.setChecked(false);
                    bl.rc0588.setChecked(false);
                    bl.rc0588x.setText(null);
                }
            }
        });


        bl.rc06.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.rc06a.isChecked()) {
                    bl.fldGrprc07.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrprc07.setVisibility(View.GONE);
                    bl.rc07a.setChecked(false);
                    bl.rc07b.setChecked(false);
                    bl.rc07c.setChecked(false);
                    bl.rc07d.setChecked(false);
                    bl.rc0788.setChecked(false);
                    bl.rc0788x.setText(null);
                    bl.rc08.clearCheck();
                    bl.rc09w.clearCheck();
                    bl.rc09bp.clearCheck();
                    bl.rc09st.clearCheck();
                    bl.rc09ud.clearCheck();
                    bl.rc09hb.clearCheck();
                    bl.rc09us.clearCheck();
                    bl.rc09ti.clearCheck();
                }
            }
        });

        bl.rc1066.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    bl.rc10a.setEnabled(false);
                    bl.rc10b.setEnabled(false);
                    bl.rc10c.setEnabled(false);
                    bl.rc10d.setEnabled(false);
                    bl.rc10e.setEnabled(false);
                    bl.rc10f.setEnabled(false);
                    bl.rc10g.setEnabled(false);
                    bl.rc10a.setChecked(false);
                    bl.rc10b.setChecked(false);
                    bl.rc10c.setChecked(false);
                    bl.rc10d.setChecked(false);
                    bl.rc10e.setChecked(false);
                    bl.rc10f.setChecked(false);
                    bl.rc10g.setChecked(false);


                } else {
                    bl.rc10a.setEnabled(true);
                    bl.rc10b.setEnabled(true);
                    bl.rc10c.setEnabled(true);
                    bl.rc10d.setEnabled(true);
                    bl.rc10e.setEnabled(true);
                    bl.rc10f.setEnabled(true);
                    bl.rc10g.setEnabled(true);
                }

            }
        });

        bl.rc11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.rc11a.isChecked()) {
                    bl.fldGrprc12.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrprc12.setVisibility(View.GONE);
                    bl.rc12a.setChecked(false);
                    bl.rc12b.setChecked(false);
                    bl.rc12c.setChecked(false);
                    bl.rc12d.setChecked(false);
                    bl.rc12e.setChecked(false);
                    bl.rc12f.setChecked(false);
                    bl.rc1288.setChecked(false);
                    bl.rc1288x.setText(null);
                    bl.rc1299.setChecked(false);


                }
            }
        });

        bl.rc0199.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bl.rc01.setVisibility(View.GONE);
                    bl.rc01.setText(null);
                } else {
                    bl.rc01.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    public Boolean formValidation() {

//        3.1
        if (!bl.rc0199.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bl.rc01, getString(R.string.rc01))) {
                return false;
            }
        }

//       3.2 Weeks
        if (!validatorClass.EmptyTextBox(this, bl.rc02, getString(R.string.rc02w))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, bl.rc02, 3, 42, getString(R.string.rc02w), " weeks")) {
            return false;
        }

//       3.2 Days
        /*if (!validatorClass.EmptyTextBox(this, bl.rc02d, getString(R.string.rc02d))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, bl.rc02d, 0, 6, getString(R.string.rc02d), " days")) {
            return false;
        }*/

        // 3.3
        if (!validatorClass.EmptyTextBox(this, bl.rc03, getString(R.string.rc03))) {
            return false;
        }

//       3.4
        if (!validatorClass.EmptyRadioButton(this, bl.rc04, bl.rc04a, getString(R.string.rc04))) {
            return false;
        }

//        3.5
        if (bl.rc04a.isChecked()) {
            if (!validatorClass.EmptyCheckBox(this, bl.fldGrprcCheck05, bl.rc05a, getString(R.string.rc05))) {
                return false;
            }

            if (!validatorClass.EmptyCheckBox(this, bl.fldGrprcCheck05, bl.rc0588, bl.rc0588x, getString(R.string.rc05) + " - " + getString(R.string.other))) {
                return false;
            }
        }
//      3.6
        if (!validatorClass.EmptyRadioButton(this, bl.rc06, bl.rc06a, getString(R.string.rc06))) {
            return false;
        }


        //      3.7

        if (bl.rc06a.isChecked()) {
            if (!validatorClass.EmptyCheckBox(this, bl.Grprc07, bl.rc07a, getString(R.string.rc07))) {
                return false;
            }

            if (!validatorClass.EmptyCheckBox(this, bl.Grprc07, bl.rc0788, bl.rc0788x, getString(R.string.rc07) + " - " + getString(R.string.other))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc08, bl.rc08a, getString(R.string.rc08))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09w, bl.rc09wa, getString(R.string.rc09w))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09bp, bl.rc09bpa, getString(R.string.rc09bp))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09st, bl.rc09sta, getString(R.string.rc09st))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09ud, bl.rc09uda, getString(R.string.rc09ud))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09hb, bl.rc09hba, getString(R.string.rc09hb))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09us, bl.rc09usa, getString(R.string.rc09us))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.rc09ti, bl.rc09tia, getString(R.string.rc09ti))) {
                return false;
            }

        }

        // 3.10

        if (!bl.rc1066.isChecked()) {

            if (!validatorClass.EmptyCheckBox(this, bl.fldGrprc10, bl.rc10a, getString(R.string.rc10))) {
                return false;
            }
        }


        // 3.11
        if (!validatorClass.EmptyRadioButton(this, bl.rc11, bl.rc11a, getString(R.string.rc11))) {
            return false;
        }

        // 3.12
        if (bl.rc11a.isChecked() && !bl.rc1299.isChecked()) {

            if (!validatorClass.EmptyCheckBox(this, bl.fldGrprc12, bl.rc12a, getString(R.string.rc12))) {
                return false;
            }

            if (!validatorClass.EmptyCheckBox(this, bl.fldGrprc12, bl.rc12c, bl.rc12cx, getString(R.string.rc12c))) {
                return false;
            }
            if (!validatorClass.EmptyCheckBox(this, bl.fldGrprc12, bl.rc1288, bl.rc1288x, getString(R.string.other))) {
                return false;
            }


        }

        // 3.13

        if (!validatorClass.EmptyRadioButton(this, bl.rc13, bl.rc13a, getString(R.string.rc13))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc13, bl.rc1388, bl.rc1388x, getString(R.string.rc13))) {
            return false;
        }

        // 3.14
        if (!validatorClass.EmptyRadioButton(this, bl.rc14c, bl.rc14ca, getString(R.string.rc14c))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bl.rc14fb, bl.rc14fba, getString(R.string.rc14fb))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14la, bl.rc14laa, getString(R.string.rc14la))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14nv, bl.rc14nva, getString(R.string.rc14nv))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, bl.rc14cf, bl.rc14cfa, getString(R.string.rc14cf))) {
            return false;
        }


        if (!validatorClass.EmptyRadioButton(this, bl.rc14hs, bl.rc14hsa, getString(R.string.rc14hs))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14fs, bl.rc14fsa, getString(R.string.rc14fs))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bl.rc14sh, bl.rc14sha, getString(R.string.rc14sh))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14fv, bl.rc14fva, getString(R.string.rc14fv))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14plb, bl.rc14plba, getString(R.string.rc14plb))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bl.rc14bm, bl.rc14bma, getString(R.string.rc14bm))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14bv, bl.rc14bva, getString(R.string.rc14bv))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14ddp, bl.rc14ddpa, getString(R.string.rc14ddp))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc14gh, bl.rc14gha, getString(R.string.rc14gh))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc15, bl.rc15a, getString(R.string.rc15))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc16, bl.rc16a, getString(R.string.rc16))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17dpd, bl.rc17dpda, getString(R.string.rc17dpd))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bl.rc17dn, bl.rc17dna, getString(R.string.rc17dn))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17fs, bl.rc17fsa, getString(R.string.rc17fs))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17fp, bl.rc17fpa, getString(R.string.rc17fp))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bl.rc17b, bl.rc17ba, getString(R.string.rc17b))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17ebf, bl.rc17ebfa, getString(R.string.rc17ebf))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17av, bl.rc17ava, getString(R.string.rc17av))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17ahv, bl.rc17ahva, getString(R.string.rc17ahv))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc17ar, bl.rc17ara, getString(R.string.rc17ar))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.rc1788, bl.rc1788a, getString(R.string.rc17) + " - " + getString(R.string.other))) {
            return false;
        }

        return validatorClass.EmptyRadioButton(this, bl.rc1788, bl.rc1788a, bl.rc1788x, getString(R.string.rc17) + " - " + getString(R.string.other));

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

                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        JSONObject sRc = new JSONObject();

        sRc.put("rc01", bl.rc0199.isChecked() ? "99"
                : bl.rc01.getText().toString());

        sRc.put("rc02", bl.rc02.getText().toString());

        sRc.put("rc03", bl.rc03.getText().toString());

        sRc.put("rc04", bl.rc04a.isChecked() ? "1"
                : bl.rc04b.isChecked() ? "2" : "0");

        sRc.put("rc05a", bl.rc05a.isChecked() ? "1" : "0");

        sRc.put("rc05b", bl.rc05b.isChecked() ? "2" : "0");

        sRc.put("rc05c", bl.rc05c.isChecked() ? "3" : "0");

        sRc.put("rc0588", bl.rc0588.isChecked() ? "88" : "0");

        sRc.put("rc0588x", bl.rc0588x.getText().toString());

        sRc.put("rc06", bl.rc06a.isChecked() ? "1"
                : bl.rc06b.isChecked() ? "2" : "0");

        sRc.put("rc07a", bl.rc07a.isChecked() ? "1" : "0");

        sRc.put("rc07b", bl.rc07b.isChecked() ? "2" : "0");

        sRc.put("rc07c", bl.rc07c.isChecked() ? "3" : "0");

        sRc.put("rc07d", bl.rc07d.isChecked() ? "4" : "0");

        sRc.put("rc0788", bl.rc0788.isChecked() ? "88" : "0");

        sRc.put("rc0788x", bl.rc0788x.getText().toString());

        sRc.put("rc08", bl.rc08a.isChecked() ? "1"
                : bl.rc08b.isChecked() ? "2"
                : bl.rc08c.isChecked() ? "3"
                : bl.rc08d.isChecked() ? "4"
                : bl.rc08e.isChecked() ? "5" : "0");

        sRc.put("rc09w", bl.rc09wa.isChecked() ? "1"
                : bl.rc09wb.isChecked() ? "2" : "0");

        sRc.put("rc09bp", bl.rc09bpa.isChecked() ? "1"
                : bl.rc09bpb.isChecked() ? "2" : "0");

        sRc.put("rc09st", bl.rc09sta.isChecked() ? "1"
                : bl.rc09stb.isChecked() ? "2" : "0");

        sRc.put("rc09ud", bl.rc09uda.isChecked() ? "1"
                : bl.rc09udb.isChecked() ? "2" : "0");

        sRc.put("rc09hb", bl.rc09hba.isChecked() ? "1"
                : bl.rc09hbb.isChecked() ? "2" : "0");

        sRc.put("rc09us", bl.rc09usa.isChecked() ? "1"
                : bl.rc09usb.isChecked() ? "2" : "0");

        sRc.put("rc09ti", bl.rc09tia.isChecked() ? "1"
                : bl.rc09tib.isChecked() ? "2" : "0");

        sRc.put("rc10a", bl.rc10a.isChecked() ? "1" : "0");

        sRc.put("rc10b", bl.rc10b.isChecked() ? "2" : "0");

        sRc.put("rc10c", bl.rc10c.isChecked() ? "3" : "0");

        sRc.put("rc10d", bl.rc10d.isChecked() ? "4" : "0");

        sRc.put("rc10e", bl.rc10e.isChecked() ? "5" : "0");

        sRc.put("rc10f", bl.rc10f.isChecked() ? "6" : "0");

        sRc.put("rc10g", bl.rc10g.isChecked() ? "7" : "0");

        sRc.put("rc1066", bl.rc1066.isChecked() ? "66" : "0");

        sRc.put("rc011", bl.rc11a.isChecked() ? "1"
                : bl.rc11b.isChecked() ? "2" : "0");

        sRc.put("rc12a", bl.rc12a.isChecked() ? "1" : "0");

        sRc.put("rc12b", bl.rc12b.isChecked() ? "2" : "0");

        sRc.put("rc12c", bl.rc12c.isChecked() ? "3" : "0");

        sRc.put("rc12d", bl.rc12d.isChecked() ? "4" : "0");

        sRc.put("rc12e", bl.rc12e.isChecked() ? "5" : "0");

        sRc.put("rc12f", bl.rc12f.isChecked() ? "6" : "0");

        sRc.put("rc1288", bl.rc1288.isChecked() ? "88" : "0");

        sRc.put("rc1288x", bl.rc1288x.getText().toString());

        sRc.put("rc1299", bl.rc1299.isChecked() ? "99" : "0");

        sRc.put("rc013", bl.rc13a.isChecked() ? "1"
                : bl.rc13b.isChecked() ? "2"
                : bl.rc13c.isChecked() ? "3"
                : bl.rc1388.isChecked() ? "88"
                : bl.rc1399.isChecked() ? "99" : "0");

        sRc.put("rc1388x", bl.rc1388x.getText().toString());

        sRc.put("rc14c", bl.rc14ca.isChecked() ? "1"
                : bl.rc14cb.isChecked() ? "2" : "0");

        sRc.put("rc14fb", bl.rc14fba.isChecked() ? "1"
                : bl.rc14fbb.isChecked() ? "2" : "0");

        sRc.put("rc14la", bl.rc14laa.isChecked() ? "1"
                : bl.rc14lab.isChecked() ? "2" : "0");

        sRc.put("rc14nv", bl.rc14nva.isChecked() ? "1"
                : bl.rc14nvb.isChecked() ? "2" : "0");

        sRc.put("rc14v", bl.rc14va.isChecked() ? "1"
                : bl.rc14vb.isChecked() ? "2" : "0");

        sRc.put("rc14cf", bl.rc14cfa.isChecked() ? "1"
                : bl.rc14cfb.isChecked() ? "2" : "0");

        sRc.put("rc14hs", bl.rc14hsa.isChecked() ? "1"
                : bl.rc14hsb.isChecked() ? "2" : "0");

        sRc.put("rc14fs", bl.rc14fsa.isChecked() ? "1"
                : bl.rc14fsb.isChecked() ? "2" : "0");

        sRc.put("rc14sh", bl.rc14sha.isChecked() ? "1"
                : bl.rc14shb.isChecked() ? "2" : "0");

        sRc.put("rc14fv", bl.rc14fva.isChecked() ? "1"
                : bl.rc14fvb.isChecked() ? "2" : "0");

        sRc.put("rc14plb", bl.rc14plba.isChecked() ? "1"
                : bl.rc14plbb.isChecked() ? "2" : "0");

        sRc.put("rc14bm", bl.rc14bma.isChecked() ? "1"
                : bl.rc14bmb.isChecked() ? "2" : "0");

        sRc.put("rc14bv", bl.rc14bva.isChecked() ? "1"
                : bl.rc14bvb.isChecked() ? "2" : "0");

        sRc.put("rc14ddp", bl.rc14ddpa.isChecked() ? "1"
                : bl.rc14ddpb.isChecked() ? "2" : "0");

        sRc.put("rc14gh", bl.rc14gha.isChecked() ? "1"
                : bl.rc14ghb.isChecked() ? "2" : "0");

        sRc.put("rc15", bl.rc15a.isChecked() ? "1"
                : bl.rc15b.isChecked() ? "2" : "0");

        sRc.put("rc16", bl.rc16a.isChecked() ? "1"
                : bl.rc16b.isChecked() ? "2" : "0");

        sRc.put("rc17dpd", bl.rc17dpda.isChecked() ? "1"
                : bl.rc17dpdb.isChecked() ? "2" : "0");

        sRc.put("rc17dn", bl.rc17dna.isChecked() ? "1"
                : bl.rc17dnb.isChecked() ? "2" : "0");

        sRc.put("rc17dfs", bl.rc17fsa.isChecked() ? "1"
                : bl.rc17fsb.isChecked() ? "2" : "0");

        sRc.put("rc17fp", bl.rc17fpa.isChecked() ? "1"
                : bl.rc17fpb.isChecked() ? "2" : "0");

        sRc.put("rc17b", bl.rc17ba.isChecked() ? "1"
                : bl.rc17bb.isChecked() ? "2" : "0");

        sRc.put("rc17ebf", bl.rc17ebfa.isChecked() ? "1"
                : bl.rc17ebfb.isChecked() ? "2" : "0");

        sRc.put("rc17av", bl.rc17ava.isChecked() ? "1"
                : bl.rc17avb.isChecked() ? "2" : "0");

        sRc.put("rc17ahv", bl.rc17ahva.isChecked() ? "1"
                : bl.rc17ahvb.isChecked() ? "2" : "0");

        sRc.put("rc17ar", bl.rc17ara.isChecked() ? "1"
                : bl.rc17arb.isChecked() ? "2" : "0");

        sRc.put("rc1788", bl.rc1788a.isChecked() ? "1"
                : bl.rc1788b.isChecked() ? "2" : "0");

        sRc.put("rc1788x", bl.rc1788x.getText().toString());


        MainApp.fc.setsC(String.valueOf(sRc));


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
