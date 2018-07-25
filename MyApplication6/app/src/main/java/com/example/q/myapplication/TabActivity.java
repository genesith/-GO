package com.example.q.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class TabActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener, Tab4.OnFragmentInteractionListener{

    public static String UserID = "갓우진";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        makeTabs();

    }
    public void ReportTextClick(View view){
        Log.i("function for reporting", "it worked");
    }
    public void DoAVerification(View view){
        Log.i("lets write", "gogogo");
        Intent myIntent = new Intent(this, VerifyingActivity.class);
        startActivity(myIntent);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
    }
    public void makeTabs(){
        TabLayout tabLayout =  findViewById(R.id.tablayout);
        Resources res = getResources();
        Drawable profile = res.getDrawable(R.drawable.profile);
        Drawable newsfeed = res.getDrawable(R.drawable.newsfeed);
        Drawable check = res.getDrawable(R.drawable.check);
        Drawable popular = res.getDrawable(R.drawable.popular);
        tabLayout.addTab(tabLayout.newTab().setIcon(profile));
        tabLayout.addTab(tabLayout.newTab().setIcon(newsfeed));
        tabLayout.addTab(tabLayout.newTab().setIcon(check));
        tabLayout.addTab(tabLayout.newTab().setIcon(popular));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
