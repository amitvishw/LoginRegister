package com.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;
    Button bLogout;
    TextView tvName,tvAge,tvUsername,tvPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bLogout         =(Button)findViewById(R.id.bLogout);
        tvName          =(TextView)findViewById(R.id.tvName);
        tvAge           =(TextView)findViewById(R.id.tvAge);
        tvUsername      =(TextView)findViewById(R.id.tvUsername);
        tvPassword      =(TextView)findViewById(R.id.tvPassword);
        userLocalStore  =new UserLocalStore(this);
        isLoggedIn(userLocalStore);

    }
    public void isLoggedIn(UserLocalStore userLocalStore)
    {
        if(userLocalStore.getUserLoggedIn())
        {
            User user=userLocalStore.getLoggedInUser();
            tvName.setText(user.name);
            tvAge.setText(Integer.toString(user.age));
            tvUsername.setText(user.username);
            tvPassword.setText(user.password);
        }
        else
        {
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        }
    }
    public void onClickLogout(View view)
    {
        userLocalStore.setUserLoggedIn(false);
        userLocalStore.clearUserData();
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }
}
