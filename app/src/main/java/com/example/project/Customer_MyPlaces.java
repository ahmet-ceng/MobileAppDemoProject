package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Customer_MyPlaces extends AppCompatActivity {
    private Toolbar toolbar;
    Button btn1;

    public void init(){
        toolbar = findViewById(R.id.action_barmyPlaces);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Places");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn1 = findViewById(R.id.mysaloons1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places);
        init();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Saloon/OnurSaloon/Name");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                btn1.setText(name);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_MyPlaces.this, onursSaloon.class);
                startActivity(intent);

            }
        });


    }
}