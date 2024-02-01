package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDetails extends AppCompatActivity {
    Button btnback;
    ListView lst;
    List list;
    HashMap item;
    SimpleAdapter sa;
    private String order_details[][]={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details3);
        btnback=findViewById(R.id.btnback);
        lst=findViewById(R.id.listvieworder);

        SharedPreferences sp=getSharedPreferences("shared", Context.MODE_PRIVATE);
        String username=sp.getString("username","unknown");

        Database db=new Database(OrderDetails.this,"healthcare",null,1);
        ArrayList data=db.getOrderData(username);
        order_details = new String[data.size()][5];

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetails.super.onBackPressed();
            }
        });

        for(int i=0;i<data.size();i++){
            String arrData=data.get(i).toString();
            String strData[]=arrData.split("\\$");
            order_details[i][0]=strData[0];
            order_details[i][4]="Cost : "+strData[6]+" /-";
            order_details[i][2]=strData[1]; //time
            order_details[i][1]=strData[2];
            //fullname , address contactno pincode date time amount otype
        if(strData[7].compareTo("medicine")==0)
            {
                order_details[i][3]="Del : "+strData[4]+" "+strData[5];
            }
            else{
                order_details[i][3]="Del : "+strData[4]+" "+strData[5];
            }

        }

        list=new ArrayList();
        for(int i=0;i<order_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",order_details[i][0]);
            item.put("line2",order_details[i][1]);
            item.put("line3",order_details[i][2]);
            item.put("line4",order_details[i][3]);
            item.put("line5",order_details[i][4]);

            list.add(item);


        }
        sa=new SimpleAdapter(this,list,
                R.layout.list_layout,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});

        lst.setAdapter(sa);

    }
}
