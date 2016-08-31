package com.nirma.libapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.maintoolbar);
        setSupportActionBar(toolbar);

        Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.home);
        Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.search);


        gridArray.add(new Item(userIcon,"E-Lib"));
        gridArray.add(new Item(homeIcon,"DigitalContent"));
        gridArray.add(new Item(userIcon,"Search"));
        gridArray.add(new Item(homeIcon,"NewArrival"));
        gridArray.add(new Item(userIcon,"PeriodicContents"));
        gridArray.add(new Item(homeIcon,"NewsClips"));
        gridArray.add(new Item(userIcon,"ExamPapers"));
        gridArray.add(new Item(homeIcon,"ContactUS"));


        gridView = (GridView) findViewById(R.id.gridView);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.customgridview, gridArray);
        gridView.setAdapter(customGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Item data = gridArray.get(pos);
                Intent i;
                switch(data.getTitle()){
                    case "E-Lib":
                        i = new Intent(MainActivity.this,ELib.class);
                        startActivity(i);
                        break;
                    case "DigitalContent":
                        i = new Intent(MainActivity.this,DigContent.class);
                        startActivity(i);
                        break;
                    case "Search":
                        i = new Intent(MainActivity.this,Search.class);
                        startActivity(i);
                        break;
                    case "ContactUS":
                        i = new Intent(MainActivity.this,Contactus.class);
                        startActivity(i);
                        break;
                    case "NewArrival":
                        i = new Intent(MainActivity.this,NewArrival.class);
                        startActivity(i);
                        break;
                    case "PeriodicContents":
                        i = new Intent(MainActivity.this,PeriodicContent.class);
                        startActivity(i);
                        break;
                    case "NewsClips":
                        i = new Intent(MainActivity.this,NewsClips.class);
                        startActivity(i);
                        break;
                    case "ExamPapers":
                        i = new Intent(MainActivity.this,ExamPapers.class);
                        startActivity(i);
                        break;
                }
            }
        });


    }
}
