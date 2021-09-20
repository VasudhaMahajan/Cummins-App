package com.s1.cumminsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.method.LinkMovementMethod;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        TextView text = (TextView) findViewById(R.id.text5);
        text.setMovementMethod(LinkMovementMethod.getInstance());

        final ImageView zoom = (ImageView)findViewById(R.id.imageView1);
        final Animation zooman = AnimationUtils.loadAnimation(this,R.anim.zoom);
        zoom.startAnimation(zooman);

    }
}

