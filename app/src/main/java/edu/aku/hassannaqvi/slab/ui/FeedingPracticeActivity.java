package edu.aku.hassannaqvi.slab.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.databinding.ActivityEligibilityFormBinding;
import edu.aku.hassannaqvi.slab.databinding.ActivityFeedingPracticeBinding;

public class FeedingPracticeActivity extends AppCompatActivity {
    ActivityFeedingPracticeBinding bi;
    DatabaseHelper db;
    String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_feeding_practice);
        db = new DatabaseHelper(this);
        bi.setCallback(this);

        setupView();
    }

    private void setupView() {
        bi.sfu50.setManager(getSupportFragmentManager());
        bi.sfu50.setMaxDate(dateToday);


        bi.sfu12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu12b) {
                    bi.fldGrpsfu13.setVisibility(View.GONE);
                    bi.sfu13.clearCheck();
                    bi.sfu14.clearCheck();
                    bi.sfu1496x.setText(null);
                } else {
                    bi.fldGrpsfu13.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.sfu13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.sfu13a) {
                    bi.fldGrpsfu14.setVisibility(View.GONE);
                    bi.sfu14.clearCheck();
                    bi.sfu1496x.setText(null);
                } else {
                    bi.fldGrpsfu14.setVisibility(View.VISIBLE);
                }
            }
        });

        bi.sfu49.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sfu49b){
                    bi.fldGrpsfu50.setVisibility(View.GONE);
                    bi.sfu51.clearCheck();
                    bi.sfu5196x.setText(null);
                    bi.sfu52.setText(null);
                    bi.sfu53.setText(null);
                    bi.sfu53a.setChecked(false);

                }else{
                    bi.fldGrpsfu50.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
