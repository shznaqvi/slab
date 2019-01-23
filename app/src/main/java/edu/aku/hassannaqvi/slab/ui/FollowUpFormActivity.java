package edu.aku.hassannaqvi.slab.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.JsonModelClasses.RecruitmentJSONModel;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.ChildListContract;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityFollowUpFormBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class FollowUpFormActivity extends AppCompatActivity {
    ActivityFollowUpFormBinding bi;
    DatabaseHelper db;
    String dtToday = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime());
    String todaysDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date().getTime());
    static String FUPLOCATION_TAG = "fupLocation";
    FormsContract fc;
    //    FormsContract fc_1;
    ChildListContract childListContract;
    RecruitmentJSONModel recmodel;
    public String localMrno;
    public String localStudyID;
    String childName;
    int noofsachet;
    Context context;
    int length = 0;
    private static final String TAG = FollowUpFormActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_follow_up_form);
        context = FollowUpFormActivity.this;
        db = new DatabaseHelper(context);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.sfu101));
        checkIntents();
        setupView();
        validatorClass.setScrollViewFocus(bi.scrollView);
        fc = new FormsContract();
//        fc_1 = new FormsContract();
        childListContract = new ChildListContract();
        recmodel = new RecruitmentJSONModel();

    }

    private void checkIntents() {
        Intent intent = getIntent();
        if (intent.hasExtra(MainApp.MRNO_TAG) && intent.hasExtra(MainApp.STUDYID_TAG) && intent.hasExtra(MainApp.CHILDNAME_TAG)) {
            Bundle bundle = intent.getExtras();
            localMrno = bundle.getString(MainApp.MRNO_TAG);
            localStudyID = bundle.getString(MainApp.STUDYID_TAG);
            childName = bundle.getString(MainApp.CHILDNAME_TAG);
            bi.sfu001.setText(localMrno);
            bi.sfu002.setText(localStudyID);
            bi.ChildName.setText(childName);

            bi.sfu001.setEnabled(false);
            bi.btnCheckMrno.setVisibility(View.GONE);
            bi.fldGrpA.setVisibility(View.VISIBLE);
            bi.fldGrpB.setVisibility(View.VISIBLE);

        } else {
            bi.sfu001.setEnabled(true);
            bi.btnCheckMrno.setVisibility(View.VISIBLE);
            bi.fldGrpA.setVisibility(View.GONE);
            bi.fldGrpB.setVisibility(View.GONE);

            // Do something else
            Toast.makeText(context, "Restart your app or contact your support team!", Toast.LENGTH_SHORT);

        }

    }

    private void setupView() {

        bi.sfu001.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                bi.sfu001.setInputType(InputType.TYPE_CLASS_NUMBER);
                length = charSequence.toString().length();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                clearFields();


                if (!bi.sfu001.getText().toString().isEmpty() && bi.sfu001.getText().toString().length() == 3) {
                    if (bi.sfu001.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 4) {
                            bi.sfu001.setText(bi.sfu001.getText().toString() + "-");
                            bi.sfu001.setSelection(bi.sfu001.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }
                if (!bi.sfu001.getText().toString().isEmpty() && bi.sfu001.getText().toString().length() == 6) {
                    if (bi.sfu001.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 7) {
                            bi.sfu001.setText(bi.sfu001.getText().toString() + "-");
                            bi.sfu001.setSelection(bi.sfu001.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });



        bi.sfu106.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu106f) {
                    bi.fldGrpsfu106.setVisibility(View.GONE);
                    bi.sfu107.setText(null);
                    bi.sfu108.setText(null);
                    bi.sfu109.setText(null);
                }  else if (i == R.id.sfu106a) {
                    bi.fldGrpsfu107.setVisibility(View.GONE);
                    bi.sfu109.setText(null);
                    bi.sfu108.setText(null);
                    bi.fldGrpsfu106.setVisibility(View.VISIBLE);
                }else {
                    bi.fldGrpsfu107.setVisibility(View.VISIBLE);
                    bi.fldGrpsfu106.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private boolean formValidation() {
        Toast.makeText(context, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (!validatorClass.EmptyTextBox(context, bi.sfu001, getString(R.string.mrno))) {
            return false;
        }
        if (bi.sfu001.getText().toString().length() == 9) {
            String[] str = bi.sfu001.getText().toString().split("-");
            if (str.length > 3 || bi.sfu001.getText().toString().charAt(3) != '-' || bi.sfu001.getText().toString().charAt(6) != '-') {
                bi.sfu001.setError("Wrong presentation!!");
                return false;
            }
        } else {
            Toast.makeText(context, "Invalid length: " + getString(R.string.mrno), Toast.LENGTH_SHORT).show();
            bi.sfu001.setError("Invalid length");
            return false;
        }
        if (!validatorClass.EmptyRadioButton(context, bi.sfu105, bi.sfu105a, getString(R.string.sfu105))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(context, bi.sfu106, bi.sfu106a, getString(R.string.sfu106))) {
            return false;
        }

        if (!bi.sfu106f.isChecked()) {

            if (!validatorClass.EmptyTextBox(context, bi.sfu107, getString(R.string.sfu107))) {
                return false;
            }
//            Completed (4): Change weight of neonate with a range of 5000 kg's!
            if (!validatorClass.RangeTextBox(context, bi.sfu107, 1000, 5000, getString(R.string.sfu107), " Weight")) {
                return false;
            }
            if (!bi.sfu106f.isChecked() && !bi.sfu106a.isChecked()) {

                if (!validatorClass.EmptyTextBox(context, bi.sfu108, getString(R.string.sfu108))) {
                    return false;
                }
                if (!bi.sfu108.getText().toString().contains(".")) {
                    Toast.makeText(context, "Length of neonate should be in decimal", Toast.LENGTH_SHORT);
                    bi.sfu108.setError("Length of neonate should be in decimal");
                    return false;
                } else {
                    bi.sfu108.setError(null);
                }
                if (!validatorClass.EmptyTextBox(context, bi.sfu109, getString(R.string.sfu109))) {
                    return false;
                }
                if (!bi.sfu109.getText().toString().contains(".")) {
                    Toast.makeText(context, "Head of Circumference should be decimal", Toast.LENGTH_SHORT);
                    bi.sfu109.setError("Head of Circumference should be decimal");
                    return false;
                } else {
                    bi.sfu109.setError(null);
                }
            }
        }
      /*  if (!validatorClass.EmptyTextBox(context, bi.sfu10, getString(R.string.sfu10))) {
            return false;
        }
*/
        return true;
    }


    public void BtnContinue() {
        //  Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                //  Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();
                finish();
                 MainApp.fupLocation = bi.sfu106a.isChecked() ? 1 : bi.sfu106b.isChecked() ? 2 : bi.sfu106c.isChecked() ? 3 :bi.sfu106d.isChecked() ? 4 :bi.sfu106e.isChecked() ? 5 :bi.sfu106f.isChecked() ? 6 : 0;
              /*  Boolean defaultValue = true;
                if (bi.sfu01c.isChecked()) {
                    defaultValue = false;
                } else {
                    defaultValue = true;
                }*/
                //startActivity(new Intent(this, FeedingPracticeActivity.class).putExtra("openExamSec", defaultValue).putExtra("childName", bi.ChildName.getText().toString()));


                startActivity(new Intent(context, FeedingPracticeActivity.class)
                        .putExtra(FUPLOCATION_TAG, MainApp.fupLocation)
                        .putExtra("childName", bi.ChildName.getText().toString())
                        .putExtra("mrno", bi.sfu001.getText().toString())
                        .putExtra("studyID", bi.sfu002.getText().toString()));
                /*    if (!bi.sfu10.getText().toString().equals("0") && !bi.sfu10.getText().toString().equals("00")) {
                    startActivity(new Intent(context, HistoryActivity.class)
                            .putExtra(FUPLOCATION_TAG, fuplocation)
                            .putExtra("childName", bi.ChildName.getText().toString())
                            .putExtra("noofSachet", bi.sfu10.getText().toString())
                            .putExtra("mrno", bi.sfu001.getText().toString())
                            .putExtra("studyID", bi.sfu002.getText().toString()));
                } else {
                    startActivity(new Intent(context, FeedingPracticeActivity.class)
                            .putExtra(FUPLOCATION_TAG, fuplocation)
                            .putExtra("childName", bi.ChildName.getText().toString())
                            .putExtra("mrno", bi.sfu001.getText().toString())
                            .putExtra("studyID", bi.sfu002.getText().toString()));
                }*/
            } else {
                 Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(context);

        Long newrowid = db.addForm(MainApp.fc);

        MainApp.fc.set_ID(String.valueOf(newrowid));

        if (newrowid != 0) {
            //Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.fc.setUID((MainApp.fc.getDeviceID() + MainApp.fc.get_ID()));
            db.updateFormID();

            return true;
        } else {
            //  Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void BtnEnd() {
        final Intent endingIntent = new Intent(context, EndingActivity.class).putExtra("complete", false);
//Completed (5): Add a prompt "Do you really want to exit?Yes/No" on click on exit button
        // Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder
                .setMessage("Do you want to Exit??")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
//                                    if (formValidation()) {
                                try {
                                    SaveDraft();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (UpdateDB()) {
                                    finish();
                                    startActivity(endingIntent);
                                } else {
                                    Toast.makeText(context, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
                                }
//                                    }
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    private void SaveDraft() throws JSONException {
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        MainApp.fc = new FormsContract();
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setDevicetagID(MainApp.getTagName(context));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.fc.setsMrno(bi.sfu001.getText().toString());
        MainApp.fc.setsStudyid(bi.sfu002.getText().toString());
        MainApp.fc.setFormtype(MainApp.FORMTYPE_Fup);
        MainApp.fc.setIsinserted("1");
        setGPS(); //Set GPS

        JSONObject fu = new JSONObject();
        fu.put("sfudatetime", dtToday);
        fu.put("uuid", bi.ruid.getText().toString());
        fu.put("childName", bi.ChildName.getText().toString());
        fu.put("sfu105", bi.sfu105a.isChecked() ? "1"
                : bi.sfu105b.isChecked() ? "2"
                : bi.sfu10596.isChecked() ? "96"
                : "0");
        fu.put("sfu10596x", bi.sfu10596x.getText().toString());
        fu.put("sfu106", bi.sfu106a.isChecked() ? "1"
                : bi.sfu106b.isChecked() ? "2"
                : bi.sfu106c.isChecked() ? "3"
                : bi.sfu106d.isChecked() ? "4"
                : bi.sfu106e.isChecked() ? "5"
                : bi.sfu106f.isChecked() ? "6"
                : "0");
        fu.put("sfu107", bi.sfu107.getText().toString());
        fu.put("sfu108", bi.sfu108.getText().toString());
        fu.put("sfu109", bi.sfu109.getText().toString());

        MainApp.fc.setsFup(String.valueOf(fu));

    }

    public void btnCheckMrno() {

        if (!bi.sfu001.getText().toString().trim().isEmpty() && !bi.sfu002.getText().toString().trim().isEmpty()) {

            // fc = db.getStudyID(bi.sfu001.getText().toString());
            // fc_1 = db.getChildName(bi.sfu001.getText().toString(), bi.sfu002.getText().toString());
            // childListContract =  db.getChildName(bi.sfu001.getText().toString(), bi.sfu002.getText().toString());

            //TODO : Checking mrno is recruited on server or not!!!
//            if ((!childListContract.getMrNo().isEmpty() && !childListContract.getStudyID().isEmpty()) || db.isChildFound(bi.sfu001.getText().toString(), bi.sfu002.getText().toString())) {
            if (db.isChildFound(bi.sfu001.getText().toString(), bi.sfu002.getText().toString())) {

                //TODO() : checking followup already inserted today or not!!

                if (!db.iffupexist(bi.sfu001.getText().toString(), bi.sfu002.getText().toString(), todaysDate + " 00:00", dtToday)) {
                   /* if(!db.isChildFound(bi.sfu001.getText().toString(), bi.sfu002.getText().toString())){
//                        recmodel = JSONUtilClass.getModelFromJSON(fc_1.getsRecr(), RecruitmentJSONModel.class);
                       // bi.sfu002.setText(fc_1.getsStudyid());
                        bi.ruid.setText(childListContract.get_ruid());
                        bi.ChildName.setText(childListContract.getChildname());
                        MainApp.fetchLocal = true;
                    }else{*/
                    // TODO checking list from server side data
                    childListContract = db.getChildDetail(bi.sfu001.getText().toString(), bi.sfu002.getText().toString());
                    // bi.sfu002.setText(childListContract.getStudyID());
                    bi.ruid.setText(childListContract.get_ruid());
                    bi.ChildName.setText(childListContract.getChildname());

                    //  MainApp.fetchLocal = false;
                    // }

                    // Toast.makeText(this, "MR Number found!", Toast.LENGTH_SHORT).show();
                    bi.fldGrpA.setVisibility(View.VISIBLE);
                    bi.fldGrpB.setVisibility(View.VISIBLE);
                } else {

                    Toast.makeText(context, "You have already inserted a followup Today!", Toast.LENGTH_SHORT).show();
                }


            } else {
                bi.fldGrpA.setVisibility(View.GONE);
                bi.fldGrpB.setVisibility(View.GONE);
             /*   bi.fldGrpA.setVisibility(View.VISIBLE);
                bi.fldGrpB.setVisibility(View.VISIBLE);*/
                Toast.makeText(context, "No MR No or Study ID found!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context, "Please Enter correct MrNo and Study ID", Toast.LENGTH_SHORT).show();
        }

    }

    private void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");

            if (lat == "0" && lang == "0") {
                // Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                //  Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            MainApp.fc.setGpsLat(lat);
            MainApp.fc.setGpsLng(lang);
            MainApp.fc.setGpsAcc(acc);
            MainApp.fc.setGpsDT(date); // Timestamp is converted to date above

            //Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }


}
