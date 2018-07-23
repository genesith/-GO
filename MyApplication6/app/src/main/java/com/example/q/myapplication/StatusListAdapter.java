package com.example.q.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.q.myapplication.OnspotVerification.getRestaurantNameFromID;
import static com.example.q.myapplication.StatusClass.getUserByUserID;

public class StatusListAdapter extends BaseAdapter {
    int groupid;
    ArrayList<StatusClass> StatusList;
    Context context;

    public StatusListAdapter(Context context, int vg, ArrayList<StatusClass> StatusFeed){
        this.context=context;
        groupid=vg;
        StatusList = StatusFeed;
    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public ImageView TheImage, Heart;
        public TextView ResText, StarText, StatusText;
    }
    public Object getItem(int position) {
        return  StatusList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public  int getCount() {
        return StatusList.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder viewHolder;
        // Inflate the list_item.xml file if convertView is null
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.TheImage= (ImageView) rowView.findViewById(R.id.imageView);
            viewHolder.ResText= (TextView) rowView.findViewById(R.id.ResText);
            viewHolder.StarText= (TextView) rowView.findViewById(R.id.StarsText);
            viewHolder.StatusText= (TextView) rowView.findViewById(R.id.StatusText);

            rowView.setTag(viewHolder);
        }
        // Set text to each TextView of ListView item
        else{
            viewHolder = (ViewHolder) rowView.getTag();
        }
        StatusClass temp = StatusList.get(position);
        viewHolder.ResText.setText( getRestaurantNameFromID(temp.ResID) + "에서의 식사 by " + getUserByUserID(temp.UserID));
        viewHolder.StarText.setText(String.valueOf(temp.Stars));
        viewHolder.StatusText.setText(temp.StatusContent);


        return rowView;
    }
}
