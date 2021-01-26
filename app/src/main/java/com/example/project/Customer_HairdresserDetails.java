package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Customer_HairdresserDetails extends AppCompatActivity {
    private Toolbar toolbar;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth auth;
    Button saloon1;

    public void init(){
        toolbar = findViewById(R.id.action_barHairdrasserDetails);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hairdrasser Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        saloon1 = (Button) findViewById(R.id.hairsaloon1);
        auth = FirebaseAuth.getInstance();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hairdresser_details);
        init();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("NearPlaces/Hairdressers/other");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String other = dataSnapshot.getValue(String.class);
                saloon1.setText("no hairdressers near you");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}