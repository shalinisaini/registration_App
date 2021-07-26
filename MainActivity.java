package com.example.nyx.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvname,tvpassword,tvnewuser;
    EditText etusername,etpassword;
    Button btlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusername=(EditText)findViewById(R.id.etuser);
        etpassword=(EditText)findViewById(R.id.etpassword);
        tvnewuser=(TextView)findViewById(R.id.tvnewuser);
        btlogin=(Button)findViewById(R.id.btlogin);

        btlogin.setOnClickListener(this);
        tvnewuser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btlogin:
                String username=etusername.getText().toString();
                String password=etpassword.getText().toString();

                //Login code
                DbHelper dbHelper=new DbHelper(this);

                Object[] result=dbHelper.login(username,password);


                if((boolean)result[0])
                {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.tvnewuser:
                Intent intentRegister=new Intent(this,register_page.class);
                startActivity(intentRegister);
                Toast.makeText(this, "Register Here", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
