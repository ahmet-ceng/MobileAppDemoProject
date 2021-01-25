package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

public class owner_saloon extends AppCompatActivity {
    private Button btncustomer;
    private Button btn_owner;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private TextView testView;
    private FirebaseAuth auth;
    String currentuserid;

    private static final String TAG = "MyActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_saloon);
        auth = FirebaseAuth.getInstance();


        currentuserid = auth.getCurrentUser().getUid();
        checkuser();
        init();


    }


    public void init(){
        btncustomer = findViewById(R.id.btncustomer);
        btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCustomer();

                Intent intent = new Intent(owner_saloon.this, Customer_MainActivity.class);
                startActivity(intent);
            }
        });
        btn_owner = findViewById(R.id.ownerButton);
        btn_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadOwner();
                Intent intent = new Intent(owner_saloon.this, Owner_menu.class);
                startActivity(intent);
            }
        });

        auth = FirebaseAuth.getInstance();

    }


    private void loadOwner(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        String owner = "owner";
        currentuserid = auth.getCurrentUser().getUid();

        HashMap map = new HashMap();
        map.put("usertype", owner);

        reference.updateChildren(map);

        reference.child("users").child(currentuserid).updateChildren(map);
    }

    private void loadCustomer(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        String customer = "customer";
        currentuserid = auth.getCurrentUser().getUid();

        HashMap map = new HashMap();
        map.put("usertype", customer);

        reference.updateChildren(map);

        reference.child("users").child(currentuserid).updateChildren(map);}


    private void checkuser() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users/"+currentuserid+"/usertype");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String own = "owner";
                String cst = "customer";
                String usr = "user";

                String user = dataSnapshot.getValue(String.class);
                Log.e(TAG, "onDataChange: "+ user );

                if (user.equals(cst)){
                    Intent intent = new Intent(owner_saloon.this,Customer_MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                }else if(user.equals(own)){
                    Intent intent = new Intent(owner_saloon.this,Owner_menu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "yaaaaaaaaaaaa: " );

            }
        });

    }
}