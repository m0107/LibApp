package com.nirma.libapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Contactus extends AppCompatActivity {

    TextView tv,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
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
                "Phone No: +91 - 2717 - 241911 to 15\n" +
                "Fax No: +91 - 2717 - 241916 - 17\n";
        tv.setText(text);

        String timing = "Library Timing\n" +
                "Monday – Friday: 08.45 am to 09.15 pm\n" +
                "Saturday: 08.45 to 04.00\n" +
                "Closed on Sundays & Public Holidays\n";
        tv2.setText(timing);



    }
}
