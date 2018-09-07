package com.unity.recorder;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button btnStart, btnStop, btnPlay, btnPause;
    TextView txt1;
    Thread runner;
    private static double mEMA=0.0;
    static final private double EMA_FILTER= 0.6;
    public static final int RequestPermissionCode=1;

    String AudioSavePathDevice= null;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    public double soundDb(double ampl){
        return  20*Math.log10(getAmplitudeEMA()/ampl);
    }

    public double getAmplitude(){
        if (mediaRecorder != null){
            return  (mediaRecorder.getMaxAmplitude());
        }
        else
            return 0;

    }
    public double getAmplitudeEMA(){
        double amp = getAmplitude();
        mEMA = EMA_FILTER*amp+(1.0-EMA_FILTER*mEMA);
        return (int) mEMA;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.button1);
        btnStop = (Button) findViewById(R.id.button2);
        btnPlay = (Button) findViewById(R.id.button3);
        btnPause = (Button) findViewById(R.id.button4);
        txt1 = (TextView) findViewById(R.id.txt);

        //btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        btnPlay.setEnabled(false);
        btnPause.setEnabled(false);

        if (runner == null){
            runner = new Thread(){
                public void run(){
                    while (runner != null){
                        try {
                            Thread.sleep(2000);
                            Log.i(TAG, "run: Tock");
                        }catch (InterruptedException ex){
                            ex.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txt1.setText(Double.toString(soundDb(1))+"dB");
                            }
                        });

                    }
                }
            };
            runner.start();
            Log.d(TAG, "onCreate: runner Started");
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckPermission()){
                    AudioSavePathDevice = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+ "AudioRecord.3gpp";
                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    }catch (IllegalStateException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    btnStart.setEnabled(false);
                    btnStop.setEnabled(true);
                    btnPlay.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Recording Started", Toast.LENGTH_SHORT).show();
                }
                else {
                    RequestPermission();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecorder.stop();
                btnStop.setEnabled(false);
                btnPlay.setEnabled(true);
                btnPause.setEnabled(false);

                Toast.makeText(MainActivity.this, "Recording Completed.", Toast.LENGTH_SHORT).show();
            }
        });

         btnPlay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) throws IllegalStateException,IllegalArgumentException,SecurityException{
                 btnStop.setEnabled(false);
                 btnStart.setEnabled(false);
                 btnPause.setEnabled(true);

                 mediaPlayer = new MediaPlayer();
                 try {
                     mediaPlayer.setDataSource(AudioSavePathDevice);
                     mediaPlayer.prepare();
                 }catch (IOException e){
                     e.printStackTrace();
                 }
                 mediaPlayer.start();
                 Toast.makeText(MainActivity.this, "Last Recording Playing....", Toast.LENGTH_SHORT).show();
             }
         });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStop.setEnabled(false);
                btnStart.setEnabled(true);
                btnPlay.setEnabled(true);
                btnPause.setEnabled(false);
            }
        });

    }

    public void MediaRecorderReady(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathDevice);
    }

    private void RequestPermission(){
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{WRITE_EXTERNAL_STORAGE,RECORD_AUDIO},RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch(requestCode){
                case RequestPermissionCode:
                    if (grantResults.length>0){
                        boolean StroragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                        boolean RecordPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                        if (StroragePermission && RecordPermission){
                            Toast.makeText(this,"Permission Granted",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show();
                        }
                    }
                    break;

            }
    }

    public  boolean CheckPermission(){
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),RECORD_AUDIO);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }
}
