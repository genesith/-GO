package com.example.q.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberofTabs){
        super(fm);
        this.mNoOfTabs = NumberofTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                StatusFragment s = new StatusFragment();
                return s;
            case 2:
                return new ReviewFragment();
            case 3:
                Tab4 tab4 = new Tab4();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
