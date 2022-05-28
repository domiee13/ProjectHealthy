package com.example.projecthealthy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthealthy.AddActivity;
import com.example.projecthealthy.Main2Activity;
import com.example.projecthealthy.MainActivity;
import com.example.projecthealthy.R;
import com.example.projecthealthy.UpdateDeleteActivity;
import com.example.projecthealthy.adapter.FoodRecycleViewAdapter;
import com.example.projecthealthy.dal.SQLiteHelper;
import com.example.projecthealthy.model.Food;
import com.example.projecthealthy.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentHome extends Fragment implements FoodRecycleViewAdapter.ItemListener{
    private Button btAdd;
    private RecyclerView recyclerView;
    private FoodRecycleViewAdapter adapter;
    private SQLiteHelper sqLiteHelper;
    private TextView tongKcal,tvHello;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tongKcal = view.findViewById(R.id.tvTong);
        tvHello = view.findViewById(R.id.tvHello);
        btAdd = view.findViewById(R.id.btAdd);
        recyclerView = view.findViewById(R.id.rview);
        //
        Main2Activity main2Activity = (Main2Activity) getActivity();
//        User loggedInUser = main2Activity.getLoggedUser();
        String username = main2Activity.getLoggedUsername();
        //
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        tvHello.setText("Hello "+username);
//        Toast.makeText(main2Activity, username, Toast.LENGTH_SHORT).show();
        sqLiteHelper = new SQLiteHelper(getContext());
        User user = sqLiteHelper.getUserByUsername(username);
        //Recycle view
        adapter = new FoodRecycleViewAdapter();
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        List<Food> list = sqLiteHelper.getAllFood(user.getId(),f.format(d));
        adapter.setList(list);
        adapter.setItemListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        //
        tongKcal.setText(tong(list)+" (Kcal)");
    }

    public float tong(List<Food> list){
        float res=0;
        for(Food i:list){
            res += i.getKcal();
        }
        return res;
    }

    public void setTong(List<Food> list){
        tongKcal.setText(tong(list)+" (Kcal)");
    }

    @Override
    public void onResume() {
        super.onResume();
        Main2Activity main2Activity = (Main2Activity) getActivity();
        String username = main2Activity.getLoggedUsername();
        User user = sqLiteHelper.getUserByUsername(username);
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        List<Food> list = sqLiteHelper.getAllFood(user.getId(),f.format(d));
        adapter.setList(list);
        tongKcal.setText(tong(list)+" (Kcal)");
    }

    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(getContext(), "Hehe", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        Food item = adapter.getItem(position);
        intent.putExtra("item",item);
        startActivity(intent);
    }
}
