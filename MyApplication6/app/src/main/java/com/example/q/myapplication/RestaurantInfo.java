package com.example.q.myapplication;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.q.myapplication.BadgesGridAdapter.badgeList;
import static com.example.q.myapplication.OnspotVerification.getRestaurantNameFromID;

public class RestaurantInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        int incoming = (Integer) getIntent().getExtras().get("position");
        boolean showbadge = (boolean) getIntent().getExtras().get("caught");
        Log.i("yooo", "incoming is " + incoming + " and caught is " + showbadge);
        TextView temp = findViewById(R.id.name);
        temp.setText(getRestaurantNameFromID(incoming, getApplicationContext()));

        ImageView badgeImage = findViewById(R.id.badge);
        Glide.with(this).load(badgeList[incoming]).into(badgeImage);
        if(!showbadge)
            badgeImage.setColorFilter(0xff666666);

        temp = findViewById(R.id.intro);
        if (incoming != 0)
            temp.setText("어은동에 위치한 음식점, " + getRestaurantNameFromID(incoming, getApplicationContext()) + "입니다");

        ImageView certifImage = findViewById(R.id.certifImage);
        Glide.with(this).load(R.drawable.kaistlogo).into(certifImage);
        certifImage.setClipToOutline(true);
        certifImage.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
    }
}
