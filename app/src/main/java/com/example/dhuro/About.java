package com.example.dhuro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class About extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Button btnEmailIntent;

    @SuppressLint({"NonConstantResourceId", "IntentReset", "QueryPermissionsNeeded"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initInstances();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.quickPlay:
                    startActivity(new Intent(getApplicationContext(), QuickPlay.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.puzzles:
                    startActivity(new Intent(getApplicationContext(), Puzzles.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.news:
                    startActivity(new Intent(getApplicationContext(), NewsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.blogs:
                    startActivity(new Intent(getApplicationContext(), ImagesActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return true;

        });

        btnEmailIntent = findViewById(R.id.emailBtn);
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

    @SuppressLint("NonConstantResourceId")
    private void initInstances() {
        drawerLayout = findViewById(R.id.my_drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), Profile.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.nav_settings:
                return true;
            case R.id.nav_about:
                startActivity(new Intent(getApplicationContext(), About.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
        }
        return true;
    }
}