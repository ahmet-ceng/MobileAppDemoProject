package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings_customer extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnchangelocation;
    private Button btneditprofile;


    public void init(){
        toolbar = findViewById(R.id.action_barSettings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnchangelocation = findViewById(R.id.changelocation);
        btnchangelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Settings_customer.this, Customer_Changelocation.class);
                startActivity(intent);
            }
        });

        btneditprofile = findViewById(R.id.editprofile);
        btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Settings_customer.this, Customer_Editprofile.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }
}