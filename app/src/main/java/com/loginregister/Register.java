package com.loginregister;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity
{
    EditText etName,etAge,etUsername,etPassword;
    Button bRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        etName=(EditText)findViewById(R.id.etName);
        etAge=(EditText)findViewById(R.id.etAge);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        bRegister=(Button)findViewById(R.id.bRegister);
    }
}