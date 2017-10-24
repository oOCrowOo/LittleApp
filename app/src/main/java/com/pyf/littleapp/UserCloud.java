package com.pyf.littleapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yf_p on 2017/10/20.
 */

public class UserCloud {

    String cloud_str = "http://123.207.125.107:8080";

    public void login(final String username, final String pwd)
    {
        HttpURLConnection connection = null;
        BufferedReader reader;
        try{
            String login_url_str = cloud_str + "/login?username=" + username + "&pwd=" + pwd;
            System.out.println(login_url_str);
            URL url = new URL(login_url_str);
            connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Charset","UTF-8");
            connection.setConnectTimeout(8000);
            connection.setDoInput(true);
            connection.setReadTimeout(8000);
            connection.connect();
            System.out.println(connection.getResponseCode());
            InputStream in = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            System.out.println(reader);

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("login exception");
        }finally{
            if(connection!=null){
                connection.disconnect();
            }

        }

    }
}
