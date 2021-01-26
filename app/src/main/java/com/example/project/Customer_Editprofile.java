package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Customer_Editprofile extends AppCompatActivity {
    private Toolbar toolbar;

    private TextView txtFirstName,txtLastName,txtphone;
    private Button btnupdate;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth auth;
    private String currentuserid;

    public void init(){
        toolbar = findViewById(R.id.action_bareditprofile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        txtFirstName = findViewById(R.id.first_Name);
        txtLastName= findViewById(R.id.Last_Name);
        txtphone= findViewById(R.id.telephone);
        btnupdate = findViewById(R.id.update);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String changeusername =txtFirstName.getText().toString();
                String changelastname =txtLastName.getText().toString();
                String changeusertelephone =txtphone.getText().toString();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                currentuserid = auth.getCurrentUser().getUid();
                HashMap map = new HashMap();
                map.put("firstName", changeusername);
                reference.child("users").child(currentuserid).updateChildren(map);

                HashMap map2 = new HashMap();
                map2.put("lastName", changelastname);
                reference.child("users").child(currentuserid).updateChildren(map2);

                HashMap map3 = new HashMap();
                map3.put("telephone", changeusertelephone);
                reference.child("users").child(currentuserid).updateChildren(map3);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        init();
    }
}