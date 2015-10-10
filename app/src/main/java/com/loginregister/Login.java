package com.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    Button bLogin;
    EditText etUsername,etPassword;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin=(Button)findViewById(R.id.bLogIn);
        etUsername=(EditText)findViewById(R.id.etUsernameLogin);
        etPassword=(EditText)findViewById(R.id.etPasswordLogin);
       /* Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            String j =(String) b.get("name");
            name.setText(j);
        }*/
    }
}
