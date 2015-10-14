package com.loginregister;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    public void onLoginClick(View view)
    {
        String username=etUsername.getText().toString();
        String password=etPassword.getText().toString();
        User user=new User(username,password);
        authenticate(user);

    }
    private void authenticate(User user)
    {
        ServerRequest serverRequest=new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User user) {
                if (user == null) {
                    showErrorMessage();
                }
                else{

                }
            }
        });
    }
    private void showErrorMessage()
    {
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("User Details Incorrect");
        dialogBuilder.setPositiveButton("OK",null);
        dialogBuilder.show();
    }
}
