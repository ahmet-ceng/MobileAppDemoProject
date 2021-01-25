package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Introduction extends AppCompatActivity {
    private Button btnSignUp;
    private Button btnAlreadyHaveAnAccount;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private FirebaseAuth auth;



    private EditText password, phone, firstname, lastname, email, usertelephone;
    ProgressDialog logup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        init();
        init2();
    }


    public void  init () {

        email = (EditText) findViewById(R.id.EmailCreate);
        password = (EditText) findViewById(R.id.Password);
        phone = (EditText) findViewById(R.id.Telephone);
        firstname = (EditText) findViewById(R.id.FirstName);
        lastname = (EditText) findViewById(R.id.LastName);
        usertelephone = (EditText) findViewById(R.id.Telephone);


        auth = FirebaseAuth.getInstance();
        btnSignUp = (Button) findViewById(R.id.SignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addValuesToDatabase();
                createNewAccount();
            }
        });
        logup = new ProgressDialog(this);
    }

    private void addValuesToDatabase(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        //get all the values
        String firstName = firstname.getText().toString();
        String lastName = lastname.getText().toString();
        String txtEmail = email.getText().toString();
        String telephone = phone.getText().toString();
        String txtPassword = password.getText().toString();

        UserHelperClass helperClass = new UserHelperClass(firstName, lastName, txtEmail, telephone, txtPassword);

        reference.child(firstName).setValue(helperClass);
    }

    private void createNewAccount() {
        String txtEmail = email.getText().toString();
        String txtPassword = password.getText().toString();


        if (TextUtils.isEmpty(txtEmail)) {
            Toast.makeText(this, "E-MAIL FIELD CANNOT BE EMPTY!!!", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(txtPassword)) {
            Toast.makeText(this, "PASSWORD FIELD CANNOT BE EMPTY!!!", Toast.LENGTH_LONG).show();
        } else {
            //progress
            logup.setTitle("Signin Up...");
            logup.setMessage("Please Wait...");
            logup.setCanceledOnTouchOutside(true);
            logup.show();



            auth.createUserWithEmailAndPassword(txtEmail, txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(Introduction.this, "REGISTRATION SUCCESSFUL!!!\n", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Introduction.this, SignIn.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        logup.dismiss();
                        finish();
                    }else {
                        String message = task.getException().toString();
                        Toast.makeText(Introduction.this, "REGISTRATION WRONG!!!\n" + "Errors: " + message, Toast.LENGTH_LONG).show();
                        logup.dismiss();
                    }
                }
            });
        }
    }



    public void init2(){
           btnAlreadyHaveAnAccount = (Button) findViewById(R.id.AlreadyHaveAnAccount);

           btnAlreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(Introduction.this,SignIn.class);
                   startActivity(intent);
               }
           });




    } }