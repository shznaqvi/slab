package edu.aku.hassannaqvi.slab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import edu.aku.hassannaqvi.slab.R;

public class FollowupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followup);
    }

    public void BtnEnd() {
        finish();
        Toast.makeText(this, "complete Section", Toast.LENGTH_SHORT).show();
        Intent endSec = new Intent(this, EndingActivity.class);
        endSec.putExtra("complete", false);
        startActivity(endSec);
    }

    public void BtnContinue() {

        /*Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //if (UpdateDB()) {
            Toast.makeText(this, "Starting Ending Section", Toast.LENGTH_SHORT).show();

            finish();


        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
        // }*/
    }
}
