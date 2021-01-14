package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

public class deneme1 extends AppCompatActivity {
    private Toolbar actionBarLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme1);
        actionBarLogin = findViewById(R.id.action_barmyAppointments);
        setSupportActionBar(actionBarLogin);
        getSupportActionBar().setTitle("07:00-Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}