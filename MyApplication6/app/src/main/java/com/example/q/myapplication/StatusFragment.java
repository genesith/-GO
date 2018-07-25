package com.example.q.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class StatusFragment extends Fragment {

    boolean testmode;

    ArrayList<StatusClass> StatusFeed;



    private View viewforuse;


    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set to false VIA CODE once things are set up
        //SERVER
        testmode = true;


        // need to simulate 불러와 the onspot verifications lol
        if (testmode) {
            StatusFeed=new ArrayList<>();
            StatusClass lol = new StatusClass(1, 1, 1, 3.5f, "와우 오늘 돈까스 시켰는데 돈까스가 아닌 ㅈ까스인줄 ㄹㅇ 개노맛, 근데 알바 누나 이뻐서 3.5 드림" , Calendar.getInstance(TimeZone.getTimeZone("")).getTime());
            StatusFeed.add(lol);
            lol = new StatusClass(1, 3, 2, 4.5f, "너무 맛있어서 눈물 흘림... 근데 알바가 남성분이라 0.5 차감함", Calendar.getInstance(TimeZone.getTimeZone("")).getTime());
            StatusFeed.add(lol);
            lol = new StatusClass(2, 5, 3, 5f, "별루임", Calendar.getInstance(TimeZone.getTimeZone("")).getTime());
            StatusFeed.add(lol);
        }
        else{
            //write code about getting the list through server
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewforuse = inflater.inflate(R.layout.reviewfragment_layout, container, false);

        // Ask for permissions
        final ListView ListViewVerifications = viewforuse.findViewById(R.id.listview);


        StatusListAdapter adapter = new StatusListAdapter (getContext(), R.layout.statuslist_layout, StatusFeed);
        ListViewVerifications.setAdapter(adapter);

        TextView emptyView = new TextView(getContext());
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setText("This appears when the list is empty");
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)ListViewVerifications.getParent()).addView(emptyView);
        ListViewVerifications.setEmptyView(emptyView);

        return viewforuse;
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

/*
    public void GoToWriteActivity(OnspotVerification VerifElement){
        int REQ_CODE = 123;
        Intent myIntent = new Intent(getContext(), WriteReviewActivity.class);


        //Possibly need to modify string so that it looks good when received

        SimpleDateFormat df = new SimpleDateFormat("M월 d일. h시 m분 a");
        df.setTimeZone(TimeZone.getTimeZone("GMT+9:00"));
        String DateString = df.format(VerifElement.getDate());

        myIntent.putExtra("Date", DateString); //Optional parameters
        myIntent.putExtra("Res", getRestaurantNameFromID(VerifElement.getRestaurantID())); //Optional parameters
        startActivityForResult(myIntent, REQ_CODE);
    }
*/
}
