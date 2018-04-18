package edu.aku.hassannaqvi.slab.Adapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
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

    public FollowupAdapter(List<FollowupListContract> childList,  OnItemClickListener listener) {
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
        this.holder.bindUser(this.childList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return childList.size() > 0 ? childList.size() : 0;
    }

    public class FollowupHolder extends RecyclerView.ViewHolder {

        ActivityFollowupAdapterBinding followupadapterBinding;



        public FollowupHolder(View itemView)  {
            super(itemView);
            followupadapterBinding = DataBindingUtil.bind(itemView);
        }

        public void bindUser(final FollowupListContract listModel,  final OnItemClickListener listener) {
            followupadapterBinding.childname.setText(listModel.getChildname());
            followupadapterBinding.mothername.setText(listModel.getMothername());
            followupadapterBinding.studyID.setText("Study ID: "+listModel.getStudyID());
            followupadapterBinding.MRno.setText("MR # "+listModel.getMrNo());
            followupadapterBinding.dischargeDate.setText(listModel.getDischargeDate());
            followupadapterBinding.enrolmentDate.setText(listModel.getEnrolmentDate());
            followupadapterBinding.status.setText(listModel.getStatus());
            //FollowupAdapter.this;

            followupadapterBinding.typeimg.setImageDrawable(listModel.getTypeimg());

            if (listModel.getType().equals("1")){
                followupadapterBinding.typetext.setText("Home");
            }
            else if (listModel.getType().equals("2")){
                followupadapterBinding.typetext.setText("Hospital");

            }
            else if (listModel.getType().equals("3")){
                followupadapterBinding.typetext.setText("Telephone");
            }
            else if (listModel.getType().equals("4")){
                followupadapterBinding.typetext.setText("CTU");
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(listModel);
                }
            });
        }
    }
}
