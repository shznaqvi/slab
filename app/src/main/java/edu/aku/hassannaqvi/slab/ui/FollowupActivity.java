package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityFollowupBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class FollowupActivity extends AppCompatActivity {

    ActivityFollowupBinding bl;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_followup);
        bl = DataBindingUtil.setContentView(this, R.layout.activity_followup);
        db = new DatabaseHelper(this);
        bl.setCallback(this);
        setUpViews();

    }

    public void setUpViews() {

        bl.sfu11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.sfu11a.isChecked()) {
                    bl.fldGrpsfu12.setVisibility(View.VISIBLE);
                    bl.fldGrpsfu1202.setVisibility(View.GONE);
                    bl.fldGrpsfu1203.setVisibility(View.GONE);
                    bl.sfu1202.clearCheck();
                    bl.sfu120288x.setText(null);
                    bl.sfu1203.clearCheck();
                    bl.sfu120388x.setText(null);
                } else if (bl.sfu11b.isChecked()) {
                    bl.fldGrpsfu1202.setVisibility(View.VISIBLE);
                    bl.fldGrpsfu12.setVisibility(View.GONE);
                    bl.fldGrpsfu1203.setVisibility(View.GONE);
                    bl.sfu12.clearCheck();
                    bl.sfu1201.clearCheck();
                    bl.sfu120188x.setText(null);
                    bl.sfu1203.clearCheck();
                    bl.sfu120388x.setText(null);
                } else if (bl.sfu11c.isChecked()) {
                    bl.fldGrpsfu1203.setVisibility(View.VISIBLE);
                    bl.fldGrpsfu1202.setVisibility(View.GONE);
                    bl.fldGrpsfu12.setVisibility(View.GONE);
                    bl.sfu1202.clearCheck();
                    bl.sfu120288x.setText(null);
                    bl.sfu12.clearCheck();
                    bl.sfu1201.clearCheck();
                    bl.sfu120188x.setText(null);

                }
            }
        });

        bl.sfu12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.sfu12b.isChecked()) {
                    bl.fldGrpsfu1201.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpsfu1201.setVisibility(View.GONE);
                    bl.sfu1201.clearCheck();
                    bl.sfu120188x.setText(null);
                }
            }
        });

        bl.sfu13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (bl.sfu13a.isChecked()) {
                    bl.fldGrpsfu13.setVisibility(View.VISIBLE);
                } else {
                    bl.fldGrpsfu13.setVisibility(View.GONE);
                    bl.sfu1301.clearCheck();
                    bl.sfu1302.setText(null);
                    bl.sfu1303.clearCheck();
                    bl.sfu1304.setText(null);

                    bl.sfu1305.clearCheck();
                    bl.sfu1306.setText(null);
                    bl.sfu1307.clearCheck();
                    bl.sfu1308.setText(null);
                    bl.sfu1309.clearCheck();
                    bl.sfu1310.setText(null);
                    bl.sfu1311.clearCheck();
                    bl.sfu1312.setText(null);
                    bl.sfu1313.clearCheck();
                    bl.sfu1314.setText(null);
                    bl.sfu1315.clearCheck();
                    bl.sfu1316.setText(null);
                    bl.sfu1317.clearCheck();
                    bl.sfu1318.setText(null);
                    bl.sfu1319.clearCheck();
                    bl.sfu1320.setText(null);
                    bl.sfu1321.clearCheck();
                    bl.sfu1322.setText(null);
                    bl.sfu1323.setText(null);
                }
            }
        });


    }


    public Boolean formValidation() {

        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyRadioButton(this, bl.sfu01, bl.sfu01a, getString(R.string.sfu01))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bl.sfu05, getString(R.string.sfu05))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bl.sfu11, bl.sfu11a, getString(R.string.sfu11))) {
            return false;
        }

        if (bl.sfu11a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bl.sfu12, bl.sfu12a, getString(R.string.sfu12))) {
                return false;
            }

            if (bl.sfu12b.isChecked()) {
                if (!validatorClass.EmptyRadioButton(this, bl.sfu1201, bl.sfu1201a, getString(R.string.sfu1201))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bl.sfu1201, bl.sfu120188, bl.sfu120188x, getString(R.string.sfu1201) + " - " + getString(R.string.others))) {
                    return false;
                }

            }
        } else if (bl.sfu11b.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bl.sfu1202, bl.sfu1202a, getString(R.string.sfu1202))) {
                return false;
            }

            if (!validatorClass.EmptyRadioButton(this, bl.sfu1202, bl.sfu120288, bl.sfu120288x, getString(R.string.sfu1202) + " - " + getString(R.string.others))) {
                return false;
            }
        } else if (bl.sfu11c.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bl.sfu1203, bl.sfu1203a, getString(R.string.sfu1203))) {
                return false;
            }
            if (!validatorClass.EmptyRadioButton(this, bl.sfu1203, bl.sfu120388, bl.sfu120388x, getString(R.string.sfu1203) + " - " + getString(R.string.others))) {
                return false;
            }
        }


        if (!validatorClass.EmptyRadioButton(this, bl.sfu13, bl.sfu13a, getString(R.string.sfu13))) {
            return false;
        }

        if (bl.sfu13a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bl.sfu1301, bl.sfu1301a, getString(R.string.sfu1301))) {
                return false;
            }

            if (bl.sfu1301a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1302, getString(R.string.sfu1302))) {
                    return false;
                }
            }

            if (!validatorClass.EmptyRadioButton(this, bl.sfu1303, bl.sfu1303a, getString(R.string.sfu1303))) {
                return false;
            }

            if (bl.sfu1303a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1304, getString(R.string.sfu1304))) {
                    return false;
                }
            }

            if (!validatorClass.EmptyRadioButton(this, bl.sfu1305, bl.sfu1305a, getString(R.string.sfu1305))) {
                return false;
            }

            if (bl.sfu1305a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1306, getString(R.string.sfu1306))) {
                    return false;
                }
            }

            if (!validatorClass.EmptyRadioButton(this, bl.sfu1307, bl.sfu1307a, getString(R.string.sfu1307))) {
                return false;
            }

            if (bl.sfu1307a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1308, getString(R.string.sfu1308))) {
                    return false;
                }
            }


            if (!validatorClass.EmptyRadioButton(this, bl.sfu1309, bl.sfu1309a, getString(R.string.sfu1309))) {
                return false;
            }

            if (bl.sfu1309a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1310, getString(R.string.sfu1310))) {
                    return false;
                }
            }

            if (!validatorClass.EmptyRadioButton(this, bl.sfu1311, bl.sfu1311a, getString(R.string.sfu1311))) {
                return false;
            }

            if (bl.sfu1311a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1312, getString(R.string.sfu1312))) {
                    return false;
                }
            }

            if (!validatorClass.EmptyRadioButton(this, bl.sfu1313, bl.sfu1313a, getString(R.string.sfu1313))) {
                return false;
            }

            if (bl.sfu1313a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1314, getString(R.string.sfu1314))) {
                    return false;
                }
            }

            if (!validatorClass.EmptyRadioButton(this, bl.sfu1315, bl.sfu1315a, getString(R.string.sfu1315))) {
                return false;
            }

            if (bl.sfu1315a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1316, getString(R.string.sfu1316))) {
                    return false;
                }
            }

            if (!validatorClass.EmptyRadioButton(this, bl.sfu1317, bl.sfu1317a, getString(R.string.sfu1317))) {
                return false;
            }

            if (bl.sfu1317a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1318, getString(R.string.sfu1318))) {
                    return false;
                }
            }

            if (!validatorClass.EmptyRadioButton(this, bl.sfu1319, bl.sfu1319a, getString(R.string.sfu1319))) {
                return false;
            }

            if (bl.sfu1319a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1320, getString(R.string.sfu1320))) {
                    return false;
                }
            }

            if (!validatorClass.EmptyRadioButton(this, bl.sfu1321, bl.sfu1321a, getString(R.string.sfu1321))) {
                return false;
            }

            if (bl.sfu1321a.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, bl.sfu1322, getString(R.string.sfu1322))) {
                    return false;
                }
            }

            if (!validatorClass.EmptyTextBox(this, bl.sfu1323, getString(R.string.sfu1323))) {
                return false;
            }


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
            //if (UpdateDB()) {
            Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();
            finish();
            if (MainApp.fupLocation == 2) {
                startActivity(new Intent(this, AnthropometryActivity.class));
            } else {
                startActivity(new Intent(this, LabInvestigationsActivity.class));
            }

        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
        //}
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

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();

        MainApp.fc.setDevicetagID(sharedPref.getString("tagName", null));
        MainApp.fc.setFormDate(new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);

        JSONObject sa = new JSONObject();

        sa.put("sfu01", bl.sfu01a.isChecked() ? "1"
                : bl.sfu01b.isChecked() ? "2"
                : bl.sfu01c.isChecked() ? "3" : "0");
        sa.put("sfu02", new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
        sa.put("sfu05", bl.sfu05.getText().toString());
        sa.put("sfu11", bl.sfu11a.isChecked() ? "1"
                : bl.sfu11b.isChecked() ? "2"
                : bl.sfu11c.isChecked() ? "3" : "0");
        sa.put("sfu12", bl.sfu12a.isChecked() ? "1"
                : bl.sfu12b.isChecked() ? "2" : "0");
        sa.put("sfu1201", bl.sfu1201a.isChecked() ? "1"
                : bl.sfu1201b.isChecked() ? "2"
                : bl.sfu1201c.isChecked() ? "3"
                : bl.sfu120188.isChecked() ? "88" : "0");
        sa.put("sfu120188x", bl.sfu120188x.getText().toString());

        sa.put("sfu1202", bl.sfu1202a.isChecked() ? "1"
                : bl.sfu1202b.isChecked() ? "2"
                : bl.sfu1202c.isChecked() ? "3"
                : bl.sfu120288.isChecked() ? "88" : "0");
        sa.put("sfu120288x", bl.sfu120288x.getText().toString());

        sa.put("sfu1203", bl.sfu1203a.isChecked() ? "1"
                : bl.sfu1203b.isChecked() ? "2"
                : bl.sfu1203c.isChecked() ? "3"
                : bl.sfu120388.isChecked() ? "88" : "0");
        sa.put("sfu120388x", bl.sfu120388x.getText().toString());

        sa.put("sfu12", bl.sfu13a.isChecked() ? "1"
                : bl.sfu13b.isChecked() ? "2" : "0");
        sa.put("sfu1301", bl.sfu1301a.isChecked() ? "1"
                : bl.sfu1301b.isChecked() ? "2" : "0");
        sa.put("sfu1302", bl.sfu1302.getText().toString());

        sa.put("sfu1303", bl.sfu1303a.isChecked() ? "1"
                : bl.sfu1303b.isChecked() ? "2" : "0");
        sa.put("sfu1304", bl.sfu1304.getText().toString());

        sa.put("sfu1305", bl.sfu1305a.isChecked() ? "1"
                : bl.sfu1305b.isChecked() ? "2" : "0");
        sa.put("sfu1306", bl.sfu1306.getText().toString());

        sa.put("sfu1307", bl.sfu1307a.isChecked() ? "1"
                : bl.sfu1307b.isChecked() ? "2" : "0");
        sa.put("sfu1308", bl.sfu1308.getText().toString());

        sa.put("sfu1309", bl.sfu1309a.isChecked() ? "1"
                : bl.sfu1309b.isChecked() ? "2" : "0");
        sa.put("sfu1310", bl.sfu1310.getText().toString());

        sa.put("sfu1311", bl.sfu1311a.isChecked() ? "1"
                : bl.sfu1311b.isChecked() ? "2" : "0");
        sa.put("sfu1312", bl.sfu1312.getText().toString());

        sa.put("sfu1313", bl.sfu1313a.isChecked() ? "1"
                : bl.sfu1313b.isChecked() ? "2" : "0");
        sa.put("sfu1314", bl.sfu1314.getText().toString());

        sa.put("sfu1315", bl.sfu1315a.isChecked() ? "1"
                : bl.sfu1315b.isChecked() ? "2" : "0");
        sa.put("sfu1316", bl.sfu1316.getText().toString());


        sa.put("sfu1317", bl.sfu1317a.isChecked() ? "1"
                : bl.sfu1317b.isChecked() ? "2" : "0");
        sa.put("sfu1318", bl.sfu1318.getText().toString());

        sa.put("sfu1319", bl.sfu1319a.isChecked() ? "1"
                : bl.sfu1319b.isChecked() ? "2" : "0");
        sa.put("sfu1320", bl.sfu1320.getText().toString());

        sa.put("sfu1321", bl.sfu1321a.isChecked() ? "1"
                : bl.sfu1321b.isChecked() ? "2" : "0");
        sa.put("sfu1322", bl.sfu1322.getText().toString());

        sa.put("sfu1315", bl.sfu1315a.isChecked() ? "1"
                : bl.sfu1315b.isChecked() ? "2" : "0");
        sa.put("sfu1316", bl.sfu1316.getText().toString());

        sa.put("sfu1317", bl.sfu1317a.isChecked() ? "1"
                : bl.sfu1317b.isChecked() ? "2" : "0");
        sa.put("sfu1318", bl.sfu1318.getText().toString());

        sa.put("sfu1319", bl.sfu1319a.isChecked() ? "1"
                : bl.sfu1319b.isChecked() ? "2" : "0");
        sa.put("sfu1320", bl.sfu1320.getText().toString());

        sa.put("sfu1321", bl.sfu1321a.isChecked() ? "1"
                : bl.sfu1321b.isChecked() ? "2" : "0");
        sa.put("sfu1322", bl.sfu1322.getText().toString());

        sa.put("sfu1323", bl.sfu1323.getText().toString());

        MainApp.fupLocation = bl.sfu01.indexOfChild(findViewById(bl.sfu01.getCheckedRadioButtonId())) + 1;
        MainApp.fc.setsA(String.valueOf(sa));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {

        Long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fc.setUID(
                    (MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFormID();

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
