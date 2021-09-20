package com.s1.cumminsapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class AboutUs extends AppCompatActivity implements View.OnClickListener{

    ViewFlipper vf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TextView text = (TextView) findViewById(R.id.abtus);
        text.setMovementMethod(LinkMovementMethod.getInstance());


        vf = (ViewFlipper)findViewById(R.id.viewflipper);

        vf.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == vf)
        {
            vf.showNext();
        }
    }
}

