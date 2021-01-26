package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Customer_MyAppointments extends AppCompatActivity {
    private Toolbar toolbar;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth auth;
    private TextView txtmessage;
    String currentuserid;
    Button changeappo, cancelappo;
    private static final String TAG = "MyActivity";


    public void init(){
        toolbar = findViewById(R.id.action_barmyAppointments);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Appointments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auth = FirebaseAuth.getInstance();
        txtmessage = (TextView) findViewById(R.id.txtmessage);
        changeappo = (Button) findViewById(R.id.change);
        cancelappo = findViewById(R.id.cancel);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);
        init();
        userAppointmentDate();
        userAppointmentStatus();

        changeappo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_MyAppointments.this, customerNewAppointment.class);
                startActivity(intent);
            }
        });
        cancelappo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Customer_MyAppointments.this);
                builder.setTitle("Appointment");
                builder.setMessage("Are you sure?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String apt = "no appointment";
                        String pending = "pending";

                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        map2.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);

                            }
                });
                builder.show();
            }
        });
    }

    private void userAppointmentDate(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        currentuserid = auth.getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference("users/"+currentuserid+"/Current Appointments/date");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String date = dataSnapshot.getValue(String.class);
                Log.e(TAG, "date: "+ date );

                txtmessage.setText(date);

            }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    private void userAppointmentStatus(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        currentuserid = auth.getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference("users/"+currentuserid+"/Current Appointments/status");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String status = dataSnapshot.getValue(String.class);

                if (status.equals("pending")){
                    txtmessage.setBackgroundColor(getResources().getColor(R.color.yellow));
                }
                else if (status.equals("Approved")){
                    txtmessage.setBackgroundColor(getResources().getColor(R.color.green));
                }
                else if (status.equals("Refused")){
                    txtmessage.setBackgroundColor(getResources().getColor(R.color.red));
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }








}





