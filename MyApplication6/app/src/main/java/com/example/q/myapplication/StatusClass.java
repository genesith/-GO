package com.example.q.myapplication;

import java.util.Date;

public class StatusClass {
    float Stars;
    //image might be in form of URL? int code?
    int ImageID;
    int ResID;
    String StatusContent;
    int UserID;
    Date Date;

    public StatusClass(int UserID_, int ResID_, int ImageID_, float Stars_, String StatusContent_, Date Date_){
        UserID= UserID_;
        Stars = Stars_;
        ImageID = ImageID_;
        ResID = ResID_;
        StatusContent = StatusContent_;
        Date = Date_;
    }
    public static String getUserByUserID(int ID) {
        //TODO
        switch (ID) {
            case 1:
                return "genesith";
            case 2:
                return "YuHanGyeol";
            case 3:
                return "ByunggyuCh";
            default:
                return "Restaurant " + ID;
        }
    }

}
