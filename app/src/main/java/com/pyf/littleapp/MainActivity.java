package com.pyf.littleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view) {
        EditText user_text = (EditText) findViewById(R.id.username);
        EditText pwd_text = (EditText) findViewById(R.id.password);
        username = user_text.getText().toString();
        password = pwd_text.getText().toString();
    }
}
