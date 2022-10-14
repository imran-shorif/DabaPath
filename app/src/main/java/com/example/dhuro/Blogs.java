package com.example.dhuro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.*;
import androidx.fragment.app.Fragment;

public class Blogs extends Fragment {

    public Blogs(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_blogs, container, false);
    }
}