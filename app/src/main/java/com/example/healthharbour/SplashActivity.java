package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences("checklogin", Context.MODE_PRIVATE);
                boolean b=sharedPreferences.getBoolean("log",false);
                Intent intent;
                //if user is logged in
                if(b){
                     intent =new Intent(SplashActivity.this, HomeActivity.class);

                }
                // if user is not login
                else{intent=new Intent(SplashActivity.this, LoginActivity.class);}
                startActivity(intent);
            }
        }, 3000);
    }
}