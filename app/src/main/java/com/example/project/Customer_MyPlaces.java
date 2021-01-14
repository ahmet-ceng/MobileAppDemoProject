package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class Customer_MyPlaces extends AppCompatActivity {
    private Toolbar toolbar;

    public void init(){
        toolbar = findViewById(R.id.action_barmyPlaces);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Places");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);
        init();
    }
}