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

public class TomodifyDB extends Activity implements OnClickListener
{
    EditText editFaculty1,editDepartment1,editYear1,editSubject1,editCabin1;
    Button btnAdd,btnDelete,btnUpdate;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomodify_db);
        editFaculty1=(EditText)findViewById(R.id.editFaculty1);
        editDepartment1=(EditText)findViewById(R.id.editDepartment1);
        editYear1=(EditText)findViewById(R.id.editYear1);
        editSubject1=(EditText)findViewById(R.id.editSubject1);
        editCabin1=(EditText)findViewById(R.id.editCabin1);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnUpdate=(Button)findViewById(R.id.btnModify);
        //set onclick listener on button
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        //creating database name StudentRecordsDB
        db=openOrCreateDatabase("TeacherRecordsDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS teacher(facultyname VARCHAR primary key,department VARCHAR,year VARCHAR,subject VARCHAR,cabinlocation VARCHAR);");
    }
    public void onClick(View view)
    {
        if(view==btnAdd)
        {
            //checking for blank fields
            if(editFaculty1.getText().toString().trim().length()==0||
                    editDepartment1.getText().toString().trim().length()==0||
                    editYear1.getText().toString().trim().length()==0||
                    editSubject1.getText().toString().trim().length()==0||
                    editCabin1.getText().toString().trim().length()==0)
            {
                alert("Error", "Please enter all fields");
                return;
            }
            // inserting values to DB.
            db.execSQL("INSERT INTO teacher VALUES('"+editFaculty1.getText()+"','"+editDepartment1.getText()+
                    "','"+editYear1.getText()+"','"+editSubject1.getText()+"','"+editCabin1.getText()+"');");
            alert("Success", "Record added");
            clearText();
        }
        if(view==btnDelete)
        {
            if(editFaculty1.getText().toString().trim().length()==0)
            {
                alert("Error", "Please enter Faculty Name");
                return;
            }

            //calling cursor object
            Cursor c=db.rawQuery("SELECT * FROM teacher WHERE facultyname='"+editFaculty1.getText()+"'", null);
            if(c.moveToFirst())
            {
                // delete data from table
                db.execSQL("DELETE FROM teacher WHERE facultyname='"+editFaculty1.getText()+"'");
                alert("Success", "Record Deleted");
            }
            else
            {
                alert("Error", "Invalid Faculty name");
            }
            clearText();
        }
        if(view==btnUpdate)
        {
            if(editFaculty1.getText().toString().trim().length()==0)
            {
                alert("Error", "Please enter Faculty name");
                return;
            }

            Cursor c=db.rawQuery("SELECT * FROM teacher WHERE facultyname='"+editFaculty1.getText()+"'", null);
            if(c.moveToFirst())
            {
                //update table
                db.execSQL("UPDATE teacher SET department='"+editDepartment1.getText()+
                        "', year ='"+editYear1.getText()+"', subject ='"+editSubject1.getText()+"', cabinlocation = '"+editCabin1.getText()+"' WHERE facultyname='"+editFaculty1.getText()+"'");
                alert("Success", "Record Modified");
            }
            else
            {
                alert("Error", "Invalid Faculty name");
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
        editSubject1.setText("");
        editDepartment1.setText("");
        editCabin1.setText("");
        editYear1.setText("");
        editFaculty1.setText("");
        editFaculty1.requestFocus();

    }
}



