package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class owner_saloon extends AppCompatActivity {
    private Button btncustomer;
    private Button btn_owner;

    public void init(){
        btncustomer = findViewById(R.id.btncustomer);
        btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(owner_saloon.this, Customer_MainActivity.class);
                startActivity(intent);
            }
        });
        btn_owner = findViewById(R.id.ownerButton);
        btn_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(owner_saloon.this, Owner_menu.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_saloon);
        init();
    }
}