package com.example.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected boolean running = false;
    protected boolean reset = false;
    Thread th1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar p = (ProgressBar) findViewById(R.id.ProgressBar1);
        TextView t = (TextView) findViewById(R.id.TextView1);
        Button b = (Button) findViewById(R.id.Button1);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {

                    int temp = i;
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (reset == true){
                        reset = false;
                        System.out.println("Stopped Progress");
                        return;
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (running==false){
                                p.setProgress(0);
                                t.setText("");
                                reset = true;
                                System.out.println("Reset Progress Bar");
                                return;
                            }
                            p.setProgress(temp);
                            t.setText(temp + "%");
                        }
                    });
                }
            }
        };



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running==false){
                    th1 = new Thread(r);
                    running=true;
                    th1.start();
                    b.setText("Stop Thread");
                }
                else{
                    running=false;
                    b.setText("Start Thread");
                }
            }
        });
    }
}