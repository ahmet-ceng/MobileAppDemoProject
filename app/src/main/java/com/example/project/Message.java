package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class Message extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton imageButton;
    private EditText enterusermessage;
    private ScrollView scrollView;
    private TextView Showmessage;

    private String currentGroupname,currentuserId,activeusername,activeDate,activeTime;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference,groupDatabaseReference,groupsmessagekeyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);



        currentGroupname = getIntent().getExtras().get("GroupsName").toString();
        Toast.makeText(Message.this,currentGroupname,Toast.LENGTH_LONG).show();

        //Firebase
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Account");
        groupDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupname);
        currentuserId = auth.getCurrentUser().getUid();


        toolbar= findViewById(R.id.messagelayout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(currentGroupname);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageButton = findViewById(R.id.btnSend);
        enterusermessage = findViewById(R.id.EnterGroupMessage);
        Showmessage = findViewById(R.id.GroupChatShow);
        scrollView = findViewById(R.id.myScrolview);

        //kullanıcı bilgisi alma
        takeuserınfo();
        //mesajı dtabase yollama

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveMessageDatabase();
                enterusermessage.setText("");

                scrollView.fullScroll(scrollView.FOCUS_DOWN);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        groupDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()){
                    showmessage(dataSnapshot);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()){
                    showmessage(dataSnapshot);
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showmessage(DataSnapshot dataSnapshot) {
        Iterator iterator = dataSnapshot.getChildren().iterator();

        while (iterator.hasNext()){
            String sohbetarihi = (String) ((DataSnapshot)iterator.next()).getValue();
            String sohbetmessage = (String) ((DataSnapshot)iterator.next()).getValue();
            String sohbeAdi = (String) ((DataSnapshot)iterator.next()).getValue();
            String sohbetZamani = (String) ((DataSnapshot)iterator.next()).getValue();

            Showmessage.append("                                   "+sohbetarihi+ "\n" + sohbeAdi +":  "+ sohbetmessage + " \n"+"                                                                    " + sohbetZamani  + "\n\n\n\n");

            scrollView.fullScroll(scrollView.FOCUS_DOWN);
        }
    }

    private void saveMessageDatabase() {
        String message = enterusermessage.getText().toString();
        String messageKey = groupDatabaseReference.push().getKey();

        if (TextUtils.isEmpty(message)){
            Toast.makeText(Message.this," Cannot empty message area", Toast.LENGTH_LONG).show();

        }else{
            //database yazma

            Calendar datefordate = Calendar.getInstance();
            SimpleDateFormat activeDateFormat = new SimpleDateFormat("dd,MM,yyyy");
            activeDate = activeDateFormat.format(datefordate.getTime());

            Calendar  datefortime = Calendar.getInstance();
            SimpleDateFormat activeTimeFormat = new SimpleDateFormat("hh:mm:ss a");
            activeTime = activeTimeFormat.format(datefortime.getTime());

            HashMap<String,Object> GroupMessageKey = new HashMap<>();
            groupDatabaseReference.updateChildren(GroupMessageKey);

            groupsmessagekeyRef = groupDatabaseReference.child(messageKey);
            HashMap<String,Object>messageınfomap = new HashMap<>();
            messageınfomap.put("Name",activeusername);
            messageınfomap.put("Message",message);
            messageınfomap.put("Date",activeDate);
            messageınfomap.put("Time",activeTime);

            groupsmessagekeyRef.updateChildren(messageınfomap);
        }
    }

    private void takeuserınfo() {
        databaseReference.child(currentuserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    activeusername = dataSnapshot.child("Name").getValue().toString();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}