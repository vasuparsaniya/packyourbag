package com.example.packyourbag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.packyourbag.Adapter.Adapter;
import com.example.packyourbag.Constants.MyConstants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> titles;
    List<Integer> images;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerView);

        addAllTitles();
        addAllImages();

        adapter = new Adapter(this,titles,images,MainActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    //for back from application
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if(mBackPressed+TIME_INTERVAL>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{

            Toast.makeText(this, "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

    private void addAllTitles(){
        titles = new ArrayList<>();
        titles.add(MyConstants.BASIC_NEEDS_CAMEL_CASE);
        titles.add(MyConstants.CLOTHING_CAMEL_CASE);
        titles.add(MyConstants.PERSONAL_CARE_CAMEL_CASE);
        titles.add(MyConstants.BABY_NEEDS_CAMEL_CASE);
        titles.add(MyConstants.HEALTH_CAMEL_CASE);
        titles.add(MyConstants.TECHNOLOGY_CAMEL_CASE);
        titles.add(MyConstants.FOOD_CAMEL_CASE);
        titles.add(MyConstants.BEACH_SUPPLIES_CAMEL_CASE);
        titles.add(MyConstants.CAR_SUPPLIES_FOOD_CAMEL_CASE);
        titles.add(MyConstants.NEEDS_CAMEL_CASE);
        titles.add(MyConstants.MY_LIST_CAMEL_CASE);
        titles.add(MyConstants.MY_SELECTIONS_CAMEL_CASE);
    }

    private void addAllImages(){
        images = new ArrayList<>();
        images.add(R.drawable.p1);
        images.add(R.drawable.p2);
        images.add(R.drawable.p3);
        images.add(R.drawable.p4);
        images.add(R.drawable.p5);
        images.add(R.drawable.p6);
        images.add(R.drawable.p7);
        images.add(R.drawable.p8);
        images.add(R.drawable.p9);
        images.add(R.drawable.p10);
        images.add(R.drawable.p11);
        images.add(R.drawable.p12);

    }
}