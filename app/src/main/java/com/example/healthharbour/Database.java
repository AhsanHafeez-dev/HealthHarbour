package com.example.healthharbour;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    Context context;
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table users(username text , email text , password text)";
        db.execSQL(query);
        String query2="create table cart(username text,product text,price float,otype text)";
        db.execSQL(query2);
        String query3="create table orderplace(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        db.execSQL(query3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public  void register(String name,String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues user=new ContentValues();
        user.put("username",name);
        user.put("email",email);;
        user.put("password",password);
        db.insert("users",null,user);
        db.close();
    }

    public  int login(String username,String password)
    {
        int result =0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username=? and password=?",new String[]{username,password});
        if(cursor.moveToFirst())
        {
            result=1;
        }
        db.close();
        return result;
    }

    public  void AddToCart(String name,String product,float price,String otype)
    {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues user=new ContentValues();
        user.put("username",name);
        user.put("product",product);;
        user.put("price",price);
        user.put("otype",otype);

        db.insert("cart",null,user);
        db.close();
    }
    public  int checkCart(String username,String product)
    {

        int result =0;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from cart where username = ? and product = ? ",new String[]{username,product});

        if(cursor.moveToFirst())
        {
            result=1;
        }


        db.close();
        return result;
    }
    public  void removeCart(String username,String otype)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.delete("cart","username=? and otype=? ",new String[]{username,otype});
        db.close();
    }
    public ArrayList getCardData(String username,String otype)
    {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList data=new ArrayList();
        String str[]={username,otype};
        Cursor cursor=db.rawQuery("select * from cart where username=? and otype=?",str);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext())
        {
            String product=cursor.getString(1);
            Float price=cursor.getFloat(2);

            data.add(product+"$"+price);
        }
        }

                db.close();
                return data;
    }
    public  void addOrder(String Username,String fullname,String address,String contact,int pincode,String date,String time,String otype,float price)
    {
        ContentValues cv=new ContentValues();
        cv.put("username",Username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contactno",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();


    }
    public  ArrayList getOrderData(String username)
    {
        ArrayList data=new ArrayList();
        SQLiteDatabase db=getReadableDatabase();
        String str[]={username};
        Cursor cursor=db.rawQuery("select * from orderplace where username = ?",str);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                data.add(cursor.getString(1)+"$"+cursor.getString(2)+"$"+cursor.getString(3)+"$"+cursor.getString(4)+"$"+cursor.getString(5)+"$"+cursor.getString(6)+"$"+cursor.getString(7)+"$"+cursor.getString(8) );
            }

        }
        //fullname , address contactno pin
//(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)
        return  data;
    }
}
