package com.cs246.clark.mysqltestapp;
import android.os.AsyncTask;
import android.content.Context;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/***********************************************************************
 * Activity to demonstrate using background threads and updating UI views
 *
 * This activity has three buttons. One generates a file and fills it with ints,
 * one reads that file into a list and sets the list to a ListView, and one
 * is set to clear the ListView and reset the UI progress bar
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
        String address = "http://96.18.168.42/home/app/login.php:5432";

        //set up the strings so we can send them to the php files
        String user_name = params[1];
        String user_password = params[2];
        String user_email = params[3];

        //check the method to see if we will register or login
        String method = params[0];

        if (method.equals("register")) {
            //try opening the connection to the ip/php file...
            try {

                //open the connection with the correct url from our strings above...
                URL url = new URL(address);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                //This method, "POST"(we can call it what we want), will be used in the php file to pull each variable we pass in
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                //open the OutputStream and BufferedReader to write the data
                OutputStream out = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

                //This will name and set our vars to send to the php file. It is formatted this way to be read by php
                String data = URLEncoder.encode("user_name", "UTF-8")     + "=" + URLEncoder.encode(user_name, "UTF-8")+"&"+
                              URLEncoder.encode("user_password", "UTF-8") + "=" + URLEncoder.encode(user_password, "UTF-8")+"&"+
                              URLEncoder.encode("user_email", "UTF-8")    + "=" + URLEncoder.encode(user_email, "UTF-8");

                //write the data, flush the buffer, and close up shop
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                out.close();

                //this will be sent to 'onPostExecute' and show as Toast
                return "Registration Complete!";

            //catch everything that might have gone wrong...
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //this should never happen. If it does...please hang up and call 911
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        //display a confirmation message as Toast when we're done
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

}
