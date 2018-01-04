package com.osito.osoyoga;

/**
 * Created by RaquelMarcos on 27/12/17.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;

public class CountdownActivity extends AppCompatActivity {

    private TextView countdownText;
    private Button countdownBotton;
    private Button stopButton;
    TareaCronometro tareaCronometro;

    private CountDownTimer countDownTimer;
    private long timeLeft=600000; //10 minutos
    private boolean timerRunning;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdown);

        countdownText=(TextView) findViewById(R.id.time_text);
        countdownBotton=(Button) findViewById(R.id.inicio);
        stopButton=(Button) findViewById(R.id.boton_parar);

        //Para no detectar el movimiento de la pantalla y tener que guardar
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        tareaCronometro = new TareaCronometro();

        countdownBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });

    }

    public void startStop(){
        if(timerRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }





    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft=l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        countdownBotton.setText("PAUSE");

        timerRunning= true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        countdownBotton.setText("START");
        timerRunning=false;

    }



    public void updateTimer(){
        int min=(int) timeLeft/60000;
        int sec=(int) timeLeft%60000 /1000;

        String timeLeftText;
        timeLeftText= ""+ min;
        timeLeftText+= ":";

        if(sec<10) timeLeftText += "0";
        timeLeftText+=sec;


        countdownText.setText(timeLeftText);
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

}

class TareaCronometro extends AsyncTask<Void, String, Void>

