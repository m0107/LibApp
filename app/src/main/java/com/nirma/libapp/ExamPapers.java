package com.nirma.libapp;

import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ExamPapers extends AppCompatActivity {

    WebView browse;
    WebSettings ws;
    private static final String MyUrl = "https://sites.google.com/a/nirmauni.ac.in/library/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_papers);


        Toolbar toolbar = (Toolbar) findViewById(R.id.Exampaperstoolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        browse = (WebView) findViewById(R.id.ExampaperswebView);
        browse.setWebViewClient(new MyWebViewClient());
        ws = browse.getSettings();
        ws.setJavaScriptEnabled(true);
        browse.loadUrl(MyUrl);
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(Uri.parse(url).getHost().equals(MyUrl+"/")){
                return false;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public void onBackPressed() {

        Log.d("url:",browse.getUrl());
        if(browse.getUrl().equals(MyUrl)){
            this.finish();

        }
        else{
            browse.goBack();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;



        }

        return super.onOptionsItemSelected(item);
    }
}
