package com.example.dhuro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {

    Button btnEmailIntent;

    @SuppressLint({"NonConstantResourceId", "IntentReset", "QueryPermissionsNeeded"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnEmailIntent = btnEmailIntent.findViewById(R.id.button25);
        btnEmailIntent.setOnClickListener(view -> {
            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setData(Uri.parse("mailto:"));
            Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"dabapath@gmail.com"});
            Email.putExtra(Intent.EXTRA_SUBJECT, "Suggestion");
            Email.setType("plain/text");
            Email.putExtra(Intent.EXTRA_TEXT, "I love DabaPath, moreover I'd like to suggest that: ");

            if (Email.resolveActivity(this.getPackageManager()) != null) {
                startActivity(Email);
            } else {
                Toast.makeText(this, "Redirect Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}