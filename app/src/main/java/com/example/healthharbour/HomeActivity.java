package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
CardView labtest,buymed,doctor,article,order,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences=getSharedPreferences("shared", Context.MODE_PRIVATE);
        String name=sharedPreferences.getString("username","unknown");
        labtest=findViewById(R.id.labtest);
        buymed=findViewById(R.id.buymed);
        doctor=findViewById(R.id.finddoctor);
        article=findViewById(R.id.article);
        order=findViewById(R.id.orders);
        exit=findViewById(R.id.exit);


        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(HomeActivity.this,LabTest.class);
              startActivity(intent);

            }
        });
        buymed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(HomeActivity.this,BuyMedicine.class);
                startActivity(intent);
            }
        });
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, FindDoctor.class);
                startActivity(intent);
            }
        });

        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,Article.class);
                startActivity(intent);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, OrderDetails.class);
                startActivity(intent);
            }
        });






        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent=new Intent(HomeActivity.this, LoginActivity.class);
                SharedPreferences login=getSharedPreferences("checklogin",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1=login.edit();
                editor1.putBoolean("log",false);
                editor1.apply();
                startActivity(intent);
            }
        });

    }
}