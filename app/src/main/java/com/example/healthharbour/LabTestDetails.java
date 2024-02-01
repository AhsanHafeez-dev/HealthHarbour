package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetails extends AppCompatActivity {
    Button back,btnpurchase;
    EditText labdetail;
    TextView fees,text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        Intent intent=getIntent();
        String name=intent.getStringExtra("text1");
        String details=intent.getStringExtra("text2");
        String fee=intent.getStringExtra("text3");
        labdetail=findViewById(R.id.labdetails);
        back=findViewById(R.id.btnback);
        fees=findViewById(R.id.fees);
        btnpurchase=findViewById(R.id.btnpurchase);
        labdetail.setKeyListener(null);
        text=findViewById(R.id.text);

        text.setText(name);
        labdetail.setText(details);
        fees.setText("Fee : "+fee);;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LabTestDetails.super.onBackPressed();
            }
        });


        btnpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared", Context.MODE_PRIVATE);

                String username=sharedPreferences.getString("username","unknown");

                String product=name;
                float price=Float.parseFloat(fee);
                Database db=new Database(LabTestDetails.this,"healthcare",null,1);

                if(db.checkCart(username,product)==1)
                {
                    Toast.makeText(LabTestDetails.this, "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    db.AddToCart(username,product,price,"lab");
                    Toast.makeText(LabTestDetails.this, "Succesfully added to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetails.this, LabTest.class));
                }


            }
        });


    }
}