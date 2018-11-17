package com.example.teguh.amikom.ui.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.teguh.amikom.R;
import com.example.teguh.amikom.preferences.UserPreferences;
import com.example.teguh.amikom.ui.login.LoginActivity;
import com.example.teguh.amikom.ui.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ProgresBar_Activity extends AppCompatActivity {

    ProgressBar progressBar;
    Handler handler;
    Runnable runnable;
    Timer timer;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progres_bar_);

        userPreferences = new UserPreferences(getBaseContext());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = (ProgressBar) findViewById(R.id.progresbar_id);
        progressBar.setVisibility(View.VISIBLE);

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                timer.cancel();
                progressBar.setVisibility(View.GONE);

            }
        };

         timer = new Timer();
         timer.schedule(new TimerTask() {
             @Override
             public void run() {

                 handler.post(runnable);
                 if (userPreferences.getIslogin()) {
                     Intent intent = new Intent(ProgresBar_Activity.this, MainActivity.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                     startActivity(intent);
                     finish();
                 } else {
                     Intent intent = new Intent(ProgresBar_Activity.this, LoginActivity.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                     startActivity(intent);
                     finish();
                 }

             }
         },9000,20000);


    }



    }

