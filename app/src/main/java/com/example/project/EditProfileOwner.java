package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditProfileOwner extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView txtname,txtphone,txtaddress;
    private Button btnupdate;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth auth;

    public void init(){
        toolbar = findViewById(R.id.action_barEditProfileOwner);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtname = findViewById(R.id.txtOwnerName);
        txtphone= findViewById(R.id.txtOwnerTelephone);
        txtaddress= findViewById(R.id.txtOwnerAddress);


        auth = FirebaseAuth.getInstance();

        btnupdate = findViewById(R.id.btnOwnerUpdate);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String changeusername =txtname.getText().toString();
                String changeuseraddress =txtaddress.getText().toString();
                String changeusertelephone =txtphone.getText().toString();

                String comment = "Great Saloon";
                String rating = "10/10";
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference();
                HashMap map = new HashMap();
                map.put("Name", changeusername);
                reference.child("Saloon").child("OnurSaloon").updateChildren(map);

                HashMap map2 = new HashMap();
                map2.put("Address", changeuseraddress);
                reference.child("Saloon").child("OnurSaloon").updateChildren(map2);

                HashMap map3 = new HashMap();
                map3.put("Telephone", changeusertelephone);
                reference.child("Saloon").child("OnurSaloon").updateChildren(map3);

                HashMap map4 = new HashMap();
                map4.put("Rating", rating);
                reference.child("Saloon").child("OnurSaloon").updateChildren(map4);

                HashMap map5 = new HashMap();
                map5.put("Comment", comment);
                reference.child("Saloon").child("OnurSaloon").updateChildren(map5);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_owner);
        init();
    }
}
