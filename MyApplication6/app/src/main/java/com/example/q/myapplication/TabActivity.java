package com.example.q.myapplication;

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
import android.view.View;
import android.widget.Toast;

public class TabActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener,Tab2.OnFragmentInteractionListener,Tab3.OnFragmentInteractionListener, Tab4.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        makeTabs();

    }
    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
    }
    public void makeTabs(){
        TabLayout tabLayout =  findViewById(R.id.tablayout);
        Resources res = getResources();
        Drawable phonebook = res.getDrawable(R.drawable.phonebook);
        Drawable galleryImage = res.getDrawable(R.drawable.gallery_image);
        Drawable calendar = res.getDrawable(R.drawable.calendar);
        tabLayout.addTab(tabLayout.newTab().setIcon(phonebook));
        tabLayout.addTab(tabLayout.newTab().setIcon(galleryImage));
        tabLayout.addTab(tabLayout.newTab().setIcon(calendar));
        tabLayout.addTab(tabLayout.newTab().setText("popular"));
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
