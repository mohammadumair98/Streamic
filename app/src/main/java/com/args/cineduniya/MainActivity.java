package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    public int SPLASH_TIME = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(MainActivity.this, Login_activity.class);
                startActivity(login);
                finish();
            }
        },SPLASH_TIME);
    }
}
