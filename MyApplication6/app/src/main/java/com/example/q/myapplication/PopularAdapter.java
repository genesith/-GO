package com.example.q.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularViewHolder>{

    ArrayList<Bitmap> thumbsList;

    //Adapter 초기화 및 생성자로 받은 데이터기반으로 Adapter 내 데이터 세팅
    public PopularAdapter( ArrayList<Bitmap> data){
        thumbsList = data;
    }

    //ViewHolder가 초기화 될 때 혹은 ViewHolder를 초기화 할 때 실행되는 메서드
    @Override
    public PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.popular_items, parent, false);
        PopularViewHolder viewHolder = new PopularViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PopularViewHolder holder, int position) {
                holder.img.setImageBitmap(Bitmap.createScaledBitmap(thumbsList.get(position),360,360, true));
    }

    //요 메서드가 arrayListOfStudent에 들어있는 Student 개수만큼 카운트해줌
    //요녀석이 있어야 arrayListOfStudent에 넣어준 Student의 데이터를 모두 그릴수 있음
    @Override
    public int getItemCount() {
        return thumbsList.size();
    }
}
class PopularViewHolder extends RecyclerView.ViewHolder{

    //public ImageView big;
    public ImageView img;

    public PopularViewHolder(View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
    }
}


