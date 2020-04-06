package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class PrivacyPolicy_activity extends AppCompatActivity {

    WebView privacy_policy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy_activity);

        privacy_policy = findViewById(R.id.privacy);



        privacy_policy.setWebViewClient(new WebViewClient());
        privacy_policy.loadUrl("https://streamic-0.flycricket.io/privacy.html");


    }
}
