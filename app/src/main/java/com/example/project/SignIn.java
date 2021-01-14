package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignIn extends AppCompatActivity {
    private Toolbar toolbar;

    private EditText email;
    private EditText password;
    private Button btnsigin;
    private Button btnbypass;
    private  Button btn_forgetPassword;


    private FirebaseAuth auth;

    ProgressDialog login;

    public  void init() {
        toolbar = findViewById(R.id.action_barSignIn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        email = findViewById(R.id.EmailSıngIn);
        password = findViewById(R.id.PasswordSıgnIn);
        btnsigin = findViewById(R.id.SignInBtn);
        auth = FirebaseAuth.getInstance();

        btnsigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        login = new ProgressDialog(this);

        btn_forgetPassword = (Button) findViewById(R.id.btnForgetPassword);
        btn_forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setMessage("Enter Your Email to Receive Reset Link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract email and send reset link
                        String mail = resetMail.getText().toString();
                        auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SignIn.this, "Reset Link Succesfully Sent to Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignIn.this, "Error!, Resent Link Couldn't Be Sent", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });

    }

        private void signIn() {
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            if (TextUtils.isEmpty(Email)) {
                Toast.makeText(this, "E-MAİL FIELD CANNOT BE EMPTY!!!", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(Password)) {
                Toast.makeText(this, "PASSWORD FIELD CANNOT BE EMPTY!!!", Toast.LENGTH_LONG).show();
            } else {

                //progress
                login.setTitle("Logging In");
                login.setMessage("Please Wait");
                login.setCanceledOnTouchOutside(true);
                login.show();

                auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignIn.this, "Sign In SUCCESSFUL!!!\n", Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(SignIn.this, owner_saloon.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            login.dismiss();
                            finish();
                        } else {
                            String message = task.getException().toString();
                            Toast.makeText(SignIn.this, "Sıgn In WRONG!!!\n" + "Errors : " + message, Toast.LENGTH_LONG).show();
                            login.dismiss();
                        }
                    }
                });
            }
        }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }
}
