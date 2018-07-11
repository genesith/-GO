package com.example.q.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Tab1ListAdapter extends BaseAdapter {
    int groupid;
    ArrayList<String> nameList;
    ArrayList<String> phoneNumList;
    Context context;

    public Tab1ListAdapter(Context context, int vg, ArrayList<String> nameList, ArrayList<String> phoneNumList){
        this.context=context;
        groupid=vg;
        this.nameList = nameList;
        this.phoneNumList= phoneNumList;
    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView nameView;
        public TextView phoneNumView;
        public String name;
        public String phoneNum;
    }
    public Object getItem(int position) {
        return  nameList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public  int getCount() {
        return nameList.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = convertView;
            // Inflate the list_item.xml file if convertView is null
            if(rowView==null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView= inflater.inflate(groupid, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.name = nameList.get(position);
                viewHolder.phoneNum = phoneNumList.get(position);
                viewHolder.nameView = (TextView) rowView.findViewById(R.id.name);
                viewHolder.phoneNumView = (TextView) rowView.findViewById(R.id.phoneNum);
                rowView.setTag(viewHolder);
            }
            // Set text to each TextView of ListView item
            ViewHolder viewHolder = (ViewHolder) rowView.getTag();
            viewHolder.name = nameList.get(position);
            viewHolder.phoneNum = phoneNumList.get(position);
            viewHolder.nameView.setText(viewHolder.name);
            viewHolder.phoneNumView.setText(viewHolder.phoneNum);
            return rowView;
    }
}
