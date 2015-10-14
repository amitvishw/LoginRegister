package com.loginregister;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    public void onClickRegister(View view)
    {
        String name     = etName.getText().toString();
        int age         = Integer.parseInt(etAge.getText().toString());
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        User user       =new User(name,age,username,password);
        registerUser(user);
    }
    public void registerUser(User user)
    {
        ServerRequest serverRequest=new ServerRequest(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User user) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}