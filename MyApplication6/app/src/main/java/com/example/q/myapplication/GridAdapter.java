package com.example.q.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Bitmap> thumbsList;
    ArrayList<String> idList;
    LayoutInflater inf;

    public GridAdapter(Context context, ArrayList<Bitmap> data, ArrayList<String> idData) {
        this.context = context;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        thumbsList = data;
        idList = idData;
    }
    static class ViewHolder {
        public String id;
    }

    @Override
    public int getCount(){
        return thumbsList.size();
    }

    @Override
    public Object getItem(int position) {
        return  thumbsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView view;

        if (convertView == null){
            view = new ImageView(context);
        }else{
            view = (ImageView) convertView;
        }

        if (position < thumbsList.size()) {
            view.setPadding(2, 2, 2, 2);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageBitmap(thumbsList.get(position));
        }

        ViewHolder idHolder = new ViewHolder();
        idHolder.id = idList.get(position);
        view.setTag(idHolder);
        return view;
    }

}
