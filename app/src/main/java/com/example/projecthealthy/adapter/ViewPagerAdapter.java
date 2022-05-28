package com.example.projecthealthy.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.projecthealthy.fragment.FragmentAccount;
import com.example.projecthealthy.fragment.FragmentCal;
import com.example.projecthealthy.fragment.FragmentHome;
import com.example.projecthealthy.fragment.FragmentStep;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentStep();
            case 2:
                return new FragmentCal();
            case 3:
                return new FragmentAccount();
//            default:
//                return new FragmentHome();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
