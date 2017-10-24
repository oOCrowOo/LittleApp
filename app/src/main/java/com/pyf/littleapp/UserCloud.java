package com.pyf.littleapp;

import org.json.JSONObject;

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

    public JSONObject login(final String username, final String pwd)
    {
        HttpURLConnection connection = null;
        BufferedReader reader;
        JSONObject user_info = new JSONObject();
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



            if(connection.getResponseCode()==200){
                InputStream in = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while((line=reader.readLine())!=null){
                    response.append(line);
                }
                if(response.length()!=0){
                    user_info = new JSONObject(response.toString());
                    //System.out.println(user_info);
                }
            }
        }catch(Exception e){
            System.out.println("login exception");
            e.printStackTrace();
        }finally{
            if(connection!=null){
                connection.disconnect();
            }
        }
        return user_info;
    }
}
