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

public class SearchLab extends Activity implements OnClickListener
{
    EditText editlabName;
    Button btnView,btnViewAll,btnShowInfo;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lab);
        editlabName=(EditText)findViewById(R.id.searchLab);
//        stu_editName=(EditText)findViewById(R.id.editName);
//        stu_editMarks=(EditText)findViewById(R.id.editMarks);

        btnView=(Button)findViewById(R.id.btnViewL1);
        btnViewAll=(Button)findViewById(R.id.btnViewAllL1);

        //set onclick listener on button

        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);


        //creating database name StudentRecordsDB
        db=openOrCreateDatabase("TeacherRecordsDB", Context.MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR primary key,name VARCHAR,marks VARCHAR);");
    }
    public void onClick(View view)
    {

        if(view==btnView)
        {
            if(editlabName.getText().toString().trim().length()==0)
            {
                alert("Error", "Please enter lab Name");
                return;
            }

            Cursor c=db.rawQuery("SELECT * FROM labs WHERE labname='"+editlabName.getText()+"'", null);

            if(c.moveToFirst())
            {
                //view with the help of cursor object
//                stu_editName.setText(c.getString(1));
//                stu_editMarks.setText(c.getString(2));
                StringBuffer buffer=new StringBuffer();
                //view all
                buffer.append("Lab Name: "+c.getString(0)+"\n");
                buffer.append("Department Name: "+c.getString(1)+"\n");
                buffer.append("Lab Location: "+c.getString(2)+"\n");


                alert("Lab Details", buffer.toString());

            }
            else
            {
                alert("Error", "Invalid Lab Name");
                clearText();
            }
        }
       else if(view==btnViewAll)
        {
            Cursor c=db.rawQuery("SELECT * FROM labs", null);
            if(c.getCount()==0)
            {
                alert("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                //view all
                buffer.append("Lab Name: "+c.getString(0)+"\n");
                buffer.append("Department Name: "+c.getString(1)+"\n");
                buffer.append("Lab Location: "+c.getString(2)+"\n");
            }
            alert("Lab Details", buffer.toString());
        }

    }
    public void alert(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message+"\n");
        builder.show();
    }
    public void clearText()
    {
        editlabName.setText("");
        editlabName.requestFocus();
    }
}


