package com.example.projecthealthy.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecthealthy.R;

public class FragmentCal extends Fragment {
    private EditText eWeight, eHeight, eAge, eGender;
    private Spinner sp;
    private Button btCal;
    private TextView tvRes;
    private RadioButton rdMale, rdFemale;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cal,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Init views
        eWeight = view.findViewById(R.id.etWeight);
        eHeight = view.findViewById(R.id.etHeight);
        eAge = view.findViewById(R.id.etAge);
        rdMale = view.findViewById(R.id.rdMale);
        rdFemale = view.findViewById(R.id.rdFemale);
        sp = view.findViewById(R.id.sp);
        tvRes = view.findViewById(R.id.tvRes);
        btCal = view.findViewById(R.id.btCal);
        btCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float result=0;
                float weight = Float.parseFloat(eWeight.getText().toString());
                float height = Float.parseFloat(eHeight.getText().toString());
                int age = Integer.parseInt(eAge.getText().toString());
                if(rdMale.isChecked()){
                    float tile = 1;
                    int n = sp.getSelectedItemPosition();
                    switch (n){
                        case 0:
                            tile = (float)1.2;
                            break;
                        case 1:
                            tile = (float)1.3;
                            break;
                        case 2:
                            tile = (float)1.4;
                            break;
                        default:
                            tile = (float)1.2;
                    }
                    result = (float) ((66.5 + 13.8*weight + 5*height) - (6.8*age));
                    result = result*tile;
                }else if(rdFemale.isChecked()){
                    float tile = 1;
                    int n = sp.getSelectedItemPosition();
                    switch (n){
                        case 0:
                            tile = (float)1.2;
                            break;
                        case 1:
                            tile = (float)1.3;
                            break;
                        case 2:
                            tile = (float)1.4;
                            break;
//                        default:
//                            tile = (float)1.2;
                    }
                    result = (float) ((655.1 + 9.6*weight + 1.9*height) - (4.7*age));
                    result = result*tile;
                }
                tvRes.setText("Lượng Kcal: "+result+
                        "\nCần nạp vào: "+(result-10) + " để giảm cân\n" +
                        "Cần nạp vào: "+ (result+10) + " để tăng cân.");
            }
        });
    }


}
