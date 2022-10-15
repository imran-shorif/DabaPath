package com.example.dhuro;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    FirebaseAuth mAuth;

    EditText sName;
    EditText sUsername;
    EditText sMail;
    EditText sPassword;
    EditText sCPassword;
    Button btnSignup;
    Button btnToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sName = findViewById(R.id.entryName);
        sUsername = findViewById(R.id.entryUsername);
        sMail = findViewById(R.id.enterMail);
        sPassword = findViewById(R.id.enterPassword);
        sCPassword = findViewById(R.id.entryCPassword);

        btnSignup = findViewById(R.id.btnSignup);
        btnToLogin = findViewById(R.id.btnToLogin);

        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(view -> {
            createUser();
        });

        btnToLogin.setOnClickListener(view ->
                startActivity(new Intent(Signup.this, Login.class))
        );
    }

    private void createUser(){
        String sNameText = sName.getText().toString();
        String sUsernameText = sUsername.getText().toString();
        String sMailText = sMail.getText().toString();
        String sPasswordText = sPassword.getText().toString();
        String sCPasswordText = sCPassword.getText().toString();

        if(TextUtils.isEmpty(sNameText)){
            sName.setError("Name cannot be empty");
            sName.requestFocus();
        }
        else if(TextUtils.isEmpty(sUsernameText)){
            sUsername.setError("Username cannot be empty");
            sUsername.requestFocus();
        }
        else if(TextUtils.isEmpty(sMailText)){
            sMail.setError("Email cannot be empty");
            sMail.requestFocus();
        }
        else if(TextUtils.isEmpty(sPasswordText)){
            sPassword.setError("Password cannot be empty");
            sPassword.requestFocus();
        }
        else if(TextUtils.isEmpty(sCPasswordText)){
            sCPassword.setError("Password cannot be empty");
            sCPassword.requestFocus();
        }
        else if (!sPasswordText.equals(sCPasswordText)){
            sCPassword.setError("Passwords didn't match");
            sCPassword.requestFocus();
        }
        else {
            mAuth.createUserWithEmailAndPassword(sMailText, sPasswordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Signup.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup.this, Login.class));
                    }
                    else{
                        Toast.makeText(Signup.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}