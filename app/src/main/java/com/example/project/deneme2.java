package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class deneme2 extends AppCompatActivity {
    private Toolbar actionBarLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme2);
        actionBarLogin = findViewById(R.id.action_barmyAppointments);
        setSupportActionBar(actionBarLogin);
        getSupportActionBar().setTitle("12:00-Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}