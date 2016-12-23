package com.nirma.libapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class  MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;
    float columnDelay=0.1f,rowDelay=0.1f;
    Animation animation;
    GridLayoutAnimationController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.maintoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.nu);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(" NU Library");



        Bitmap search = BitmapFactory.decodeResource(this.getResources(), R.drawable.search);
        Bitmap papers = BitmapFactory.decodeResource(this.getResources(), R.drawable.papers);
        Bitmap periodic = BitmapFactory.decodeResource(this.getResources(), R.drawable.periodic);
        Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.laptop);
        Bitmap newarrival = BitmapFactory.decodeResource(this.getResources(), R.drawable.newarr);
        Bitmap login = BitmapFactory.decodeResource(this.getResources(), R.drawable.login);

        Bitmap news = BitmapFactory.decodeResource(this.getResources(), R.drawable.news);

        Bitmap contact = BitmapFactory.decodeResource(this.getResources(), R.drawable.contact);

        animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.grid_item_anim);
         controller = new GridLayoutAnimationController(animation, columnDelay, rowDelay);


        gridArray.add(new Item(login,"Login-Library Account"));
        gridArray.add(new Item(search,"Search"));
        gridArray.add(new Item(userIcon,"Remote Access"));
        gridArray.add(new Item(newarrival,"New Arrival"));
        gridArray.add(new Item(news,"News Clips"));
        gridArray.add(new Item(papers,"Exam Papers"));

        gridArray.add(new Item(periodic,"Content Page Service"));
        gridArray.add(new Item(contact,"Contact-us"));


        gridView = (GridView) findViewById(R.id.gridView);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.customgridview, gridArray);
        gridView.setAdapter(customGridAdapter);
        gridView.setLayoutAnimation(controller);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Item data = gridArray.get(pos);
                Intent i;
                switch(data.getTitle()){
                    case "Remote Access":
                        i = new Intent(MainActivity.this,Remote_Access.class);
                        i.putExtra("facility","Remote Access");
                        startActivity(i);
                        break;
                    case"Login-Library Account":
                        i = new Intent(MainActivity.this,Login_Library_Account.class);
                        i.putExtra("facility","Login-Library Account");
                        startActivity(i);
                        break;
                    case "Search":
                        i = new Intent(MainActivity.this,Search.class);
                        i.putExtra("facility","Search");
                        startActivity(i);
                        break;
                    case "Contact-us":
                        i = new Intent(MainActivity.this,Contactus.class);
                        i.putExtra("facility","Contact-us");
                        startActivity(i);
                        break;
                    case "New Arrival":
                        i = new Intent(MainActivity.this,Select_institute.class);
                        i.putExtra("facility","New Arrival");
                        startActivity(i);
                        break;
                    case "Content Page Service":
                        i = new Intent(MainActivity.this,Select_institute.class);
                        i.putExtra("facility","Content Page Service");
                        startActivity(i);
                        break;
                    case "News Clips":
                        i = new Intent(MainActivity.this,Select_institute.class);
                        i.putExtra("facility","News Clips");
                        startActivity(i);
                        break;
                    case "Exam Papers":
                        i = new Intent(MainActivity.this,Select_institute.class);
                        i.putExtra("facility","Exam Papers");
                        startActivity(i);
                        break;
                }
            }
        });


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
            Intent i=new Intent(MainActivity.this,AboutLib.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
         controller = new GridLayoutAnimationController(animation, columnDelay, rowDelay);
        columnDelay=0f;
        rowDelay=0f;

    }

    @Override
    protected void onResume() {
        super.onResume();
         controller = new GridLayoutAnimationController(animation, columnDelay, rowDelay);
        columnDelay=0f;
        rowDelay=0f;

    }
}
