package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class LabOrderDetails extends AppCompatActivity {
    Button btnback,checkout;
    EditText date,time;
    ListView lst;
    TextView fees;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    HashMap item;
    List list;
    String packages[][];
    SimpleAdapter sa;
    float totalAmount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        btnback=findViewById(R.id.btnback);
        checkout=findViewById(R.id.checkout);
        lst=findViewById(R.id.labdetails);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        fees=findViewById(R.id.fees);

        date.setKeyListener(null);
        time.setKeyListener(null);

        Intent intent=getIntent();



        SharedPreferences sharedPreferences=getSharedPreferences("shared", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","unknown");

        Database db=new Database(LabOrderDetails.this,"healthcare",null,1);
        ArrayList data=db.getCardData(username,"lab");

        packages=new String[data.size()][];
        for(int i=0;i<packages.length;i++){packages[i]=new String[5];}

        for(int i=0;i<data.size();i++){
            String arrData=data.get(i).toString();
            String strData[]=arrData.split("\\$");
            packages[i][0]=strData[0];
            packages[i][4]="Cost : "+strData[1]+" /-";
            totalAmount+=Float.parseFloat(strData[1]);
        }
        fees.setText("Total Cost : "+totalAmount);


        list=new ArrayList();
        for(int i=0;i<packages.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Test Fees : "+packages[i][4]+" PKR /-");
            list.add(item);


        }
        sa=new SimpleAdapter(this,list,
                R.layout.list_layout,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});

        lst.setAdapter(sa);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LabOrderDetails.this,LabTestBookActivity.class);
                Toast.makeText(LabOrderDetails.this, totalAmount+"", Toast.LENGTH_SHORT).show();
                intent1.putExtra("price",totalAmount);
                intent1.putExtra("date",date.getText());
                intent1.putExtra("time",time.getText());
                intent1.putExtra("otype","lab");

                startActivity(intent1);

            }
        });



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
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LabOrderDetails.super.onBackPressed();
            }
        });


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
            public void onTimeSet(TimePicker view, int i, int i1) {
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