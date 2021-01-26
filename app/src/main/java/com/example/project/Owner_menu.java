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

public class Owner_menu extends AppCompatActivity {
    private Button btn_mySaloon, btn_Owner_Appointments, btn_ownerPrices;
    private FirebaseAuth auth;
    private Toolbar toolbar;
    FirebaseDatabase rootNode;
    private String currentuserid;
    DatabaseReference reference;
    private TextView welcome;

    public void init(){
        btn_mySaloon = (Button) findViewById(R.id.btnMySaloon);
        btn_Owner_Appointments = (Button) findViewById(R.id.btnOwnerAppointments);
        btn_ownerPrices = (Button) findViewById(R.id.ownerPrices);
        welcome = findViewById(R.id.welcometxt);

        auth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.actionBarLogin);
        setSupportActionBar(toolbar);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.Message){

        }
        if (item.getItemId()==R.id.Settings){
            Intent intent =new Intent(Owner_menu.this, Settings_owner.class);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.LogOut) {
            auth.signOut();
            Intent intent = new Intent(Owner_menu.this, SignIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_menu);
        init();
        welcome();
        btn_mySaloon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_ownerSaloonActivity();
            }
        });

        btn_Owner_Appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_ownerAppointmentsActivity();
            }
        });

        btn_ownerPrices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_ownerPricesActivity();
            }
        });


    }


    public void open_ownerSaloonActivity(){
        Intent intent = new Intent(this, Mysaloon.class);
        startActivity(intent);
    }
    public void open_ownerAppointmentsActivity(){
        Intent intent = new Intent(this, OwnerAppointment.class);
        startActivity(intent);
    }

    public void open_ownerPricesActivity(){
        Intent intent = new Intent(this, ownerPrices.class);
        startActivity(intent);
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
    }