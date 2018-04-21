package edu.aku.hassannaqvi.slab.Adapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FollowupListContract;
import edu.aku.hassannaqvi.slab.databinding.ActivityFollowupAdapterBinding;
import edu.aku.hassannaqvi.slab.ui.MainActivity;
import edu.aku.hassannaqvi.slab.ui.ViewFollowUpActivity;
import edu.aku.hassannaqvi.slab.validation.validatorClass;

public class FollowupAdapter extends RecyclerView.Adapter<FollowupAdapter.FollowupHolder> {

    FollowupAdapter.FollowupHolder holder;
    private final List<FollowupListContract> childList;
    private final OnItemClickListener listener;

    public FollowupAdapter(List<FollowupListContract> childList, OnItemClickListener listener) {
        this.childList = childList;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(FollowupListContract followupListModel);
    }

    @Override
    public FollowupAdapter.FollowupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_followup_adapter, parent, false);

        return new FollowupAdapter.FollowupHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowupAdapter.FollowupHolder holder, int position) {
        this.holder = holder;
        this.holder.bindUser(this.childList.get(position), listener);
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

        public void bindUser(final FollowupListContract listModel, final OnItemClickListener listener) {
            followupadapterBinding.childname.setText(listModel.getChildname());
            followupadapterBinding.mothername.setText(listModel.getMothername());
            followupadapterBinding.studyID.setText("Study ID: " + listModel.getStudyID());
            followupadapterBinding.MRno.setText("MR # " + listModel.getMrNo());
            followupadapterBinding.followupDate.setText(listModel.getLastfupdate());
            followupadapterBinding.enrolmentDate.setText(listModel.getEnrolmentDate());
            if (!listModel.getDischargeDate().equals("")) {
                followupadapterBinding.rlDischargedt.setVisibility(View.VISIBLE);
                followupadapterBinding.DischargeDate.setText(listModel.getDischargeDate());
            } else {
                followupadapterBinding.rlDischargedt.setVisibility(View.GONE);
                followupadapterBinding.DischargeDate.setText("");
            }
            if (listModel.getFupstatus().equals("1") || listModel.getFupstatus().equals("7")) {
                followupadapterBinding.status.setText("Done");
                followupadapterBinding.status.setBackgroundColor(Color.GREEN);
            } else {
                followupadapterBinding.status.setText("Incomplete");
                followupadapterBinding.status.setBackgroundColor(Color.RED);
            }
            followupadapterBinding.fupround.setText(listModel.getFollowupRound());
            //FollowupAdapter.this;


            if (listModel.getFuplocation().equals("1")) {
                followupadapterBinding.typetext.setText("Hospital");
                followupadapterBinding.typehome.setVisibility(View.GONE);
                followupadapterBinding.typehospital.setVisibility(View.VISIBLE);
                followupadapterBinding.typephone.setVisibility(View.GONE);
            } else if (listModel.getFuplocation().equals("2")) {
                followupadapterBinding.typetext.setText("Home");
                followupadapterBinding.typehome.setVisibility(View.VISIBLE);
                followupadapterBinding.typehospital.setVisibility(View.GONE);
                followupadapterBinding.typephone.setVisibility(View.GONE);

            } else if (listModel.getFuplocation().equals("3")) {
                followupadapterBinding.typetext.setText("Telephone");
                followupadapterBinding.typehome.setVisibility(View.GONE);
                followupadapterBinding.typehospital.setVisibility(View.GONE);
                followupadapterBinding.typephone.setVisibility(View.VISIBLE);
            } else if (listModel.getFuplocation().equals("4")) {
                followupadapterBinding.typetext.setText("CTU");
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(listModel);
                }
            });
        }
    }
}
