package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer_PlacesNearMe extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnBarber;
    private Button btnHairdresser;

    public void init(){
        toolbar = findViewById(R.id.action_barplacesnearme);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Places Near Me");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnBarber= findViewById(R.id.barber);
        btnBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_PlacesNearMe.this, Customer_BarberDetails.class);
                startActivity(intent);
            }
        });
        btnHairdresser = findViewById(R.id.hairdressers);
        btnHairdresser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_PlacesNearMe.this, Customer_HairdresserDetails.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_near_me);
        init();
    }
}