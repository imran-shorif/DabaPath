package com.example.dhuro;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button5);
        button.setOnClickListener(view -> nowLogin((View) view));

        Button buttonS = findViewById(R.id.button6);
        buttonS.setOnClickListener(view -> nowLogin((View) view));
    }


    public void nowLogin(View view) {
        Intent Login = new Intent(MainActivity.this, Login.class);
        startActivity(Login);
    }
}