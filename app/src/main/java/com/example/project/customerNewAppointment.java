package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    String currentuserid;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 07:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("7").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("7").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 08:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("8").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("8").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 09:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("9").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("9").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 10:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("10").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("10").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 11:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("11").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("11").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 12:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("12").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("12").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 13:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("13").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("13").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 14:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("14").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("14").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 15:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("15").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("15").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 16:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("16").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("16").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 17:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("17").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("17").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(customerNewAppointment.this);
                builder.setTitle("Appointment");
                builder.setMessage("Do you want to make an appointment?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        String full = "1";
                        String apt = "26.01.2021 - 18:00";
                        currentuserid = auth.getCurrentUser().getUid();
                        HashMap map2 = new HashMap();
                        map2.put("date", apt);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map2);
                        HashMap map = new HashMap();
                        map.put("status", full);
                        reference.child("Appointments").child("18").updateChildren(map);


                        HashMap map4 = new HashMap();
                        map4.put("UID", currentuserid);
                        reference.child("Appointments").child("18").updateChildren(map4);

                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference.child("users").child(currentuserid).child("Current Appointments").updateChildren(map3);
                    }
                });
                builder.show();
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

            DatabaseReference myRef = database.getReference("Appointments/" + i+"/status");
            Log.e(TAG,"myref"+ myRef+"  i :"+i+ "counter  "+counter);

            final String empty = "0";
            final String pending = "1";
            final String full = "2";

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


                    }else if (appointment.equals(pending)){
                        Log.e(TAG, "button : " + BtnArray[finalCounter]);
                        BtnArray[finalCounter].setBackgroundColor(getResources().getColor(R.color.yellow));


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