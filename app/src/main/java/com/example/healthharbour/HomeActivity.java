package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


public class HomeActivity extends AppCompatActivity {
CardView labtest,buymed,doctor,article,order,logout;

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
        logout=findViewById(R.id.logout);


        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              switchA(LabTest.class);

            }
        });
        buymed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchA(BuyMedicine.class);
            }
        });
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchA(FindDoctor.class);
            }
        });

        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchA(Article.class);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchA(OrderDetails.class);
            }
        });






        logout.setOnClickListener(new View.OnClickListener() {
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
    public  void switchA(Class c)
    {
        Intent i =new Intent(HomeActivity.this,c);
        startActivity(i);
    }
}