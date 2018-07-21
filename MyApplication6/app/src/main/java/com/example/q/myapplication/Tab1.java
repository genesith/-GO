package com.example.q.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mongodb.lang.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.example.q.myapplication.MainActivity.mDbOpenHelper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance(String param1, String param2) {
        Tab1 fragment = new Tab1();
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
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);


        Button uploadButton =  view.findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

                Log.d("dd","downloadButtonclicked");
                View wholeview = getView();
                ListView l1 = wholeview.findViewById(R.id.listview);
                ListUpdate(l1);
                ContactUpload();
            }
        });

        ImageView imageView = view.findViewById(R.id.downloadImage);
        int resourceID = R.drawable.stitch_phone;
        Glide.with(this).load(resourceID).into(imageView);

        Button downloadButton =  view.findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Log.d("dd","downloadButtonclicked");
                View wholeview = getView();
                ListView l1 = wholeview.findViewById(R.id.listview);
                ContactDownload(l1);
            }
        });

        final ListView l1 = view.findViewById(R.id.listview);
        l1.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i("ang", "Gimotti!!");
                TextView lul = (TextView)view.findViewById(R.id.VerifText);
                Log.i("ang it says", "" +lul.getText());
            }
        });
        TextView emptyView = new TextView(getContext());
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setText("This appears when the list is empty");
        emptyView.setVisibility(View.GONE);
        ((ViewGroup)l1.getParent()).addView(emptyView);
        l1.setEmptyView(emptyView);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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

    public View ListUpdate(ListView l1){
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,projection,null,null, null);

        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> phoneNumList = new ArrayList<>();

        while(cursor.moveToNext()) {
            //CONTACTS.execSQL("CREATE TABLE IF NOT EXISTS people (Name TEXT, Phonenumber TEXT, Favor INTEGER)");
            // 쿼리문으로 데이터 불러옴
            String name = cursor.getString(0);
            String phoneNum = cursor.getString(1);
            nameList.add(name);
            phoneNumList.add(phoneNum);
            mDbOpenHelper.insertColumn(name, phoneNum, 0);
        }
        cursor.close();
        VerificationListAdapter adapter = new VerificationListAdapter (getContext(), R.layout.list_item, new ArrayList<OnspotVerification>());
        l1.setAdapter(adapter);


        return l1;
    }

    public void ContactUpload(){
        Log.d("dd", "contactupload");
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,projection,null,null, null);
        JSONArray jsonarray = new JSONArray();
        while(cursor.moveToNext()) {
            //CONTACTS.execSQL("CREATE TABLE IF NOT EXISTS people (Name TEXT, Phonenumber TEXT, Favor INTEGER)");
            // 쿼리문으로 데이터 불러옴

            String name = cursor.getString(0);
            String phoneNum = cursor.getString(1);
            Contact c = new Contact(name, phoneNum);
            JSONObject jsonObject = new JSONObject();
            try {
                Log.d("Hm..","      GOOD");
                jsonObject.accumulate("name", name);
                jsonObject.accumulate("number", phoneNum);
                jsonarray.put(jsonObject);
            }catch (Exception e){
                Log.d("Hm..","      BAD");
            }
        }

        NetworkTask contactsUpload = new NetworkTask( "api/contacts", "post", null, jsonarray);
        contactsUpload.execute();

    }
    public void ContactDownload(ListView l1){

        Log.d("d","contactDownload is called");




    }
}
