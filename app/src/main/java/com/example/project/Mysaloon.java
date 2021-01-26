package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
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

public class Mysaloon extends AppCompatActivity {
    private Toolbar actionBarLogin;
    private Button btn_editProfile;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth auth;

    private TextView txtname,txtphone,txtaddress,txtcomment,txtrating;

    public void init() {
        btn_editProfile = (Button) findViewById(R.id.btnOwnerEditProfile);

        actionBarLogin = (Toolbar) findViewById(R.id.actionBarLogin);
        setSupportActionBar(actionBarLogin);
        getSupportActionBar().setTitle("My Saloon");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auth = FirebaseAuth.getInstance();

        txtname = findViewById(R.id.namedb);
        txtphone = findViewById(R.id.telephonedb);
        txtaddress = findViewById(R.id.addressdb);
        txtcomment = findViewById(R.id.commentsdb);
        txtrating = findViewById(R.id.ratingdb);
    }
    public void TakeValue() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Saloon/OnurSaloon/Name");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                txtname.setText(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void TakeValue1() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Saloon/OnurSaloon/Telephone");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String phone = dataSnapshot.getValue(String.class);
                txtphone.setText(phone);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void TakeValue2() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Saloon/OnurSaloon/Address");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String address = dataSnapshot.getValue(String.class);
                txtaddress.setText(address);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void TakeValue3() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Saloon/OnurSaloon/Comment");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String comment = dataSnapshot.getValue(String.class);
                txtcomment.setText(comment);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void TakeValue4() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Saloon/OnurSaloon/Rating");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String rating = dataSnapshot.getValue(String.class);
                txtrating.setText(rating);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysaloon);
        init();
        TakeValue();
        TakeValue1();
        TakeValue2();
        TakeValue3();
        TakeValue4();

        btn_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditProfileOwnerActivity();
            }
        });

    }

    public void openEditProfileOwnerActivity(){
        Intent intent = new Intent(this, EditProfileOwner.class);
        startActivity(intent);
    }
}
