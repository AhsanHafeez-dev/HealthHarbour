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
import java.util.List;

public class Article extends AppCompatActivity {
    List list;
    SimpleAdapter sa;
    Button btnback;
    ListView article;
    String article_details[][]=
            {
                    {"Walking Daily","","","","Click for more details"},
                    {"Home care of COVID-19","","","","Click for more details"},
                    {"Stop smoking","","","","Click for more details"},
                    {"Menstrual cramp","","","","Click for more details"},
                    {"Healthy gut","","","","Click for more details"},
            };
    private  int images[]={
      R.drawable.health1,
      R.drawable.health2,
      R.drawable.health3,
      R.drawable.health4,
      R.drawable.health5,

    };
    HashMap item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        btnback=findViewById(R.id.btnback);
        article=findViewById(R.id.article);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Article.super.onBackPressed();
            }
        });
        list=new ArrayList();
        for(int i=0;i<article_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1",article_details[i][0]);
            item.put("line2",article_details[i][1]);
            item.put("line3",article_details[i][2]);
            item.put("line4",article_details[i][3]);
            item.put("line5",article_details[i][4]);
            list.add(item);


        }
        sa=new SimpleAdapter(this,list,
                R.layout.list_layout,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5});
        article.setAdapter(sa);
        article.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(Article.this,HealthArticleDetail.class);
                it.putExtra("text1",article_details[position][0]);
                it.putExtra("text2",images[position]);
                startActivity(it);
            }
        });

    }
}