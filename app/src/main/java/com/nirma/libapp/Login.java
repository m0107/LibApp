package com.nirma.libapp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    WebView browse;
    WebSettings ws;
    private static final String url = "http://librarysearch.nirmauni.ac.in/cgi-bin/koha/opac-user.pl";
    private ProgressDialog progressDialog=null;
    private boolean isredirected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Digitalttoolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        browse = (WebView) findViewById(R.id.digwebView);
        browse.setWebViewClient(new MyWebViewClient());
        ws = browse.getSettings();
        ws.setJavaScriptEnabled(true);
        browse.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(Uri.parse(url).getHost().equals(url+"/")){
                return false;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            isredirected = false;
            Log.d("pagestart:","true");
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.d("pageLoad1:","true");
            if(!isredirected){
                if(progressDialog==null){
                    progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(true);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            isredirected=true;
            Log.d("pagefin:","true");

            if (progressDialog!=null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        }
    }

    @Override
    public void onBackPressed() {
        Log.d("url:",browse.getUrl());




        if(browse.getUrl().equals(url)){
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
