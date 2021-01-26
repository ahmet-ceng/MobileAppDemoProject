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

public class Customer_BarberDetails extends AppCompatActivity {
    private Toolbar toolbar;
    Button saloon1, other;

    public void init(){
        toolbar = findViewById(R.id.action_barBarberDetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Barber Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        saloon1 = (Button) findViewById(R.id.barbersaloon1);
        other = (Button) findViewById(R.id.btnother);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_details);
        init();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Saloon/OnurSaloon/Name");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String saloon = dataSnapshot.getValue(String.class);
                saloon1.setText(saloon);
                saloon1.setBackgroundColor(getResources().getColor(R.color.green));
                other.setText("no other place near you");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saloon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_BarberDetails.this,onursSaloon.class);
                startActivity(intent);
            }
        });

    }
}