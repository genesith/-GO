package com.example.q.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import static com.example.q.myapplication.OnspotVerification.getRestaurantNameFromID;


/*

Okay
There should be lists of 현인s pending approval.
For 현인s, request an optional receipt picture for further 인증
These should be above a profile-esque list of past posts.
(필수) attach picture(s) of food
(optional) attach picture of receipt
(optional) rating and review
*/


public class ReviewFragment extends Fragment {

    boolean testmode;


    ArrayList<OnspotVerification> UnpushedCommits;



    private View viewforuse;


    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set this false VIA CODE once things are set up
        testmode = true;


        // need to simulate 불러와 the onspot verifications lol
        if (testmode) {
            UnpushedCommits = new ArrayList<>();
            OnspotVerification lol = new OnspotVerification(Calendar.getInstance(TimeZone.getTimeZone("")).getTime(), 2);
            UnpushedCommits.add(lol);
            lol = new OnspotVerification(Calendar.getInstance(TimeZone.getTimeZone("")).getTime(), 5);
            UnpushedCommits.add(lol);
        }
        else{
            //SERVER
            //write code about getting the list through server
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        viewforuse = inflater.inflate(R.layout.reviewfragment_layout, container, false);

        // Ask for permissions
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
            return viewforuse;

        final ListView ListViewVerifications = viewforuse.findViewById(R.id.listview);
        ListViewVerifications.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CommitThisVerification(UnpushedCommits.get(position));
            }
        });

        VerificationListAdapter adapter = new VerificationListAdapter(getContext(), R.layout.list_item, UnpushedCommits);

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

    private void CommitThisVerification(OnspotVerification Verif){
        Log.i("ang", "Gimotti!!, this is a Verif of ResID: " + Verif.getRestaurantID() + " and Date of " + Verif.getDate().toString());
        GoToWriteActivity(Verif);
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



    public void GoToWriteActivity(OnspotVerification VerifElement){
        int REQ_CODE = 123;
        Intent myIntent = new Intent(getContext(), WriteReviewActivity.class);

        //Possibly need to modify string so that it looks good when received

        SimpleDateFormat df = new SimpleDateFormat("M월 d일. h시 m분 a");
        df.setTimeZone(TimeZone.getTimeZone("GMT+9:00"));
        String DateString = df.format(VerifElement.getDate());

        myIntent.putExtra("Date", DateString); //Optional parameters
        myIntent.putExtra("Res", getRestaurantNameFromID(VerifElement.getRestaurantID(), getContext())); //Optional parameters
        startActivityForResult(myIntent, REQ_CODE);
    }
}
