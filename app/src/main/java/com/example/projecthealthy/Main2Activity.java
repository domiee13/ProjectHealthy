package com.example.projecthealthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projecthealthy.adapter.ViewPagerAdapter;
import com.example.projecthealthy.dal.SQLiteHelper;
import com.example.projecthealthy.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main2Activity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sqLiteHelper = new SQLiteHelper(this);
//        Toast.makeText(this, username+" Hehe", Toast.LENGTH_SHORT).show();
        navigationView=findViewById(R.id.bottom_nav);
        viewPager=findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.mKcal).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.mStep).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.mCal).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.mAccount).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mKcal:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mStep:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mCal:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.mAccount:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }
    public User getLoggedUser(){
        //get logged user info from Login Activity
        String username = getIntent().getStringExtra("username");
        User loggedUser = sqLiteHelper.getUserByUsername(username);
        return loggedUser;
    }

    public String getLoggedUsername(){
        return getIntent().getStringExtra("username");
    }
}