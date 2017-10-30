package com.pyf.littleapp;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    String username;
    String password;
    String phone;
    private UserCloud userCloud = new UserCloud();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void reg(View view) {
        EditText user_text = (EditText) findViewById(R.id.username);
        EditText pwd_text = (EditText) findViewById(R.id.password);
        EditText phone_text = (EditText) findViewById(R.id.phone);
        username = user_text.getText().toString();
        password = pwd_text.getText().toString();
        phone = phone_text.getText().toString();
        final String username_temp = username;
        final String pwd_temp = password;
        final String phone_temp = phone;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int result = userCloud.reg(username,password,phone);

                if(result==1){
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    Looper.prepare();
                    Toast.makeText(RegisterActivity.this, "注册成功！",Toast.LENGTH_SHORT).show();
                    Looper.loop();

                }
                else{
                    Looper.prepare();
                    Toast.makeText(RegisterActivity.this, "注册失败！",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();


    }

    public void backToMain(View view) {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
