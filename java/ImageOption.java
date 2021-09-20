package com.s1.cumminsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ImageOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_option);
    }
    public void upload(View view)
    {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public void Retrieve(View view)
    {
        Intent intent=new Intent(this,ImageListActivity.class);
        startActivity(intent);
    }
}
