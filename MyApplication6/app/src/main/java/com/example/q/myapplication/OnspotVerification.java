package com.example.q.myapplication;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class OnspotVerification {
    //대충 생각해보면 현장 인증에 필요한거라면
    //식당 아이디 (int), 시간 (Date)
    // )
//
    private Date TimeOfVerification;
    private int Restaurant;

    public void printtime()
    {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(""));
        Date currentLocalTime = cal.getTime();
        SimpleDateFormat date = new SimpleDateFormat("M월 d일. h시 m분 a");
        // you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+9:00"));
        String localTime = date.format(currentLocalTime);
        Log.i("wow", ""+ currentLocalTime);
    }

    public OnspotVerification(Date CurrentTime, int restaurantID){
        TimeOfVerification = CurrentTime;
        Restaurant = restaurantID;
    }
    public int getRestaurantID(){
        return Restaurant;
    }
    public Date getDate(){
        return TimeOfVerification;
    }
    public static String getRestaurantNameFromID (int ID){
        //TODO
        //implement dictionary
        return "Restaurant " + ID;
    }

}
