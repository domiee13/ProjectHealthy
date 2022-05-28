package com.example.projecthealthy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthealthy.Main2Activity;
import com.example.projecthealthy.R;
import com.example.projecthealthy.adapter.StepRecycleViewAdapter;
import com.example.projecthealthy.dal.SQLiteHelper;
import com.example.projecthealthy.model.Step;
import com.example.projecthealthy.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentStep extends Fragment {
    private RecyclerView recyclerView;
    private StepRecycleViewAdapter adapter;
    SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_step,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.stepRV);
        adapter = new StepRecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        Main2Activity main2Activity = (Main2Activity) getActivity();
        String username = main2Activity.getLoggedUsername();
        User user = db.getUserByUsername(username);
//        Toast.makeText(main2Activity, user.getFullname(), Toast.LENGTH_SHORT).show();
        List<Step> list = db.getAllSteps(user.getId(), f.format(d));
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        Main2Activity main2Activity = (Main2Activity) getActivity();
        String username = main2Activity.getLoggedUsername();
        User user = db.getUserByUsername(username);
        List<Step> list = db.getAllSteps(user.getId(), f.format(d));
        adapter.setList(list);
    }
}
