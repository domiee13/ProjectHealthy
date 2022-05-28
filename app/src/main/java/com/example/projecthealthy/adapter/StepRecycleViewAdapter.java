package com.example.projecthealthy.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthealthy.R;
import com.example.projecthealthy.model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepRecycleViewAdapter extends RecyclerView.Adapter<StepRecycleViewAdapter.StepViewHolder> {
    private List<Step> list;

    public StepRecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<Step> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Step getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item,parent,false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        Step item = list.get(position);
        holder.date.setText(item.getDate());
        holder.count.setText(item.getCount()+" bước");
        holder.kcal.setText(item.getKcal()+" Kcal");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder{
        private TextView date, count, kcal;
        public StepViewHolder(@NonNull View view) {
            super(view);
            date = view.findViewById(R.id.tvDate);
            count = view.findViewById(R.id.tvCount);
            kcal = view.findViewById(R.id.tvKcal);
        }
    }
}
