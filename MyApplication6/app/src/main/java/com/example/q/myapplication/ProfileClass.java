package com.example.q.myapplication;

import android.util.Log;

public class ProfileClass {
    String ProfileImage, Username;
    int completion, followernum, followingnum, XP, Chingho;
    boolean[] badgelist;

    public ProfileClass(){
    }

    public int LevelFromXP(){
        return XP/800;
    }

    public String Chinghofromint(){
        switch(Chingho){
            case 1:
                return "Tonkatsu Serial Killer";
            default:
                return "아직 칭호가 없습니다";
        }
    }
    public void MakeBadgeList(String ParseMe){
        if (ParseMe.length() == 10){
            int temp = 0, count = 0;
            boolean[] array = new boolean[40];
            for (char ch:ParseMe.toCharArray()){
                switch (ch){
                    case '0':
                        array[temp] = false;
                        array[temp+1] = false;
                        array[temp+2] = false;
                        array[temp+3] = false;
                        temp +=4;
                        count += 1;
                        break;
                    case '1':
                        array[temp] = false;
                        array[temp+1] = false;
                        array[temp+2] = false;
                        array[temp+3] = true;
                        temp +=4;
                        count += 1;
                        break;
                    case '2':
                        array[temp] = false;
                        array[temp+1] = false;
                        array[temp+2] = true;
                        array[temp+3] = false;
                        temp +=4;
                        count += 1;
                        break;
                    case '3':
                        array[temp] = false;
                        array[temp+1] = false;
                        array[temp+2] = true;
                        array[temp+3] = true;
                        temp +=4;
                        count += 2;
                        break;
                    case '4':
                        array[temp] = false;
                        array[temp+1] = true;
                        array[temp+2] = false;
                        array[temp+3] = false;
                        temp +=4;
                        count += 1;
                        break;
                    case '5':
                        array[temp] = false;
                        array[temp+1] = true;
                        array[temp+2] = false;
                        array[temp+3] = true;
                        temp +=4;
                        count += 2;
                        break;
                    case '6':
                        array[temp] = false;
                        array[temp+1] = true;
                        array[temp+2] = true;
                        array[temp+3] = false;
                        temp +=4;
                        count += 2;
                        break;
                    case '7':
                        array[temp] = false;
                        array[temp+1] = true;
                        array[temp+2] = true;
                        array[temp+3] = true;
                        temp +=4;
                        count += 3;
                        break;
                    case '8':
                        array[temp] = true;
                        array[temp+1] = false;
                        array[temp+2] = false;
                        array[temp+3] = false;
                        temp +=4;
                        count += 1;
                        break;
                    case '9':
                        array[temp] = true;
                        array[temp+1] = false;
                        array[temp+2] = false;
                        array[temp+3] = true;
                        temp +=4;
                        count += 2;
                        break;
                    case 'A':
                        array[temp] = true;
                        array[temp+1] = false;
                        array[temp+2] = true;
                        array[temp+3] = false;
                        temp +=4;
                        count += 2;
                        break;
                    case 'B':
                        array[temp] = true;
                        array[temp+1] = false;
                        array[temp+2] = true;
                        array[temp+3] = true;
                        temp +=4;
                        count += 3;
                        break;
                    case 'C':
                        array[temp] = true;
                        array[temp+1] = true;
                        array[temp+2] = false;
                        array[temp+3] = false;
                        temp +=4;
                        count += 2;
                        break;
                    case 'D':
                        array[temp] = true;
                        array[temp+1] = true;
                        array[temp+2] = false;
                        array[temp+3] = true;
                        temp +=4;
                        count += 3;
                        break;
                    case 'E':
                        array[temp] = true;
                        array[temp+1] = true;
                        array[temp+2] = true;
                        array[temp+3] = false;
                        temp +=4;
                        count += 3;
                        break;
                    case 'F':
                        array[temp] = true;
                        array[temp+1] = true;
                        array[temp+2] = true;
                        array[temp+3] = true;
                        temp +=4;
                        count += 4;
                        break;
                }
            }
            completion = count;
            badgelist = array;
        }
        else
        {
            Log.i("MakeBadgeList", "cannot do it, length is " + ParseMe.length());
        }
    }
}
