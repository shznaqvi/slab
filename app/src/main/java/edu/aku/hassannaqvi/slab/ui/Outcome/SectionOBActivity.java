package edu.aku.hassannaqvi.slab.ui.Outcome;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivitySectionObBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class SectionOBActivity extends Activity {

    ActivitySectionObBinding bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_section_ob);

        bl = DataBindingUtil.setContentView(this, R.layout.activity_section_ob);
        setupViews();
        bl.setCallback(this);
    }

    public void setupViews() {

        // Q 2.1

        bl.ob01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.ob01b.isChecked() || bl.ob01c.isChecked() || bl.ob01d.isChecked()) {
                    bl.fldGrpob16.setVisibility(View.GONE);
                    bl.ob16.clearCheck();
                    bl.ob1688x.setText(null);
                    bl.ob17.clearCheck();
                    bl.ob1788x.setText(null);
                    bl.ob18.clearCheck();
                    bl.ob1888x.setText(null);
                    bl.ob19.clearCheck();
                    bl.ob20.clearCheck();
                    bl.ob2088x.setText(null);
                    bl.ob21.clearCheck();
                    bl.ob22a.setText(null);
                    bl.ob2244.setChecked(false);
                    bl.ob23.clearCheck();
                    bl.ob24.clearCheck();
                    bl.ob2488x.setText(null);
                    bl.ob25.clearCheck();
                    bl.ob26.clearCheck();
                    bl.ob27.clearCheck();
                    bl.ob28.clearCheck();
                    bl.ob29.clearCheck();
                    bl.ob2988x.setText(null);
                    bl.ob30.clearCheck();
                    bl.ob31.clearCheck();
                } else {
                    bl.fldGrpob16.setVisibility(View.VISIBLE);
                }
            }
        });

        bl.ob05.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.ob05d.isChecked()) {
                    bl.fldGrpob06.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpob06.setVisibility(View.GONE);
                    bl.ob06a.setChecked(false);
                    bl.ob06b.setChecked(false);
                    bl.ob06c.setChecked(false);
                    bl.ob06d.setChecked(false);
                    bl.ob06e.setChecked(false);
                    bl.ob06f.setChecked(false);
                    bl.ob0688.setChecked(false);
                    bl.ob0688x.setText(null);
                    bl.ob0699.setChecked(false);
                }
            }
        });

        bl.ob0699.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    bl.ob06a.setEnabled(false);
                    bl.ob06b.setEnabled(false);
                    bl.ob06c.setEnabled(false);
                    bl.ob06d.setEnabled(false);
                    bl.ob06e.setEnabled(false);
                    bl.ob06f.setEnabled(false);
                    bl.ob0688.setEnabled(false);
                    bl.ob06a.setChecked(false);
                    bl.ob06b.setChecked(false);
                    bl.ob06c.setChecked(false);
                    bl.ob06d.setChecked(false);
                    bl.ob06e.setChecked(false);
                    bl.ob06f.setChecked(false);
                    bl.ob0688.setChecked(false);
                    bl.ob0688x.setText(null);
                } else {
                    bl.ob06a.setEnabled(true);
                    bl.ob06b.setEnabled(true);
                    bl.ob06c.setEnabled(true);
                    bl.ob06d.setEnabled(true);
                    bl.ob06e.setEnabled(true);
                    bl.ob06f.setEnabled(true);
                    bl.ob0688.setEnabled(true);
                }
            }
        });

        bl.ob13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.ob13a.isChecked()) {
                    bl.fldGrpob14.setVisibility(View.GONE);
                    bl.ob14.clearCheck();
                    bl.ob15.clearCheck();
                    bl.ob1588x.setText(null);
                } else {
                    bl.fldGrpob14.setVisibility(View.VISIBLE);
                }
            }
        });

        bl.ob19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.ob19a.isChecked()) {
                    bl.fldGrpob20.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpob20.setVisibility(View.GONE);
                    bl.ob20.clearCheck();
                    bl.ob2088x.setText(null);
                }
            }
        });

        bl.ob23.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.ob23a.isChecked()) {
                    bl.fldGrpob24.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpob24.setVisibility(View.GONE);
                    bl.ob24.clearCheck();
                    bl.ob2488x.setText(null);
                }
            }
        });

        bl.ob26.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.ob26a.isChecked()) {
                    bl.fldGrpob27.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpob27.setVisibility(View.GONE);
                    bl.ob27.clearCheck();
                }
            }
        });

        bl.ob3255.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bl.ob32a.setVisibility(View.GONE);
                    bl.ob32a.setText(null);
                    bl.ob3277.setEnabled(false);
                    bl.ob3277.setChecked(false);
                    bl.ob3299.setEnabled(false);
                    bl.ob3299.setChecked(false);
                } else {
                    bl.ob32a.setVisibility(View.VISIBLE);
                    bl.ob3277.setEnabled(true);
                    bl.ob3299.setEnabled(true);

                }

            }
        });

        bl.ob3277.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bl.ob32a.setVisibility(View.GONE);
                    bl.ob32a.setText(null);
                    bl.ob3255.setEnabled(false);
                    bl.ob3255.setChecked(false);
                    bl.ob3299.setEnabled(false);
                    bl.ob3299.setChecked(false);
                } else {
                    bl.ob32a.setVisibility(View.VISIBLE);
                    bl.ob3255.setEnabled(true);
                    bl.ob3299.setEnabled(true);

                }

            }
        });

        bl.ob3299.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bl.ob32a.setVisibility(View.GONE);
                    bl.ob32a.setText(null);
                    bl.ob3255.setEnabled(false);
                    bl.ob3255.setChecked(false);
                    bl.ob3277.setEnabled(false);
                    bl.ob3277.setChecked(false);
                } else {
                    bl.ob32a.setVisibility(View.VISIBLE);
                    bl.ob3255.setEnabled(true);
                    bl.ob3277.setEnabled(true);

                }

            }
        });

        bl.ob1077.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    bl.ob10a.setEnabled(false);
                    bl.ob10b.setEnabled(false);
                    bl.ob10c.setEnabled(false);
                    bl.ob10d.setEnabled(false);
                    bl.ob10e.setEnabled(false);
                    bl.ob10f.setEnabled(false);
                    bl.ob10g.setEnabled(false);
                    bl.ob10a.setChecked(false);
                    bl.ob10b.setChecked(false);
                    bl.ob10c.setChecked(false);
                    bl.ob10d.setChecked(false);
                    bl.ob10e.setChecked(false);
                    bl.ob10f.setChecked(false);
                    bl.ob10g.setChecked(false);
                } else {
                    bl.ob10a.setEnabled(true);
                    bl.ob10b.setEnabled(true);
                    bl.ob10c.setEnabled(true);
                    bl.ob10d.setEnabled(true);
                    bl.ob10e.setEnabled(true);
                    bl.ob10f.setEnabled(true);
                    bl.ob10g.setEnabled(true);
                }
            }
        });


    }


    public Boolean formValidation() {

        // 2.1
        if (!validatorClass.EmptyRadioButton(this, bl.ob01, bl.ob01a, getString(R.string.ob01))) {
            return false;
        }
        // 2.2
        if (!validatorClass.EmptyRadioButton(this, bl.ob02, bl.ob02a, getString(R.string.ob02))) {
            return false;
        }

        // 2.3
        if (!validatorClass.EmptyRadioButton(this, bl.ob03, bl.ob03a, getString(R.string.ob03))) {
            return false;
        }

        // 2.4
        if (!validatorClass.EmptyRadioButton(this, bl.ob04, bl.ob04a, getString(R.string.ob04))) {
            return false;
        }

        // 2.5
        if (!validatorClass.EmptyRadioButton(this, bl.ob05, bl.ob05a, getString(R.string.ob05))) {
            return false;
        }

        // 2.5x
        if (!validatorClass.EmptyRadioButton(this, bl.ob05, bl.ob0588, bl.ob0588x, getString(R.string.ob05) + " - " + getString(R.string.other))) {
            return false;
        }

        if (bl.ob05d.isChecked() && !bl.ob0699.isChecked()) {
            // 2.6
            if (!validatorClass.EmptyCheckBox(this, bl.fldGrpob06, bl.ob06a, getString(R.string.ob06))) {
                return false;
            }

            // 2.6x
            if (!validatorClass.EmptyCheckBox(this, bl.fldGrpob06, bl.ob0688, bl.ob0688x, getString(R.string.ob06) + " - " + getString(R.string.other))) {
                return false;
            }
        }


        // 2.7
        if (!validatorClass.EmptyRadioButton(this, bl.ob07, bl.ob07a, getString(R.string.ob07))) {
            return false;
        }

        // 2.7x
        if (!validatorClass.EmptyRadioButton(this, bl.ob07, bl.ob0788, bl.ob0788x, getString(R.string.ob07) + " - " + getString(R.string.other))) {
            return false;
        }

        // 2.8
        if (!validatorClass.EmptyRadioButton(this, bl.ob08, bl.ob08a, getString(R.string.ob08))) {
            return false;
        }

        // 2.9
        if (!validatorClass.EmptyRadioButton(this, bl.ob09, bl.ob09a, getString(R.string.ob09))) {
            return false;
        }

        // 2.9x
        if (!validatorClass.EmptyRadioButton(this, bl.ob09, bl.ob0988, bl.ob0988x, getString(R.string.ob09) + " - " + getString(R.string.other))) {
            return false;
        }


        if (!bl.ob1077.isChecked()) {
            // 2.10
            if (!validatorClass.EmptyCheckBox(this, bl.fldGrpob10, bl.ob10a, getString(R.string.ob10))) {
                return false;
            }
        }

        // 2.11
        if (!validatorClass.EmptyRadioButton(this, bl.ob11, bl.ob11a, getString(R.string.ob11))) {
            return false;
        }

        // 2.11x
        if (!validatorClass.EmptyRadioButton(this, bl.ob11, bl.ob1188, bl.ob1188x, getString(R.string.ob11) + " - " + getString(R.string.other))) {
            return false;
        }

        // 2.12
        if (!validatorClass.EmptyRadioButton(this, bl.ob12, bl.ob12a, getString(R.string.ob12))) {
            return false;
        }

        // 2.12x
        if (!validatorClass.EmptyRadioButton(this, bl.ob12, bl.ob1288, bl.ob1288x, getString(R.string.ob12) + " - " + getString(R.string.other))) {
            return false;
        }

        // 2.13
        if (!validatorClass.EmptyRadioButton(this, bl.ob13, bl.ob13a, getString(R.string.ob13))) {
            return false;
        }

        if (!bl.ob13a.isChecked()) {
            // 2.14
            if (!validatorClass.EmptyRadioButton(this, bl.ob14, bl.ob14a, getString(R.string.ob14))) {
                return false;
            }

            // 2.15
            if (!validatorClass.EmptyRadioButton(this, bl.ob15, bl.ob15a, getString(R.string.ob15))) {
                return false;
            }

            // 2.15x
            if (!validatorClass.EmptyRadioButton(this, bl.ob15, bl.ob1588, bl.ob1588x, getString(R.string.ob15) + " - " + getString(R.string.other))) {
                return false;
            }
        }

        if (!bl.ob01b.isChecked() || !bl.ob01c.isChecked() || !bl.ob01d.isChecked()) {
            // 2.16
            if (!validatorClass.EmptyRadioButton(this, bl.ob16, bl.ob16a, getString(R.string.ob16))) {
                return false;
            }

            // 2.16x
            if (!validatorClass.EmptyRadioButton(this, bl.ob16, bl.ob1688, bl.ob1688x, getString(R.string.ob16) + " - " + getString(R.string.other))) {
                return false;
            }

            // 2.17
            if (!validatorClass.EmptyRadioButton(this, bl.ob17, bl.ob17a, getString(R.string.ob17))) {
                return false;
            }

            // 2.17x
            if (!validatorClass.EmptyRadioButton(this, bl.ob17, bl.ob1788, bl.ob1788x, getString(R.string.ob17) + " - " + getString(R.string.other))) {
                return false;
            }

            // 2.18
            if (!validatorClass.EmptyRadioButton(this, bl.ob18, bl.ob18a, getString(R.string.ob18))) {
                return false;
            }

            // 2.18x
            if (!validatorClass.EmptyRadioButton(this, bl.ob18, bl.ob1888, bl.ob1888x, getString(R.string.ob18) + " - " + getString(R.string.other))) {
                return false;
            }

            // 2.19
            if (!validatorClass.EmptyRadioButton(this, bl.ob19, bl.ob19a, getString(R.string.ob19))) {
                return false;
            }

            // 2.20
            if (!validatorClass.EmptyRadioButton(this, bl.ob20, bl.ob20a, getString(R.string.ob20))) {
                return false;
            }

            // 2.20x
            if (!validatorClass.EmptyRadioButton(this, bl.ob20, bl.ob2088, bl.ob2088x, getString(R.string.ob20) + " - " + getString(R.string.other))) {
                return false;
            }

            // 2.21
            if (!validatorClass.EmptyRadioButton(this, bl.ob21, bl.ob21a, getString(R.string.ob21))) {
                return false;
            }

            // 2.22
            if (!bl.ob2244.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.ob22a, getString(R.string.ob21))) {
                    return false;
                }

            }

            // 2.23
            if (!validatorClass.EmptyRadioButton(this, bl.ob23, bl.ob23a, getString(R.string.ob23))) {
                return false;
            }


            if (!bl.ob23a.isChecked()) {
                // 2.24
                if (!validatorClass.EmptyRadioButton(this, bl.ob24, bl.ob24a, getString(R.string.ob24))) {
                    return false;
                }

                // 2.24x
                if (!validatorClass.EmptyRadioButton(this, bl.ob24, bl.ob2488, bl.ob2488x, getString(R.string.ob24) + " - " + getString(R.string.other))) {
                    return false;
                }

            }

            // 2.25
            if (!validatorClass.EmptyRadioButton(this, bl.ob25, bl.ob25a, getString(R.string.ob25))) {
                return false;
            }

            // 2.26
            if (!validatorClass.EmptyRadioButton(this, bl.ob26, bl.ob26a, getString(R.string.ob26))) {
                return false;
            }

            // 2.27
            if (!validatorClass.EmptyRadioButton(this, bl.ob27, bl.ob27a, getString(R.string.ob27))) {
                return false;
            }

            // 2.28
            if (!validatorClass.EmptyRadioButton(this, bl.ob28, bl.ob28a, getString(R.string.ob28))) {
                return false;
            }

            // 2.29
            if (!validatorClass.EmptyRadioButton(this, bl.ob29, bl.ob29a, getString(R.string.ob29))) {
                return false;
            }

            // 2.29x
            if (!validatorClass.EmptyRadioButton(this, bl.ob29, bl.ob2988, bl.ob2988x, getString(R.string.ob24) + " - " + getString(R.string.other))) {
                return false;
            }

            // 2.30
            if (!validatorClass.EmptyRadioButton(this, bl.ob30, bl.ob30a, getString(R.string.ob30))) {
                return false;
            }

            // 2.31
            if (!validatorClass.EmptyRadioButton(this, bl.ob31, bl.ob31a, getString(R.string.ob31))) {
                return false;
            }

        }


        if (!bl.ob3255.isChecked() && !bl.ob3299.isChecked() && !bl.ob3277.isChecked()) {
            // 2.32
            if (!validatorClass.EmptyTextBox(this, bl.ob32a, getString(R.string.ob31))) {
                return false;
            }

            // 2.32
            if (!validatorClass.RangeTextBox(this, bl.ob32a, 1.0, 7.0, getString(R.string.ob32), " kg")) {
                return false;
            }
        }

        // 2.33
        return validatorClass.EmptyRadioButton(this, bl.ob33, bl.ob33a, getString(R.string.ob33));
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

        sOb.put("ob01", bl.ob01a.isChecked() ? "1"
                : bl.ob01b.isChecked() ? "2"
                : bl.ob01c.isChecked() ? "3"
                : bl.ob01d.isChecked() ? "4"
                : bl.ob01e.isChecked() ? "5"
                : bl.ob01f.isChecked() ? "6" : "0");

        sOb.put("ob02", bl.ob02a.isChecked() ? "1"
                : bl.ob02b.isChecked() ? "2"
                : bl.ob0277.isChecked() ? "77" : "0");

        sOb.put("ob03", bl.ob03a.isChecked() ? "1"
                : bl.ob03b.isChecked() ? "2"
                : bl.ob0399.isChecked() ? "99" : "0");

        sOb.put("ob04", bl.ob04a.isChecked() ? "1"
                : bl.ob04b.isChecked() ? "2"
                : bl.ob0499.isChecked() ? "99" : "0");

        sOb.put("ob05", bl.ob05a.isChecked() ? "1"
                : bl.ob05b.isChecked() ? "2"
                : bl.ob05c.isChecked() ? "3"
                : bl.ob05d.isChecked() ? "4"
                : bl.ob0588.isChecked() ? "88" : "0");
        sOb.put("ob0588x", bl.ob0588x.getText().toString());

        sOb.put("ob06a", bl.ob06a.isChecked() ? "1" : "0");
        sOb.put("ob06b", bl.ob06b.isChecked() ? "2" : "0");
        sOb.put("ob06c", bl.ob06c.isChecked() ? "3" : "0");
        sOb.put("ob06d", bl.ob06d.isChecked() ? "4" : "0");
        sOb.put("ob06e", bl.ob06e.isChecked() ? "5" : "0");
        sOb.put("ob06f", bl.ob06f.isChecked() ? "6" : "0");
        sOb.put("ob0688", bl.ob0688.isChecked() ? "88" : "0");
        sOb.put("ob0699", bl.ob0699.isChecked() ? "99" : "0");

        sOb.put("ob0688x", bl.ob0688x.getText().toString());

        sOb.put("ob07", bl.ob07a.isChecked() ? "1"
                : bl.ob07b.isChecked() ? "2"
                : bl.ob07c.isChecked() ? "3"
                : bl.ob07d.isChecked() ? "4"
                : bl.ob07e.isChecked() ? "5"
                : bl.ob0788.isChecked() ? "88" : "0");
        sOb.put("ob0788x", bl.ob0788x.getText().toString());

        sOb.put("ob08", bl.ob08a.isChecked() ? "1"
                : bl.ob08b.isChecked() ? "2"
                : bl.ob08c.isChecked() ? "3" : "0");

        sOb.put("ob09", bl.ob09a.isChecked() ? "1"
                : bl.ob09b.isChecked() ? "2"
                : bl.ob09c.isChecked() ? "3"
                : bl.ob09d.isChecked() ? "4"
                : bl.ob0977.isChecked() ? "77"
                : bl.ob0988.isChecked() ? "88" : "0");
        sOb.put("ob0988x", bl.ob0988x.getText().toString());

        sOb.put("ob10a", bl.ob10a.isChecked() ? "1" : "0");
        sOb.put("ob10b", bl.ob10b.isChecked() ? "2" : "0");
        sOb.put("ob10c", bl.ob10c.isChecked() ? "3" : "0");
        sOb.put("ob10d", bl.ob10d.isChecked() ? "4" : "0");
        sOb.put("ob10e", bl.ob10e.isChecked() ? "5" : "0");
        sOb.put("ob10f", bl.ob10f.isChecked() ? "6" : "0");
        sOb.put("ob10g", bl.ob10g.isChecked() ? "7" : "0");
        sOb.put("ob1077", bl.ob1077.isChecked() ? "77" : "0");

        sOb.put("ob11", bl.ob11a.isChecked() ? "1"
                : bl.ob11b.isChecked() ? "2"
                : bl.ob11c.isChecked() ? "3"
                : bl.ob11d.isChecked() ? "4"
                : bl.ob11e.isChecked() ? "5"
                : bl.ob11f.isChecked() ? "6"
                : bl.ob1188.isChecked() ? "88"
                : bl.ob1199.isChecked() ? "99" : "0");
        sOb.put("ob1188x", bl.ob1188x.getText().toString());

        sOb.put("ob12", bl.ob12a.isChecked() ? "1"
                : bl.ob12b.isChecked() ? "2"
                : bl.ob12c.isChecked() ? "3"
                : bl.ob1288.isChecked() ? "88"
                : bl.ob1299.isChecked() ? "99" : "0");
        sOb.put("ob1288x", bl.ob1288x.getText().toString());

        sOb.put("ob13", bl.ob13a.isChecked() ? "1"
                : bl.ob13b.isChecked() ? "2"
                : bl.ob1377.isChecked() ? "77" : "0");

        sOb.put("ob14", bl.ob14a.isChecked() ? "1"
                : bl.ob14b.isChecked() ? "2"
                : bl.ob1477.isChecked() ? "77" : "0");

        sOb.put("ob15", bl.ob15a.isChecked() ? "1"
                : bl.ob15b.isChecked() ? "2"
                : bl.ob15c.isChecked() ? "3"
                : bl.ob1588.isChecked() ? "88"
                : bl.ob1577.isChecked() ? "77" : "0");
        sOb.put("ob1588x", bl.ob1588x.getText().toString());

        sOb.put("ob16", bl.ob16a.isChecked() ? "1"
                : bl.ob16b.isChecked() ? "2"
                : bl.ob16c.isChecked() ? "3"
                : bl.ob16d.isChecked() ? "4"
                : bl.ob16e.isChecked() ? "5"
                : bl.ob1688.isChecked() ? "88"
                : bl.ob1699.isChecked() ? "99"
                : bl.ob1677.isChecked() ? "77" : "0");
        sOb.put("ob1688x", bl.ob1688x.getText().toString());

        sOb.put("ob17", bl.ob17a.isChecked() ? "1"
                : bl.ob17b.isChecked() ? "2"
                : bl.ob17c.isChecked() ? "3"
                : bl.ob17d.isChecked() ? "4"
                : bl.ob1788.isChecked() ? "88"
                : bl.ob1799.isChecked() ? "99"
                : bl.ob1777.isChecked() ? "77" : "0");
        sOb.put("ob1788x", bl.ob1788x.getText().toString());

        sOb.put("ob18", bl.ob18a.isChecked() ? "1"
                : bl.ob18b.isChecked() ? "2"
                : bl.ob18c.isChecked() ? "3"
                : bl.ob1888.isChecked() ? "88"
                : bl.ob1899.isChecked() ? "99"
                : bl.ob1877.isChecked() ? "77" : "0");
        sOb.put("ob1888x", bl.ob1888x.getText().toString());

        sOb.put("ob19", bl.ob19a.isChecked() ? "1"
                : bl.ob19b.isChecked() ? "2"
                : bl.ob1999.isChecked() ? "99"
                : bl.ob1977.isChecked() ? "77" : "0");

        sOb.put("ob20", bl.ob20a.isChecked() ? "1"
                : bl.ob20b.isChecked() ? "2"
                : bl.ob20c.isChecked() ? "3"
                : bl.ob20d.isChecked() ? "4"
                : bl.ob20e.isChecked() ? "5"
                : bl.ob20f.isChecked() ? "6"
                : bl.ob20g.isChecked() ? "7"
                : bl.ob20h.isChecked() ? "8"
                : bl.ob2088.isChecked() ? "88"
                : bl.ob2099.isChecked() ? "99"
                : bl.ob2077.isChecked() ? "77" : "0");
        sOb.put("ob2088x", bl.ob2088x.getText().toString());

        sOb.put("ob21", bl.ob21a.isChecked() ? "1"
                : bl.ob21b.isChecked() ? "2"
                : bl.ob2177.isChecked() ? "77" : "0");

        sOb.put("ob22", bl.ob2244.isChecked() ? "44" : bl.ob22a.getText().toString());

        sOb.put("ob23", bl.ob23a.isChecked() ? "1"
                : bl.ob23b.isChecked() ? "2"
                : bl.ob2399.isChecked() ? "99"
                : bl.ob2377.isChecked() ? "77" : "0");

        sOb.put("ob24", bl.ob24a.isChecked() ? "1"
                : bl.ob24b.isChecked() ? "2"
                : bl.ob2488.isChecked() ? "88"
                : bl.ob2477.isChecked() ? "77" : "0");
        sOb.put("ob2488x", bl.ob2488x.getText().toString());

        sOb.put("ob25", bl.ob25a.isChecked() ? "1"
                : bl.ob25b.isChecked() ? "2"
                : bl.ob2599.isChecked() ? "99"
                : bl.ob2577.isChecked() ? "77" : "0");

        sOb.put("ob26", bl.ob26a.isChecked() ? "1"
                : bl.ob26b.isChecked() ? "2"
                : bl.ob2677.isChecked() ? "77" : "0");

        sOb.put("ob27", bl.ob27a.isChecked() ? "1"
                : bl.ob27b.isChecked() ? "2"
                : bl.ob27c.isChecked() ? "3"
                : bl.ob27d.isChecked() ? "4"
                : bl.ob27e.isChecked() ? "5"
                : bl.ob27f.isChecked() ? "6"
                : bl.ob27g.isChecked() ? "7"
                : bl.ob2777.isChecked() ? "77" : "0");

        sOb.put("ob28", bl.ob28a.isChecked() ? "1"
                : bl.ob28b.isChecked() ? "2"
                : bl.ob2899.isChecked() ? "99"
                : bl.ob2877.isChecked() ? "77" : "0");

        sOb.put("ob29", bl.ob29a.isChecked() ? "1"
                : bl.ob29b.isChecked() ? "2"
                : bl.ob29c.isChecked() ? "3"
                : bl.ob2988.isChecked() ? "88"
                : bl.ob2977.isChecked() ? "77" : "0");
        sOb.put("ob2988x", bl.ob2988x.getText().toString());

        sOb.put("ob30", bl.ob30a.isChecked() ? "1"
                : bl.ob30b.isChecked() ? "2"
                : bl.ob3099.isChecked() ? "99"
                : bl.ob3077.isChecked() ? "77" : "0");

        sOb.put("ob31", bl.ob31a.isChecked() ? "1"
                : bl.ob31b.isChecked() ? "2"
                : bl.ob3199.isChecked() ? "99" : "0");

        sOb.put("ob32", bl.ob3255.isChecked() ? "55"
                : bl.ob3277.isChecked() ? "77"
                : bl.ob3299.isChecked() ? "99"
                : bl.ob32a.getText().toString());


        sOb.put("ob33", bl.ob33a.isChecked() ? "1"
                : bl.ob33b.isChecked() ? "2"
                : bl.ob33c.isChecked() ? "3" : "0");


        MainApp.fc.setsB(String.valueOf(sOb));


        //sRc.put()


        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
//Long rowId;
        DatabaseHelper db = new DatabaseHelper(this);


        Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

        // 2. UPDATE FORM ROWID
        int updcount = db.updateSB();

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