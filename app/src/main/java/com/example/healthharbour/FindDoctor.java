package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctor extends AppCompatActivity {
    CardView familydoctor,dietician,dentist,cardiologist,surgeon,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        familydoctor=findViewById(R.id.familyDoctor);
        dietician=findViewById(R.id.dietician);
        dentist=findViewById(R.id.dentist);
        cardiologist=findViewById(R.id.cardio);
        surgeon=findViewById(R.id.surgeon);
        exit=findViewById(R.id.exit);

        familydoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","Family Physician");
                startActivity(intent);
            }
        });

        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","Dietician");
                startActivity(intent);
            }
        });
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","Dentist");
                startActivity(intent);
            }
        });
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","Surgeon");
                startActivity(intent);
            }
        });
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","Cardiologist");
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctor.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}