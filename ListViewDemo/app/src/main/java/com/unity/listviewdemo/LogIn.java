package com.unity.listviewdemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogIn extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    EditText userName, userPass;
    Button loginBtn, signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        textView = (TextView) findViewById(R.id.texiViewId);
        userName = (EditText) findViewById(R.id.unameEditTextId);
        userPass = (EditText) findViewById(R.id.upassEditTextId);
        loginBtn = (Button) findViewById(R.id.loginBtnId);
        signupBtn = (Button) findViewById(R.id.signupBtnId);


        MyDBHelper myDBHelper = new MyDBHelper(this);
        //SQLiteDatabase sqLiteDatabase = myDBHelper.getWritableDatabase();

        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {



        if (view.getId()==R.id.loginBtnId){
            Intent intent = new Intent(LogIn.this,Welcome.class);
            startActivity(intent);
        }
        if (view.getId()==R.id.signupBtnId){

            Intent intent = new Intent(LogIn.this,SignupActivity.class);
            startActivity(intent);

        }

    }
}
