package com.s1.cumminsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


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

public class SearchCabin extends Activity implements OnClickListener
{
    EditText faculty;
    Button btnView,btnViewAll,btnShowInfo;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cabin1);
        faculty=(EditText)findViewById(R.id.Faculty);
//        stu_editName=(EditText)findViewById(R.id.editName);
//        stu_editMarks=(EditText)findViewById(R.id.editMarks);

        btnView=(Button)findViewById(R.id.btnView);
        btnViewAll=(Button)findViewById(R.id.btnViewAll);
        //btnShowInfo=(Button)findViewById(R.id.btnShowInfo);
        //set onclick listener on button

        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        //btnShowInfo.setOnClickListener(this);

        //creating database name StudentRecordsDB
        db=openOrCreateDatabase("TeacherRecordsDB", Context.MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR primary key,name VARCHAR,marks VARCHAR);");
    }
    public void onClick(View view)
    {

        if(view==btnView)
        {
            if(faculty.getText().toString().trim().length()==0)
            {
                alert("Error", "Please enter Faculty Name");
                return;
            }

            Cursor c=db.rawQuery("SELECT * FROM teacher WHERE facultyname='"+faculty.getText()+"'", null);

            if(c.moveToFirst())
            {
                //view with the help of cursor object
//                stu_editName.setText(c.getString(1));
//                stu_editMarks.setText(c.getString(2));
                StringBuffer buffer=new StringBuffer();
                //view all
                buffer.append("Faculty Name: "+c.getString(0)+"\n");
                buffer.append("Department: "+c.getString(1)+"\n");
                buffer.append("Year: "+c.getString(2)+"\n");
                buffer.append("Subject: "+c.getString(3)+"\n");
                buffer.append("Cabin: "+c.getString(4)+"\n\n");

                alert("Teacher Details", buffer.toString());

            }
            else
            {
                alert("Error", "Invalid Faculty Name");
                clearText();
            }
        }
        if(view==btnViewAll)
        {
            Cursor c=db.rawQuery("SELECT * FROM teacher", null);
            if(c.getCount()==0)
            {
                alert("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                //view all
                buffer.append("Faculty Name: "+c.getString(0)+"\n");
                buffer.append("Department: "+c.getString(1)+"\n");
                buffer.append("Year: "+c.getString(2)+"\n");
                buffer.append("Subject: "+c.getString(3)+"\n");
                buffer.append("Cabin: "+c.getString(4)+"\n\n");
            }
            alert("Teacher Details", buffer.toString());
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
        faculty.setText("");
        faculty.requestFocus();
    }
}


