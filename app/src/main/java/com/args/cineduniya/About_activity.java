package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class About_activity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_activity);

        //for the top left back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        webView= findViewById(R.id.about_webview);
        progressBar = findViewById(R.id.progressBarwebabout);
        webView.setWebViewClient(new WebViewClient()
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
        webView.loadUrl("https://streamicmedia.web.app/about.html");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
