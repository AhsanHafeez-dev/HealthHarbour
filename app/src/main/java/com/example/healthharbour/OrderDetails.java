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
        order_details = new String[data.size()][6];

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetails.super.onBackPressed();
            }
        });

//        String query3="create table orderplace(0 fullname text,1 address text,2 contactno text,3 pincode int,4 date text,5 time text,6 amount float,7 otype text)";
        for(int i=0;i<data.size();i++){
            String arrData=data.get(i).toString();
            String strData[]=arrData.split("\\$");
            order_details[i][0]=strData[0];//name
            order_details[i][4]="Cost : "+strData[6]+" /-";//amount
            order_details[i][2]="Time : "+strData[5]+"  \t"; //time
            order_details[i][1]=strData[1];//address
            //fullname , address contactno pincode date time amount otype
            order_details[i][3]="Del : "+strData[2]+" ";//contact
            order_details[i][5]="Type : "+strData[7];


        }

        list=new ArrayList();
        for(int i=0;i<order_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",order_details[i][0]);//name
            item.put("line2",order_details[i][1]);//address
            item.put("line3",order_details[i][2]);//time
            item.put("line4",order_details[i][3]);//contact
            item.put("line5",order_details[i][4]);//amount
            item.put("line6",order_details[i][5]);//amount
            list.add(item);


        }
        sa=new SimpleAdapter(this,list,
                R.layout.list_layout,
                new String[]{"line1","line2","line3","line4","line5","line6"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5,R.id.line6});

        lst.setAdapter(sa);

    }
}
