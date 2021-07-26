package com.example.nyx.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FellowMemberActivity extends AppCompatActivity {

    TextView tvname,tvusername,tvphone,tvcity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fellow_member);

        tvname =(TextView)findViewById(R.id.tvname);
        tvusername=(TextView)findViewById(R.id.tvusername);
        tvcity=(TextView)findViewById(R.id.tvcity);
        tvphone=(TextView)findViewById(R.id.tvphone);

        String username=getIntent().getStringExtra("username");
        tvusername.setText(username);

        DbHelper dbHelper=new DbHelper(this);
        Cursor cursor=dbHelper.getMember(username);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();//move to first row

            String _username= cursor.getString(0);
            String _name=cursor.getString(cursor.getColumnIndex("name"));
            String _phone=cursor.getString(cursor.getColumnIndex("phone"));
            String _city=cursor.getString(cursor.getColumnIndex("city"));
            tvname.setText(_name);
            tvusername.setText(_username);
            tvcity.setText(_city);
            tvphone.setText(_phone);


        }

    }
 }

