package com.example.q.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.AccessController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import static com.example.q.myapplication.MainActivity.mDbOpenHelper;


import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.security.AccessController.getContext;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;


    public Tab3() {
        // Required empty public constructor
    }

    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

        ImageView imageView = view.findViewById(R.id.bubbleStitch);
        int resourceID = R.drawable.stitch_pop;
        Glide.with(this).load(resourceID).into(imageView);


        mCalendarView =  view.findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int pseudomonth, final int day) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                final int month = pseudomonth + 1;
                EditText userInput = getView().findViewById(R.id.editText);

                Button deleteButton = getView().findViewById(R.id.deleteButton);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        calendarDelete(month,day);
                    }
                });

                Button displayInput = getView().findViewById(R.id.uploadButton);
                displayInput.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        calendarDelete(month,day);
                        EditText userInput = getView().findViewById(R.id.editText);
                        String event = userInput.getText().toString();
                        //imm.hideSoftInputFromWindow(userInput.getWindowToken(), 0);
                        userInput.setText(null);
                        userInput.setHint("Enter");
                        JSONArray jsonarray = new JSONArray();
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.accumulate("month", month);
                            jsonObject.accumulate("day", day);
                            jsonObject.accumulate("content", event);
                            jsonarray.put(jsonObject);
                        } catch (Exception e) {
                            Log.d("exception", e.getMessage());
                        }
                        Log.d("1", ""+ jsonarray.length());
                        NetworkTask networkTask = new NetworkTask("api/calendars", "post", null, jsonarray);
                        networkTask.execute();


                    }
                });
                userInput.setText(calendarDownload(month,day));
            }
        });

        return view;
    }


    public String calendarDownload(int targetMonth, int targetDay){
        NetworkTask getcalendars = new NetworkTask("api/getAllCalendars", "get", null, null);
        getcalendars.execute();
        try{
            String s=  getcalendars.get();
            Log.d("getcalendar:", s);
            JSONArray calendarList = new JSONArray(s);
            Log.d(" length is :  ",calendarList.length()+"");
            for(int i=0; i<calendarList.length(); i++){
                JSONObject json = (JSONObject) calendarList.get(i);
                int month = json.getInt("month");
                int day = json.getInt("day");

                String content = json.getString("content");
                if(month == targetMonth && day == targetDay)
                    return content;

            }
        }catch (Exception e){
        }
        return "";
    }
    public void calendarDelete(int targetMonth ,int targetDay){
        JSONArray jsonarray = new JSONArray();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("month", targetMonth);
            jsonObject.accumulate("day", targetDay);
            jsonarray.put(jsonObject);
        } catch (Exception e) {
            Log.d("exception", e.getMessage());
        }
        Log.d("1", "" + jsonarray.length());
        NetworkTask networkTask = new NetworkTask("api/calendars", "delete", null, jsonarray);
        networkTask.execute();
    }

    // TODO: Rename method, update argument and hook method into UI event
 /*   public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}