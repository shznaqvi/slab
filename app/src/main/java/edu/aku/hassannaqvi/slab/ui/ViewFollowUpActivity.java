package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import edu.aku.hassannaqvi.slab.Adapter.FollowupAdapter;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FollowupListContract;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.databinding.ActivityViewFollowUpBinding;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ViewFollowUpActivity extends AppCompatActivity {
    ActivityViewFollowUpBinding bi;
    FollowupAdapter adapter;
    DatabaseHelper db;
    List<FollowupListContract> followupList;
    String localmrno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_view_follow_up);
        bi.setCallback(this);
        gettingList();
        setUpView();
    }

    private void gettingList() {
        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        if (intent.hasExtra("mrno")) {
            Bundle bundle = intent.getExtras();
            localmrno = bundle.getString("mrno");
            followupList = db.getAllFollowups(localmrno);
        } else {
            // Do something else
            // Toast.makeText(this, "Restart your app or contact your support team!", Toast.LENGTH_SHORT);
            followupList = db.getAllFollowups();
        }

    }

    private void setUpView() {
        new generateFollowupList(this).execute();
    }

    public void addFollowUp(View v) {
        startActivity(new Intent(this, FollowUpFormActivity.class));
    }
    public void fetchFollowUp(View v) {
        // get inputmr_layout.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View inputmrView = li.inflate(R.layout.inputmr_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(inputmrView);

        final EditText usermrno = inputmrView
                .findViewById(R.id.etmrno);


        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                followupList = null;
                                followupList = db.getAllFollowups(usermrno.getText().toString());
                                adapter.refreshList(followupList);
                                adapter.notifyDataSetChanged();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }



    public class generateFollowupList extends AsyncTask<String, String, String> {
        private Context mContext;

        public generateFollowupList(Context mContext) {
            this.mContext = mContext;
           bi.loadingfollowup.setVisibility(VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
//              Setting Adapter to Recycler View
                    adapter = new FollowupAdapter(followupList, new FollowupAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(FollowupListContract followupListModel) {
                            //  Toast.makeText(getApplicationContext(),"child Name is "+followupListModel.getChildname(),Toast.LENGTH_SHORT).show();
                            //  startActivity(new Intent(getApplicationContext(), FollowUpFormActivity.class).putExtra(MainApp.MRNO_TAG, followupListModel.getMrno()).putExtra(MainApp.STUDYID_TAG, followupListModel.getStudyid()).putExtra(MainApp.CHILDNAME_TAG, followupListModel.getChildname()));
                        }
                    });


                    if (adapter.getItemCount() > 0) {
                        bi.recylerfollowuplists.setVisibility(VISIBLE);
                        bi.nofup.setVisibility(GONE);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        bi.recylerfollowuplists.setLayoutManager(mLayoutManager);
                        bi.recylerfollowuplists.setItemAnimator(new DefaultItemAnimator());
                        bi.recylerfollowuplists.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } else {
                        bi.recylerfollowuplists.setVisibility(GONE);
                        bi.nofup.setVisibility(VISIBLE);
                        // Toast.makeText(this, "No Child Found!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            bi.loadingfollowup.setVisibility(GONE);

          /*  new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // notifychildchange(childAdapter);
//                   Background black for those that's data filled
                  *//*  for (int item : MainApp.hhClicked) {
                        binding.recyclerChild.getChildAt(item).setBackgroundColor(Color.BLACK);
                    }*//*
                }
            }, 800);*/
        }
    }
}
