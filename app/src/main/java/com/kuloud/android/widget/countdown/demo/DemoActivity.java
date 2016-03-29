package com.kuloud.android.widget.countdown.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kuloud.android.widget.countdown.CountDownCallback;
import com.kuloud.android.widget.countdown.CountDownProgressBar;
import com.kuloud.android.widget.countdown.CountDownTextView;

import java.util.Random;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CountDownTextView countDownTextView = (CountDownTextView) findViewById(R.id.count_down_text);
        countDownTextView.setCallback(new CountDownCallback() {
            @Override
            public void onCountDown(int second) {
                if (second <= 0) {
                    countDownTextView.setText("done");
                }
            }
        });

        final CountDownProgressBar countDownProgressBar = (CountDownProgressBar) findViewById(R.id.count_down_progress_bar);
        countDownProgressBar.setCallback(new CountDownCallback() {
            @Override
            public void onCountDown(int second) {
            }
        });

        final CountDownProgressBar countDownProgressBar2 = (CountDownProgressBar) findViewById(R.id.count_down_progress_bar_horizontal);
        countDownProgressBar2.setCallback(new CountDownCallback() {
            @Override
            public void onCountDown(int second) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int countNum = new Random().nextInt(30);
                countDownTextView.countDown(countNum);
                countDownProgressBar.countDown(countNum);
                countDownProgressBar2.countDown(countNum);
            }
        });
    }
}
