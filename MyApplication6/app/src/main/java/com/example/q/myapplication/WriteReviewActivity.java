package com.example.q.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.w3c.dom.Text;

public class WriteReviewActivity extends AppCompatActivity {

    String Date;
    String ResName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Date = getIntent().getStringExtra("Date");
        ResName = getIntent().getStringExtra("Res");
        Log.i("lets goooo", "Date is " + Date + " and ResName is " + ResName);
        setContentView(R.layout.review_layout);
        RatingBar StarBar = findViewById(R.id.ratingBar);

        TextView ResTextView = findViewById(R.id.ResTextView);
        ResTextView.setText(ResName + "에서의 식사");
        TextView DateTextView = findViewById(R.id.DateTextView);
        DateTextView.setText("현장 인증이 " + Date + "에 완료 되었습니다");

    }


}
