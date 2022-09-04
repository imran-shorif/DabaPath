package com.example.dhuro;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Fonts extends Home {
    ListView simpleList;
    String[] fontList = {"garamond", "montserrat", "poppins", "recursive"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fonts);
        simpleList = (ListView)findViewById(android.R.id.list);
        //fontList =getResources().getStringArray(R.array.font_options);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, fontList);
        simpleList.setAdapter(arrayAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), fontList[i],Toast.LENGTH_SHORT).show();
            }
        });
    }


}