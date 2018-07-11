package com.example.q.myapplication;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.q.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Imageupg extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageupg);

        Intent i = getIntent();
        final String id = i.getExtras().getString("imageId");

        Button closebutton = (Button) findViewById(R.id.btnClose);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Button deleteButton = (Button) findViewById(R.id.btnDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.accumulate("_id", id);
                    jsonArray.put(jsonObject);
                }catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
                NetworkTask networkTask = new NetworkTask("api/images", "delete", null, jsonArray);
                networkTask.execute();
            }
        });


        ArrayList<String> pathList = getPathList(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] {MediaStore.Images.Media.DATA});
        final ImageSlideAdapter imageSlideAdapter = new ImageSlideAdapter(this, pathList );
        final ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(imageSlideAdapter);
        int position = i.getExtras().getInt("index");
        pager.setCurrentItem(position);



    }

    public ArrayList<String> getPathList( Uri uri, String[] projection){
        ArrayList<String> pathList = new ArrayList<>();
        Cursor cursor = this.getContentResolver().query(uri, projection, null, null, null);
        if(cursor.moveToFirst()){
            do{
                pathList.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        return pathList;
    }
}