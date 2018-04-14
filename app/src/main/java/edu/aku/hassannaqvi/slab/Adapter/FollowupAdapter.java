package edu.aku.hassannaqvi.slab.Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FollowupListModel;
import edu.aku.hassannaqvi.slab.databinding.ActivityFollowupAdapterBinding;

public class FollowupAdapter extends RecyclerView.Adapter<FollowupAdapter.FollowupHolder> {

    FollowupAdapter.FollowupHolder holder;
    private List<FollowupListModel> childList;


    public FollowupAdapter(List<FollowupListModel> childList) {

        this.childList = childList;
    }

    @Override
    public FollowupAdapter.FollowupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_followup_adapter, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//add on click event here
            }
        });
        return new FollowupAdapter.FollowupHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowupAdapter.FollowupHolder holder, int position) {
        this.holder = holder;
        this.holder.bindUser(this.childList.get(position));
    }

    @Override
    public int getItemCount() {
        return childList.size() > 0 ? childList.size() : 0;
    }

    public class FollowupHolder extends RecyclerView.ViewHolder {

        ActivityFollowupAdapterBinding followupadapterBinding;



        public FollowupHolder(View itemView) {
            super(itemView);
            followupadapterBinding = DataBindingUtil.bind(itemView);

        }

        public void bindUser(FollowupListModel listModel) {
            followupadapterBinding.childname.setText(listModel.getChildname());
            followupadapterBinding.mothername.setText(listModel.getMothername());
            followupadapterBinding.studyID.setText("StudyID: "+listModel.getStudyID());
            followupadapterBinding.MRno.setText("MR No.: "+listModel.getMrNo());
            followupadapterBinding.dischargeDate.setText(listModel.getDate());
            //followupadapterBinding.type.setImageDrawable());
        }
    }
}
