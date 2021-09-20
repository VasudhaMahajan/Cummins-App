package com.s1.cumminsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import static android.R.id.message;

public class Assignments extends AppCompatActivity implements View.OnClickListener {
    private Button b;
    private EditText e1;
    private EditText e2;
    private TextView t1;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        b= (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.email);
        e2 = (EditText) findViewById(R.id.pass);
        t1 = (TextView) findViewById(R.id.t1);

        b.setOnClickListener(this);
        t1.setOnClickListener(this);
    }

    private void registerUser()
    {
        String email = e1.getText().toString().trim();
        String password = e2.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter your email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering user");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(!task.isSuccessful()){

                    FirebaseAuthException e = (FirebaseAuthException )task.getException();
                    progressDialog.hide();
                    Toast.makeText(Assignments.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.hide();
                    return;
                }
                else
                {
                    Toast.makeText(Assignments.this,"Registered Successfully...",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onClick(View v) {
        if(v == b)
        {
            registerUser();
        }
        if(v == t1)
        {
            Intent intent = new Intent(Assignments.this,LoginActivity.class);
            startActivity(intent);
        }

    }
}

