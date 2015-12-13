package com.cs246.clark.mysqltestapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

/***********************************************************************
 *
 * 10/26/2015
 *
 * @author Weston Clark, Shem Sedrick, Jared Mefford
 * @version 1.0
 **********************************************************************/
public class BackgroundTask extends AsyncTask<String, String, String> implements Observer {

    User     user;
    String   method;
    Response responseClass;
    String   date;
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String finalDate;
    String sTime;
    String eTime;

    private static final String TAG = "Background Task";

    BackgroundTask(User _user, String _method, Response _response){
        user          = _user;
        method        = _method;
        responseClass = _response;
    }

    BackgroundTask(String _date, String _method, Response _response){
        date   = _date;
        method = _method;
        responseClass = _response;
    }

    BackgroundTask(String _method, String _firstName, String _lastName, String _address, String _city,
                   String _state, String _finalDate, String _sTime, String _eTime){
        method    = _method;
        firstName = _firstName;
        lastName  = _lastName;
        address   = _address;
        city      = _city;
        state     = _state;
        finalDate = _finalDate;
        sTime     = _sTime;
        eTime     = _eTime;

    }

    @Override
    public void update(Observable observable, Object data){

    }

    @Override
    protected void onPreExecute(){
        //we don't need to do anything here...
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String...params) {



        //directs to the register php file
        String login = "";
        String data  = "";

        if(method.equals("register")) {
            login = "http://96.18.168.42:80/register_user.php";
            Log.i(TAG, "Trying to register a new user");
        } else if(method.equals("login")){
            login = "http://96.18.168.42:80/verify_login.php";
        } else if(method.equals("pull_time")){
            login = "http://96.18.168.42:80/pull_times.php";
        } else if(method.equals("confirm")) {
            login = "http://96.18.168.42:80/reserve.php";
        }

        //try opening the connection to the server
        try {
            //set up the connection
            URL url = new URL(login);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoInput (true);
            connection.setDoOutput(true);

            if(method.equals("register")) {
                //write & encode the data to be sent via the "POST" method
                 data = URLEncoder.encode("first_name", "UTF-8")+ "=" + URLEncoder.encode(user.getFirstName(), "UTF-8")+ "&" +
                        URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(user.getLastName(), "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8")  + "=" + URLEncoder.encode(user.getPassword(), "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8")     + "=" + URLEncoder.encode(user.getEmail(), "UTF-8")    + "&" +
                        URLEncoder.encode("phone", "UTF-8")     + "=" + URLEncoder.encode(user.getPhone(), "UTF-8");
            } else if(method.equals("login")){
                 data = URLEncoder.encode("email","UTF-8")      + "=" + URLEncoder.encode(user.getEmail(), "UTF-8")    + "&" +
                        URLEncoder.encode("password", "UTF-8")  + "=" + URLEncoder.encode(user.getPassword(), "UTF-8");
            } else if(method.equals("pull_time")){
                data = URLEncoder.encode("date","UTF-8")       + "=" + URLEncoder.encode(date, "UTF-8");
                System.out.println("The date pass was successful: " + date);
            } else if(method.equals("confirm")) {
                 data = URLEncoder.encode("first_name", "UTF-8")+ "=" + URLEncoder.encode(firstName, "UTF-8")+ "&" +
                        URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8") + "&" +
                        URLEncoder.encode("street", "UTF-8")  + "=" + URLEncoder.encode(address, "UTF-8") + "&" +
                        URLEncoder.encode("city", "UTF-8")     + "=" + URLEncoder.encode(city, "UTF-8")    + "&" +
                        URLEncoder.encode("state", "UTF-8")     + "=" + URLEncoder.encode(state, "UTF-8")+ "&" +
                        URLEncoder.encode("date", "UTF-8")     + "=" + URLEncoder.encode(finalDate, "UTF-8")+ "&" +
                        URLEncoder.encode("start_time", "UTF-8")     + "=" + URLEncoder.encode(sTime, "UTF-8")+ "&" +
                        URLEncoder.encode("end_time", "UTF-8")     + "=" + URLEncoder.encode(eTime, "UTF-8");
            }

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
            responseClass.setCode(responseCode);
            if(responseCode != 200){
                Log.e(TAG, "Received bad response code: " + responseCode);

                return "Failed to connect to the server...";
            }

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));

            String line;
            String response = "";
            while((line = reader.readLine()) != null){
                response+= line;
            }
            reader.close();
            in.close();
            connection.disconnect();

            responseClass.setText(response);
            System.out.println(response);

        } catch (Exception e) {
            Log.e(TAG, "Error in http connection " + e.toString());
        }

        //this should never ever happen, so if it does..please alert the local authorities
        return null;
    }


    @Override
    protected void onPostExecute(String result){
        //display a confirmation message as Toast when we're done

    }
}