package com.nirma.libapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Login_Library_Account extends AppCompatActivity {

    WebView browse;
    WebSettings ws;
    private static final String url = "http://librarysearch.nirmauni.ac.in/cgi-bin/koha/opac-user.pl";
    private ProgressDialog progressDialog=null;
    private boolean isredirected = false;
    private Handler TimeOutHandler;
    private Runnable runnable;

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
        TimeOutHandler = new Handler();
        browse.setWebViewClient(new MyWebViewClient());
        ws = browse.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= 19) {
            browse.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            browse.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
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
                    progressDialog = new ProgressDialog(Login_Library_Account.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("You can see the status of your Library Account with Library Login_Library_Account and Password");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setTitle("Loading...");
                    progressDialog.show();

                    runnable = new  Runnable() {
                        @Override
                        public void run() {
                            if(progressDialog!=null && progressDialog.isShowing()) {
                                final Intent mainIntent = new Intent(Login_Library_Account.this, MainActivity.class);
                                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                browse.stopLoading();
                                startActivity(mainIntent);
                                Login_Library_Account.this.finish();
                                Toast.makeText(getApplication(),"Slow Internet Connection",Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    TimeOutHandler.postDelayed(runnable, 60000);

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
                TimeOutHandler.removeCallbacks(runnable);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.info) {


            Toast.makeText(getApplicationContext(),R.string.loginInfo , Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }




}
