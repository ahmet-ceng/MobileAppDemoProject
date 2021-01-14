package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class Customer_BarberDetails extends AppCompatActivity {
    private Toolbar toolbar;

    public void init(){
        toolbar = findViewById(R.id.action_barBarberDetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Barber Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_details);
        init();
    }
}