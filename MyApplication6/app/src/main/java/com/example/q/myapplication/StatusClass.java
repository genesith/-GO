package com.example.q.myapplication;

import java.util.Date;

public class StatusClass {
    float Stars;
    //image might be in form of URL? int code?
    int ImageID;
    int ResID;
    String StatusContent;
    int StatusID;
    int UserID;
    Date Date;
    int LikeNumber = 1;
    boolean liked = false;

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

    public static String getImageForLoad(int ID){
        switch (ID) {
            case 1:
                return "http://www.ohfun.net/contents/article/images/2015/0607/1433677499050108.jpg";
            case 2:
                return "http://mpmedia001.video.nate.com/img/006/DA/00/01/B_20071123211100310652251006.jpg";
            case 3:
                return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBy0N1MvmP5fHprvQAshi-n_59Px5rwj8PIAoOd1cCoVL2vDES";
            default:
                return "https://pbs.twimg.com/profile_images/960923973755129856/Tai7Pdqh_200x200.jpg";
        }
    }

}
