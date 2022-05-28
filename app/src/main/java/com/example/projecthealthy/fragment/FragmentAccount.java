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

import com.example.projecthealthy.Main2Activity;
import com.example.projecthealthy.MainActivity;
import com.example.projecthealthy.R;
import com.example.projecthealthy.dal.SQLiteHelper;
import com.example.projecthealthy.model.User;

public class FragmentAccount extends Fragment {
    private Button logoutBtn;
    private TextView tvName, tvUsername, tvAge, tvWeight, tvHeight, tvGender;
    private SQLiteHelper sqLiteHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        tvName = view.findViewById(R.id.tvFullName);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvAge = view.findViewById(R.id.tvAge);
        tvWeight = view.findViewById(R.id.tvWeight);
        tvHeight = view.findViewById(R.id.tvHeight);
        tvGender = view.findViewById(R.id.tvGender);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Logout", Toast.LENGTH_SHORT).show();
                //Logout, return to login screen
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        Main2Activity main2Activity = (Main2Activity) getActivity();
        String username = main2Activity.getLoggedUsername();
        Toast.makeText(main2Activity, username, Toast.LENGTH_SHORT).show();
        sqLiteHelper = new SQLiteHelper(getContext());
        User loggedInUser = sqLiteHelper.getUserByUsername(username);
        tvName.setText(loggedInUser.getFullname());
        tvUsername.setText("Username: "+loggedInUser.getUsername());
        tvAge.setText("Tuổi: "+loggedInUser.getAge());
        tvWeight.setText("Cân nặng: "+loggedInUser.getWeight());
        tvHeight.setText("Chiều cao: "+loggedInUser.getHeight());
        if(loggedInUser.getGender() == 0){
            tvGender.setText("Nam");
        }else if(loggedInUser.getGender() == 1){
            tvGender.setText("Nữ");
        }

    }
}
