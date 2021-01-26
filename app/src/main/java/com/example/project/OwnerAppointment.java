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
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/8/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("8");

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
                                    reference.child("Appointments").child("8").updateChildren(map2);
                                    update("8","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("8");
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
                                    reference.child("Appointments").child("8").updateChildren(map2);
                                    update("8","Approved");


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
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/9/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("9");

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
                                    reference.child("Appointments").child("9").updateChildren(map2);
                                    update("9","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("9");
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
                                    reference.child("Appointments").child("9").updateChildren(map2);
                                    update("9","Approved");


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
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/10/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("10");

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
                                    reference.child("Appointments").child("10").updateChildren(map2);
                                    update("10","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("10");
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
                                    reference.child("Appointments").child("10").updateChildren(map2);
                                    update("10","Approved");


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
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/11/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("11");

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
                                    reference.child("Appointments").child("11").updateChildren(map2);
                                    update("11","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("11");
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
                                    reference.child("Appointments").child("11").updateChildren(map2);
                                    update("11","Approved");


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
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/12/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("12");

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
                                    reference.child("Appointments").child("12").updateChildren(map2);
                                    update("12","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("12");
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
                                    reference.child("Appointments").child("12").updateChildren(map2);
                                    update("12","Approved");


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
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/13/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("13");

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
                                    reference.child("Appointments").child("13").updateChildren(map2);
                                    update("13","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("13");
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
                                    reference.child("Appointments").child("13").updateChildren(map2);
                                    update("13","Approved");


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
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/14/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("14");

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
                                    reference.child("Appointments").child("14").updateChildren(map2);
                                    update("14","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("14");
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
                                    reference.child("Appointments").child("14").updateChildren(map2);
                                    update("14","Approved");


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
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/151515151515/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("15");

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
                                    reference.child("Appointments").child("15").updateChildren(map2);
                                    update("15","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("15");
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
                                    reference.child("Appointments").child("15").updateChildren(map2);
                                    update("15","Approved");


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
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/16/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("16");

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
                                    reference.child("Appointments").child("16").updateChildren(map2);
                                    update("16","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("16");
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
                                    reference.child("Appointments").child("16").updateChildren(map2);
                                    update("16","Approved");


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
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/17/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("17");

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
                                    reference.child("Appointments").child("17").updateChildren(map2);
                                    update("17","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("17");
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
                                    reference.child("Appointments").child("17").updateChildren(map2);
                                    update("17","Approved");


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
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Appointments/18/status");
                Log.e(TAG, "ref: " + myRef);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String status = dataSnapshot.getValue(String.class);

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();
                        if (status.equals("2")) {
                            information("18");

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
                                    reference.child("Appointments").child("18").updateChildren(map2);
                                    update("18","Refused");

                                }
                            });
                            builder.setNeutralButton("Information", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    information("18");
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
                                    reference.child("Appointments").child("18").updateChildren(map2);
                                    update("18","Approved");


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





    }
}