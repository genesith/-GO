package com.example.q.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
    private static Context mContext;
    public static DbOpenHelper mDbOpenHelper;


    LoginButton login_button;
    Button mloginbtn, mlogoutbtn;
    CallbackManager callbackManager;
    AccessToken mAccessToken;
    static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        updateEmail(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mContext = this;

        Button goTabButton = findViewById(R.id.gotab);
        goTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email != null) {
                    Intent intent2 = new Intent(view.getContext(), TabActivity.class);
                    intent2.putExtra("UserID", email);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(MainActivity.this, "페이스북 로그인을 하셔야 진행이 가능합니다", Toast.LENGTH_SHORT).show();
                }


            }
        });

        if( (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED))
        {
            Permissions();
        }
        else{
            FacebookSdk.sdkInitialize(getApplicationContext());
            initializeControls();
        }

        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();
        updateEmail(mContext);
    }

    public void Permissions(){
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_CONTACTS)) {
            Toast.makeText(this, "Need to access contacts", Toast.LENGTH_SHORT).show();
        }
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(this, "Need to access External Storage", Toast.LENGTH_SHORT).show();
        }
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this, "Need to access location", Toast.LENGTH_SHORT).show();
        }
        ActivityCompat.requestPermissions(this,

                new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do
                    Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();

                    FacebookSdk.sdkInitialize(getApplicationContext());
                    initializeControls();


                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }




    private void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
        login_button = (LoginButton)findViewById(R.id.login_button);
        login_button.setReadPermissions(Arrays.asList("email"));

        mloginbtn = (Button)findViewById(R.id.login_2);
        mloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                if (loggedIn == true) {
                    login_button.performClick();
                } else {
                    Toast.makeText(getApplicationContext(), "현재 log in 상태", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mlogoutbtn = (Button)findViewById(R.id.logout);
        mlogoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disconnectFromFacebook();
            }
        });


        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "Log in 성공", Toast.LENGTH_SHORT).show();
                mAccessToken = loginResult.getAccessToken();
                updateEmail(mContext);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "log in 취소", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "log in ERROR", Toast.LENGTH_LONG).show();
            }
        });



    }

    public void disconnectFromFacebook() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
                Toast.makeText(getApplicationContext(), "log out", Toast.LENGTH_SHORT).show();
                email = null;

            }
        }).executeAsync();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode,resultCode,data);

    }

    public static void updateEmail(final Context ctx){
        if(AccessToken.getCurrentAccessToken() !=null) {
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            Log.v("LoginActivity Response ", response.toString());

                            try {
                                Toast.makeText(ctx, "Email is updated to " + object.getString("email"), Toast.LENGTH_SHORT).show();
                                //txtEmail.setText("You are logged in as: " + FEmail);
                                email = object.getString("email");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email");
            request.setParameters(parameters);
            request.executeAsync();


        }
        else
            Log.i("updateEmail", "there was no AccessToken");
    }

}
