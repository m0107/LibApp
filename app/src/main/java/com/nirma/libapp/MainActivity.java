package com.nirma.libapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.GridView;

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

        Bitmap search = BitmapFactory.decodeResource(this.getResources(), R.drawable.search);
        Bitmap papers = BitmapFactory.decodeResource(this.getResources(), R.drawable.papers);
        Bitmap periodic = BitmapFactory.decodeResource(this.getResources(), R.drawable.periodic);
        Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.elib);
        Bitmap newarrival = BitmapFactory.decodeResource(this.getResources(), R.drawable.newarr);
        Bitmap digital = BitmapFactory.decodeResource(this.getResources(), R.drawable.digital);

        Bitmap news = BitmapFactory.decodeResource(this.getResources(), R.drawable.news);

        Bitmap contact = BitmapFactory.decodeResource(this.getResources(), R.drawable.contact);

        Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.grid_item_anim);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);


        gridArray.add(new Item(userIcon,"E-Lib"));
        gridArray.add(new Item(digital,"Login"));
        gridArray.add(new Item(search,"Search"));
        gridArray.add(new Item(newarrival,"New Arrival"));
        gridArray.add(new Item(periodic,"Periodic Content"));
        gridArray.add(new Item(news,"News Clips"));
        gridArray.add(new Item(papers,"Exam Papers"));
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
                    case "E-Lib":
                        i = new Intent(MainActivity.this,ELib.class);
                        startActivity(i);
                        break;
                    case"Login":
                        i = new Intent(MainActivity.this,Login.class);
                        startActivity(i);
                        break;
                    case "Search":
                        i = new Intent(MainActivity.this,Search.class);
                        startActivity(i);
                        break;
                    case "Contact-us":
                        i = new Intent(MainActivity.this,Contactus.class);
                        startActivity(i);
                        break;
                    case "New Arrival":
                        i = new Intent(MainActivity.this,NewArrival.class);
                        startActivity(i);
                        break;
                    case "Periodic Content":
                        i = new Intent(MainActivity.this,PeriodicContent.class);
                        startActivity(i);
                        break;
                    case "News Clips":
                        i = new Intent(MainActivity.this,NewsClips.class);
                        startActivity(i);
                        break;
                    case "Exam Papers":
                        i = new Intent(MainActivity.this,ExamPapers.class);
                        startActivity(i);
                        break;
                }
            }
        });


    }
}
