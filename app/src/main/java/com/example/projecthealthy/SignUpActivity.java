package com.example.projecthealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.projecthealthy.dal.SQLiteHelper;
import com.example.projecthealthy.model.User;

public class SignUpActivity extends AppCompatActivity {
    private EditText user,password,fullname,age,height,weight;
    private RadioButton rdMale, rdFemale;
    private SQLiteHelper sqLiteHelper;
    private Button btSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sqLiteHelper = new SQLiteHelper(this);
        initView();
        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userFullname = fullname.getText().toString();
                String username = user.getText().toString();
                String userPassword = password.getText().toString();
                float userWeight = Float.parseFloat(weight.getText().toString());
                float userHeight = Float.parseFloat(height.getText().toString());
                int userAge = Integer.parseInt(age.getText().toString());
                int gender = 0;
                if(rdMale.isChecked()){
                    gender =0;
                }else if(rdFemale.isChecked()){
                    gender=1;
                }
                sqLiteHelper.addUser(new User(userFullname,username,userPassword,gender,userAge,userHeight,userWeight));
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }

        });
    }

    public void initView(){
        user = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        fullname = findViewById(R.id.etFullname);
        age = findViewById(R.id.etAge);
        height = findViewById(R.id.etHeight);
        weight = findViewById(R.id.etWeight);
        rdMale=findViewById(R.id.rdMale);
        rdFemale=findViewById(R.id.rdFemale);
        btSignup = findViewById(R.id.signupBtn);
    }
}