package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {
    EditText name,pin,contact,address;
    Button btnback,btnbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        name=findViewById(R.id.name);

        pin=findViewById(R.id.pin);
        contact=findViewById(R.id.contact);
        address=findViewById(R.id.address);
        btnback=findViewById(R.id.back);
        btnbook=findViewById(R.id.btnbook);

        Intent intent=getIntent();

        String price=intent.getStringExtra("price");

        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LabTestBookActivity.super.onBackPressed();
            }
        });
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared", Context.MODE_PRIVATE);
                String naam=sharedPreferences.getString("username","unknown");
                Toast.makeText(LabTestBookActivity.this, "pref  "+naam, Toast.LENGTH_SHORT).show();


                Database db=new Database(LabTestBookActivity.this,"healthcare",null,1);
                Toast.makeText(LabTestBookActivity.this, "dbstart", Toast.LENGTH_SHORT).show();

                    Toast.makeText(LabTestBookActivity.this, "if", Toast.LENGTH_SHORT).show();

                db.addOrder(naam,"ahsan","Mehmooodabad corporation","0307 2793796",Integer.parseInt("0909"),date,time,"lab",Float.parseFloat("1200"));



                db.removeCart(naam,"lab");

                Toast.makeText(LabTestBookActivity.this, "Order placed Succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));

            }
        });
    }
}