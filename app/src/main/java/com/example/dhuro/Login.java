package com.example.dhuro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class Login extends MainActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowSignUp((View) view);
            }});

        Button buttonL = findViewById(R.id.button6);
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowHome((View) view);
            }});
    }

    public void nowHome(View view) {
        Intent Home = new Intent(Login.this, Home.class);
        startActivity(Home);
    }

    public void nowSignUp(View view) {
        Intent SignUp = new Intent(Login.this, MainActivity.class);
        startActivity(SignUp);
    }
}