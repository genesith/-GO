package com.example.q.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class WriteReviewActivity extends AppCompatActivity {

    String Date;
    String ResName;
    private static final int SELECT_PHOTO = 99;
    ImageView uploadhere;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Date = getIntent().getStringExtra("Date");
        ResName = getIntent().getStringExtra("Res");
        setContentView(R.layout.review_layout);
        RatingBar StarBar = findViewById(R.id.ratingBar);
        uploadhere = findViewById(R.id.ReqImage);
        TextView ResTextView = findViewById(R.id.ResTextView);
        ResTextView.setText(ResName + "에서의 식사");
        TextView DateTextView = findViewById(R.id.DateTextView);
        DateTextView.setText("현장 인증이 " + Date + "에 완료 되었습니다");

        uploadhere.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        InputStream imageStream = null;
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                    uploadhere.setImageBitmap(yourSelectedImage);
                }
        }
    }

}
