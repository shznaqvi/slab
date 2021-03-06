package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_view_follow_up);
        bi.setCallback(this);
        this.setTitle(getResources().getString(R.string.list));
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

        usermrno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                usermrno.setInputType(InputType.TYPE_CLASS_NUMBER);
                length = charSequence.toString().length();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                clearFields();


                if (!usermrno.getText().toString().isEmpty() && usermrno.getText().toString().length() == 3) {
                    if (usermrno.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 4) {
                            usermrno.setText(usermrno.getText().toString() + "-");
                            usermrno.setSelection(usermrno.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }
                if (!usermrno.getText().toString().isEmpty() && usermrno.getText().toString().length() == 6) {
                    if (usermrno.getText().toString().substring(0, 3).matches("[0-9]+")) {
                        if (length < 7) {
                            usermrno.setText(usermrno.getText().toString() + "-");
                            usermrno.setSelection(usermrno.getText().length());
                            //binding.nh108.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        }

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

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
