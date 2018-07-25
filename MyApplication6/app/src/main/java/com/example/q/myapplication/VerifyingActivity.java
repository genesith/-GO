package com.example.q.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.example.q.myapplication.OnspotVerification.getRestaurantNameFromID;

public class VerifyingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int ResID;
    String UserID;
    TextView tv;
    ToggleButton tb;

    Button submitButton, PicCompare;
    double longitude = 1;
    double latitude = 1;
    double cutoff = 0.0007;
    double Res_longitude;
    double Res_latitude;





    private boolean[] verified = {false, false};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verificationlayout);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerboi);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.restaurant_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //for ACTUAL app, this should come from known data depending on restaurant, but this is now set to Kaist N1
        Res_longitude = 127.366038;
        Res_latitude = 36.374170;

        tv = (TextView) findViewById(R.id.textView2);
        tv.setText("위치정보 미수신중");
        tb = (ToggleButton)findViewById(R.id.toggle1);

        // LocationManager 객체를 얻어온다
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(tb.isChecked()){
                        tv.setText("수신중..");
                        // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
                        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);
                        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);
                    }else{

                        tv.setText("위치정보 미수신중");
                        lm.removeUpdates(mLocationListener);  //  미수신할때는 반드시 자원해체를 해주어야 한다.

                        Log.i("Long and Lat", longitude + " " +latitude);
                        if (longitude<cutoff && longitude > - cutoff && latitude<cutoff && latitude > - cutoff){
                            tv.setText("인증 완료!!");
                            verified[0] = true;
                        }
                        else{
                            tv.setText("인증실패! 다시 시도 해주세요");
                            verified[0] = false;
                        }

                    }
                }catch(SecurityException ex){
                }
            }
        });
        UserID = TabActivity.UserID;
        submitButton=findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (verified[0] == true && verified[1]== true){

                    JSONArray jsonList = new JSONArray();
                    try {
                        JSONObject json = new JSONObject();

                        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(""));
                        Date currentLocalTime = cal.getTime();
                        SimpleDateFormat date = new SimpleDateFormat("h:m a, M/d");
                        // you can get seconds by adding  "...:ss" to it
                        date.setTimeZone(TimeZone.getTimeZone("GMT+9:00"));
                        String localTime = date.format(currentLocalTime);
                        Log.i("wow", ""+ currentLocalTime);

                        json.accumulate("date", localTime);
                        json.accumulate("user_id", UserID);
                        json.accumulate("r_id", ResID);
                        jsonList.put(json);
                    }
                    catch (Exception e){

                    }
                    NetworkTask postOnspot= new NetworkTask("api/onspot","post", null, jsonList);
                    postOnspot.execute();

                    Toast.makeText( VerifyingActivity.this, "현장 인증 성공!", Toast.LENGTH_LONG).show();
                    Toast.makeText( VerifyingActivity.this, "맛있게 식사하신 후 리뷰를 작성해주세요" + verified[0] + " " + verified[1], Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText( VerifyingActivity.this, "인증이 완료되지 않아 제출할 수 없습니다 ", Toast.LENGTH_LONG).show();
                }
            }
        });

        PicCompare=findViewById(R.id.ComparePicButton);
        PicCompare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int REQ_CODE = 54;
                Intent CompareIntent = new Intent (VerifyingActivity.this, opencvActivity.class);
                CompareIntent.putExtra("ResName", getRestaurantNameFromID(ResID, getApplicationContext()));
                startActivityForResult(CompareIntent, REQ_CODE);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        if (data!=null) {
            verified[1] = data.getExtras().getBoolean("tf");
        }
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Log.i("spinner", "SELECTED POS" + pos);
        ResID = pos;
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        Log.i("spinner", "NOTHING SELECTED");
    }

    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

            Log.d("test", "onLocationChanged, location:" + location);
            longitude = location.getLongitude(); //경도
            latitude = location.getLatitude();   //위도
            //String provider = location.getProvider();   //위치제공자
            //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
            //Network 위치제공자에 의한 위치변화
            //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.


            //tv.setText("위치정보 : " + provider + "\n위도 : " + latitude + "\n경도 : " + longitude + "\n고도 : " + altitude + "\n정확도 : "  + accuracy);
            longitude -= Res_longitude;
            latitude -= Res_latitude;

            if (longitude<cutoff && longitude > - cutoff && latitude<cutoff && latitude > - cutoff){
                tv.setText("인증 완료!!");
                verified[0] = true;
            }
            else {
                tv.setText("인증이 실패했습니다");
                verified[0] = false;
            }


        }
        public void onProviderDisabled(String provider) {
            // Disabled시
            Log.d("test", "onProviderDisabled, provider:" + provider);
        }

        public void onProviderEnabled(String provider) {
            // Enabled시
            Log.d("test", "onProviderEnabled, provider:" + provider);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // 변경시
            Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
        }
    };
}
