package com.example.q.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import static com.example.q.myapplication.OnspotVerification.getRestaurantNameFromID;

public class VerificationListAdapter extends BaseAdapter {
    int groupid;
    ArrayList<OnspotVerification> VerifsList;
    Context context;

    public VerificationListAdapter(Context context, int vg, ArrayList<OnspotVerification> VerifArrayList){
        this.context=context;
        groupid=vg;
        /*
        OnspotVerification lol = new OnspotVerification(Calendar.getInstance(TimeZone.getTimeZone("")).getTime(), 2);
        VerifsList.add(lol);
        lol = new OnspotVerification(Calendar.getInstance(TimeZone.getTimeZone("")).getTime(), 5);
        VerifsList.add(lol);*/
        VerifsList = VerifArrayList;
    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView VerifTextView, DateView;
        public int ResID;
        public Date VerifDate;
    }
    public Object getItem(int position) {
        return  VerifsList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public  int getCount() {
        return VerifsList.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder viewHolder;
        // Inflate the list_item.xml file if convertView is null
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ResID = VerifsList.get(position).getRestaurantID();
            viewHolder.VerifDate = VerifsList.get(position).getDate();
            viewHolder.VerifTextView = (TextView) rowView.findViewById(R.id.VerifText);
            viewHolder.DateView = (TextView) rowView.findViewById(R.id.VerifDateText);
            rowView.setTag(viewHolder);
        }
        // Set text to each TextView of ListView item
        else{
            viewHolder = (ViewHolder) rowView.getTag();
            viewHolder.ResID = VerifsList.get(position).getRestaurantID();
            viewHolder.VerifDate = VerifsList.get(position).getDate();
            viewHolder.DateView = (TextView) rowView.findViewById(R.id.VerifDateText);
        }

        Date currentLocalTime = viewHolder.VerifDate;
        SimpleDateFormat date = new SimpleDateFormat("h:m a, M/d");
        date.setTimeZone(TimeZone.getTimeZone("GMT+9:00"));
        String localTime = date.format(currentLocalTime);

        viewHolder.VerifTextView.setText("@" + getRestaurantNameFromID(viewHolder.ResID, context));
        viewHolder.DateView.setText(localTime);


        return rowView;
    }
}
