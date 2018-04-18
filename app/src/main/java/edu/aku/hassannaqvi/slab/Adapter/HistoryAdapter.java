package edu.aku.hassannaqvi.slab.Adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.contracts.FollowupListContract;
import edu.aku.hassannaqvi.slab.databinding.HistoryadapterBinding;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    HistoryAdapter.HistoryHolder holder;
    int noofSachet;
    int[][] answers;

    public HistoryAdapter(int noofSachet) {
        //no instance
        this.noofSachet = noofSachet;
    }

    public int[][] getAnswers() {
        return answers;
    }

    @Override
    public HistoryAdapter.HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.historyadapter, parent, false);
        answers = new int[getItemCount()][2];

        return new HistoryAdapter.HistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.HistoryHolder holder, int position) {
        this.holder = holder;
        this.holder.bindUser(this.noofSachet);
    }

    @Override
    public int getItemCount() {
        return noofSachet > 0 ? noofSachet : 0;
    }


    public class HistoryHolder extends RecyclerView.ViewHolder {
        HistoryadapterBinding binding;


        public HistoryHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindUser(final int noofsachet) {
            final int count = getAdapterPosition() + 1;
            binding.sfudaytext.setText("Day " + count + " (have you given the supplement)?");

            binding.sfu11a1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    Log.d("adapter", "postion: " + getAdapterPosition());
                    answers[getAdapterPosition()][0] = i;
                    Log.d("adapter", "answer: " + answers[getAdapterPosition()][0]);

                }
            });
            binding.sfu11a2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    Log.d("checking ID", "Integer: " + i);
                    Log.d("adapter position", "postion: " + getAdapterPosition());
                    answers[getAdapterPosition()][1] = i;

                }
            });
        }
    }
}