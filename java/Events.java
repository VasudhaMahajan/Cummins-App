package com.s1.cumminsapp;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
public class Events extends AppCompatActivity {
        MediaController mediaController;
        Button b1;
        Button b2;
        TextView t3;
        @Override

        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_events);

                b1 = (Button) findViewById(R.id.b1);
                getWindow().setFormat(PixelFormat.UNKNOWN);

                b2= (Button) findViewById(R.id.b2);
                getWindow().setFormat(PixelFormat.UNKNOWN);
                t3 = (TextView) findViewById(R.id.showMore1);
                t3.setMovementMethod(LinkMovementMethod.getInstance());

                VideoView v1 = (VideoView) findViewById(R.id.video1);
                VideoView v2 = (VideoView) findViewById(R.id.video2);
                mediaController = new MediaController(this);

                String path = "android.resource://com.s1.cumminsapp/" + R.raw.video;
                Uri uri1 = Uri.parse(path);
                v1.setVideoURI(uri1);
                v1.requestFocus();
                  v1.seekTo(100);
                String path1 = "android.resource://com.s1.cumminsapp/" + R.raw.video_s;
                Uri uri11 = Uri.parse(path1);
                v2.setVideoURI(uri11);
                v2.requestFocus();
                v2.seekTo(100);

                if (b1 != null ) {

                        b1.setOnClickListener(new Button.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        b2=null;
                                        VideoView vv = (VideoView) findViewById(R.id.video1);
                                        String pathh = "android.resource://com.s1.cumminsapp/" + R.raw.video;
                                        Uri uri2 = Uri.parse(pathh);

                                        vv.setVideoURI(uri2);
                                        vv.setMediaController(mediaController);
                                        mediaController.setAnchorView(vv);
                                        vv.requestFocus();
                                        vv.start();

                                }
                        });
                        if (b2 != null ) {

                                b2.setOnClickListener(new Button.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                b1=null;
                                                VideoView v22 = (VideoView) findViewById(R.id.video2);
                                                String path1 = "android.resource://com.s1.cumminsapp/" + R.raw.video_s;
                                                Uri uri22 = Uri.parse(path1);

                                                v22.setVideoURI(uri22);
                                                v22.setMediaController(mediaController);
                                                mediaController.setAnchorView(v22);
                                                v22.requestFocus();
                                                v22.start();
                                        }
                                });
                        }

                }
        }
}











