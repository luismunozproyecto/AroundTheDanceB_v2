package com.example.paxi.aroundthedanceb.Activities;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.paxi.aroundthedanceb.R;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityCarga extends Activity
{
    private static final long SPLASH_SCREEN_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paginadecarga);

        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                Intent mainIntent = new Intent().setClass(ActivityCarga.this, ActivityInicio.class);
                startActivity(mainIntent);

                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

}
