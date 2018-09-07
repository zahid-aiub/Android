package com.unity.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Welcome extends AppCompatActivity {

    private ProgressBar progressBar;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        setContentView(R.layout.activity_welcome);

        progressBar = (ProgressBar) findViewById(R.id.prograssBarId);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApplication();
            }
        });
        thread.start();

    }

    public void doWork(){

        for (progress = 0; progress<=100; progress = progress+20){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public void startApplication(){
        Intent intent = new Intent(Welcome.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}
