package com.example.dhuro;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowLogin((View) view);
            }});

        Button buttonS = findViewById(R.id.button6);
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowLogin((View) view);
            }});
    }


    public void nowLogin(View view) {
        Intent Login = new Intent(MainActivity.this, Login.class);
        startActivity(Login);
    }
}