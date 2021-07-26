package com.example.nyx.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class  register_page extends AppCompatActivity implements View.OnClickListener {
    EditText etname,etusername,etphone,etcity,etpassword;
    Button btregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        etname=(EditText)findViewById(R.id.etname);
        etpassword=(EditText)findViewById(R.id.etpassword);
        etusername=(EditText)findViewById(R.id.etuser);
        etphone=(EditText)findViewById(R.id.etphone);
        etcity=(EditText)findViewById(R.id.etcity);
        btregister=(Button)findViewById(R.id.btregister);


        btregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name=etname.getText().toString();
        String username=etusername.getText().toString();
        String phone=etphone.getText().toString();
        String password=etpassword.getText().toString();
        String city=etcity.getText().toString();

        //registration code

        DbHelper dbHelper=new DbHelper(this);

        long result = dbHelper.registration(
                 username
                ,name
                ,password
                ,phone
                ,city);

        if (result>0)
        {
            Toast.makeText(this, "User Registration Successfull", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "User Registration Failed", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
