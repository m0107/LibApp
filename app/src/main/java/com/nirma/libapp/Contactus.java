package com.nirma.libapp;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
                "Institute of Technology\n" +
                "Nirma University\n" +
                "Sarkhej-Gandhinagar Highway, \n" +
                "Post: Chandlodia, Via : Gota, \n" +
                "Ahmedabad - 382 481.\n" +
                "Gujarat, India\n" +
                "\n" +
                "E-mail: lib.it@nirmauni.ac.in\n" +
                "Phone No: +91-2717-241911 to 15\n" +
                "Fax No: +91-2717-241916-17\n";
        tv.setText(text);

        String timing =
                "Monday – Friday\n"+ "08:45 am to 06:15 pm\n\n" +
                "Saturday:\n"+"08:45 am to 01:00 pm\n\n" +
                "*Closed on Sundays & Public Holidays\n";
        tv2.setText(timing);



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
