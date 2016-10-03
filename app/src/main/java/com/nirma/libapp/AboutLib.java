package com.nirma.libapp;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

public class AboutLib extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_lib);

        Toolbar toolbar = (Toolbar) findViewById(R.id.AboutLibtoolbar);
        tv = (TextView) findViewById(R.id.aboutus);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tv.setText(Html.fromHtml("<div style='text-align: justify;text-justify: inter-word;'>"+getResources().getString(R.string.about_us)+"</div>"));


    }
}
