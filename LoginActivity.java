package com.example.nyx.database;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvUser;
    Button btedit,btdelete;
    ListView lvuserlist;
    String username;

    List <String> listuser=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvUser=(TextView)findViewById(R.id.textView);
        btedit=(Button)findViewById(R.id.btedit);
        btdelete=(Button)findViewById(R.id.btdelete);
        lvuserlist=(ListView)findViewById(R.id.listView);
        username=getIntent().getStringExtra("username");

        tvUser.setText("Welcome "+username);


        btedit.setOnClickListener(this);
        btdelete.setOnClickListener(this);

        initialseList();
          }


    void initialseList()
    {
        DbHelper dbHelper=new DbHelper(this);
        Cursor cursor=dbHelper.getFellowMember();

        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();

            do{
                String _username=cursor.getString(0);
                String _name=cursor.getString(cursor.getColumnIndex("name"));
                String _phone=cursor.getString(cursor.getColumnIndex("phone"));
                String _city=cursor.getString(cursor.getColumnIndex("city"));

                listuser.add(_username);
            }while(cursor.moveToNext());
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listuser);

            lvuserlist.setAdapter(adapter);

            lvuserlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(LoginActivity.this,FellowMemberActivity.class);
                    intent.putExtra("username",listuser.get(position));
                    startActivity(intent);
                }
            });


        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btedit:
                Intent intent=new Intent(LoginActivity.this,EditActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;

            case R.id.btdelete:
                AlertDialog.Builder simpleBuilder=new AlertDialog.Builder(this);

                simpleBuilder.setTitle("Alert !!!!");
                simpleBuilder.setMessage("Do u want to Delete your account ?");
                simpleBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                simpleBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                break;
        }
    }

}
