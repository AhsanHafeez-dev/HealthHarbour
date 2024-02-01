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

public class BuyMedicine extends AppCompatActivity {
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;
    Button btnback,btnToCart;
    String[][] medicine = {
            {"Indrop D", "", "", "", "200"},
            {"Osnate D", "", "", "", "300"},
            {"Concor 2.5", "", "", "", "900"},
            {"Xplendid", "", "", "", "1200"},
            {"Lowplat", "", "", "", "1400"},
            {"Risek", "", "", "", "1800"},
            {"Mixtard Penfil", "", "", "", "1800"},
            {"Covam 5/80", "", "", "", "1900"},
            {"Galvusmet 50/1000", "", "", "", "3500"}
    };
String medicineDetails[]={
        "Vitamin D 2000iu for absorbing calcium excellent for joint and bones specially for people over 30\n Usual Dosage : 1/week",
        "Multi Vitamin good for bones and joints specially recomended for elders of over 40 of age",
        "Generic Formula : Bisoprolo Hemifumarate \nFor cure of : Blood Pressure \nused for reducing blood pressure for use on doctor prescription available in 2.5,5 and 10mg",
        "Generic Formula : Vildagliptin Metformin HCL \nFor cure of : Diabetes \nused for reducing blood pressure for use on doctor prescription available in 2.5,5 and 10mg",
        "Multi Vitamin good for bones and joints specially recomended for elders of over 40 of age",
        "Multi Vitamin good for bones and joints specially recomended for elders of over 40 of age",
        "Multi Vitamin good for bones and joints specially recomended for elders of over 40 of age",
        "Multi Vitamin good for bones and joints specially recomended for elders of over 40 of age",
        "Generic Formula : Vildagliptin Metformin HCL \nFor cure of : Diabetes \nused for reducing blood pressure for use on doctor prescription available in 2.5,5 and 10mg",

};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        btnback=findViewById(R.id.btnback);
        btnToCart=findViewById(R.id.btnpurchase);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyMedicine.super.onBackPressed();
            }
        });

        btnToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(BuyMedicine.this, MedicineCheckoutCart.class);
                startActivity(intent);

            }
        });

        list=new ArrayList();
        for(int i=0;i<medicine.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",medicine[i][0]);
            item.put("line2",medicine[i][1]);
            item.put("line3",medicine[i][2]);
            item.put("line4",medicine[i][3]);
            item.put("line5","Price : "+medicine[i][4]+" PKR /-");
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
                Intent intent1=new Intent(BuyMedicine.this, CartBuyMedicine.class);
                intent1.putExtra("text1",medicine[position][0]);
                intent1.putExtra("text2",medicineDetails[position]);
                intent1.putExtra("text3",medicine[position][4]);


                startActivity(intent1);

            }
        });



    }
}