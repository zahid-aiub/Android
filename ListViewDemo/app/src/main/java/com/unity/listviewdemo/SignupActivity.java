package com.unity.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    MyDBHelper myDbHelper;

    private EditText nameTxt, emailTxt, passTxt, genderTxt;
    private Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameTxt = (EditText) findViewById(R.id.nameEditTextId);
        emailTxt = (EditText) findViewById(R.id.emailEditTextId);
        passTxt = (EditText) findViewById(R.id.passwordEditTextId);
        genderTxt = (EditText) findViewById(R.id.genderEditTextId);
        saveBtn = (Button) findViewById(R.id.saveInfoBtnId);

        saveBtn.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {

        String name = nameTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String pass = passTxt.getText().toString();
        String gender = genderTxt.getText().toString();


        if (view.getId()==R.id.saveInfoBtnId){

            //Toast.makeText(this, "ABCDEF", Toast.LENGTH_SHORT).show();
            long rowId = myDbHelper.saveInfo(name,email,pass,gender);

            if (rowId==-1){
                Toast.makeText(this, "Error..!!Information not saved", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Information saved successfully..!!", Toast.LENGTH_SHORT).show();
            }


        }

    }
}
