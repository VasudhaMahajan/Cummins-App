package com.s1.cumminsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ZoomImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);

        final ImageView zoom = (ImageView)findViewById(R.id.imageView);
        final Animation zooman = AnimationUtils.loadAnimation(this,R.anim.zoom);
        zoom.startAnimation(zooman);
    }
}

