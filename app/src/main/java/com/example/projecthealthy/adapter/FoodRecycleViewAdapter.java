package com.example.projecthealthy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthealthy.R;
import com.example.projecthealthy.dal.SQLiteHelper;
import com.example.projecthealthy.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodRecycleViewAdapter extends RecyclerView.Adapter<FoodRecycleViewAdapter.FoodViewHolder>{
    private List<Food> list;
    private ItemListener itemListener;
    private SQLiteHelper sqLiteHelper;
    public FoodRecycleViewAdapter() {
        this.list = new ArrayList<>();
    }
    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food item = list.get(position);
        holder.name.setText(item.getName());
        holder.kcal.setText(item.getKcal()+" calo");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List list){
        this.list = list;
        notifyDataSetChanged();
    }

    public Food getItem(int position){//Bug o day
        return list.get(position);
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name,kcal;
        public FoodViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.tvName);
            kcal = view.findViewById(R.id.tvKcal);
            sqLiteHelper = new SQLiteHelper(view.getContext());
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), "Dep trai vl hhehe", Toast.LENGTH_SHORT).show();
            if(itemListener!=null){
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view, int position);
    }

}
