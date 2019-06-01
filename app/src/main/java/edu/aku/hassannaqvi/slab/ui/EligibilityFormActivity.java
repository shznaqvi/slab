package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityEligibilityFormBinding;
import edu.aku.hassannaqvi.slab.other.DateUtils;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class EligibilityFormActivity extends AppCompatActivity {
    ActivityEligibilityFormBinding bi;
    DatabaseHelper db;
    String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
    String dtToday = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime());
    String tToday = new SimpleDateFormat("HH:mm").format(new Date().getTime());
    int length = 0;
    private static final String TAG = EligibilityFormActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_eligibility_form);
        db = new DatabaseHelper(this);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.selheading));
        setupView();
        ScrollView el_scrollview = findViewById(R.id.el_scrollview);
//        validatorClass.setScrollViewFocus(bi.el_scrollview);
        validatorClass.setScrollViewFocus(el_scrollview);


    }

    private void setupView() {
        bi.sel06d.setManager(getSupportFragmentManager());
        bi.sel06d.setMaxDate(dateToday);
        bi.sel06d.setMinDate(DateUtils.getThreeDaysBack("dd/MM/yyyy", -3));
        bi.sel06t.setManager(getSupportFragmentManager());
        bi.sel06t.setTimeFormat("HH:mm");
        bi.sel06t.setIs24HourView(true);
        bi.sel01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                bi.sel01.setInputType(InputType.TYPE_CLASS_NUMBER);
                length = charSequence.toString().length();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!bi.sel01.getText().toString().isEmpty() && bi.sel01.getText().toString().length() == 3) {
                    if (bi.sel01.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 4) {
                            bi.sel01.setText(bi.sel01.getText().toString() + "-");
                            bi.sel01.setSelection(bi.sel01.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }
                if (!bi.sel01.getText().toString().isEmpty() && bi.sel01.getText().toString().length() == 6) {
                    if (bi.sel01.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 7) {
                            bi.sel01.setText(bi.sel01.getText().toString() + "-");
                            bi.sel01.setSelection(bi.sel01.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        bi.sel09w.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*if (!bi.sel09w.getText().toString().isEmpty() && !bi.sel09d.getText().toString().isEmpty()) {
                    if ((Integer.valueOf(bi.sel09w.getText().toString()) >= 28 && Integer.valueOf(bi.sel09d.getText().toString()) <= 36) && (Integer.valueOf(bi.sel09d.getText().toString()) >= 0 && Integer.valueOf(bi.sel09d.getText().toString()) <= 6)) {
                        bi.sel10a.setChecked(true);
                    } else {
                        bi.sel10b.setChecked(true);

                    }
                } else {
                    bi.sel10.clearCheck();
                }*/

                if (bi.sel09w.getText().toString().isEmpty()) {
                    bi.sel09d.setText(null);
                    bi.sel09d.setEnabled(false);
                    bi.sel10.clearCheck();
                    return;
                }

                bi.sel09d.setEnabled(true);

                if ((Integer.valueOf(bi.sel09w.getText().toString()) >= 28 && Integer.valueOf(bi.sel09w.getText().toString()) <= 36))
                    bi.sel10a.setChecked(true);
                else
                    bi.sel10b.setChecked(true);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bi.sel09d.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*if (!bi.sel09w.getText().toString().isEmpty() && !bi.sel09d.getText().toString().isEmpty()) {
                    if ((Integer.valueOf(bi.sel09w.getText().toString()) >= 28 && Integer.valueOf(bi.sel09d.getText().toString()) <= 36) && (Integer.valueOf(bi.sel09d.getText().toString()) >= 0 && Integer.valueOf(bi.sel09d.getText().toString()) <= 6)) {
                        bi.sel10a.setChecked(true);
                    } else {
                        bi.sel10b.setChecked(true);
                    }
                } else {
                    bi.sel10.clearCheck();
                }*/

                if (bi.sel09d.getText().toString().isEmpty()) return;

                if ((Integer.valueOf(bi.sel09w.getText().toString()) >= 28 && Integer.valueOf(bi.sel09w.getText().toString()) <= 36) && (Integer.valueOf(bi.sel09d.getText().toString()) >= 0 && Integer.valueOf(bi.sel09d.getText().toString()) <= 6)) {
                    bi.sel10a.setChecked(true);
                } else {
                    bi.sel10b.setChecked(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bi.sel11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!bi.sel11.getText().toString().isEmpty()) {
                    if (Integer.valueOf(bi.sel11.getText().toString()) >= 1000 && Integer.valueOf(bi.sel11.getText().toString()) <= 2500) {
                        bi.sel12a.setChecked(true);
                    } else {
                        bi.sel12b.setChecked(true);
                    }
                } else {
                    bi.sel12.clearCheck();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        bi.sel06t.setManager(getSupportFragmentManager());
        bi.sel10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sel10a && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel18b.isChecked() && bi.sel19a.isChecked() && bi.sel21b.isChecked() && bi.sel15b.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {

                    if (bi.sel20a.isChecked()) {
                        bi.sel23a.setChecked(true);
                    } else {
                        bi.sel23b.setChecked(true);
                    }

                } else if (i == R.id.sel10a && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel18b.isChecked() && bi.sel19b.isChecked() && bi.sel21b.isChecked() && bi.sel15b.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    bi.sel23a.setChecked(true);
                } else {
                    bi.sel23b.setChecked(true);
                }
            }
        });
        bi.sel12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sel12a && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel18b.isChecked() && bi.sel19a.isChecked() && bi.sel21b.isChecked() && bi.sel15b.isChecked() && bi.sel10a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {

                    if (bi.sel20a.isChecked()) {
                        bi.sel23a.setChecked(true);
                    } else {
                        bi.sel23b.setChecked(true);
                    }

                } else if (i == R.id.sel12a && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel18b.isChecked() && bi.sel19b.isChecked() && bi.sel21b.isChecked() && bi.sel15b.isChecked() && bi.sel10a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    bi.sel23a.setChecked(true);
                } else {
                    bi.sel23b.setChecked(true);
                }
            }
        });
        bi.sel15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sel15b && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel18b.isChecked() && bi.sel19a.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {

                    if (bi.sel20a.isChecked()) {
                        bi.sel23a.setChecked(true);
                    } else {
                        bi.sel23b.setChecked(true);
                    }

                } else if (i == R.id.sel15b && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel18b.isChecked() && bi.sel19b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    bi.sel23a.setChecked(true);
                } else {
                    bi.sel23b.setChecked(true);
                }
            }
        });
        bi.sel16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sel16a && bi.sel15b.isChecked() && bi.sel17b.isChecked() && bi.sel19a.isChecked() && bi.sel18b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    if (bi.sel20a.isChecked()) {
                        bi.sel23a.setChecked(true);
                    } else {
                        bi.sel23b.setChecked(true);
                    }

                } else if (i == R.id.sel16a && bi.sel15b.isChecked() && bi.sel17b.isChecked() && bi.sel19b.isChecked() && bi.sel18b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    bi.sel23a.setChecked(true);

                } else {
                    bi.sel23b.setChecked(true);
                }
            }
        });
        bi.sel17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sel17b && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel19a.isChecked() && bi.sel18b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    if (bi.sel20a.isChecked()) {
                        bi.sel23a.setChecked(true);
                    } else {
                        bi.sel23b.setChecked(true);
                    }
                } else if (i == R.id.sel17b && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel19b.isChecked() && bi.sel18b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    bi.sel23a.setChecked(true);

                } else {
                    bi.sel23b.setChecked(true);
                }
            }
        });
        bi.sel18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sel18b && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel19b.isChecked() && bi.sel17b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    bi.sel23a.setChecked(true);
                } else if (i == R.id.sel18b && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel19a.isChecked() && bi.sel17b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    if (bi.sel20a.isChecked()) {
                        bi.sel23a.setChecked(true);
                    } else {
                        bi.sel23b.setChecked(true);
                    }
                } else {
                    bi.sel23b.setChecked(true);

                }
            }
        });
        bi.sel19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sel19a) {
                    bi.fldGrpsel19.setVisibility(VISIBLE);

                } else {
                    bi.fldGrpsel19.setVisibility(GONE);
                    bi.sel20.clearCheck();
                }
                if (i == R.id.sel19a && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel18b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    if (bi.sel20a.isChecked()) {
                        bi.sel23a.setChecked(true);
                    } else {
                        bi.sel23b.setChecked(true);
                    }
                } else if (i == R.id.sel19b && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel18b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    bi.sel23a.setChecked(true);
                } else {
                    bi.sel23b.setChecked(true);
                }
            }
        });
        bi.sel20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sel20a && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel18b.isChecked() && bi.sel19a.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    bi.sel23a.setChecked(true);
                } else {
                    bi.sel23b.setChecked(true);
                }
            }
        });
        bi.sel21.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sel21b && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel19a.isChecked() && bi.sel18b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked() && (bi.sel22b.isChecked() || bi.sel22c.isChecked())) {
                    if (bi.sel20a.isChecked()) {
                        bi.sel23a.setChecked(true);
                    } else {
                        bi.sel23b.setChecked(true);
                    }
                } else if (i == R.id.sel21b && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel19b.isChecked() && bi.sel18b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked()) {
                    bi.sel23a.setChecked(true);

                } else {
                    bi.sel23b.setChecked(true);
                }
            }
        });
        bi.sel22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if ((i == R.id.sel22b || i == R.id.sel22c) && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel19a.isChecked() && bi.sel18b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked()) {
                    if (bi.sel20a.isChecked()) {
                        bi.sel23a.setChecked(true);
                    } else {
                        bi.sel23b.setChecked(true);
                    }
                } else if ((i == R.id.sel22b || i == R.id.sel22c) && bi.sel15b.isChecked() && bi.sel16a.isChecked() && bi.sel17b.isChecked() && bi.sel19b.isChecked() && bi.sel18b.isChecked() && bi.sel21b.isChecked() && bi.sel10a.isChecked() && bi.sel12a.isChecked()) {
                    bi.sel23a.setChecked(true);
                } else {
                    bi.sel23b.setChecked(true);
                }
            }
        });


    }

    private boolean formValidation() {
        String regex = "^\\d*\\.\\d+|\\d+\\.\\d*$";
        if (!validatorClass.EmptyTextBox(this, bi.sel01, getString(R.string.mrno))) {
//            bi.sel01err.setVisibility(View.VISIBLE);
            return false;
        }
        if (bi.sel01.getText().toString().length() == 9) {
            String[] str = bi.sel01.getText().toString().split("-");
            if (str.length > 3 || bi.sel01.getText().toString().charAt(3) != '-' || bi.sel01.getText().toString().charAt(6) != '-') {
                bi.sel01.setError("Wrong presentation!!");
                return false;
            }
        } else {
            Toast.makeText(this, "Invalid length: " + getString(R.string.mrno), Toast.LENGTH_SHORT).show();
            bi.sel01.setError("Invalid length");
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel02, getString(R.string.childname))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel03, getString(R.string.sel03))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel04, getString(R.string.sel04))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel06d, getString(R.string.sel06) + " - " + getString(R.string.date))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel06t, getString(R.string.sel06) + " - " + getString(R.string.time))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sel08, bi.sel08b, getString(R.string.sel08))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel07, bi.sel0796, bi.sel0796x, getString(R.string.sel07))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.sel09w, getString(R.string.sel09) + " - " + getString(R.string.weeks))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, bi.sel09w, 28, 42, getString(R.string.sel09) + " - " + getString(R.string.weeks), " weeks")) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(this, bi.sel09d, getString(R.string.sel09) + " - " + getString(R.string.days))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, bi.sel09d, 0, 6, getString(R.string.sel09) + " - " + getString(R.string.days), " days")) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel10, bi.sel10a, getString(R.string.sel10))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel11, getString(R.string.sel11))) {
            return false;
        }

        if (!validatorClass.RangeTextBox(this, bi.sel11, 500, 4000, getString(R.string.sel11), " Weight")) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel12, bi.sel12a, getString(R.string.sel12))) {
            return false;
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel13, getString(R.string.sel13))) {
            return false;
        }
        if (!bi.sel13.getText().toString().contains(".")) {
            Toast.makeText(EligibilityFormActivity.this, "Length of neonate should be in decimal", Toast.LENGTH_SHORT).show();
            bi.sel13.setError("Length of neonate should be in decimal");
            return false;
        } else {
            bi.sel13.setError(null);
        }

        if (!bi.sel13.getText().toString().matches(regex)) {
            Toast.makeText(EligibilityFormActivity.this, "Invalid decimal number", Toast.LENGTH_SHORT).show();
            bi.sel13.setError("Invalid decimal number");
            return false;
        } else {
            bi.sel13.setError(null);
        }
        if (!validatorClass.EmptyTextBox(this, bi.sel14, getString(R.string.sel14))) {
            return false;
        }
        /*if (!validatorClass.RangeTextBox(this, bi.sel14,1.0,4.0, getString(R.string.sel14)," cm")) {
            return false;
        }*/
        if (!bi.sel14.getText().toString().contains(".")) {
            Toast.makeText(EligibilityFormActivity.this, "Head of Circumference should be decimal", Toast.LENGTH_SHORT);
            bi.sel14.setError("Head of Circumference should be decimal");
            return false;
        } else {
            bi.sel14.setError(null);
        }
        if (!bi.sel14.getText().toString().matches(regex)) {
            Toast.makeText(EligibilityFormActivity.this, "Invalid decimal number", Toast.LENGTH_SHORT);
            bi.sel14.setError("Invalid decimal number");
            return false;
        } else {
            bi.sel14.setError(null);
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sel15, bi.sel15a, getString(R.string.sel15))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel16, bi.sel16a, getString(R.string.sel16))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel17, bi.sel17a, getString(R.string.sel17))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel18, bi.sel18a, getString(R.string.sel18))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel19, bi.sel19a, getString(R.string.sel19))) {
            return false;
        }
        if (bi.sel19a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bi.sel20, bi.sel20a, getString(R.string.sel20))) {
                return false;
            }
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sel21, bi.sel21a, getString(R.string.sel21))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sel22, bi.sel22a, getString(R.string.sel22))) {
            return false;
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sel23, bi.sel23a, getString(R.string.sel23))) {
            return false;
        }

        if (bi.sel23a.isChecked()) {
            return validatorClass.EmptyRadioButton(this, bi.sel25, bi.sel25b, bi.sel25bx, getString(R.string.sel25));
        }

        return true;
    }

    public void BtnContinue() {

        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        Long newrowid = db.addForm(MainApp.fc);

        MainApp.fc.set_ID(String.valueOf(newrowid));

        if (newrowid != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fc.setUID((MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFormID();

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void BtnEnd() {

        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {

        MainApp.fc = new FormsContract();
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setDevicetagID(MainApp.getTagName(this));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setsMrno(bi.sel01.getText().toString());
        MainApp.fc.setFormtype(MainApp.FORMTYPE_EL);

        MainApp.fc.setIsEl(bi.sel23a.isChecked() ? "1"
                : bi.sel23b.isChecked() ? "2"
                : "0");

        setGPS(); //Set GPS

        JSONObject ef = new JSONObject();
        ef.put("sel01", bi.sel01.getText().toString());

        ef.put("sel02", bi.sel02.getText().toString());

        ef.put("sel03", bi.sel03.getText().toString());
        ef.put("sel04", bi.sel04.getText().toString());

        ef.put("sel06d", bi.sel06d.getText().toString());
        ef.put("sel06t", bi.sel06t.getText().toString());
        ef.put("sel07", bi.sel07a.isChecked() ? "1"
                : bi.sel07b.isChecked() ? "2"
                : bi.sel0796.isChecked() ? "96"
                : "0");
        ef.put("sel0796x", bi.sel0796x.getText().toString());

        ef.put("sel08", bi.sel08a.isChecked() ? "1"
                : bi.sel08b.isChecked() ? "2"
                : bi.sel08c.isChecked() ? "3"
                : "0");

        ef.put("sel09w", bi.sel09w.getText().toString());
        ef.put("sel09d", bi.sel09d.getText().toString());

        ef.put("sel10", bi.sel10a.isChecked() ? "1"
                : bi.sel10b.isChecked() ? "2"
                : "0");
        ef.put("sel11", bi.sel11.getText().toString());
        ef.put("sel12", bi.sel12a.isChecked() ? "1"
                : bi.sel12b.isChecked() ? "2"
                : "0");
        ef.put("sel13", bi.sel13.getText().toString());
        ef.put("sel14", bi.sel14.getText().toString());

        ef.put("sel15", bi.sel15a.isChecked() ? "1"
                : bi.sel15b.isChecked() ? "2"
                : "0");
        ef.put("sel16", bi.sel16a.isChecked() ? "1"
                : bi.sel16b.isChecked() ? "2"
                : "0");
        ef.put("sel17", bi.sel17a.isChecked() ? "1"
                : bi.sel17b.isChecked() ? "2"
                : "0");
        ef.put("sel18", bi.sel18a.isChecked() ? "1"
                : bi.sel18b.isChecked() ? "2"
                : "0");
        ef.put("sel19", bi.sel19a.isChecked() ? "1"
                : bi.sel19b.isChecked() ? "2"
                : "0");
        ef.put("sel20", bi.sel20a.isChecked() ? "1"
                : bi.sel20b.isChecked() ? "2"
                : "0");
        ef.put("sel21", bi.sel21a.isChecked() ? "1"
                : bi.sel21b.isChecked() ? "2"
                : "0");
        ef.put("sel22", bi.sel22a.isChecked() ? "1"
                : bi.sel22b.isChecked() ? "2"
                : bi.sel22c.isChecked() ? "3"
                : "0");
        ef.put("sel23", bi.sel23a.isChecked() ? "1"
                : bi.sel23b.isChecked() ? "2"
                : "0");
        ef.put("sel25", bi.sel25a.isChecked() ? "1"
                : bi.sel25b.isChecked() ? "2"
                : "0");
        ef.put("sel25x", bi.sel25bx.getText().toString());

        MainApp.fc.setsEl(String.valueOf(ef));
    }

    public void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            MainApp.fc.setGpsLat(lat);
            MainApp.fc.setGpsLng(lang);
            MainApp.fc.setGpsAcc(acc);
            MainApp.fc.setGpsDT(date); // Timestamp is converted to date above

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }

}
