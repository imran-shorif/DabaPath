package com.example.dhuro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @SuppressLint({"QueryPermissionsNeeded", "IntentReset"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(this::onItemSelected);
        loadFragment(new QuickPlay());


        Button buttonEmail = findViewById(R.id.button25);
        buttonEmail.setOnClickListener(view -> {
            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setData(Uri.parse("mailto:"));
            Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"dabapath@gmail.com"});
            Email.putExtra(Intent.EXTRA_SUBJECT, "Suggestion");
            Email.setType("plain/text");
            Email.putExtra(Intent.EXTRA_TEXT, "I love DabaPath, moreover I'd like to suggest that: ");

            if (Email.resolveActivity(getPackageManager()) != null) {
                startActivity(Email);
            } else {
                Toast.makeText(Home.this, "dabapath@gmail.com", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @SuppressLint("NonConstantResourceId")
    public boolean onItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.quickPlay:
                fragment = new QuickPlay();
                break;
            case R.id.puzzles:
                fragment = new Puzzles();
                break;
            case R.id.news:
                fragment = new News();
                break;
            case R.id.blogs:
                fragment = new Blogs();
                break;
        }
        if (fragment != null) {
            loadFragment(fragment);
        }
        return true;
    }
    void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment).commit();
    }
}