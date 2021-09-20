package com.s1.cumminsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Search extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        spinner=(Spinner)findViewById(R.id.spinner);
//        adapter=ArrayAdapter.createFromResource(this,R.array.search,android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter)
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(parent.getItemAtPosition(position)== "Search LAB")
//                {
//                    Intent intent = new Intent(this,SearchLab.class);
//                    startActivity(intent);
//                }
//                else
//                {
//                    Intent intent = new Intent(this,SearchCabin.class);
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    public void viewLab(View view)
    {
        Intent intent = new Intent(this,SearchLab.class);
        startActivity(intent);
    }
    public void viewCabin(View view)
    {
        Intent intent = new Intent(this,SearchCabin.class);
        startActivity(intent);
    }
    public void modifyall(View view)
    {
        Intent intent = new Intent(this,TomodifyDB.class);
        startActivity(intent);
    }
    public void modifyLabs(View view)
    {
        Intent intent = new Intent(this,TomodifyLabs.class);
        startActivity(intent);
    }
}
