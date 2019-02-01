package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityFeedingPracticeBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static edu.aku.hassannaqvi.slab.ui.FollowUpFormActivity.FUPLOCATION_TAG;

public class FeedingPracticeActivity extends AppCompatActivity {
    ActivityFeedingPracticeBinding bi;
    DatabaseHelper db;
    String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());
    Boolean nextExamSec;
    String childName, localmrno, localstudyID;
    int fupLocation;
    Boolean skip38;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_feeding_practice);
        db = new DatabaseHelper(this);
        bi.setCallback(this);
        gettingIntents();
        setupView();
        if(MainApp.fupLocation == 1 || MainApp.fupLocation == 3 || MainApp.fupLocation == 4){
            skip38 = true;
        }else {
            skip38 = false;
        }
        validatorClass.setScrollViewFocus(bi.scrollView);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

    private void gettingIntents() {

        Intent intent = getIntent();
        if (intent.hasExtra(FUPLOCATION_TAG) && intent.hasExtra("childName") && intent.hasExtra("mrno") && intent.hasExtra("studyID")) {
            Bundle bundle = intent.getExtras();
            fupLocation = bundle.getInt(FUPLOCATION_TAG);
            childName = bundle.getString("childName");
            localmrno = bundle.getString("mrno");
            localstudyID = bundle.getString("studyID");
        } else {
            // Do something else
            Toast.makeText(this, "Restart your app or contact your support team!", Toast.LENGTH_SHORT);

        }

    }

    private void setupView() {

        bi.sfu338.setManager(getSupportFragmentManager());
        bi.sfu338.setMaxDate(dateToday);
        bi.sfu344bx.setManager(getSupportFragmentManager());
        bi.sfu344bx.setMaxDate(dateToday);
        bi.childname.setText(childName);
//        Completed (6): Change the skipping pattern in feeding practice.
        bi.sfu201.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.sfu201a:
                        bi.fldGrpsfu202.setVisibility(View.VISIBLE);
                        bi.fldGrpsfu204.setVisibility(GONE);
                        bi.sfu204.clearCheck();
                        bi.sfu20496x.setText(null);
                        break;
                    case R.id.sfu201b:
                        bi.fldGrpsfu204.setVisibility(View.VISIBLE);
                        bi.fldGrpsfu202.setVisibility(GONE);
                        bi.sfu202.clearCheck();
                        bi.sfu203.clearCheck();
                        bi.sfu20396x.setText(null);
                        break;
                    case R.id.sfu201c:
                        bi.fldGrpsfu202.setVisibility(View.VISIBLE);
                        bi.fldGrpsfu204.setVisibility(View.VISIBLE);
                        break;
                }
