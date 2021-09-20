package com.s1.cumminsapp;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class FirstPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

    }



    public void s_labs(View view)
    {
        Intent intent = new Intent(this,Search.class);
        startActivity(intent);
    }
    public void events(View view)
    {
        Intent intent = new Intent(this,BeforeVideo.class);
        startActivity(intent);
    }
    public void assig(View view)
    {
        Intent intent = new Intent(this,ImageOption.class);
        startActivity(intent);
    }
    public void s_hostels(View view)
    {
        Intent intent = new Intent(this,GPS.class);
        startActivity(intent);
    }

}