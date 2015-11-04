package com.cs246.clark.mysqltestapp;
import android.os.AsyncTask;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/***********************************************************************
 *
 *
 * 10/26/2015
 *
 * @author Weston Clark, Shem Sedrick, Jared Mefford
 * @version 1.0
 **********************************************************************/
public class BackgroundTask extends AsyncTask<String, String, String> {

    Context context;

    BackgroundTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        //we don't need to do anything here...
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String...params) {

        //we can store the ip and each php file we want to run here
        String login = "http://96.18.168.42:80/home/app/";

        //set up the strings so we can send them to the php files
        String user_name     = params[1];
        String user_password = params[2];
        String user_email    = params[3];

        //check the method to see if we will register or login
        String method = params[0];

        if (method.equals("register")) {
            //try opening the connection to the ip/php file...
            try {

                URL url = new URL(login);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");
                //connection.setDoInput(true);
                connection.setDoOutput(true);

                String data = URLEncoder.encode("user_name", "UTF-8")     + "=" + URLEncoder.encode(user_name, "UTF-8")+"&"+
                              URLEncoder.encode("user_password", "UTF-8") + "=" + URLEncoder.encode(user_password, "UTF-8") +"&"+
                              URLEncoder.encode("value", "UTF-8")         + "=" + URLEncoder.encode("89 Charlotte St.", "UTF-8");

                OutputStream out = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(data);
                writer.flush();
                writer.close();
                out.close();

                connection.connect();

                int responseCode = connection.getResponseCode();
                System.out.println("POST Response Code :: " + responseCode);

                return "Success!";

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
        }
    return null;
    }

    @Override
    protected void onPostExecute(String result){
        //if(!result.equals("Connected Successfully")){
         //   result = "Something went wrong...";
       // }
        //display a confirmation message as Toast when we're done
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

}