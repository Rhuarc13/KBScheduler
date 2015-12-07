package com.cs246.clark.mysqltestapp;

import android.os.AsyncTask;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rhuarc on 12/7/15.
 */
public class CalendarEvents extends AsyncTask<String, String, String> {
    User user;
    Response responseClass;
    private static final String TAG = "Pull Calendar Events";

    CalendarEvents(User _user, Response _response){
        user   = _user;
        responseClass = _response;
    }

    protected String doInBackground(String... params) {
        String login = "http://96.18.168.42:80/pull_dates_times.php";
        String data = "";
        String response = "";
        try {
            //set up the connection
            URL url = new URL(login);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            
        } catch (Exception e) {
            Log.e(TAG, "Setting up URL connection returned an exception: " + e.toString());
        }
        return response;
    }
}
