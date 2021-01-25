package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

public class owner_saloon extends AppCompatActivity {
    private Button btncustomer;
    private Button btn_owner;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private FirebaseAuth auth;
    private String currentuserid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_saloon);
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
}