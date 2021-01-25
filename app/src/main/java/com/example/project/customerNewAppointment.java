package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;

public class customerNewAppointment extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btn7;

    public void  init () {
        btn7 = (Button) findViewById(R.id.button7);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_new_appointment);
        toolbar = findViewById(R.id.actionBarselectnewappointment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}