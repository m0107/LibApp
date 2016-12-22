package com.nirma.libapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Select_institute extends AppCompatActivity {



   ListView instituteNameList;
    Map<String,List<String>> map;
    List<String> institutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_institute);
        final String names[]={"Technology","Management","Pharmacy","Science","Law","Architecture","Commerce"};
        final String facility=getIntent().getExtras().getString("facility");

        map = new HashMap<>();
        putComingSoonData();     // add or remove Coming Soon links

        instituteNameList=(ListView)findViewById(R.id.instituteName);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        instituteNameList.setAdapter(adapter);

        instituteNameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

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
        });

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


}
