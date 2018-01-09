package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.DistrictsContract;
import edu.aku.hassannaqvi.slab.contracts.FormsContract;
import edu.aku.hassannaqvi.slab.contracts.VillagesContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityEligibilityBinding;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class EligibilityActivity extends AppCompatActivity {

    private static final String TAG = EligibilityActivity.class.getName();

    ActivityEligibilityBinding binding;
    int check = 0;
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());

    DatabaseHelper db;
    Map<String, String> getAllDistricts, getAllVillages;
    List<String> Districts, Villages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_eligibility);
        db = new DatabaseHelper(this);

//        Get data from Main Activity
        check = getIntent().getExtras().getInt("check");
//        Assigning data to UI binding
        checking ch = new checking(check);
        //binding.setCheckFlag(ch);
        binding.setCallback(this);

//        Setting DATETIME picker and spinners

        populateSpinner(); //populate spinner for ucs and villages

        switch (check) {
            case 1:
                binding.dfa10.setManager(getSupportFragmentManager());
                binding.dfa15.setManager(getSupportFragmentManager());

                binding.dfa11.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(new String[]{"....", "7", "14", "28", "40"})));

                break;
            case 2:
                binding.da07.setManager(getSupportFragmentManager());
                break;
            case 3:
                binding.pfa14.setManager(getSupportFragmentManager());
                binding.pfa17.setManager(getSupportFragmentManager());

                binding.pfa10.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(new String[]{"....", "1", "2"})));
                binding.pfa11.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(new String[]{"....", "Second", "Third"})));
                break;
            case 4:
                binding.ra10.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(new String[]{"....", "First", "Second", "Third"})));
                break;
            default:
                break;
        }


