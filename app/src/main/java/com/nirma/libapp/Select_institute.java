package com.nirma.libapp;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Select_institute extends AppCompatActivity {




    Map<String,List<String>> map;
    List<String> institutes;
    RecyclerView recyclerView;
    Radpater radpater;
    String facility;
    String names[]={"Technology","Management","Pharmacy","Science","Law","Architecture","Commerce"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_institute);
        facility =getIntent().getExtras().getString("facility");
        Toolbar toolbar = (Toolbar) findViewById(R.id.InstituteList);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(facility);
        }



        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), R.drawable.divider));
        radpater = new Radpater(this,names);
        recyclerView.setAdapter(radpater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        map = new HashMap<>();
        putComingSoonData();     // add or remove Coming Soon links




    }

    public void itermSelected(int pos){
        if(checkComingSoon(facility,names[pos])){
            Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_LONG).show();
            return;
        }
        Intent i;
        switch (facility) {
            case "New Arrival":
                i = new Intent(Select_institute.this, NewArrival.class);
                i.putExtra("Institute",names[pos]);
                startActivity(i);
                Select_institute.this.finish();
                break;
            case "Content Page Service":
                i = new Intent(Select_institute.this, Content_Page_Service.class);
                i.putExtra("Institute",names[pos]);
                startActivity(i);
                Select_institute.this.finish();
                break;
            case "News Clips":
                i = new Intent(Select_institute.this, NewsClips.class);
                i.putExtra("Institute",names[pos]);
                startActivity(i);
                Select_institute.this.finish();
                break;
            case "Exam Papers":
                i = new Intent(Select_institute.this, ExamPapers.class);
                i.putExtra("Institute",names[pos]);
                startActivity(i);
                Select_institute.this.finish();
                break;
        }
    }



    public boolean checkComingSoon(String facilities,String institute){

        List<String> data = map.get(facilities);
        if(data.contains(institute)){
            return true;
        }
        return false;
    }


    public void putComingSoonData(){

        institutes = new ArrayList<>();
        institutes.add("Commerce");
        map.put("New Arrival",institutes);

        institutes = new ArrayList<>();
        institutes.add("Management");
        institutes.add("Pharmacy");
        institutes.add("Science");
        institutes.add("Law");
        institutes.add("Architecture");
        institutes.add("Commerce");
        map.put("Content Page Service",institutes);

        institutes = new ArrayList<>();
        institutes.add("Management");
        institutes.add("Science");
        institutes.add("Commerce");
        map.put("News Clips",institutes);

        institutes = new ArrayList<>();
        institutes.add("Management");
        institutes.add("Commerce");
        map.put("Exam Papers",institutes);

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
