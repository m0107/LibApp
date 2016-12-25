package com.nirma.libapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }
    void mis(View view)
    {

        Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_LONG).show();
    }
    void lib(View view)
    {
        Intent intent = new Intent(Splash.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
