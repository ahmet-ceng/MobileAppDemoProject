package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ownerPrices extends AppCompatActivity {
    private Toolbar actionBarLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth auth;

    private TextView priceHaircut,priceBeardShave,priceWax,priceHairWashing;
    private Button  btnup,btn2,btn19,btn6;
    private  TextView txtHaircut,txtBeardShave,txtWax,txtHairwashing;


    public void init(){
        actionBarLogin = (Toolbar) findViewById(R.id.actionBarLogin);
        setSupportActionBar(actionBarLogin);
        getSupportActionBar().setTitle("My Prices");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        priceHaircut= findViewById(R.id.textView3);
        priceBeardShave= findViewById(R.id.textView5);
        priceHairWashing= findViewById(R.id.textView11);
        priceWax= findViewById(R.id.textView9);

        btnup = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn19 = findViewById(R.id.button19);
        btn6 = findViewById(R.id.button6);

        txtHaircut = findViewById(R.id.textView3);
        txtBeardShave = findViewById(R.id.textView5);
        txtWax = findViewById(R.id.textView9);
        txtHairwashing = findViewById(R.id.textView11);

        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String changepriceHaircut =priceHaircut.getText().toString();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                HashMap map = new HashMap();
                map.put("Haircut", changepriceHaircut);
                reference.child("Prices").child("OnurSaloon").updateChildren(map);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String changeBeardShave =priceBeardShave.getText().toString();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                HashMap map = new HashMap();
                map.put("Beard Shave", changeBeardShave);
                reference.child("Prices").child("OnurSaloon").updateChildren(map);

            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String changeWax =priceWax.getText().toString();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                HashMap map = new HashMap();
                map.put("Wax", changeWax);
                reference.child("Prices").child("OnurSaloon").updateChildren(map);

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String changeHairWashing =priceHairWashing.getText().toString();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                HashMap map = new HashMap();
                map.put("Hair Washing", changeHairWashing);
                reference.child("Prices").child("OnurSaloon").updateChildren(map);

            }
        });


    }
    public void TakeValue() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Prices/OnurSaloon/Haircut");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Haircut = dataSnapshot.getValue(String.class);
                txtHaircut.setText(Haircut+"TL");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void TakeValue1() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Prices/OnurSaloon/Beard Shave");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String BeardShave = dataSnapshot.getValue(String.class);
                txtBeardShave.setText(BeardShave+"TL");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void TakeValue2() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Prices/OnurSaloon/Wax");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Wax = dataSnapshot.getValue(String.class);
                txtWax.setText(Wax+"TL");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void TakeValue3() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Prices/OnurSaloon/Hair Washing");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Hairwashing = dataSnapshot.getValue(String.class);
                txtHairwashing.setText(Hairwashing+"TL");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_prices);

        init();
        TakeValue();
        TakeValue1();
        TakeValue2();
        TakeValue3();

    }
}