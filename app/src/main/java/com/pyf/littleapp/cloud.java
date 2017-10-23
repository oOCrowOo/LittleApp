package com.pyf.littleapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yf_p on 2017/10/20.
 */

public class Cloud {

    String cloud_str = "123.207.125.107";

    public void login(final String username, final String pwd)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    System.out.println("111111");
                    String login_url_str = cloud_str + "?username=" + username + "&pwd=" + pwd;
                    System.out.println(login_url_str);
                    URL url = new URL(login_url_str);
                    connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    System.out.println(response);

                }catch(Exception e){
                    System.out.println("login exception");
                }finally{
                    connection.disconnect();
                }
            }
        }).start();
    }
}
