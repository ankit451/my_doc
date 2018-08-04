package com.example.android.pokedr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import Utils.SharedPref;

/**
 * Created by Siddhant Choudhary on 21-01-2017.
 */
public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    SharedPref sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            //    Intent i = new Intent(SplashActivity.this,LoginActivity.class);
           //     startActivity(i);
                // close this activity
            //    finish();
                sp=new SharedPref(getApplicationContext());
                HashMap<String,String> map=sp.getuserdetails();
                if(map.get("uname")==null && map.get("password")==null){
                    Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(i);
                }
                else
                {
                    Intent i= new Intent(getApplicationContext(),FrontActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
