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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.aku.hassannaqvi.slab.Adapter.FollowupAdapter;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FollowupListContract;
import edu.aku.hassannaqvi.slab.core.MainApp;
import edu.aku.hassannaqvi.slab.databinding.ActivityViewFollowUpBinding;

public class ViewFollowUpActivity extends AppCompatActivity {
    ActivityViewFollowUpBinding bi;
    FollowupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_view_follow_up);
        bi.setCallback(this);

        setUpView();
    }

    private void setUpView() {
        new generateFollowupList(this).execute();
    }


    public class generateFollowupList extends AsyncTask<String, String, String> {
        private Context mContext;

        public generateFollowupList(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    List<FollowupListContract> list = new ArrayList<>();
                    FollowupListContract model;
                    for (int i = 0; i < 10; i++) {
                        model = new FollowupListContract();
                        model.setChildname("Child Name " + i);
                        model.setMothername("Mother Name " + i);
                        model.setMrNo("111-22-3" + i);
                        model.setStudyID("123" + i);
                        model.setDischargeDate("14-4-201" + i);
                        model.setEnrolmentDate("10-4-201" + i);
                        if (i == 0 || i == 3 || i == 6 || i == 9){
                            model.setType("1");
                            model.setTypeimg(getDrawable(R.drawable.home));
                        }
                        else if (i == 1 || i == 4 || i == 7){
                            model.setType("2");
                            model.setTypeimg(getDrawable(R.drawable.hospital));
                        }
                        else if (i == 2 || i == 5 || i == 8){
                            model.setType("3");
                            model.setTypeimg(getDrawable(R.drawable.phone));
                        }
                        else{
                            model.setType("1");
                            model.setTypeimg(getDrawable(R.drawable.home));
                        }
                        String type = model.getType();
                        if(type.equals("1"))
                        model.setStatus("Not Due");
                        else if(type.equals("2"))
                        model.setStatus("Due");
                        else
                        model.setStatus("Done");
                        list.add(model);
                    }
//              Setting Adapter to Recycler View
                    adapter = new FollowupAdapter(list, new FollowupAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(FollowupListContract followupListModel) {
                          //  Toast.makeText(getApplicationContext(),"child Name is "+followupListModel.getChildname(),Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), FollowUpFormActivity.class).putExtra(MainApp.MRNO_TAG,followupListModel.getMrNo()).putExtra(MainApp.STUDYID_TAG,followupListModel.getStudyID()).putExtra(MainApp.CHILDNAME_TAG,followupListModel.getChildname()));
                        }
                    });


                    if (adapter.getItemCount() > 0) {
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        bi.recylerfollowuplists.setLayoutManager(mLayoutManager);
                        bi.recylerfollowuplists.setItemAnimator(new DefaultItemAnimator());
                        bi.recylerfollowuplists.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    } else {
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
}
