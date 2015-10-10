package com.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bLogout;
    TextView tvName,tvAge,tvUsername,tvPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bLogout     =(Button)findViewById(R.id.bLogout);
        tvName     =(TextView)findViewById(R.id.tvName);
        tvAge     =(TextView)findViewById(R.id.tvAge);
        tvUsername     =(TextView)findViewById(R.id.tvUsername);
        tvPassword     =(TextView)findViewById(R.id.tvPassword);
    }
}
