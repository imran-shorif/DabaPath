package com.example.dhuro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends Login {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button buttonF = findViewById(R.id.fonts);
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowFonts((View) view);
            }});

        Button buttonN = findViewById(R.id.button7);
        buttonN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowNews((View) view);
            }});

        Button buttonQ = findViewById(R.id.button3);
        buttonQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowQuick((View) view);
            }});

        Button buttonP = findViewById(R.id.button4);
        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowPuzzles((View) view);
            }});

        Button buttonB = findViewById(R.id.button8);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowBlogs((View) view);
            }});

        Button buttonEmail = findViewById(R.id.button25);
        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View view) {
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
            }
        });
    }
    public void nowNews(View view) {
        Intent News = new Intent(Home.this, News.class);
        startActivity(News);
    }

    public void nowList(View view) {
        Intent List = new Intent(Home.this, Fonts.class);
        startActivity(List);
    }

    public void nowQuick(View view) {
        Intent Quick = new Intent(Home.this, QuickPlay.class);
        startActivity(Quick);
    }

    public void nowPuzzles(View view) {
        Intent Puzzles = new Intent(Home.this, Puzzles.class);
        startActivity(Puzzles);
    }

    public void nowBlogs(View view) {
        Intent Blogs = new Intent(Home.this, Blogs.class);
        startActivity(Blogs);

    }

    public void nowFonts(View view) {
        Intent Fonts = new Intent(Home.this, Fonts.class);
        startActivity(Fonts);
    }
}