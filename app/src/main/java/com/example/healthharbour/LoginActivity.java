package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button loginbtn;
    TextView sighnup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences login=getSharedPreferences("checklogin",Context.MODE_PRIVATE);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginbtn=findViewById(R.id.loginbtn);
        sighnup=findViewById(R.id.sighnup);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=username.getText().toString();
                String pass=password.getText().toString();
                if(name.length()==0||pass.length()==0){
                    Toast.makeText(LoginActivity.this, "plz fill all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Database db=new Database(LoginActivity.this,"healthcare",null,1);
                    if(db.login(name,pass)==1)
                    {
                        Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                        SharedPreferences sharedPreferences=getSharedPreferences("shared", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        SharedPreferences.Editor edit=login.edit();
                        edit.putBoolean("log",true);
                        editor.putString("username",name);
                        editor.apply();
                        edit.apply();
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "user didnot exist", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        sighnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}