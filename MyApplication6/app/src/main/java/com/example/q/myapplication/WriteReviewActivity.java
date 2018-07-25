package com.example.q.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.q.myapplication.OnspotVerification.getRestaurantNameFromID;

public class WriteReviewActivity extends AppCompatActivity {

    String Date;
    int ResCode;
    String photo;
    float score;
    String content;
    private static final int SELECT_PHOTO = 99;
    ImageView uploadhere;
    boolean photowasuploaded = false;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Date = getIntent().getStringExtra("Date");
        ResCode = getIntent().getIntExtra("Res", 0);
        setContentView(R.layout.review_layout);
        final RatingBar StarBar = findViewById(R.id.ratingBar);
        Button save = findViewById(R.id.SaveButton);
        Button cancel = findViewById(R.id.CancelButton);
        final EditText reviewContent = findViewById(R.id.ReviewEditText);
        uploadhere = findViewById(R.id.ReqImage);


        TextView ResTextView = findViewById(R.id.ResTextView);
        String ResName = getRestaurantNameFromID(ResCode, this);
        ResTextView.setText(ResName + "에서의 식사");
        TextView DateTextView = findViewById(R.id.DateTextView);
        DateTextView.setText("현장 인증이 " + Date + "에 완료 되었습니다");


        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View a) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                JSONArray jsonArray = new JSONArray();
                JSONObject json = new JSONObject();
                if(photowasuploaded) {
                    try {
                        json.accumulate("date", Date);
                        json.accumulate("score", StarBar.getRating());
                        json.accumulate("image", photo);
                        json.accumulate("r_code", ResCode);
                        json.accumulate("writer_id", TabActivity.UserID);
                        json.accumulate("content", reviewContent.getText().toString());
                        json.accumulate("writer_name", "test");
                        json.accumulate("like", 0);
                        jsonArray.put(json);
                        Log.d("..", jsonArray.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NetworkTask networkTask = new NetworkTask("api/statuses", "post", null, jsonArray);
                    networkTask.execute();
                    Toast.makeText(WriteReviewActivity.this, "리뷰가 등록되었습니다.", Toast.LENGTH_LONG);

                    JSONArray deleteOnspotArray = new JSONArray();
                    JSONObject deleteOnspot = new JSONObject();
                    try {
                        deleteOnspot.accumulate("date", Date);
                        deleteOnspot.accumulate("r_id", ResCode);
                        deleteOnspot.accumulate("user_id", TabActivity.UserID);
                        deleteOnspotArray.put(deleteOnspot);
                        Log.d("..", jsonArray.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NetworkTask deleteOnspotTask = new NetworkTask("api/onspot", "delete", null, deleteOnspotArray);
                    deleteOnspotTask.execute();
                    finish();
                }
                else
                    Toast.makeText(WriteReviewActivity.this, "No photo selected", Toast.LENGTH_SHORT);
            }
        });


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

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    yourSelectedImage.compress(Bitmap.CompressFormat.JPEG,100, baos);
                    byte[] b = baos.toByteArray();
                    String encodeImg = Base64.encodeToString(b,Base64.DEFAULT);
                    photo = encodeImg;
                    photowasuploaded =true;
                }
        }
    }

}
