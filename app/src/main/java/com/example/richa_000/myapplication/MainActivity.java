package com.example.richa_000.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    // Init var
    int i=1,numberOfRepetitionsCount=0, upTime =5,iteration=0,delayTime=0, totalRunTime,repeatTime,showTime=0,x=1,numberOfRepetitions;
    TextView num,halt,up,down;
    EditText test,delay,repeat;
    final Handler myHandler = new Handler();
     Timer myTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init your TextView
        num =  findViewById(R.id.textView); //removed (TextView)
        halt =  findViewById(R.id.textView2);
        up =  findViewById(R.id.textView3);
        down =  findViewById(R.id.textView4);
        test = findViewById(R.id.editText);
        delay =  findViewById(R.id.editText2);
        repeat =  findViewById(R.id.editText3);


        // Timer


    }

    // Runnable method
    final Runnable myRunnable = new Runnable() {
        public void run() {
            num.setText(String.valueOf(i)); // update your text
            //up.setText("UP");
            if (showTime< upTime) {
                up.setText("Auto Up "); //where command to shut target will be.
            }
            else // ((showTime>upTime))

               {
                up.setText("Auto Down");

            }
        }
    };

    // updateUI method related to a Runnable
    public void updateUI() {
       // totalRunTime =((upTime +delayTime) * numberOfRepetitions); //totalRunTime = total time of event
        totalRunTime =((upTime +delayTime))+1;
        i++;
        showTime++;
        myHandler.post(myRunnable); //must go here to always run!
        if(i < upTime) { //upTime uses selected number
            //i++;
            //numberOfRepetitionsCount++;
            //showTime++; //DISPLAY FIGURE
            // up.setText("Down no button"); //where command to shut target will be.

            // num.setText(String.valueOf(i)); = avoid the RunTime error
           // myHandler.post(myRunnable); // relate this to a Runnable
            //  if (i==((upTime +delayTime)*x)){   //x=iterating total to cope with repetition


        //} else if (i==((upTime +delayTime))) {   //x=iterating total to cope with repetition
        } else if (i==((totalRunTime))) {   //x=iterating total to cope with repetition
            // if (iteration==numberOfRepetitions) {
            i = 0;
            iteration++;
            showTime = 0;
         //   myHandler.post(myRunnable);
            // x++;

        } else if (iteration==numberOfRepetitions){
            myTimer.cancel(); // stop the timer

            return;



    }
    }

    public void buttonOnClick(View v) {
        up.setText("UP");
        myHandler.removeCallbacksAndMessages(null);
        i=0;
        showTime=0;
        x=1; //resets to allow loop to work
        TimerTask myTask = new TimerTask() {

            public void run() {
                String upTimer = test.getText().toString(); //gets value for upTime (up time)
                upTime =Integer.parseInt(upTimer); //converts to int
                String delayTime1 = delay.getText().toString(); //gets value for down time
                delayTime=Integer.parseInt(delayTime1); //converts to int
                String delayTime2 = repeat.getText().toString(); //gets value for repetition
                numberOfRepetitions=Integer.parseInt(delayTime2); //converts to int


                //upTime=10;
                updateUI(); // updateUI method
            }

        };
        myTimer = new Timer(); // must go here to prevent crash on restart timer button
        myTimer.schedule(myTask,0,1000); // TimerTask, delay, period

    }
    public void buttonStop(View v) {

        myTimer.cancel();
        myTimer.purge();
        halt.setText("Halted!"); // stops loop thru timer
        up.setText("Down");
        i=0;
        showTime=0;
        myHandler.removeCallbacksAndMessages(null);
    }

    public void delayClick(View v) {

        String delayTime1 = delay.getText().toString(); //gets value for upTime
        delayTime=Integer.parseInt(delayTime1); //converts to int

    }
    }
