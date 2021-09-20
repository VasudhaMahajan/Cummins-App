package com.s1.cumminsapp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;



public class TomodifyLabs extends Activity implements OnClickListener
{
    EditText Labname,Departmentname,LabLocation;
    Button btnAddL,btnDeleteL,btnUpdateL;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomodify_labs);
        Labname=(EditText)findViewById(R.id.Labname);
        Departmentname=(EditText)findViewById(R.id.Departmentname);
        LabLocation=(EditText)findViewById(R.id.LabLocation);

        btnAddL=(Button)findViewById(R.id.btnAddL);
        btnDeleteL=(Button)findViewById(R.id.btnDeleteL);
        btnUpdateL=(Button)findViewById(R.id.btnModifyL);
        //set onclick listener on button
        btnAddL.setOnClickListener(this);
        btnDeleteL.setOnClickListener(this);
        btnUpdateL.setOnClickListener(this);

        //creating database name StudentRecordsDB
        db=openOrCreateDatabase("TeacherRecordsDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS labs(labname VARCHAR primary key,departmentname VARCHAR,lablocation VARCHAR);");
    }
    public void onClick(View view)
    {
        if(view==btnAddL)
        {
            //checking for blank fields
            if(Labname.getText().toString().trim().length()==0||
                    Departmentname.getText().toString().trim().length()==0||
                    LabLocation.getText().toString().trim().length()==0)
            {
                alert("Error", "Please enter all fields");
                return;
            }
            // inserting values to DB.
            db.execSQL("INSERT INTO labs VALUES('"+Labname.getText()+"','"+Departmentname.getText()+
                    "','"+LabLocation.getText()+"');");
            alert("Success", "Record added");
            clearText();
        }
        if(view==btnDeleteL)
        {
            if(Labname.getText().toString().trim().length()==0)
            {
                alert("Error", "Please enter Lab Name");
                return;
            }

            //calling cursor object
            Cursor c=db.rawQuery("SELECT * FROM labs WHERE labname='"+Labname.getText()+"'", null);
            if(c.moveToFirst())
            {
                // delete data from table
                db.execSQL("DELETE FROM labs WHERE labname='"+Labname.getText()+"'");
                alert("Success", "Record Deleted");
            }
            else
            {
                alert("Error", "Invalid lab name");
            }
            clearText();
        }
        if(view==btnUpdateL)
        {
            if(Labname.getText().toString().trim().length()==0)
            {
                alert("Error", "Please enter lab name");
                return;
            }

            Cursor c=db.rawQuery("SELECT * FROM labs WHERE labname='"+Labname.getText()+"'", null);
            if(c.moveToFirst())
            {
                //update table
                db.execSQL("UPDATE labs SET departmentname='"+Departmentname.getText()+
                        "', lablocation ='"+LabLocation.getText()+"' WHERE labname='"+Labname.getText()+"'");
                alert("Success", "Record Modified");
            }
            else
            {
                alert("Error", "Invalid Lab name");
            }
            clearText();
        }

    }
    public void alert(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        Labname.setText("");
        LabLocation.setText("");
        Departmentname.setText("");
        Labname.requestFocus();

    }
}



