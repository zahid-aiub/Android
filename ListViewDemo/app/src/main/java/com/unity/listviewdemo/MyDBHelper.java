package com.unity.listviewdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class MyDBHelper extends SQLiteOpenHelper{

    //SQLiteDatabase sqLiteDatabase;


    private static final String DATABASE_NAME = "history";
    private static final String TABLE_NAME = "user";
    private static final String id = "_id";
    private static final String name = "name";
    private static final String email = "email";
    private static final String pass = "password";
    private static final String gender = "gender";
    private static final int VERSION_NO = 5;
    private static final String sql = "create table "+TABLE_NAME+" ("+id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+name+" VARCHAR(50), "+email+" VARCHAR(50), "+pass+" VARCHAR(10), "+gender+" VARCHAR(20));";



    Context context;
    MyDBHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION_NO);
        this.context = context;
        Toast.makeText(context, "Constructer called", Toast.LENGTH_SHORT).show();
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {

            sqLiteDatabase.execSQL(sql);
            Toast.makeText(context, "Table created successfully", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME+"");
        onCreate(sqLiteDatabase);
    }

    public long saveInfo(String uname, String uemail, String upass, String ugender){

        //Toast.makeText(context, "rrrrrrrrr", Toast.LENGTH_SHORT).show();

        SQLiteDatabase dbase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name,uname);
        contentValues.put(email,uemail);
        contentValues.put(pass,upass);
        contentValues.put(gender,ugender);

        long rowId = dbase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }
}
