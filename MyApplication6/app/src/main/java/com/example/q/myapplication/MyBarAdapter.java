package com.example.q.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyBarAdapter extends RecyclerView.Adapter<com.example.q.myapplication.MyBarHolder> {
        private String menuList[]
                = { "도감", "최근 활동", "친구", "나의 뱃지", "나의 칭호", "자기소개"};
        public MyBarAdapter( ){
        }

        @Override
        public com.example.q.myapplication.MyBarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext() ;
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.my_bar_text, parent, false);
            com.example.q.myapplication.MyBarHolder viewHolder = new com.example.q.myapplication.MyBarHolder(view);
            return viewHolder;
        }

        //RecyclerView의 Row 하나하나를 구현하기위해 Bind(묶이다) 될 때
        @Override
        public void onBindViewHolder(com.example.q.myapplication.MyBarHolder holder, int position) {

            //RecyclerView에 들어갈 Data를 기반으로 Row를 생성할 때
            //해당 row의 위치에 해당하는 이미지를 가져와서
            //       if(position > 2 ){
            TextView menu = holder.menu;
            menu.setText(menuList[position]);
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
            return menuList.length;
        }
    }

class MyBarHolder extends RecyclerView.ViewHolder{

    public TextView menu;
    /*    public View profile;
        public ImageView psa;
        public View horizonScroll;
        public View myBar;*/
    public MyBarHolder(View itemView) {
        super(itemView);
        menu = itemView.findViewById(R.id.menu);
    /*profile = itemView.findViewById(R.id.profile);
    psa = itemView.findViewById(R.id.psa);
    horizonScroll = itemView.findViewById(R.id.horizonScroll);
    myBar = itemView.findViewById(R.id.myBar);*/
    }
}

