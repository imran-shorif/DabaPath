package com.example.dhuro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;

public class Login extends Signup {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.btnToLogin);
        button.setOnClickListener(view -> nowSignUp((View) view));

        Button buttonL = findViewById(R.id.btnSignup);
        buttonL.setOnClickListener(view -> nowHome((View) view));
    }

    public void nowHome(View view) {
        Intent Home = new Intent(Login.this, Home.class);
        startActivity(Home);
    }

    public void nowSignUp(View view) {
        Intent SignUp = new Intent(Login.this, Signup.class);
        startActivity(SignUp);
    }
}