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
        String login = "http://96.18.168.42:80/query.php";

        //set up the strings so we can send them to the php files
        String user_firstName = params[1];
        String user_lastName  = params[2];
        String user_password  = params[3];
        String user_email     = params[4];
        String user_phone     = params[5];

        //check the method to see if we will register or login
        String method = params[0];

        if (method.equals("register")) {
            //try opening the connection to the ip/php file...
            try {

                //set up the connection
                URL url = new URL(login);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                //write & encode the data to be sent via the "POST" method
                String data = URLEncoder.encode("user_firstName","UTF-8") + "=" + URLEncoder.encode(user_firstName,"UTF-8")+"&"+
                              URLEncoder.encode("user_lastName", "UTF-8") + "=" + URLEncoder.encode(user_lastName, "UTF-8")+"&"+
                              URLEncoder.encode("user_password", "UTF-8") + "=" + URLEncoder.encode(user_password, "UTF-8") +"&"+
                              URLEncoder.encode("user_email",    "UTF-8") + "=" + URLEncoder.encode(user_email,    "UTF-8")+"&"+
                              URLEncoder.encode("user_phone",    "UTF-8") + "=" + URLEncoder.encode(user_phone,    "UTF-8");

                //write the data to the stream and close up shop
                OutputStream out = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(data);
                writer.flush();
                writer.close();
                out.close();

                connection.connect();

                //used to verify we got the right response back from the server
                int responseCode = connection.getResponseCode();
                System.out.println(responseCode);
                if(responseCode != 200){
                    return "Failed to connect to the server...";
                }

                return "Success!";

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
            }
        }
    //this should never ever happen, so if it does..something went terribly wrong
    return null;
    }

    @Override
    protected void onPostExecute(String result){
        //display a confirmation message as Toast when we're done
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

}