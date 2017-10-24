package com.pyf.littleapp;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String username;
    String password;
    private UserCloud userCloud = new UserCloud();
    JSONObject user_info = new JSONObject();

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
        final String username_temp = username;
        final String pwd_temp = password;

        new Thread(new Runnable() {
            @Override
            public void run() {
                user_info = userCloud.login(username_temp,pwd_temp);
                if(user_info.length()==0){
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "账号或者密码错误，请重新输入。",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
                else{
                    System.out.println("123123: "+ user_info.length());
                    try{
                        if(user_info.get("manager").equals(true)){

                            Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                            intent.putExtra("username",user_info.get("username").toString());
                            intent.putExtra("phone",user_info.get("phone").toString());
                            startActivity(intent);

                            Looper.prepare();
                            Toast.makeText(MainActivity.this, "管理员登录成功，欢迎" + user_info.get("username") + "！",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        else{

                            Intent intent = new Intent(MainActivity.this, UserActivity.class);
                            intent.putExtra("username",user_info.get("username").toString());
                            intent.putExtra("phone",user_info.get("phone").toString());
                            startActivity(intent);

                            Looper.prepare();
                            Toast.makeText(MainActivity.this, "会员登录成功，欢迎" +  user_info.get("username") + "！",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                    catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void Register(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
