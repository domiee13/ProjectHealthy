package com.example.projecthealthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projecthealthy.adapter.ViewPagerAdapter;
import com.example.projecthealthy.dal.SQLiteHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn, signupBtn;
    private EditText eUser, ePassword;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        db = new SQLiteHelper(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"User: "+eUser.getText()+" | Password: "+ePassword.getText(), Toast.LENGTH_SHORT).show();
                String username = eUser.getText().toString();
                String password = ePassword.getText().toString();
//                if(checkAuth(username,password)){
//                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(intent);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
//                }
                if(db.checkAuth(username, password)){
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initView(){
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);
        eUser = findViewById(R.id.etUsername);
        ePassword = findViewById(R.id.etPassword);
    }

//    private boolean checkAuth(String username, String password){
//        //Fixed credentials
//        if(username.equals("domiee13") && password.equals("Domiee@13")){
//            return true;
//        }
//        return false;
//    }

}