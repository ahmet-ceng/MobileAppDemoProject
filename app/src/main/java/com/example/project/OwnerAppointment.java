package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OwnerAppointment extends AppCompatActivity {
    private Toolbar actionBarLogin;
    private Button btn1,btn2;

    public void init(){
        actionBarLogin = (Toolbar) findViewById(R.id.actionBarLogin);
        setSupportActionBar(actionBarLogin);
        getSupportActionBar().setTitle("My Appointments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_appointment);

        init();
        btn1 = (Button) findViewById(R.id.button7);
        btn2 = (Button) findViewById(R.id.button12);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open2();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open1();
            }
        });
    }
    public void open1(){
        Intent intent = new Intent(this, deneme1.class);
        startActivity(intent);
    };
    public void open2(){
        Intent intent = new Intent(this, deneme2.class);
        startActivity(intent);
    };
}