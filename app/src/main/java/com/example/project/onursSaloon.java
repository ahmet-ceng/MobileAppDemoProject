package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class onursSaloon extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth auth;
    TextView txtname,txtaddress,txttel,txtrating,txtcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onurs_saloon);
        txtaddress= findViewById(R.id.Custaddressdb);
        txtname= findViewById(R.id.Custnamedb);
        txttel = findViewById(R.id.Custtelephonedb);
        txtrating=findViewById(R.id.Custratingdb);
        txtcomment=findViewById(R.id.Custcommentsdb);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Saloon/OnurSaloon");
        myRef.addValueEventListener(new ValueEventListener() {
            private static final String TAG = "aaaaaaaaaa";

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String address = dataSnapshot.child("Address").getValue(String.class);
                String name = dataSnapshot.child("Name").getValue(String.class);
                String tel = dataSnapshot.child("Telephone").getValue(String.class);
                String rating = dataSnapshot.child("Rating").getValue(String.class);
                String comments = dataSnapshot.child("Comment").getValue(String.class);
                txtaddress.setText(address);
                txtname.setText(name);
                txttel.setText(tel);
                txtrating.setText(rating);
                txtcomment.setText(comments);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}