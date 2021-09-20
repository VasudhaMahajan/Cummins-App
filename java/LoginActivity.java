package com.s1.cumminsapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b1;
    private EditText e1;
    private EditText e2;
    private TextView t1;

    private DrawerLayout mdrawer;
    private ActionBarDrawerToggle actionBar;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();


//        if(firebaseAuth.getCurrentUser() != null)
//        {
//            // 4 options to be performed....
//            finish();
//            startActivity(new Intent(getApplicationContext(),options.class));
//        }

        t1= (TextView) findViewById(R.id.t1);
        b1 = (Button) findViewById(R.id.button1);
        e1 = (EditText) findViewById(R.id.email1);
        e2 = (EditText) findViewById(R.id.pass1);

        b1.setOnClickListener(this);
        t1.setOnClickListener(this);

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(actionBar.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void userLogin() {
        String email = e1.getText().toString().trim();
        final String password = e2.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful())
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(),UploadFile.class));
                }
                else
                {

                }
            }
        });

    }
    @Override
    public void onClick(View view) {
        if(view == b1)
        {
            userLogin();
        }

        if(view == t1)
        {
            finish();
            startActivity(new Intent(this,Assignments.class));
        }
    }
}