//        Main Working from here
//        Skip Patterns

    }

    public Boolean formValidation() {

//        HH No
        if (!validatorClass.EmptyTextBox(this, binding.hhno, getString(R.string.hhno))) {
            return false;
        }

//        PW-ID
        if (!validatorClass.EmptyTextBox(this, binding.pwid, getString(R.string.pwid))) {
            return false;
        }

//       W.NAME
        if (!validatorClass.EmptyTextBox(this, binding.pwname, getString(R.string.wname))) {
            return false;
        }

//        W.AGE
        if (check != 2) {
            if (!validatorClass.EmptyTextBox(this, binding.age, getString(R.string.age))) {
                return false;
            }

            if (!validatorClass.RangeTextBox(this, binding.age, 15, 49, getString(R.string.age), " years")) {
                return false;
            }

        }

//       H.NAME
        if (!validatorClass.EmptyTextBox(this, binding.hname, getString(R.string.hname))) {
            return false;
        }

//       HH.NAME
        if (!validatorClass.EmptyTextBox(this, binding.hhname, getString(R.string.hhname))) {
            return false;
        }

//        Child.Name
        if (check == 1) {
            if (!validatorClass.EmptyTextBox(this, binding.dfa06, getString(R.string.dfa06))) {
                return false;
            }
        }

//        UC
        if (!validatorClass.EmptySpinner(this, binding.spUCs, "UCs")) {
            return false;
        }

//        Villages
        if (!validatorClass.EmptySpinner(this, binding.spVillages, "Villages")) {
            return false;
        }

//        DA 07
        if (check == 2) {
            if (!validatorClass.EmptyTextBox(this, binding.da07, getString(R.string.da07))) {
                return false;
            }
        }

//       LHV.NAME
        if (!validatorClass.EmptyTextBox(this, binding.lhvname, getString(R.string.lhvname))) {
            return false;
        }

//        DFA 10
        if (check == 1) {
            if (!validatorClass.EmptyTextBox(this, binding.dfa10, getString(R.string.dfa10))) {
                return false;
            }

//            dfa 11
            if (!validatorClass.EmptySpinner(this, binding.dfa11, getString(R.string.dfa11))) {
                return false;
            }
        }

//        PFA 10
        if (check == 3) {
            if (!validatorClass.EmptySpinner(this, binding.pfa10, getString(R.string.pfa10))) {
                return false;
            }

//            pfa 11
            if (!validatorClass.EmptySpinner(this, binding.pfa11, getString(R.string.pfa11))) {
                return false;
            }
        }

//        RA 10
        if (check == 4) {
            if (!validatorClass.EmptySpinner(this, binding.ra10, getString(R.string.ra10))) {
                return false;
            }

            if (!validatorClass.EmptyTextBox(this, binding.ra11, getString(R.string.ra11))) {
                return false;
            }
        }

//       STATUS
        if (!validatorClass.EmptyRadioButton(this, binding.istatus, binding.istatusg, getString(R.string.istatus))) {
            return false;
        }

//        DFA 15
        if (check == 1) {
            if (!(binding.dfa1599.isChecked() && binding.dfa15888.isChecked())) {
                if (!validatorClass.EmptyTextBox(this, binding.dfa15, getString(R.string.dfa15))) {
                    binding.dfa15888.setError(null);
                    return false;
                }
            }
        }

//        PFA 14
        if (check == 3) {
            if (!(binding.pfa1499.isChecked() && binding.pfa14888.isChecked())) {
                if (!validatorClass.EmptyTextBox(this, binding.pfa14, getString(R.string.pfa14))) {
                    binding.pfa14888.setError(null);
                    return false;
                }
            }

//            pfa 15
            if (!validatorClass.EmptyRadioButton(this, binding.pfa15, binding.pfa15c, getString(R.string.pfa15))) {
                return false;
            }
//            pfa 16
            if (!validatorClass.EmptyRadioButton(this, binding.pfa16, binding.pfa16g, getString(R.string.pfa16))) {
                return false;
            }
//            pfa 17
            if (!(binding.pfa1799.isChecked() && binding.pfa17888.isChecked())) {
                if (!validatorClass.EmptyTextBox(this, binding.pfa17, getString(R.string.pfa17))) {
                    binding.pfa17888.setError(null);
                    return false;
                }
            }
//            pfa 18
            if (!validatorClass.EmptyTextBox(this, binding.pfa18, getString(R.string.pfa18))) {
                return false;
            }
//            pfa 19
            if (!binding.pfa19888.isChecked()) {
                if (!validatorClass.EmptyTextBox(this, binding.pfa19, getString(R.string.pfa19))) {
                    return false;
                }
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
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

                finish();

                switch (check) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        if (!binding.ra11.getText().toString().equals("0")) {
                            //startActivity(new Intent(this, SecRBActivity.class));
                        } else {
                            //startActivity(new Intent(this, SecRCActivity.class));
                        }
                        break;
                    default:
                        break;
                }

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
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
        MainApp.fc.setFormDate(dtToday);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.fc.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        setGPS(); //Set GPS

        JSONObject sa = new JSONObject();

        sa.put("hhno", binding.hhno.getText().toString());
        sa.put("pwid", binding.pwid.getText().toString());
        sa.put("pwname", binding.pwname.getText().toString());

        if (check == 2) {
            sa.put("age", binding.age.getText().toString());
            sa.put("da07", binding.da07.getText().toString());

            MainApp.fc.setFormtype("DA");
        }

        sa.put("hname", binding.hname.getText().toString());
        sa.put("hhname", binding.hhname.getText().toString());

        if (check == 1) {
            sa.put("dfa06", binding.dfa06.getText().toString());
            sa.put("dfa10", binding.dfa10.getText().toString());
            sa.put("dfa11", binding.dfa11.getSelectedItem().toString());
            sa.put("dfa15", binding.dfa15.getText().toString().trim().isEmpty() ? (binding.dfa1599.isChecked() ? "99" : (binding.dfa15888.isChecked() ? "88" : "0"))
                    : binding.dfa15.getText().toString());

            MainApp.fc.setFormtype("DFA");
        }

        sa.put("ucCode", getAllDistricts.get(binding.spUCs.getSelectedItem().toString()));
        sa.put("villageCode", getAllVillages.get(binding.spVillages.getSelectedItem().toString()));
        sa.put("lhvname", binding.lhvname.getText().toString());

        if (check == 3) {
            sa.put("pfa10", binding.pfa10.getSelectedItem().toString());
            sa.put("pfa11", binding.pfa11.getSelectedItem().toString());
            sa.put("pfa14", binding.pfa14.getText().toString().trim().isEmpty() ? (binding.pfa1499.isChecked() ? "99" : (binding.pfa14888.isChecked() ? "88" : "0"))
                    : binding.pfa14.getText().toString());
            sa.put("pfa15", binding.pfa15a.isChecked() ? "1" : binding.pfa15b.isChecked() ? "2" : binding.pfa15c.isChecked() ? "3" : "0");
            sa.put("pfa16", binding.pfa16a.isChecked() ? "1" : binding.pfa16b.isChecked() ? "2" : binding.pfa16c.isChecked() ? "3" : binding.pfa16d.isChecked() ? "4" : binding.pfa16e.isChecked() ? "5"
                    : binding.pfa16f.isChecked() ? "6" : binding.pfa16g.isChecked() ? "7" : "0");
            sa.put("pfa17", binding.pfa17.getText().toString().trim().isEmpty() ? (binding.pfa1799.isChecked() ? "99" : (binding.pfa17888.isChecked() ? "88" : "0"))
                    : binding.pfa17.getText().toString());
            sa.put("pfa18", binding.pfa18.getText().toString());
            sa.put("pfa19", binding.pfa19.getText().toString().trim().isEmpty() ? (binding.pfa19888.isChecked() ? "88" : "0")
                    : binding.pfa19.getText().toString());


            MainApp.fc.setFormtype("PFA");
        }

        if (check == 4) {
            sa.put("ra10", binding.ra10.getSelectedItem().toString());
            sa.put("ra11", binding.ra11.getText().toString());

            MainApp.fc.setFormtype("RA");
        }

        sa.put("istatus", binding.istatusa.isChecked() ? "1" : binding.istatusb.isChecked() ? "2" : binding.istatusc.isChecked() ? "3" : binding.istatusd.isChecked() ? "4" : binding.istatuse.isChecked() ? "5"
                : binding.istatusf.isChecked() ? "6" : binding.istatusg.isChecked() ? "7" : "0");

        MainApp.womanage = Integer.valueOf(binding.age.getText().toString());
        MainApp.prevPreg = Integer.valueOf(binding.ra11.getText().toString());

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

    public void populateSpinner() {

        //        Spinner Fill

        db = new DatabaseHelper(this);

        Districts = new ArrayList<>();
        getAllDistricts = new HashMap<>();

        Districts.add("....");

        Collection<DistrictsContract> allDis = db.getAllDistricts();

        for (DistrictsContract aUCs : allDis) {
            getAllDistricts.put(aUCs.getDistrictName(), aUCs.getDistrictCode());
            Districts.add(aUCs.getDistrictName());
            Collections.sort(Districts);
        }

        binding.spUCs.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Districts));

        binding.spUCs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Villages = new ArrayList<>();
                getAllVillages = new HashMap<>();

                Villages.add("....");

                if (binding.spUCs.getSelectedItemPosition() != 0) {
                    Collection<VillagesContract> allDis = db.getAllPSUsByDistrict(getAllDistricts.get(binding.spUCs.getSelectedItem().toString()));
                    for (VillagesContract aUCs : allDis) {
                        getAllVillages.put(aUCs.getVillageName(), aUCs.getVillageCode());
                        Villages.add(aUCs.getVillageName());
                        Collections.sort(Villages);
                    }
                }

                binding.spVillages.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, Villages));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
