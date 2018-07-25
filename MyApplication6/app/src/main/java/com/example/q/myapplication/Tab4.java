package com.example.q.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab4.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab4 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab4() {
        // Required empty public constructor
    }
    ArrayList<StatusClass> StatusFeed;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab4.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab4 newInstance(String param1, String param2) {
        Tab4 fragment = new Tab4();
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

        NetworkTask getStatuses = new NetworkTask("api/getallstatuses", "get", null, null);
        getStatuses.execute();
        StatusFeed =new ArrayList<>();
        try {
            String s = getStatuses.get();
            JSONArray statusList = new JSONArray(s);
            if( s == null){
                Log.d("um..","sad");
            }
            Log.d("why",""+  statusList.length());
            for (int i = 0; i <  statusList.length(); i++) {

                JSONObject json = (JSONObject)  statusList.get(i);
                StatusClass status = new StatusClass(json.getString("writer_id"), json.getInt("r_code"), json.getString("image"), Float.parseFloat(json.getString("score")), json.getString("content"),json.getString("date"));

                StatusFeed.add(status);
            }

        } catch (Exception e) {
            Log.d("oh", e.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab4, container, false);
        //ArrayList<Bitmap> thumbList = getThumbList(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] {MediaStore.Images.Media.DATA});
        //PopularAdapter adapter = new PopularAdapter(thumbList);
        PopularAdapter adapter = new PopularAdapter(StatusFeed);
        RecyclerView recyclerView = view.findViewById(R.id.popularView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
 //       layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),3));
        recyclerView.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
/*    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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
    public ArrayList<Bitmap> getThumbList( Uri uri, String[] projection){
        ArrayList<Bitmap> thumbList = new ArrayList<>();
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if(cursor.moveToFirst()){
            do{

                Bitmap bmp = BitmapFactory.decodeFile(cursor.getString(0));
                thumbList.add(bmp);
            }while(cursor.moveToNext());
        }
        return thumbList;
    }

    public ArrayList<String> getStringList( Uri uri, String[] projection){
        ArrayList<String> thumbList = new ArrayList<>();
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if(cursor.moveToFirst()){
            do{
                thumbList.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        return thumbList;
    }
}
