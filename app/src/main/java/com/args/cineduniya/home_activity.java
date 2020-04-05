package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class home_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
