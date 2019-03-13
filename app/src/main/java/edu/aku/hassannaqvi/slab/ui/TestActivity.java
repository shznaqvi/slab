package edu.aku.hassannaqvi.slab.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

import static edu.aku.hassannaqvi.slab.core.MainApp.fc;
import static edu.aku.hassannaqvi.slab.core.MainApp.hc;
import static java.sql.Types.INTEGER;

public class TestActivity extends AppCompatActivity {
    final List<EditText> secEtArray = new ArrayList();
    final List<EditText> minEtArray = new ArrayList();
    final List<TextView> durationLabelArray = new ArrayList();
    final List<LinearLayout> childllArray = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        final LinearLayout mLlayout = findViewById(R.id.testlayout);
        final TextView Eplabel = findViewById(R.id.Eplabel);
        final LinearLayout.LayoutParams mRparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final LinearLayout.LayoutParams mRparams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        final LinearLayout.LayoutParams mRparams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        final EditText etnoofboxes = findViewById(R.id.etnoofboxes);
        final Button chck = findViewById(R.id.chck);


        etnoofboxes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 2) {
                    int noofboxes = Integer.valueOf(etnoofboxes.getText().toString());
                    if (durationLabelArray.size() > 0) {
                        for (int i = 0; i < durationLabelArray.size(); i++) {
                            mLlayout.removeView(durationLabelArray.get(i));
                            mLlayout.removeView(childllArray.get(i));
                        }
                        durationLabelArray.clear();
                        childllArray.clear();
                    }
                    Eplabel.setVisibility(View.VISIBLE);
                    for (int i = 0; i < noofboxes; i++) {

                        TextView DurationLabelTextView = new TextView(TestActivity.this);
                        DurationLabelTextView.setLayoutParams(mRparams2);
                        int numberss = i+1;
                        DurationLabelTextView.setText("Duration of Fits # " + numberss);
                        durationLabelArray.add(DurationLabelTextView);
                        mLlayout.addView(DurationLabelTextView);

                        LinearLayout llchild = new LinearLayout(TestActivity.this);
                        llchild.setLayoutParams(mRparams2);
                        llchild.setOrientation(LinearLayout.HORIZONTAL);
                        childllArray.add(llchild);
                        mLlayout.addView(llchild);

                        EditText secEditText = new EditText(TestActivity.this);
                        secEditText.setLayoutParams(mRparams1);
                        secEditText.setHint("Seconds " + numberss);
                        secEditText.setInputType(INTEGER);
                        secEditText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                        int maxLength = 2;
                        secEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                        secEtArray.add(secEditText);
                        llchild.addView(secEditText);


                        EditText minEditText = new EditText(TestActivity.this);
                        minEditText.setLayoutParams(mRparams1);
                        minEditText.setHint("Minutes " + numberss);
                        minEditText.setInputType(INTEGER);
                        minEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                        minEditText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                        minEtArray.add(minEditText);
                        llchild.addView(minEditText);
                    }
                }else {
                    Eplabel.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        chck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formValidation()) {
                    try {
                        SaveDraft();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

      /*  EditText myEditText2 = new EditText(this);
        myEditText2.setLayoutParams(mRparams);
        myEditText2.setText("Edit text 2");

        mLlayout.addView(myEditText2);
        EditText myEditText3 = new EditText(this);
        myEditText3.setLayoutParams(mRparams);
        myEditText3.setText("Edit text 3");
        mLlayout.addView(myEditText3);*/
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();
        JSONObject end = new JSONObject();
        for (int i = 0; i < childllArray.size(); i++) {
            end.put("sfus" + i, secEtArray.get(i).getText().toString());
            end.put("sfum" + i, minEtArray.get(i).getText().toString());
        }


        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean formValidation() {

        for (int i = 0; i < childllArray.size(); i++) {
            int numberss = i+1;
            if (!validatorClass.EmptyTextBox(TestActivity.this, secEtArray.get(i), "Seconds " + numberss)) {
                return false;
            }
            if (!validatorClass.EmptyTextBox(TestActivity.this, minEtArray.get(i), "Minutes " + numberss)) {
                return false;
            }
        }
        return true;
    }
}
