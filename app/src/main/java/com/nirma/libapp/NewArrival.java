package com.nirma.libapp;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class NewArrival extends AppCompatActivity {

    WebView browse;
    WebSettings ws;

    String myUrl;
    private static final String googleUrl="https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fsites.google.com%2Fa%2Fnirmauni.ac.in%2Fcontent%2F%3Fpli%3D1&followup=https%3A%2F%2Fsites.google.com%2Fa%2Fnirmauni.ac.in%2Fcontent%2F%3Fpli%3D1&btmpl=mobile&hd=nirmauni.ac.in&service=jotspot&sacu=1&rip=1#identifier";
    private ProgressDialog progressDialog=null;
    private boolean isredirected = false;
    private Handler TimeOutHandler;
    private Runnable runnable;
    private String File_Name = "Sample.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch(getIntent().getExtras().getString("Institute"))
        {
            case "Technology":
                myUrl="http://librarysearch.nirmauni.ac.in/cgi-bin/koha/opac-search.pl?q=ccode=ITDIS";
                break;

            case "Management":
                myUrl="http://librarysearch.nirmauni.ac.in/cgi-bin/koha/opac-search.pl?q=ccode=IMDIS";
                break;
            case "Pharmacy":
                myUrl="http://librarysearch.nirmauni.ac.in/cgi-bin/koha/opac-search.pl?q=ccode=IPDIS";
                break;
            case "Science":
                myUrl="http://librarysearch.nirmauni.ac.in/cgi-bin/koha/opac-search.pl?q=ccode=ISDIS";
                break;
            case "Law":
                myUrl="http://librarysearch.nirmauni.ac.in/cgi-bin/koha/opac-search.pl?q=ccode=ILDIS";
                break;
            case "Architecture":
                myUrl="http://librarysearch.nirmauni.ac.in/cgi-bin/koha/opac-search.pl?q=ccode=IADIS";
                break;
            case "Commerce":
                myUrl="coming-soon";
                break;
        }


        setContentView(R.layout.activity_new_arrival);
        Toolbar toolbar = (Toolbar) findViewById(R.id.newArraivaltoolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        browse = (WebView) findViewById(R.id.newArrivalwebView);
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
        browse.loadUrl(myUrl);
        // FOR ANY DOWNLOAD WE HAVE TO INCLUDE THIS CODE

        browse.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, File_Name); // "every file will be saved as paper.pdf"
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT); //This is important!
                intent.addCategory(Intent.CATEGORY_OPENABLE); //CATEGORY.OPENABLE
                Toast.makeText(getApplicationContext(), "Downloading File", //To notify the Client that the file is being downloaded
                        Toast.LENGTH_LONG).show();


            }
        });

    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(Uri.parse(url).getHost().equals(myUrl+"/")){
                return false;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            isredirected = false;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            if(!isredirected){
                if(progressDialog==null){
                    progressDialog = new ProgressDialog(NewArrival.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(false);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                    runnable = new  Runnable() {
                        @Override
                        public void run() {
                            if(progressDialog!=null && progressDialog.isShowing()) {
                                final Intent mainIntent = new Intent(NewArrival.this, MainActivity.class);
                                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                browse.stopLoading();
                                startActivity(mainIntent);
                                NewArrival.this.finish();
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
        if(browse.getUrl().equals(myUrl) || browse.getUrl().equals(googleUrl)){
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
