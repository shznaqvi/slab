package edu.aku.hassannaqvi.slab.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import edu.aku.hassannaqvi.slab.R;
import edu.aku.hassannaqvi.slab.databinding.HistoryAdapterBinding;

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
                .inflate(R.layout.history_adapter, parent, false);
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
        HistoryAdapterBinding binding;


        public HistoryHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindUser(final int noofsachet) {
            final int count = getAdapterPosition() + 1;
           /* binding.sfudaytext.setText("Day " + count + " (have you given the supplement)?");

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
            });*/
        }
    }
}
