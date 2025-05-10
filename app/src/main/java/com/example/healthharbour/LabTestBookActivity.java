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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LabTestBookActivity extends AppCompatActivity {
    EditText name,pin,contact,address;
    Button btnback,btnbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        name=findViewById(R.id.name);
        SharedPreferences sharedPreferences=getSharedPreferences("shared", Context.MODE_PRIVATE);
        String naam=sharedPreferences.getString("username","unknown");
        name.setEnabled(false);
        name.setText(naam);
        pin=findViewById(R.id.pin);
        contact=findViewById(R.id.contact);
        address=findViewById(R.id.address);
        btnback=findViewById(R.id.back);
        btnbook=findViewById(R.id.btnbook);

        Intent intent=getIntent();

        float price=intent.getFloatExtra("price",0);
        Toast.makeText(this, price+"", Toast.LENGTH_SHORT).show();
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

                String contactno=contact.getText().toString();
                String Address=address.getText().toString();
                LocalDate currentDate = LocalDate.now();
                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter.ofPattern("HH:mm");
                String otype=intent.getStringExtra("otype");

                Toast.makeText(LabTestBookActivity.this, "Rs :   "+price, Toast.LENGTH_SHORT).show();


                Database db=new Database(LabTestBookActivity.this,"healthcare",null,1);

                db.addOrder(naam,naam,Address,contactno,Integer.parseInt("0909"),currentDate.toString(),currentTime.toString(),otype,price);




                db.removeCart(naam,otype);

                Toast.makeText(LabTestBookActivity.this, "Order placed Succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));

            }
        });
    }
}