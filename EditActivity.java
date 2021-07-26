package com.example.nyx.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etname,etusername,etphone,etcity;
    Button btedit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);



        etname =(EditText)findViewById(R.id.etname);
        etusername=(EditText)findViewById(R.id.etusername);
        etcity=(EditText)findViewById(R.id.etcity);
        etphone=(EditText)findViewById(R.id.etphone);

        btedit=(Button)findViewById(R.id.btupdate);

        String username=getIntent().getStringExtra("username");
        etusername.setText(username);

        btedit.setOnClickListener(this);
        DbHelper dbHelper=new DbHelper(this);
        Cursor cursor=dbHelper.getMember(username);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();//move to first row

            String _username= cursor.getString(0);
            String _name=cursor.getString(cursor.getColumnIndex("name"));
            String _phone=cursor.getString(cursor.getColumnIndex("phone"));
            String _city=cursor.getString(cursor.getColumnIndex("city"));
            etname.setText(_name);
            etusername.setText(_username);
            etcity.setText(_city);
            etphone.setText(_phone);

        }

    }

    @Override
    public void onClick(View v) {

        String username=etusername.getText().toString();
        String name=etname.getText().toString();
        String phone=etphone.getText().toString();
        String city=etcity.getText().toString();

        DbHelper dbHelper=new DbHelper(EditActivity.this);

        long result =dbHelper.updateinfo(username,name,phone,city);
        if(result>0)
        {
            Toast.makeText(EditActivity.this, "Update Successfull", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Toast.makeText(EditActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
        }

    }
}




