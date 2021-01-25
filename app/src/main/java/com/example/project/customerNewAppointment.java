package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class customerNewAppointment extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18;
    private static final String TAG = "MyActivity";

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth auth;

    public void init() {
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btn10 = findViewById(R.id.button10);
        btn11= findViewById(R.id.button11);
        btn12= findViewById(R.id.button12);
        btn13= findViewById(R.id.button13);
        btn14= findViewById(R.id.button14);
        btn15= findViewById(R.id.button15);
        btn16= findViewById(R.id.button16);
        btn17= findViewById(R.id.button17);
        btn18= findViewById(R.id.button18);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_new_appointment);
        toolbar = findViewById(R.id.actionBarselectnewappointment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auth = FirebaseAuth.getInstance();

        init();
        checkuser();
        onCliks();
    }


    public void onCliks(){
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("usertype", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                String full = "1";

                HashMap map = new HashMap();
                map.put("7", full);

                reference.updateChildren(map);

                reference.child("Appointments").updateChildren(map);
            }
        });





    }


    private void checkuser() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String[] hours = {"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18"};
        final Button[] BtnArray = new Button[] { btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18};
        int counter = -1;
        for (final String i : hours) {
            counter++;

            DatabaseReference myRef = database.getReference("Appointments/" + i);
            Log.e(TAG,"myref"+ myRef+"  i :"+i+ "counter  "+counter);

            final String empty = "0";
            final String full = "1";
            final int finalCounter = counter;
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String appointment = dataSnapshot.getValue(String.class);
                    Log.e(TAG, "onDataChange: " + appointment);

                    if (appointment.equals(full)) {
                        BtnArray[finalCounter].setBackgroundColor(getResources().getColor(R.color.red));

                        Log.e(TAG, "button : " + BtnArray[finalCounter]);

                    } else if (appointment.equals(empty)){
                        Log.e(TAG, "button : " + BtnArray[finalCounter]);
                        BtnArray[finalCounter].setBackgroundColor(getResources().getColor(R.color.green));


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "yaaaaaaaaaaaa: ");

                }
            });

        }
    }
}