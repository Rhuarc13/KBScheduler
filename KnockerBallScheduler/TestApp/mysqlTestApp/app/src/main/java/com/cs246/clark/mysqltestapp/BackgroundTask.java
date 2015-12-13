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
 *                  ~~ KnockerBall Schedule App ~~
 *
 * BackgroundTask... Where to start with this beast.
 * This class is like a railroad switch yard. You tell it where
 * you want it to go, and it will get you there. Just make sure you
 * pass in all the right information, or it might just derail.
 *
 * @author Weston Clark, Shem Sedrick, Jared Mefford
 * @version 1.0
 **********************************************************************/
public class BackgroundTask extends AsyncTask<String, String, String> implements Observer {

    /*Variables for passing into the php programs*/
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
    String duration;
    String email;

    /*Tag for logging*/
    private static final String TAG = "Background Task";

    /***************************************************
     * Constructor for Sign In and Register new User
     *
     * @param _user  Structure containing email, password, and other information
     * @param _method Switch for knowing which PHP function to call
     * @param _response Class for observing progress and storing responses
     ***************************************************/
    BackgroundTask(User _user, String _method, Response _response){
        user          = _user;
        method        = _method;
        responseClass = _response;
    }

    /*****************************************************
     * Constructor for DayView Interaction
     * @param _date Class that contains the date data
     * @param _method Switch for knowing which PHP function to call
     * @param _response Class for observing progress and storing responses
     *****************************************************/
    BackgroundTask(String _date, String _method, Response _response){
        date   = _date;
        method = _method;
        responseClass = _response;
    }

    /*******************************************************
     * Constructor for Registering the User
     *
     * @param _method Switch for knowing which PHP function to call
     * @param _firstName First name of the user
     * @param _lastName Last name of the user
     * @param _address  Street address that was inputted by the user
     * @param _city  City inputted by the user
     * @param _state State inputted by the user
     * @param _finalDate Date chosen by the user
     * @param _sTime     Start time to be reserved
     * @param _eTime     End time to be reserved
     * @param _response Class for observing progress and storing responses
     *******************************************************/
    BackgroundTask(String _method, String _firstName, String _lastName, String _address, String _city,
                   String _state, String _finalDate, String _sTime, String _eTime, Response _response){
        method    = _method;
        firstName = _firstName;
        lastName  = _lastName;
        address   = _address;
        city      = _city;
        state     = _state;
        finalDate = _finalDate;
        sTime     = _sTime;
        eTime     = _eTime;
        responseClass = _response;

    }

    /****************************************************
     * Constructor for sending the PHP Email
     *
     * @param _method Switch for knowing which PHP function to call
     * @param _firstName First name of the user
     * @param _lastName Last name of the user
     * @param _address  Street address that was inputted by the user
     * @param _city  City inputted by the user
     * @param _state State inputted by the user
     * @param _sTime     Start time to be reserved
     * @param _eTime     End time to be reserved
     * @param _email    Users email
     * @param _duration Float as a string for how long the reservation will last
     *****************************************************/
    BackgroundTask(String _method, String _firstName, String _lastName, String _email, String _date,
                   String _sTime, String _eTime, String _address, String _city, String _state, String _duration){
        method    = _method;
        firstName = _firstName;
        lastName  = _lastName;
        email     = _email;
        date      = _date;
        address   = _address;
        city      = _city;
        state     = _state;
        sTime     = _sTime;
        eTime     = _eTime;
        duration  = _duration;
        ;

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

        /*Sets the login to the correct URL*/
        if(method.equals("register")) {
            login = "http://96.18.168.42:80/register_user.php";
            Log.i(TAG, "Trying to register a new user");
        } else if(method.equals("login")){
            login = "http://96.18.168.42:80/verify_login.php";
        } else if(method.equals("pull_time")){
            login = "http://96.18.168.42:80/pull_times.php";
        } else if(method.equals("confirm")) {
            login = "http://96.18.168.42:80/reserve.php";
        } else if (method.equals("email")) {
            login = "http://96.18.168.42:80/email.php";
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

            /*Sets up the POST variables depending on the method*/
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
                 data = URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8") + "&" +
                        URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8") + "&" +
                        URLEncoder.encode("street", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&" +
                        URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8") + "&" +
                        URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(state, "UTF-8") + "&" +
                        URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(finalDate, "UTF-8") + "&" +
                        URLEncoder.encode("start_time", "UTF-8") + "=" + URLEncoder.encode(sTime, "UTF-8") + "&" +
                        URLEncoder.encode("end_time", "UTF-8") + "=" + URLEncoder.encode(eTime, "UTF-8");
            } else if(method.equals("email")) {
                 data = URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8") + "&" +
                        URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8") + "&" +
                        URLEncoder.encode("street", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&" +
                        URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8") + "&" +
                        URLEncoder.encode("state", "UTF-8") + "=" + URLEncoder.encode(state, "UTF-8") + "&" +
                        URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" +
                        URLEncoder.encode("start_time", "UTF-8") + "=" + URLEncoder.encode(sTime, "UTF-8") + "&" +
                        URLEncoder.encode("end_time", "UTF-8") + "=" + URLEncoder.encode(eTime, "UTF-8")+ "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("duration", "UTF-8") + "=" + URLEncoder.encode(duration, "UTF-8");

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