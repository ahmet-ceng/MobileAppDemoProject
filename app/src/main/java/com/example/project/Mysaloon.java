package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mysaloon extends AppCompatActivity {
    private Toolbar actionBarLogin;
    private Button btn_editProfile;

    public void init(){
        btn_editProfile = (Button) findViewById(R.id.btnOwnerEditProfile);

        actionBarLogin = (Toolbar) findViewById(R.id.actionBarLogin);
        setSupportActionBar(actionBarLogin);
        getSupportActionBar().setTitle("My Saloon");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysaloon);


        init();

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
