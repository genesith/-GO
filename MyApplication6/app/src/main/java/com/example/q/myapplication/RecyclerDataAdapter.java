package com.example.q.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerDataAdapter extends RecyclerView.Adapter<ViewHolder> {
    private int badgeList[]
            = { R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4,R.drawable.b5, R.drawable.b6,R.drawable.b7,R.drawable.b8,
            R.drawable.b9,R.drawable.b10, R.drawable.b11,R.drawable.b12,R.drawable.b13,R.drawable.b14,R.drawable.b15,R.drawable.b16,
            R.drawable.b17,R.drawable.b18, R.drawable.b19,R.drawable.b20,R.drawable.b21,R.drawable.b22,R.drawable.b23,R.drawable.b24,
            R.drawable.b25,R.drawable.b26, R.drawable.b27,R.drawable.b28,R.drawable.b29,R.drawable.b30,R.drawable.b31,R.drawable.b32,
            R.drawable.b33,R.drawable.b34, R.drawable.b35,R.drawable.b36,R.drawable.b37,R.drawable.b38,R.drawable.b39,R.drawable.b40
    };
    public RecyclerDataAdapter( ){
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.badge, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //RecyclerView의 Row 하나하나를 구현하기위해 Bind(묶이다) 될 때
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //RecyclerView에 들어갈 Data를 기반으로 Row를 생성할 때
        //해당 row의 위치에 해당하는 이미지를 가져와서
 //       if(position > 2 ){
            ImageView thumbImage = holder.badgeImage;
       /*     View profile = holder.profile;
            View horizonScroll = holder.horizonScroll;
            View myBar = holder.myBar;*/
            thumbImage.setImageResource(badgeList[position]);
            //thumbImage.setColorFilter(0xff666666);
/*            profile.setVisibility(View.GONE);
            horizonScroll.setVisibility(View.GONE);
            myBar.setVisibility(View.GONE);*/
  /*      }
        else if(position == 0){
            View profile = holder.profile;
            ImageView psa = holder.psa;
            psa.setBackground(new ShapeDrawable(new OvalShape()));
            if(Build.VERSION.SDK_INT >= 21) {
                psa.setClipToOutline(true);
            }
            ImageView thumbImage = holder.badgeImage;
            View horizonScroll = holder.horizonScroll;
            View myBar = holder.myBar;

            thumbImage.setVisibility(View.GONE);
            profile.setVisibility(View.VISIBLE);
            horizonScroll.setVisibility(View.GONE);
            myBar.setVisibility(View.GONE);
        }
        else if(position == 1){
            View profile = holder.profile;
            ImageView thumbImage = holder.badgeImage;
            View horizonScroll = holder.horizonScroll;
            View myBar = holder.myBar;

            thumbImage.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
            horizonScroll.setVisibility(View.VISIBLE);
            myBar.setVisibility(View.GONE);
        }
        else if(position == 2){
            View profile = holder.profile;
            ImageView thumbImage = holder.badgeImage;
            View horizonScroll = holder.horizonScroll;
            View myBar = holder.myBar;

            thumbImage.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
            horizonScroll.setVisibility(View.GONE);
            myBar.setVisibility(View.VISIBLE);
        }
*/
    }

    //요 메서드가 arrayListOfStudent에 들어있는 Student 개수만큼 카운트해줌
    //요녀석이 있어야 arrayListOfStudent에 넣어준 Student의 데이터를 모두 그릴수 있음
    @Override
    public int getItemCount() {
        return badgeList.length ;
    }
}

class ViewHolder extends RecyclerView.ViewHolder{

    public ImageView badgeImage;
/*    public View profile;
    public ImageView psa;
    public View horizonScroll;
    public View myBar;*/
    public ViewHolder(View itemView) {
        super(itemView);
        badgeImage = itemView.findViewById(R.id.image);
        /*profile = itemView.findViewById(R.id.profile);
        psa = itemView.findViewById(R.id.psa);
        horizonScroll = itemView.findViewById(R.id.horizonScroll);
        myBar = itemView.findViewById(R.id.myBar);*/
    }
}
