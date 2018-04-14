package edu.aku.hassannaqvi.slab.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.aku.hassannaqvi.slab.Adapter.FollowupAdapter;
import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FollowupListModel;
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
                    List<FollowupListModel> list = new ArrayList<>();
                    FollowupListModel model;
                    for (int i = 0; i < 10; i++) {
                        model = new FollowupListModel();
                        model.setChildname("Child Name " + i);
                        model.setMothername("Mother Name " + i);
                        model.setMrNo("111-22-3" + i);
                        model.setStudyID("123" + i);
                        model.setDate("12-4-201" + i);
                        list.add(model);
                    }
//              Set Recycler View
                    adapter = new FollowupAdapter(list);


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
