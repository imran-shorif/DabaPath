package com.example.dhuro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

public class Home extends AppCompatActivity {

    public ActionBarDrawerToggle actionBarDrawerToggle;
    BottomNavigationView bottomNavigationView;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;

    FirebaseAuth mAuth;

    @SuppressLint({"QueryPermissionsNeeded", "IntentReset"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initInstances();

        mAuth = FirebaseAuth.getInstance();

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(this::onItemSelected);


    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigation = findViewById(R.id.sidenav);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        break;
                    case R.id.nav_profile:
                        fragment = new Profile();
                        break;
                    case R.id.nav_settings:
                        fragment = new Settings();
                        break;
                    case R.id.nav_about:
                        fragment = new About();
                        break;
                    case R.id.nav_logout:
                        FirebaseAuth.getInstance().signOut();
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
        });
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(Home.this, Login.class));
        }
    }


    @SuppressLint("NonConstantResourceId")
    public boolean onItemSelected(@NonNull MenuItem item) {
     switch(item.getItemId())

    {
        case R.id.quickPlay:
            startActivity(new Intent(getApplicationContext(), QuickPlay.class));
            overridePendingTransition(0, 0);
            return true;
        case R.id.puzzles:
            startActivity(new Intent(getApplicationContext(), Puzzles.class));
            overridePendingTransition(0, 0);
            return true;
        case R.id.news:
            startActivity(new Intent(getApplicationContext(), News.class));
            overridePendingTransition(0, 0);
            return true;
        case R.id.blogs:
            startActivity(new Intent(getApplicationContext(), Blogs.class));
            overridePendingTransition(0, 0);
            return true;
    }
                return false;
    }
}