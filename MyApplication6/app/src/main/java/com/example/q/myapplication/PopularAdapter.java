package com.example.q.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularViewHolder>{

    ArrayList<String> thumbsList;

    //private final View.OnClickListener mOnClickListener = new View.OnClickListener();

    //Adapter 초기화 및 생성자로 받은 데이터기반으로 Adapter 내 데이터 세팅
    public PopularAdapter( ArrayList<String> data){
        thumbsList = data;
    }

    //ViewHolder가 초기화 될 때 혹은 ViewHolder를 초기화 할 때 실행되는 메서드
    @Override
    public PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.status_staggered_layout, parent, false);
        //view.setOnClickListener(mOnClickListener);
        PopularViewHolder viewHolder = new PopularViewHolder(view);
        return viewHolder;
    }
    private ItemClick itemClick;
    public interface ItemClick {
        public void onClick(View view,int position);
    }

    //아이템 클릭시 실행 함수 등록 함수
    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }
    @Override
    public void onBindViewHolder(PopularViewHolder holder, int position) {
        final int Position = position;
        Glide.with(holder.img.getContext()).load(thumbsList.get(position)).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClick != null){
                    itemClick.onClick(v, Position);
                }
            }
        });
        //holder.img.setImageBitmap(thumbsList.get(position));
               // holder.img.setImageBitmap(Bitmap.createScaledBitmap(thumbsList.get(position),500,500, true));
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
        itemView.findViewById(R.id.round_linear).setClipToOutline(true);
        itemView.findViewById(R.id.psa).setClipToOutline(true);
        img = itemView.findViewById(R.id.thumbnail);
    }
}


