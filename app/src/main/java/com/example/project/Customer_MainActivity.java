package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Customer_MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnmyplaces;
    private Button Btnmyappointment;
    private Button btnnewappointment;
    private Button btnplacesnearme;
    FirebaseDatabase rootNode;
    private String currentuserid;
    DatabaseReference reference;

    private TextView welcome;
    private FirebaseAuth auth;
    public void init(){
        auth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.action_barMain);
        setSupportActionBar(toolbar);
        welcome = findViewById(R.id.welcome);
        btnmyplaces = findViewById(R.id.myplaces);
        btnmyplaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_MainActivity.this, Customer_MyPlaces.class);
                startActivity(intent);
            }
        });

        Btnmyappointment = findViewById(R.id.myappointments);
        Btnmyappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_MainActivity.this, Customer_MyAppointments.class);
                startActivity(intent);
            }
        });
        btnnewappointment = findViewById(R.id.newappoitnment);
        btnnewappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_MainActivity.this, Customer_NewAppointment.class);
                startActivity(intent);
            }
        });
        btnplacesnearme = findViewById(R.id.placesnearme);
        btnplacesnearme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_MainActivity.this, Customer_PlacesNearMe.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        welcome();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    public void welcome(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        currentuserid = auth.getCurrentUser().getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users/"+currentuserid+"/firstName");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String id = dataSnapshot.getValue(String.class);
                welcome.setText("Welcome "+id);
                welcome.setTextColor(getResources().getColor(R.color.white));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.Message){

        }
        if (item.getItemId()==R.id.Settings){
            Intent intent =new Intent(Customer_MainActivity.this, Settings_customer.class);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.LogOut) {
            auth.signOut();
            Intent intent = new Intent(Customer_MainActivity.this, SignIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return true;
    }
}