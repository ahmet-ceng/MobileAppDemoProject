package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer_MyAppointments extends AppCompatActivity {
    private Toolbar toolbar;

    public void init(){
        toolbar = findViewById(R.id.action_barmyAppointments);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Appointments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);
        init();

    }

    private void userAppointment(){




    }
}