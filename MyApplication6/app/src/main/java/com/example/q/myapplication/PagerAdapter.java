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
                ReviewFragment tab1 = new ReviewFragment();
                return tab1;
            case 1:
                StatusFragment tab2 = new StatusFragment();
                return tab2;
            case 2:
                Tab3 tab3 = new Tab3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
