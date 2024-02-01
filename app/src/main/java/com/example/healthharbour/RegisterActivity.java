package com.example.healthharbour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username,email,confirmpassword,password;
    Button registerbtn;
    TextView sighnup,login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        confirmpassword=findViewById(R.id.confirmpassword);
        email=findViewById(R.id.email);
        registerbtn=findViewById(R.id.registerbtn);
        login=findViewById(R.id.login);


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String name=username.getText().toString();
                String pass=password.getText().toString();
                String confirmpass=confirmpassword.getText().toString();
                String Email=email.getText().toString();
                if(name.length()==0||pass.length()==0||Email.length()==0||confirmpass.length()==0){
                    Toast.makeText(RegisterActivity.this, "plz fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if (!Email.endsWith("@gmail.com"))
                {
                    Toast.makeText(RegisterActivity.this, "enter valid email address", Toast.LENGTH_SHORT).show();
                }
                else if(!pass.equals(confirmpass))
                {
                    Toast.makeText(RegisterActivity.this, "password didnot match with confirmed password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!checkPassword(pass)){
                        Toast.makeText(RegisterActivity.this, "invalid password", Toast.LENGTH_SHORT).show();
                    }
                    else{

                    Database db=new Database(RegisterActivity.this,"healthcare",null,1);
                    db.register(name,Email,pass);
                        Toast.makeText(RegisterActivity.this, ""+db.login(name,pass), Toast.LENGTH_SHORT).show();


                        Toast.makeText(RegisterActivity.this, "Succesfully registered going to login page", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    }

                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

     }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);

    }
    public boolean checkPassword(String password)
    {
     return  password.length()>=8;
    }
}
