package com.unity.sensorapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    public static final String TAG="MainActivity";

    private SensorManager sensorManager;
    private Sensor acclerometer, mGyro, mMagno, mPresure, mTemp, mLight, mHumi;

    private TextView xValue, yValue, zValue, xGyroValue, yGyroValue, zGyroValue, xMagno, yMagno, zMagno,
                     light, presure, temp, humi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView) findViewById(R.id.xTxt);
        yValue = (TextView) findViewById(R.id.yTxt);
        zValue = (TextView) findViewById(R.id.zTxt);

        xGyroValue = (TextView) findViewById(R.id.xGyroTxt);
        yGyroValue = (TextView) findViewById(R.id.yGyroTxt);
        zGyroValue = (TextView) findViewById(R.id.zGyroTxt);

        xMagno = (TextView) findViewById(R.id.xMagnoTxt);
        yMagno = (TextView) findViewById(R.id.yMagnoTxt);
        zMagno = (TextView) findViewById(R.id.zMagnoTxt);

        light = (TextView) findViewById(R.id.light);
        presure = (TextView) findViewById(R.id.presure);
        temp = (TextView) findViewById(R.id.temp);
        humi = (TextView) findViewById(R.id.humi);


        Log.d(TAG, "onCreate: Registred accelerometer Listener.");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        acclerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (acclerometer != null){
            sensorManager.registerListener(MainActivity.this,acclerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registred accelerometer Listener.");
        }
        else {
            xValue.setText("Accelometer Not Support.");
            yValue.setText("Accelometer Not Support.");
            zValue.setText("Accelometer Not Support.");
        }



        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyro != null){
            sensorManager.registerListener(MainActivity.this,mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registred Gyrometer Listener.");
        }
        else {
            xGyroValue.setText("Gyrometer Not Support.");
            yGyroValue.setText("Gyrometer Not Support.");
            zGyroValue.setText("Gyrometer Not Support.");
        }


        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mMagno != null){
            sensorManager.registerListener(MainActivity.this,mMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registred Magnometer Listener.");
        }
        else {
            xMagno.setText("Magnometer Not Support.");
            yMagno.setText("Magnometer Not Support.");
            zMagno.setText("Magnometer Not Support.");
        }


        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mLight != null){
            sensorManager.registerListener(MainActivity.this,mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registred Light Listener.");
        }
        else {
            light.setText("Light Not Support.");
        }


        mPresure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (mPresure != null){
            sensorManager.registerListener(MainActivity.this,mPresure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registred Presure Listener.");
        }
        else {
            presure.setText("Presure Not Support.");
        }


        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (mTemp != null){
            sensorManager.registerListener(MainActivity.this,mTemp, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registred Tempreture Listener.");
        }
        else {
            temp.setText("Tempreture Not Support.");
        }


        mHumi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (mHumi != null){
            sensorManager.registerListener(MainActivity.this,mHumi, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Humidity Tempreture Listener.");
        }
        else {
            humi.setText("Humidity Not Support.");
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG, "onSensorChanged X: "+sensorEvent.values[0] + "Y: "+ sensorEvent.values[1] + "Z: "+sensorEvent.values[2]);

            xValue.setText("xValue: "+sensorEvent.values[0]);
            yValue.setText("yValue: "+sensorEvent.values[1]);
            zValue.setText("zValue: "+sensorEvent.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_GYROSCOPE){
            xGyroValue.setText("xGvalue: "+sensorEvent.values[0]);
            yGyroValue.setText("yGValue: "+sensorEvent.values[1]);
            zGyroValue.setText("zGValue: "+sensorEvent.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            xMagno.setText("xMValue: "+sensorEvent.values[0]);
            yMagno.setText("yMValue: "+sensorEvent.values[1]);
            zMagno.setText("zMValue: "+sensorEvent.values[2]);
        }
        else if (sensor.getType() == Sensor.TYPE_LIGHT){
            light.setText("Light: "+sensorEvent.values[0]);
        }
        else if (sensor.getType() == Sensor.TYPE_PRESSURE){
            presure.setText("Presure: "+sensorEvent.values[0]);
        }
        else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            temp.setText("Tempresure: "+sensorEvent.values[0]);
        }
        else if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            humi.setText("Humidity: "+sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
