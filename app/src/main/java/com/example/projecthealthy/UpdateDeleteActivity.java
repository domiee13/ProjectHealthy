package com.example.projecthealthy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projecthealthy.dal.SQLiteHelper;
import com.example.projecthealthy.model.Food;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private Food item;
    private Button btUpdate, btRemove, btCancel;
    private EditText etName, etKcal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        Intent intent = getIntent();
        item = (Food) intent.getSerializableExtra("item");
        etName.setText(item.getName());
        etKcal.setText(item.getKcal()+"");
        btUpdate.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    public void initView(){
        btUpdate  = findViewById(R.id.btUpdate);
        btRemove  = findViewById(R.id.btRemove);
        btCancel  = findViewById(R.id.btCancel);
        etName = findViewById(R.id.etName);
        etKcal = findViewById(R.id.etKcal);
    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(view == btCancel){
            finish();
        }
        else if(view == btUpdate){
            String name = etName.getText().toString();
            float kcal = Float.parseFloat(etKcal.getText().toString());
            db.updateFoodByName(name,kcal);
            finish();
        }
        else if(view == btRemove){
            int id = item.getId(); //Get id luon return 0 ????
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có chắc chắn muốn xóa "+item.getName()+"?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                    db.deleteFoodByName(item.getName());
                    finish();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            Toast.makeText(this, item.getId()+"", Toast.LENGTH_SHORT).show(); //Return 0
        }
    }
}