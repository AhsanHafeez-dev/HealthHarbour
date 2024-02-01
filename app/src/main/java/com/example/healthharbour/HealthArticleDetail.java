package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticleDetail extends AppCompatActivity {

    TextView title;
    ImageView img;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_detail);
        img=findViewById(R.id.img);
        btnback=findViewById(R.id.btnback);
        title=findViewById(R.id.title);
        Intent intent=getIntent();

        title.setText(intent.getStringExtra("text1"));
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            int resId=bundle.getInt("text2");
            img.setImageResource(resId);

        }


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HealthArticleDetail.super.onBackPressed();
            }
        });
    }
}