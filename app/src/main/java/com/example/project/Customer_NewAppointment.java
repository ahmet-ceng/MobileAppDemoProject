package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer_NewAppointment extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnplacesnearme, test1, test2, test3;

    public void init(){
        toolbar = findViewById(R.id.action_barnewAppointment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        test1 = findViewById(R.id.saloon1);
        test2 = findViewById(R.id.saloon2);
        test3 = findViewById(R.id.saloon3);

        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_NewAppointment.this, customerNewAppointment.class);
                startActivity(intent);
            }
        });
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_NewAppointment.this, customerNewAppointment.class);
                startActivity(intent);
            }
        });
        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_NewAppointment.this, customerNewAppointment.class);
                startActivity(intent);
            }
        });
        btnplacesnearme = findViewById(R.id.placesnearme);
        btnplacesnearme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_NewAppointment.this, Customer_PlacesNearMe.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);
        init();
    }
}