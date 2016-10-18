package com.nirma.libapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.DownloadListener;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;



public class ExamPapers extends AppCompatActivity {

    WebView browse;
    WebSettings ws;
    private static final String MyUrl = "https://sites.google.com/a/nirmauni.ac.in/library/";
    private static final String googleUrl="https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fsites.google.com%2Fa%2Fnirmauni.ac.in%2Flibrary%2F&followup=https%3A%2F%2Fsites.google.com%2Fa%2Fnirmauni.ac.in%2Flibrary%2F&btmpl=mobile&hd=nirmauni.ac.in&service=jotspot&sacu=1&rip=1#identifier";
    private ProgressDialog progressDialog=null;
    private boolean isredirected = false;
    private Handler TimeOutHandler;
    private Runnable runnable;
    private String File_Name = "Sample.pdf";
    private boolean Permission_Granted = false;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 100;

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
        TimeOutHandler = new Handler();
        browse.setWebViewClient(new MyWebViewClient());
        ws = browse.getSettings();
        ws.setJavaScriptEnabled(true);
        browse.loadUrl(MyUrl);

        // FOR ANY DOWNLOAD WE HAVE TO INCLUDE THIS CODE

        browse.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));
                try {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, File_Name); // "every file will be saved as paper.pdf"
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    dm.enqueue(request);
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT); //This is important!
                    intent.addCategory(Intent.CATEGORY_OPENABLE); //CATEGORY.OPENABLE
                    Toast.makeText(getApplicationContext(), "Downloading File", //To notify the Client that the file is being downloaded
                            Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    if(!Permission_Granted){
                        Toast.makeText(getApplicationContext(),"Try again After Allowing Permission",Toast.LENGTH_LONG).show();
                        checkPermission();
                    }
                }


            }
        });

        checkPermission();



    }

    public boolean checkPermission()
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(ExamPapers.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(ExamPapers.this, Manifest.permission.WRITE_CALENDAR)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ExamPapers.this);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission Necessary");
                    alertBuilder.setMessage("Write SDCard permission is necessary to Download files!!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(ExamPapers.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions(ExamPapers.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Permission_Granted = true;
                    //Toast.makeText(getApplicationContext(),"permission granted",Toast.LENGTH_SHORT).show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Permission_Granted = false;
                    Toast.makeText(getApplicationContext(),"You won't be able to Download the Files",Toast.LENGTH_SHORT).show();
                }

            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(Uri.parse(url).getHost().equals(MyUrl+"/")){
                return false;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            if(url.substring(url.lastIndexOf('.')+1).equals("pdf")){
                File_Name = url.substring(url.lastIndexOf("/")+1);

            }




            isredirected = false;
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            if(!isredirected){
                if(progressDialog==null){
                    progressDialog = new ProgressDialog(ExamPapers.this){
                       /* @Override
                        public void onBackPressed() {
                            super.onBackPressed();
                            browse.stopLoading();
                            progressDialog.cancel();
                            progressDialog.dismiss();
                        }*/
                    };
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(false);
                    //progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    runnable = new  Runnable() {
                        @Override
                        public void run() {
                            if(progressDialog!=null && progressDialog.isShowing()) {
                                final Intent mainIntent = new Intent(ExamPapers.this, MainActivity.class);
                                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                browse.stopLoading();
                                startActivity(mainIntent);
                                ExamPapers.this.finish();
                                Toast.makeText(getApplication(),"Slow Internet Connection",Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                   TimeOutHandler.postDelayed(runnable, 60000);
                    // Time Scheduler method
                    /*TimerTask task = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            Intent intent = new Intent(ExamPapers.this, MainActivity.class);
                            startActivity(intent);
                            ExamPapers.this.finish();
                        }
                    };
                    Timer t = new Timer();
                    t.schedule(task, 5000);*/
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

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            Log.d("webError",error.toString());
            Toast.makeText(getApplicationContext(),"You have not given Write permission to App",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        Log.d("url:",browse.getUrl());
        if(browse.getUrl().equals(MyUrl) || browse.getUrl().equals(googleUrl)){
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
