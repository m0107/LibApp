package com.nirma.libapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.TextView;

public class Contactus extends AppCompatActivity {

    TextView tv,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.contacttoolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        tv = (TextView) findViewById(R.id.contact);
        tv2 = (TextView) findViewById(R.id.timing);
        String text = "The Librarian\n" +
                "Nirma University\n" +
                "Library Resource Center\n" +
                "Sarkhej-Gandhinagar Highway, \n" +
                "Post: Chandlodia, Via : Gota, \n" +
                "Ahmedabad - '382481'.\n" +
                "Gujarat, India\n" +
                "\n";
        tv.setText(text);

        String email ="Technology\n" +
                "lib.it@nirmauni.ac.in\n\n" +
                "Management\n" +
                "lib.im@nirmauni.ac.in\n\n" +
                "Pharmacy\n" +
                "lib.ip@nirmauni.ac.in\n\n" +
                "Science\n" +
                "lib_is@nirmauni.ac.in\n\n" +
                "Law\n" +
                "library.il@nirmauni.ac.in\n\n" +
                "Architecture\n" +
                "lib.ia@nirmauni.ac.in\n\n" +
                "Commerce\n" +
                "lib.im@nirmauni.ac.in";

        tv2.setText(email);



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
