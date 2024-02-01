package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTest extends AppCompatActivity {
    private  String packages[][]={
        {"Package 1 : Full Body Checkup","","","","2000"},
        {"Package 2 : Blood Glucose Fasting","","","","700"},
        {"Package 3 : COVID-19 Antobody - IgG","","","","1800"},
        {"Package 4 : Thyroid Check","","","","1000"},
        {"Package 5 : Immunity Check","","","","1400"},
    };
    private  String package_detail[]={"Blood Glucose Fasting \n"+
            "Complete Hemogram\n"+"HbA1C\n"+"Iron Studies"+
            "Kidney Function Test\n"+"LDH lactate Dehydrogenase , serum \n"+
            "Lipid Profile\n"+"Liver Function Test \n",
            "Blood Glucose Fasting\n",
            "COVID-19 Antobody - IgG",
            "Throid Profile Total (T3,T4 and TSH Ultra-Sensitive)",
            "Complete Hemogram \n"+
                    "CRP (C Reative Protien)  Quantitative , serum\n"+
                    "Iron Studies \n"+
                    "Kidney Function Test \n"+
                    "Vitamin D Total-25 Hydroxy\n"+
                    "Liver Function Test\n"+
                    "Lipid Profile\n"
    };

    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;
    Button btnback,btnToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnback=findViewById(R.id.btnback);
        btnToCart=findViewById(R.id.btnpurchase);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LabTest.super.onBackPressed();
            }
        });

        btnToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LabTest.this, LabOrderDetails.class);
                startActivity(intent);

            }
        });

        list=new ArrayList();
        for(int i=0;i<packages.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Consultancy Fees : "+packages[i][4]+" PKR /-");
            list.add(item);


        }
        sa=new SimpleAdapter(this,list,
                R.layout.list_layout,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});
        ListView lst=findViewById(R.id.listviewlab);

        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1=new Intent(LabTest.this, LabTestDetails.class);
                intent1.putExtra("text1",packages[position][0]);
                intent1.putExtra("text2",package_detail[position]);
                intent1.putExtra("text3",packages[position][4]);


                startActivity(intent1);

            }
        });

    }


}