//                showhideTextBoxes();


            }
        });

        bi.sfu202.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu202a) {
                    bi.fldGrpsfu203.setVisibility(GONE);
                    bi.sfu203.clearCheck();
                    bi.sfu20396x.setText(null);
                } else {
                    bi.fldGrpsfu203.setVisibility(View.VISIBLE);
                }
                showhideTextBoxes();

            }
        });


        bi.sfu301.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu301a) {
                    bi.fldgrpsfu301.setVisibility(VISIBLE);

                } else {
                    bi.fldgrpsfu301.setVisibility(GONE);
                    bi.sfu302.setText(null);
                    bi.sfu303m.setText(null);
                    bi.sfu303h.setText(null);
                    bi.sfu303d.setText(null);
                }
                showhideTextBoxes();


            }
        });

        bi.sfu304.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu304a) {
                    bi.fldgrpsfu304.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu304.setVisibility(GONE);
                    bi.sfu305.setText(null);
                    bi.sfu306m.setText(null);
                    bi.sfu306h.setText(null);
                    bi.sfu306d.setText(null);
                }

                showhideTextBoxes();

            }
        });
        bi.sfu307.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu307a) {
                    bi.fldgrpsfu307.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu307.setVisibility(GONE);
                    bi.sfu308.setText(null);
                    bi.sfu309m.setText(null);
                    bi.sfu309h.setText(null);
                    bi.sfu309d.setText(null);
                }
                showhideTextBoxes();

            }
        });
        bi.sfu310.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu310a) {
                    bi.fldgrpsfu310.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu310.setVisibility(GONE);
                    bi.sfu311m.setText(null);
                    bi.sfu311h.setText(null);
                    bi.sfu311d.setText(null);
                }
                showhideTextBoxes();

            }
        });
        bi.sfu312.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu312a) {
                    bi.fldgrpsfu312.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu312.setVisibility(GONE);
                    bi.sfu313.setText(null);
                    bi.sfu314m.setText(null);
                    bi.sfu314h.setText(null);
                    bi.sfu314d.setText(null);
                }
                showhideTextBoxes();

            }
        });
        bi.sfu315.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu315a) {
                    bi.fldgrpsfu315.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu315.setVisibility(GONE);
                    bi.sfu316.setText(null);
                    bi.sfu317.setText(null);

                }
                showhideTextBoxes();

            }
        });

        bi.sfu318.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu318a) {
                    bi.fldgrpsfu318.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu318.setVisibility(GONE);
                    bi.sfu319.setText(null);
                    bi.sfu320.setText(null);
                  }
                showhideTextBoxes();

            }
        });

        bi.sfu321.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu321a) {
                    bi.fldgrpsfu321.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu321.setVisibility(GONE);
                    bi.sfu322d.setText(null);
                    bi.sfu322m.setText(null);
                    bi.sfu322h.setText(null);
                }
                showhideTextBoxes();

            }
        });
        bi.sfu323.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu323a) {
                    bi.fldgrpsfu323.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu323.setVisibility(GONE);
                    bi.sfu324.setText(null);
                    bi.sfu325d.setText(null);
                    bi.sfu325m.setText(null);
                    bi.sfu325h.setText(null);
                }
                showhideTextBoxes();

            }
        });
        bi.sfu326.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu326a) {
                    bi.fldgrpsfu326.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu326.setVisibility(GONE);
                    bi.sfu327.setText(null);

                }
                showhideTextBoxes();

            }
        });
        bi.sfu328.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu328a) {
                    bi.fldgrpsfu328.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu328.setVisibility(GONE);
                    bi.sfu329.clearCheck();
                    bi.sfu330.setText(null);
                }
                showhideTextBoxes();

            }
        });
        bi.sfu331.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu331a) {
                    bi.fldgrpsfu331.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu331.setVisibility(GONE);
                    bi.sfu332d.setText(null);
                    bi.sfu332h.setText(null);
                }
                showhideTextBoxes();

            }
        });
        bi.sfu333.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu333a) {
                    bi.fldgrpsfu333.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu333.setVisibility(GONE);
                    bi.sfu334.setText(null);
                    bi.sfu335.setText(null);
                }
                showhideTextBoxes();

            }
        });
        bi.sfu337.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu337a) {
                    bi.fldgrpsfu337.setVisibility(VISIBLE);
                } else {
                    bi.fldgrpsfu337.setVisibility(GONE);
//                    bi.sfu338.setText(null);
                    bi.sfu339.clearCheck();
                    bi.sfu340.setText(null);
                }
                showhideTextBoxes();

            }
        });
        bi.sfu342.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO implement skip pattern here
                if (checkedId == R.id.sfu342a) {
                    bi.fldGrpsfu342.setVisibility(VISIBLE);
                } else {
                    bi.fldGrpsfu342.setVisibility(GONE);
                    bi.sfu343.setText(null);
                    bi.sfu344.clearCheck();
//                    bi.sfu344bx.setText(null);
                }
                showhideTextBoxes();
            }
        });

       showhideTextBoxes();
        if (fupLocation == 6) {
            nextExamSec = false;
        } else {
            nextExamSec = true;
        }
    }

    private void showhideTextBoxes() {
        switch (fupLocation) {
            case 1:
                bi.sfu303d.setVisibility(GONE);
                bi.sfu306d.setVisibility(GONE);
                bi.sfu309d.setVisibility(GONE);
                bi.sfu311d.setVisibility(GONE);
                bi.sfu314d.setVisibility(GONE);
                bi.sfu322d.setVisibility(GONE);
                bi.sfu325d.setVisibility(GONE);
                bi.sfu303m.setVisibility(VISIBLE);
                bi.sfu306m.setVisibility(VISIBLE);
                bi.sfu309m.setVisibility(VISIBLE);
                bi.sfu311m.setVisibility(VISIBLE);
                bi.sfu314m.setVisibility(VISIBLE);
                bi.sfu322m.setVisibility(VISIBLE);
                bi.sfu325m.setVisibility(VISIBLE);
                bi.fldGrpsfu302.setVisibility(GONE);
                bi.fldGrpsfu305.setVisibility(GONE);
                bi.fldGrpsfu308.setVisibility(GONE);
                bi.fldGrpsfu313.setVisibility(GONE);
                bi.fldGrpsfu316.setVisibility(GONE);
                bi.fldGrpsfu319.setVisibility(GONE);
                bi.fldGrpsfu324.setVisibility(GONE);
                break;
            case 3:
            case 4:
                bi.sfu303d.setVisibility(GONE);
                bi.sfu306d.setVisibility(GONE);
                bi.sfu309d.setVisibility(GONE);
                bi.sfu311d.setVisibility(GONE);
                bi.sfu314d.setVisibility(GONE);
                bi.sfu322d.setVisibility(GONE);
                bi.sfu325d.setVisibility(GONE);
                bi.sfu303m.setVisibility(VISIBLE);
                bi.sfu306m.setVisibility(VISIBLE);
                bi.sfu309m.setVisibility(VISIBLE);
                bi.sfu311m.setVisibility(VISIBLE);
                bi.sfu314m.setVisibility(VISIBLE);
                bi.sfu322m.setVisibility(VISIBLE);
                bi.sfu325m.setVisibility(VISIBLE);
                bi.sfu302.setVisibility(GONE);
                bi.sfu305.setVisibility(GONE);
                bi.sfu308.setVisibility(GONE);
                bi.sfu313.setVisibility(GONE);
                bi.sfu316.setVisibility(GONE);
                bi.sfu319.setVisibility(GONE);
                bi.sfu324.setVisibility(GONE);
                bi.fldGrpsfu302.setVisibility(GONE);
                bi.fldGrpsfu305.setVisibility(GONE);
                bi.fldGrpsfu308.setVisibility(GONE);
                bi.fldGrpsfu313.setVisibility(GONE);
                bi.fldGrpsfu316.setVisibility(GONE);
                bi.fldGrpsfu319.setVisibility(GONE);
                bi.fldGrpsfu324.setVisibility(GONE);
                break;
            case 2:
            case 5:
            case 6:
                bi.sfu303d.setVisibility(VISIBLE);
                bi.sfu306d.setVisibility(VISIBLE);
                bi.sfu309d.setVisibility(VISIBLE);
                bi.sfu311d.setVisibility(VISIBLE);
                bi.sfu314d.setVisibility(VISIBLE);
                bi.sfu322d.setVisibility(VISIBLE);
                bi.sfu325d.setVisibility(VISIBLE);
                bi.sfu303m.setVisibility(GONE);
                bi.sfu306m.setVisibility(GONE);
                bi.sfu309m.setVisibility(GONE);
                bi.sfu311m.setVisibility(GONE);
                bi.sfu314m.setVisibility(GONE);
                bi.sfu322m.setVisibility(GONE);
                bi.sfu325m.setVisibility(GONE);
                bi.fldGrpsfu302.setVisibility(VISIBLE);
                bi.fldGrpsfu305.setVisibility(VISIBLE);
                bi.fldGrpsfu308.setVisibility(VISIBLE);
                bi.fldGrpsfu313.setVisibility(VISIBLE);
                bi.fldGrpsfu316.setVisibility(VISIBLE);
                bi.fldGrpsfu319.setVisibility(VISIBLE);
                bi.fldGrpsfu324.setVisibility(VISIBLE);
                break;

        }

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
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();
                finish();
                if (nextExamSec == false)
                    startActivity(new Intent(this, SupplementAdminActivity.class)
                            .putExtra(FUPLOCATION_TAG, fupLocation)
                            .putExtra("childName", childName)
                            .putExtra("mrno", localmrno)
                            .putExtra("studyID", localstudyID));
                else
                    startActivity(new Intent(this, OnExaminationActivity.class)
                            .putExtra(FUPLOCATION_TAG, fupLocation)
                            .putExtra("childName", childName)
                            .putExtra("mrno", localmrno)
                            .putExtra("studyID", localstudyID));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSFEED();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void BtnEnd() {

        //   Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                // Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                finish();

                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", false));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        MainApp.fc.setsMrno(localmrno);
        MainApp.fc.setsStudyid(localstudyID);
        JSONObject fp = new JSONObject();
        fp.put("sfu201", bi.sfu201a.isChecked() ? "1"
                : bi.sfu201b.isChecked() ? "2"
                : bi.sfu201c.isChecked() ? "3"
                : "0");
        fp.put("sfu202", bi.sfu202a.isChecked() ? "1"
                : bi.sfu202b.isChecked() ? "2"
                : "0");
        fp.put("sfu203", bi.sfu203a.isChecked() ? "1"
                : bi.sfu203b.isChecked() ? "2"
                : bi.sfu203c.isChecked() ? "3"
                : bi.sfu20396.isChecked() ? "96"
                : "0");

        fp.put("sfu20396x", bi.sfu20396x.getText().toString());

        fp.put("sfu204", bi.sfu204a.isChecked() ? "1"
                : bi.sfu204b.isChecked() ? "2"
                : bi.sfu204c.isChecked() ? "3"
                : bi.sfu20496.isChecked() ? "96"
                : "0");
        fp.put("sfu20496x", bi.sfu20496x.getText().toString());

        fp.put("sfu301", bi.sfu301a.isChecked() ? "1"
                : bi.sfu301b.isChecked() ? "2"
                : "0");
        fp.put("sfu302", bi.sfu302.getText().toString());
        fp.put("sfu303m", bi.sfu303m.getText().toString());
        fp.put("sfu303h", bi.sfu303h.getText().toString());
        fp.put("sfu303d", bi.sfu303d.getText().toString());


        fp.put("sfu304", bi.sfu304a.isChecked() ? "1"
                : bi.sfu304b.isChecked() ? "2"
                : "0");
        fp.put("sfu305", bi.sfu305.getText().toString());
        fp.put("sfu306m", bi.sfu306m.getText().toString());
        fp.put("sfu306h", bi.sfu306h.getText().toString());
        fp.put("sfu306d", bi.sfu306d.getText().toString());

        fp.put("sfu307", bi.sfu307a.isChecked() ? "1"
                : bi.sfu307b.isChecked() ? "2"
                : "0");
        fp.put("sfu308", bi.sfu308.getText().toString());
        fp.put("sfu309m", bi.sfu309m.getText().toString());
        fp.put("sfu309h", bi.sfu309h.getText().toString());
        fp.put("sfu309d", bi.sfu309d.getText().toString());

        fp.put("sfu310", bi.sfu310a.isChecked() ? "1"
                : bi.sfu310b.isChecked() ? "2"
                : "0");
        fp.put("sfu311m", bi.sfu311m.getText().toString());
        fp.put("sfu311h", bi.sfu311h.getText().toString());
        fp.put("sfu311d", bi.sfu311d.getText().toString());

        fp.put("sfu312", bi.sfu312a.isChecked() ? "1"
                : bi.sfu312b.isChecked() ? "2"
                : "0");
        fp.put("sfu313", bi.sfu313.getText().toString());
        fp.put("sfu314m", bi.sfu314m.getText().toString());
        fp.put("sfu314h", bi.sfu314h.getText().toString());
        fp.put("sfu314d", bi.sfu314d.getText().toString());

        fp.put("sfu315", bi.sfu315a.isChecked() ? "1"
                : bi.sfu315b.isChecked() ? "2"
                : "0");
        fp.put("sfu316", bi.sfu316.getText().toString());
        fp.put("sfu317", bi.sfu317.getText().toString());
        fp.put("sfu318", bi.sfu318a.isChecked() ? "1"
                : bi.sfu318b.isChecked() ? "2"
                : "0");
        fp.put("sfu319", bi.sfu319.getText().toString());
        fp.put("sfu320", bi.sfu320.getText().toString());


        fp.put("sfu321", bi.sfu321a.isChecked() ? "1"
                : bi.sfu321b.isChecked() ? "2"
                : "0");
        fp.put("sfu322m", bi.sfu322m.getText().toString());
        fp.put("sfu322h", bi.sfu322h.getText().toString());
        fp.put("sfu322d", bi.sfu322d.getText().toString());


        fp.put("sfu323", bi.sfu323a.isChecked() ? "1"
                : bi.sfu323b.isChecked() ? "2"
                : "0");
        fp.put("sfu324", bi.sfu324.getText().toString());
        fp.put("sfu325m", bi.sfu325m.getText().toString());
        fp.put("sfu325h", bi.sfu325h.getText().toString());
        fp.put("sfu325d", bi.sfu325d.getText().toString());


        fp.put("sfu326", bi.sfu326a.isChecked() ? "1"
                : bi.sfu326b.isChecked() ? "2"
                : "0");
        fp.put("sfu327", bi.sfu327.getText().toString());
        fp.put("sfu328", bi.sfu328a.isChecked() ? "1"
                : bi.sfu328b.isChecked() ? "2"
                : "0");
        fp.put("sfu329", bi.sfu329a.isChecked() ? "1"
                : bi.sfu329b.isChecked() ? "2"
                : "0");
        fp.put("sfu330", bi.sfu330.getText().toString());
        fp.put("sfu331", bi.sfu331a.isChecked() ? "1"
                : bi.sfu331b.isChecked() ? "2"
                : "0");
        fp.put("sfu332h", bi.sfu332h.getText().toString());
        fp.put("sfu332d", bi.sfu332d.getText().toString());

        fp.put("sfu333", bi.sfu333a.isChecked() ? "1"
                : bi.sfu333b.isChecked() ? "2"
                : "0");
        fp.put("sfu334", bi.sfu334.getText().toString());
        fp.put("sfu335", bi.sfu335.getText().toString());
        fp.put("sfu336", bi.sfu336.getText().toString());
        fp.put("sfu337", bi.sfu337a.isChecked() ? "1"
                : bi.sfu337b.isChecked() ? "2"
                : "0");
        fp.put("sfu338", bi.sfu338.getText().toString());
        fp.put("sfu339", bi.sfu339a.isChecked() ? "1"
                : bi.sfu33996.isChecked() ? "96"
                : "0");
        fp.put("sfu33996x", bi.sfu33996x.getText().toString());
        fp.put("sfu340", bi.sfu340.getText().toString());
        fp.put("sfu341d", bi.sfu341d.getText().toString());
        fp.put("sfu341hr", bi.sfu341hr.getText().toString());
        fp.put("sfu342", bi.sfu342a.isChecked() ? "1"
                : bi.sfu342b.isChecked() ? "2"
                : "0");
        fp.put("sfu343", bi.sfu343.getText().toString());
        fp.put("sfu344", bi.sfu344a.isChecked() ? "1"
                : bi.sfu344b.isChecked() ? "2"
                : "0");
        fp.put("sfu344bx", bi.sfu344bx.getText().toString());

        MainApp.fc.setsFeed(String.valueOf(fp));
    }

    private boolean formValidation() {
        if (!validatorClass.EmptyRadioButton(this, bi.sfu201, bi.sfu201a, getString(R.string.sfu201))) {
            return false;
        }
        int result = bi.sfu201.getCheckedRadioButtonId();
        switch (result) {
            case R.id.sfu201c:
                if (!validatorClass.EmptyRadioButton(this, bi.sfu204, bi.sfu20496, bi.sfu20496x, getString(R.string.sfu204))) {
                    return false;
                }
            case R.id.sfu201a:
                if (!validatorClass.EmptyRadioButton(this, bi.sfu202, bi.sfu202a, getString(R.string.sfu202))) {
                    return false;
                }
                if (bi.sfu202b.isChecked()) {
                    if (!validatorClass.EmptyRadioButton(this, bi.sfu203, bi.sfu20396, bi.sfu20396x, getString(R.string.sfu203))) {
                        return false;
                    }
                }
                break;
            case R.id.sfu201b:
                if (!validatorClass.EmptyRadioButton(this, bi.sfu204, bi.sfu20496, bi.sfu20496x, getString(R.string.sfu204))) {
                    return false;
                }
                break;


        }

        if (!validatorClass.EmptyRadioButton(this, bi.sfu301, bi.sfu301a, getString(R.string.sfu301))) {
            return false;
        }

        if (bi.sfu301a.isChecked()) {
            if (fupLocation == 2 || fupLocation == 5 || fupLocation == 6) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu302, getString(R.string.sfu302))) {
                    return false;
                }
            }
            if (fupLocation == 1) {

                if (!validatorClass.EmptyTextBox(this, bi.sfu303m, getString(R.string.sfu303) + " in Minutes")) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyTextBox(this, bi.sfu303d, getString(R.string.sfu303) + " in Days")) {
                    return false;
                }
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu303h, getString(R.string.sfu303) + " in Hours")) {
                return false;
            }

        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu304, bi.sfu304a, getString(R.string.sfu304))) {
            return false;
        }

        if (bi.sfu304a.isChecked()) {

            if (fupLocation == 2 || fupLocation == 5 || fupLocation == 6) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu305, getString(R.string.sfu305))) {
                    return false;
                }
            }
            if (fupLocation == 1) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu306m, getString(R.string.sfu306) + " in Minutes")) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyTextBox(this, bi.sfu306d, getString(R.string.sfu306) + " in Days")) {
                    return false;
                }
            }

            if (!validatorClass.EmptyTextBox(this, bi.sfu306h, getString(R.string.sfu306) + " in Hours")) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu307, bi.sfu307a, getString(R.string.sfu307))) {
            return false;
        }

        if (bi.sfu307a.isChecked()) {
            if (fupLocation == 2 || fupLocation == 5 || fupLocation == 6) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu308, getString(R.string.sfu308))) {
                    return false;
                }
            }
            if (fupLocation == 1) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu309m, getString(R.string.sfu309) + " in Minutes")) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyTextBox(this, bi.sfu309d, getString(R.string.sfu309) + " in Days")) {
                    return false;
                }
            }

            if (!validatorClass.EmptyTextBox(this, bi.sfu309h, getString(R.string.sfu309) + " in Hours")) {
                return false;
            }
        }

        if (!validatorClass.EmptyRadioButton(this, bi.sfu310, bi.sfu310a, getString(R.string.sfu310))) {
            return false;
        }
        if (bi.sfu310a.isChecked()) {
            if (fupLocation == 1) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu311m, getString(R.string.sfu311) + " in Minutes")) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyTextBox(this, bi.sfu311d, getString(R.string.sfu311) + " in Days")) {
                    return false;
                }
            }

            if (!validatorClass.EmptyTextBox(this, bi.sfu311h, getString(R.string.sfu311) + " in Hours")) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu312, bi.sfu312a, getString(R.string.sfu312))) {
            return false;
        }

        if (bi.sfu312a.isChecked()) {
            if (fupLocation == 2 || fupLocation == 5 || fupLocation == 6) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu313, getString(R.string.sfu313))) {
                    return false;
                }
            }
            if (fupLocation == 1) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu314m, getString(R.string.sfu314) + " in Minutes")) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyTextBox(this, bi.sfu314d, getString(R.string.sfu314) + " in Days")) {
                    return false;
                }
            }

            if (!validatorClass.EmptyTextBox(this, bi.sfu314h, getString(R.string.sfu314) + " in Hours")) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu315, bi.sfu315a, getString(R.string.sfu315))) {
            return false;
        }

        if (bi.sfu315a.isChecked()) {
            if (fupLocation == 2 || fupLocation == 5 || fupLocation == 6) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu316, getString(R.string.sfu316))) {
                    return false;
                }
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu317, getString(R.string.sfu317) + " in Days")) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu318, bi.sfu318a, getString(R.string.sfu318))) {
            return false;
        }

        if (bi.sfu318a.isChecked()) {
            if (fupLocation == 2 || fupLocation == 5 || fupLocation == 6) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu319, getString(R.string.sfu319))) {
                    return false;
                }
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu320, getString(R.string.sfu320) + " in Days")) {
                return false;
            }
        }


        if (!validatorClass.EmptyRadioButton(this, bi.sfu321, bi.sfu321a, getString(R.string.sfu321))) {
            return false;
        }
        if (bi.sfu321a.isChecked()) {
            if (fupLocation == 1) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu322m, getString(R.string.sfu322) + " in Minutes")) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyTextBox(this, bi.sfu322d, getString(R.string.sfu322) + " in Days")) {
                    return false;
                }
            }

            if (!validatorClass.EmptyTextBox(this, bi.sfu322h, getString(R.string.sfu322) + " in Hours")) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu323, bi.sfu323a, getString(R.string.sfu323))) {
            return false;
        }

        if (bi.sfu323a.isChecked()) {
            if (fupLocation == 2 || fupLocation == 5 || fupLocation == 6) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu324, getString(R.string.sfu324))) {
                    return false;
                }
            }
            if (fupLocation == 1) {
                if (!validatorClass.EmptyTextBox(this, bi.sfu325m, getString(R.string.sfu325) + " in Minutes")) {
                    return false;
                }
            } else {
                if (!validatorClass.EmptyTextBox(this, bi.sfu325d, getString(R.string.sfu325) + " in Days")) {
                    return false;
                }
            }


            if (!validatorClass.EmptyTextBox(this, bi.sfu325h, getString(R.string.sfu325) + " in Hours")) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu326, bi.sfu326a, getString(R.string.sfu326))) {
            return false;
        }
        if (bi.sfu326a.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.sfu327, getString(R.string.sfu327) + " in Numbers")) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu328, bi.sfu328a, getString(R.string.sfu328))) {
            return false;
        }
        if (bi.sfu328a.isChecked()) {
            if (!validatorClass.EmptyRadioButton(this, bi.sfu329, bi.sfu329a, getString(R.string.sfu329))) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu330, getString(R.string.sfu330) + " in Numbers")) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu331, bi.sfu331a, getString(R.string.sfu331))) {
            return false;
        }
        if (bi.sfu331a.isChecked()) {

            if (!validatorClass.EmptyTextBox(this, bi.sfu332h, getString(R.string.sfu332) + " in hours")) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu332d, getString(R.string.sfu332) + " in days")) {
                return false;
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu333, bi.sfu333a, getString(R.string.sfu333))) {
            return false;
        }
        if (bi.sfu333a.isChecked()) {

            if (!validatorClass.EmptyTextBox(this, bi.sfu334, getString(R.string.sfu334) + " in hours")) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(this, bi.sfu335, getString(R.string.sfu335) + " in seconds")) {
                return false;
            }
        }
        if (!validatorClass.EmptyTextBox(this, bi.sfu336, getString(R.string.sfu336))) {
            return false;
        }
        if(skip38){
            if (!validatorClass.EmptyRadioButton(this, bi.sfu337, bi.sfu337a, getString(R.string.sfu337))) {
                return false;
            }
            if (bi.sfu337a.isChecked()) {

                if (!validatorClass.EmptyTextBox(this, bi.sfu338, getString(R.string.sfu338))) {
                    return false;
                }
                if (!validatorClass.EmptyRadioButton(this, bi.sfu339, bi.sfu33996, bi.sfu33996x, getString(R.string.sfu339))) {
                    return false;
                }
                if (!validatorClass.EmptyTextBox(this, bi.sfu340, getString(R.string.sfu340))) {
                    return false;
                }
//            TODO: apply validation for Question 41 check from old app
                /**/
            }
        }
        if (!validatorClass.EmptyRadioButton(this, bi.sfu342, bi.sfu342a, getString(R.string.sfu342))) {
            return false;
        }
        if (bi.sfu342a.isChecked()) {

            if (!validatorClass.EmptyTextBox(this, bi.sfu343, getString(R.string.sfu343))) {
                return false;
            }
            if (!validatorClass.EmptyRadioButton(this, bi.sfu344, bi.sfu344b, bi.sfu344bx, getString(R.string.sfu344))) {
                return false;
            }
        }
        return true;
    }

    boolean validationForInpatientBirthAdmission() {

        return true;
    }

}
