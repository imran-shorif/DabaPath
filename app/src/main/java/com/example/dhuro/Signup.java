package com.example.dhuro;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button button = findViewById(R.id.btnToLogin);
        button.setOnClickListener(view -> nowLogin((View) view));

        Button buttonS = findViewById(R.id.btnSignup);
        buttonS.setOnClickListener(view -> nowLogin((View) view));

        FirebaseAuth.getInstance();


    }



    public void nowLogin(View view) {
        Intent Login = new Intent(Signup.this, Login.class);
        startActivity(Login);
    }
}