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

        Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.home);
        Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.search);

        gridArray.add(new Item(homeIcon,"Home"));
        gridArray.add(new Item(userIcon,"LogIn"));
        gridArray.add(new Item(homeIcon,"Digital"));
        gridArray.add(new Item(userIcon,"Search"));
        gridArray.add(new Item(homeIcon,"Home"));
        gridArray.add(new Item(userIcon,"ContactUS"));
        gridArray.add(new Item(homeIcon,"Home"));
        gridArray.add(new Item(userIcon,"User"));
        gridArray.add(new Item(homeIcon,"Building"));


        gridView = (GridView) findViewById(R.id.gridView);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.customgridview, gridArray);
        gridView.setAdapter(customGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Item data = gridArray.get(pos);
                Intent i;
                switch(data.getTitle()){
                    case "LogIn":
                        i = new Intent(MainActivity.this,ELib.class);
                        startActivity(i);
                        break;
                    case "Digital":
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
                }
            }
        });
        Toast.makeText(getApplicationContext(),"mainActivity",Toast.LENGTH_LONG).show();

    }
}
