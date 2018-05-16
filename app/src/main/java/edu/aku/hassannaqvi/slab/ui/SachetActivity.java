package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


import edu.aku.hassannaqvi.slab.Adapter.HistoryAdapter;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivitySachetBinding;

public class SachetActivity extends AppCompatActivity {
    String noofSachet, childName;
    Boolean nextExamSec;
    ActivitySachetBinding bi;
    HistoryAdapter adapter;
    int[][] answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_sachet);
        bi.setCallback(this);
        gettingIntents();
        setUpView();
    }

    private void setUpView() {
        bi.ChildName.setText(childName);
        new generateHistoryRadioList(this).execute();
    }

    private void gettingIntents() {
        Intent intent = getIntent();
        if (intent.hasExtra("openExamSec") && intent.hasExtra("childName") && intent.hasExtra("noofSachet")) {
            Bundle bundle = intent.getExtras();
            nextExamSec = bundle.getBoolean("openExamSec");
            childName = bundle.getString("childName");
            noofSachet = bundle.getString("noofSachet");
        } else {
            // Do something else
            Toast.makeText(this, "Restart your app or contact your support team!", Toast.LENGTH_SHORT);

        }

    }

    public class generateHistoryRadioList extends AsyncTask<String, String, String> {
        private Context mContext;

        public generateHistoryRadioList(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

//              Setting Adapter to Recycler View
                    adapter = new HistoryAdapter(Integer.parseInt(noofSachet));

                    if (adapter.getItemCount() > 0) {
                        bi.recylerDays.setVisibility(View.VISIBLE);
                        //bi.nofup.setVisibility(View.GONE);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        bi.recylerDays.setLayoutManager(mLayoutManager);
                        bi.recylerDays.setItemAnimator(new DefaultItemAnimator());
                        bi.recylerDays.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        bi.recylerDays.setVisibility(View.GONE);
                        // Toast.makeText(this, "No Child Found!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // notifychildchange(childAdapter);
//                   Background black for those that's data filled
                  /*  for (int item : MainApp.hhClicked) {
                        binding.recyclerChild.getChildAt(item).setBackgroundColor(Color.BLACK);
                    }*/
                }
            }, 800);
        }
    }

    public void BtnEnd() {

    }

    public void BtnContinue() {
        answers = adapter.getAnswers();
      //  Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
       //         Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {

        JSONObject fp = new JSONObject();
        for (int i = 1; i <= Integer.parseInt(noofSachet); i++) {
            fp.put("", "");
        }
        MainApp.fc.setsFeed(String.valueOf(fp));
    }

    private boolean formValidation() {
        for (int i = 0; i < Integer.parseInt(noofSachet); i++) {
            for (int j = 0; j < 2; j++) {
                int value = answers[i][j];
                Log.d("formvalidation", "value: " + value);
                if (value == 0) {
                    Toast.makeText(this, "Make sure all questions are answered!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

        }


        return false;
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

}
