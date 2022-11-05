package com.example.dhuro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Signup {
    FirebaseAuth mAuth;

    EditText lMail;
    EditText lPassword;
    Button btnLogin;
    Button btnToSignup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lMail = findViewById(R.id.enterMail);
        lPassword = findViewById(R.id.enterPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnToSignup = findViewById(R.id.btnToSignup);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
        btnToSignup.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, Signup.class));
        });
    }

    private void loginUser(){
        String lMailText = lMail.getText().toString();
        String lPasswordText = lPassword.getText().toString();

        if(TextUtils.isEmpty(lMailText)){
            lMail.setError("Email cannot be empty");
            lMail.requestFocus();
        }
        else if(TextUtils.isEmpty(lPasswordText)){
            lPassword.setError("password cannot be empty");
            lPassword.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(lMailText, lPasswordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this, "User Logged In Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Home.class));
                    }
                    else{
                        Toast.makeText(Login.this, "Logging In Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}