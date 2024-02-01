package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class DoctotBooking extends AppCompatActivity {
EditText name,address,phone,fees;
TextView doctorname;
EditText time,date;
DatePickerDialog datePickerDialog;
Button back,bookbtn;
TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctot_booking);
        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        phone=findViewById(R.id.contactno);
        fees=findViewById(R.id.fees);
        doctorname=findViewById(R.id.doctorname);
        time=findViewById(R.id.time);
        date=findViewById(R.id.date);
        back=findViewById(R.id.back);
        bookbtn=findViewById(R.id.bookbtn);

        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db=new Database(DoctotBooking.this,"healthcare",null,1);
                db.addOrder(name.getText().toString(),"",address.getText().toString(),phone.getText().toString(),0,date.getText().toString(),time.getText().toString(),"lab",Float.parseFloat(fees.getText().toString()));
                Toast.makeText(DoctotBooking.this, "Appoinment Booked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DoctotBooking.this,FindDoctor.class));

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctotBooking.super.onBackPressed();
            }
        });
        Intent intent=getIntent();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDatePicker();
                datePickerDialog.show();
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTimePicker();
                timePickerDialog.show();
            }
        });

        fees.setKeyListener(null);
        time.setKeyListener(null);
        date.setKeyListener(null);
        address.setKeyListener(null);
        phone.setKeyListener(null);
        name.setKeyListener(null);

        fees.setText(intent.getStringExtra("fees"));
        address.setText(intent.getStringExtra("address"));
        phone.setText(intent.getStringExtra("phone"));
        name.setText(intent.getStringExtra("name"));
        doctorname.setText(intent.getStringExtra("type"));


    }
    public  void initDatePicker()
    {
            DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    date.setText(dayOfMonth+"/"+month+"/"+year);
                }
            };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int style= AlertDialog.THEME_HOLO_DARK;

        datePickerDialog=new DatePickerDialog(this,style,onDateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
    }

    public  void initTimePicker()
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int i,int i1) {
                if(i1<10){time.setText(i+" : 0"+i1);}
                else
            time.setText(i+" : "+i1);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int min=cal.get(Calendar.MINUTE);
        int style= AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,onTimeSetListener,hrs,min,true);

    }
}