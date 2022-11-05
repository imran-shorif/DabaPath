package com.example.dhuro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    FirebaseAuth mAuth;

    @SuppressLint({"QueryPermissionsNeeded", "IntentReset", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initInstances();

        mAuth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
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
                startActivity(new Intent(getApplicationContext(), Settings.class));
                overridePendingTransition(0, 0);
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