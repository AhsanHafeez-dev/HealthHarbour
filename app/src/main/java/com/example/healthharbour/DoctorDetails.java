package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetails extends AppCompatActivity {

String detail1[][]={
        {"Doctor Name : Dr.Shehzad","Hospital Address : DHA","Exp : 5 years ","Mobile No : +92 9878339087","600"},
        {"Doctor Name : Dr.Aslam","Hospital Address : Qayumabad","Exp : 8 years ","Mobile No : +92 9878332587","900"},
        {"Doctor Name : Dr.Iqbal","Hospital Address : North Nazimabad","Exp : 9 years ","Mobile No : +92 0778339087","1000"},
        {"Doctor Name : Dr.Shahid","Hospital Address : New karachi","Exp : 12 years ","Mobile No : +92 6578339087","1500"}
};
    String detail2[][]={
            {"Doctor Name : Dr.Rameez","Hospital Address : PECHS","Experience : 5 years ","Mobile No : +92 9878339087","600"},
            {"Doctor Name : Dr.Riaz","Hospital Address : KECHS","Experience : 8 years ","Mobile No : +92 9878332587","900"},
            {"Doctor Name : Dr.Hussain","Hospital Address : M.A Jinnah Road","Experience : 9 years ","Mobile No : +92 0778339087","1000"},
            {"Doctor Name : Dr.Yousuf","Hospital Address : Karsaz","Experience : 12 years ","Mobile No : +92 6578339087","1500"}
    };
    String detail3[][]={
            {"Doctor Name : Dr.Zaid","Hospital Address : DHA","Exp : 5 years ","Mobile No : +92 9878339087","600"},
            {"Doctor Name : Dr.Saqib","Hospital Address : Highway","Exp : 8 years ","Mobile No : +92 9878332587","900"},
            {"Doctor Name : Dr.Zohaib","Hospital Address : Shah Faisal","Exp : 9 years ","Mobile No : +92 0778339087","1000"},
            {"Doctor Name : Dr.Bilal","Hospital Address : PIDC","Exp : 12 years ","Mobile No : +92 6578339087","1500"}
    };
    String detail4[][]={
            {"Doctor Name : Dr.Subhan","Hospital Address : Mehmoodabad","Exp : 5 years ","Mobile No : +92 9878339087","600"},
            {"Doctor Name : Dr.Rafay","Hospital Address : Korangi","Exp : 8 years ","Mobile No : +92 9878332587","900"},
            {"Doctor Name : Dr.Sheryar","Hospital Address : Nazimabad","Exp : 9 years ","Mobile No : +92 0778339087","1000"},
            {"Doctor Name : Dr.Asad","Hospital Address : New karachi","Exp : 12 years ","Mobile No : +92 6578339087","1500"}
    };
    String detail5[][]={
            {"Doctor Name : Dr.Shehzad","Hospital Address : DHA","Exp : 5 years ","Mobile No : +92 9878339087","600"},
            {"Doctor Name : Dr.Aslam","Hospital Address : Qayumabad","Exp : 8 years ","Mobile No : +92 9878332587","900"},
            {"Doctor Name : Dr.Iqbal","Hospital Address : North Nazimabad","Expe : 9 years ","Mobile No : +92 0778339087","1000"},
            {"Doctor Name : Dr.Shahid","Hospital Address : New karachi","Exp : 12 years ","Mobile No : +92 6578339087","1500"}
    };
    TextView doctor;
    String [][]doctor_details={};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        doctor=findViewById(R.id.doctor);
        doctor.setText(title+" details");
        Button btnback=findViewById(R.id.btnback);

        if(title.equals("Family Physician")) {
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show();;doctor_details=detail1;}
        else if(title.equals("Dietician")){doctor_details=detail2;}
        else if(title.equals("Dentist")){doctor_details=detail3;}
        else if(title.equals("Surgeon")){doctor_details=detail4;}
        else if(title.equals("Cardiologist")){doctor_details=detail5;}


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DoctorDetails.this, FindDoctor.class);

                startActivity(intent);
            }
        });
        list=new ArrayList();
        for(int i=0;i<doctor_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Consultancy Fees : "+doctor_details[i][4]+" PKR /-");
            list.add(item);


        }
        sa=new SimpleAdapter(this,list,
                R.layout.list_layout,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});
        ListView lst=findViewById(R.id.listviewdoctor);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1=new Intent(DoctorDetails.this,DoctotBooking.class);
                intent1.putExtra("name",doctor_details[position][0]);
                intent1.putExtra("address",doctor_details[position][1]);
                intent1.putExtra("phone",doctor_details[position][3]);
                intent1.putExtra("fees",doctor_details[position][4]);
                intent1.putExtra("type",title);
                startActivity(intent1);
            }
        });
    }
}