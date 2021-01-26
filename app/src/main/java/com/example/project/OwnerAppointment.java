package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class OwnerAppointment extends AppCompatActivity {
    private Toolbar actionBarLogin;
    private Button btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18;
    String currentuserid,hour;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth auth;
    private static final String TAG = "MyActivity";

    public void init(){
        actionBarLogin = (Toolbar) findViewById(R.id.actionBarLogin);
        setSupportActionBar(actionBarLogin);
        getSupportActionBar().setTitle("My Appointments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        setContentView(R.layout.activity_owner_appointment);

        init();
        checkuser();
        onCliks();

    }








    private void checkuser() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String[] hours = {"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18"};
        final Button[] BtnArray = new Button[] { btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18};
        int counter = -1;
        for (final String i : hours) {
            counter++;

            DatabaseReference myRef = database.getReference("Appointments/" + i+"/status");
            final String empty = "0";
            final String pending = "1";
            final String full = "2";

            final int finalCounter = counter;
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String appointment = dataSnapshot.getValue(String.class);

                    if (appointment.equals(full)) {
                        BtnArray[finalCounter].setBackgroundColor(getResources().getColor(R.color.red));


                    } else if (appointment.equals(empty)){
                        BtnArray[finalCounter].setBackgroundColor(getResources().getColor(R.color.green));


                    }else if (appointment.equals(pending)){
                        BtnArray[finalCounter].setBackgroundColor(getResources().getColor(R.color.yellow));


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }


    public void open1(){
        Intent intent = new Intent(this, deneme1.class);
        startActivity(intent);
    };

    public void information(String hour){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Appointments/"+hour+"/UID");
        Log.e(TAG, "ref: "+ myRef );

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uid = dataSnapshot.getValue(String.class);
                Log.e(TAG, "uid: "+ uid );

                DatabaseReference myReff = database.getReference("users/"+uid+"/firstName");
                myReff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.getValue(String.class);
                        Log.e(TAG, "name: "+ name );

                        AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
                        builder.setTitle("Customer");
                        builder.setMessage("Name of the customer is: "+ name);
                        builder.setIcon(android.R.drawable.ic_dialog_alert);
                        builder.show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void update(final String hour, final String status){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Appointments/"+hour+"/UID");
        Log.e(TAG, "ref: "+ myRef );

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uid = dataSnapshot.getValue(String.class);

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                if (status.equals("Refused")){
                    String date = "no appointment";
                    HashMap map = new HashMap();
                    map.put("date", date);
                    map.put("status", status);

                    reference.child("users").child(uid).child("Current Appointments").updateChildren(map);
                }
                else {
                    String date = hour+":00";
                    HashMap map = new HashMap();
                    map.put("date", date);
                    map.put("status", status);

                    reference.child("users").child(uid).child("Current Appointments").updateChildren(map);
                }



            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void onCliks(){
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/7/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("7");

                        } else if (status.equals("0")) {
                        }

                        else if (status.equals("1")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
                            builder.setTitle("Appointment");
                            builder.setMessage("Customer appointment is pending. Do you approve?");
                            builder.setIcon(android.R.drawable.ic_dialog_alert);
                            builder.setNegativeButton("Refuse", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    rootNode = FirebaseDatabase.getInstance();
                                    reference = rootNode.getReference();
                                    String full = "0";
                                    HashMap map2 = new HashMap();
                                    map2.put("status", full);
                                    reference.child("Appointments").child("7").updateChildren(map2);
                                    update("7","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("7");
                                }
                            });
                            builder.setPositiveButton("Approve", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();

                                    rootNode = FirebaseDatabase.getInstance();
                                    reference = rootNode.getReference();
                                    String full = "2";
                                    HashMap map2 = new HashMap();
                                    map2.put("status", full);
                                    reference.child("Appointments").child("7").updateChildren(map2);
                                    update("7","Approved");


                                }
                            });
                            builder.show();
                        }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }});
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("8", full);
                        reference.child("Appointments").updateChildren(map);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("9", full);
                        reference.child("Appointments").updateChildren(map);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("10", full);
                        reference.child("Appointments").updateChildren(map);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("11", full);
                        reference.child("Appointments").updateChildren(map);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("12", full);
                        reference.child("Appointments").updateChildren(map);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("13", full);
                        reference.child("Appointments").updateChildren(map);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("14", full);
                        reference.child("Appointments").updateChildren(map);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("15", full);
                        reference.child("Appointments").updateChildren(map);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("16", full);
                        reference.child("Appointments").updateChildren(map);
                        String pending = "pending";
                        HashMap map3 = new HashMap();
                        map3.put("status", pending);
                        reference = rootNode.getReference("users/"+currentuserid+"/Current Appointments");
                        reference.updateChildren(map3);
                    }
                });
                builder.show();
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("17", full);
                        reference.child("Appointments").updateChildren(map);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerAppointment.this);
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
                        map.put("18", full);
                        reference.child("Appointments").updateChildren(map);
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
}