package com.example.projecthealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projecthealthy.dal.SQLiteHelper;
import com.example.projecthealthy.model.Food;
import com.example.projecthealthy.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    private Button btAdd, btCancel;
    private EditText name, kcal;
    private SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get user id
                String username = getIntent().getStringExtra("username");
                String foodName = name.getText().toString();
                float foodKcal = Float.parseFloat(kcal.getText().toString());
                Date d = new Date();
                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                sqLiteHelper = new SQLiteHelper(getApplicationContext());
                User user = sqLiteHelper.getUserByUsername(username);
                sqLiteHelper.addFood(new Food(user.getId(),foodKcal,foodName,f.format(d)));
                finish();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void initView(){
        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
        name = findViewById(R.id.etName);
        kcal = findViewById(R.id.etKcal);
    }
}