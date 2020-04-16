package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class PrivacyPolicy_activity extends AppCompatActivity {

    WebView privacy_policy;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy_activity);

        //for the top left back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        privacy_policy = findViewById(R.id.privacy);
        progressBar = findViewById(R.id.progressBarprivacy);



        privacy_policy.setWebViewClient(new WebViewClient()
        {
            //when web view starts loading
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
        privacy_policy.loadUrl("https://streamicmedia.web.app/privacy.html");


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
