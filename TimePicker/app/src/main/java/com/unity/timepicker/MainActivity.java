package com.unity.timepicker;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button;
    TextView textView;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.showTimeBtn);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        TimePicker timePicker = new TimePicker(this);
        int currentHpour = timePicker.getCurrentHour();
        int currentMin = timePicker.getCurrentMinute();

        timePickerDialog = new TimePickerDialog(MainActivity.this,

                new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        textView.setText(i+":"+i1);
                    }
                },currentHpour,currentMin,true);

        timePickerDialog.show();


    }
}
