package com.unity.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedBack extends AppCompatActivity implements View.OnClickListener{

    EditText nameEditText, feedBackEditText;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        nameEditText = (EditText) findViewById(R.id.nameEditTextId);
        feedBackEditText = (EditText) findViewById(R.id.feedbackEditTextId);
        sendBtn = (Button) findViewById(R.id.sendBtnId);

        sendBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        try {
            String name = nameEditText.getText().toString();
            String feedback = feedBackEditText.getText().toString();

            if (view.getId()==R.id.sendBtnId){

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");

                intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"zahidulshr325@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback from App");
                intent.putExtra(Intent.EXTRA_TEXT,"Name: "+name + "\n\nFeedback: "+feedback);
                startActivity(Intent.createChooser(intent,"Feedback with"));


            }

            nameEditText.setText("");
            feedBackEditText.setText("");

        }catch (Exception e){

            Toast.makeText(this,"Error is: "+e,Toast.LENGTH_LONG).show();
        }

    }
}
