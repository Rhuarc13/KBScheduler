package com.cs246.clark.mysqltestapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by rhuarc on 12/7/15.
 */
public class CalendarEvents extends AsyncTask<String, String, String> {
    Response responseClass;
    HashSet<Date> dates;
    private static final String TAG = "Pull Calendar Events";

    CalendarEvents(HashSet<Date> _events, Response _response){
        dates = _events;
        responseClass = _response;
    }

    private String readAll(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    protected String doInBackground(String... params) {
        String login = "http://96.18.168.42:80/pull_dates_times.php";

        try {
            //set up the connection
            URL url = new URL(login);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

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
            try {
                String jsontext = readAll(reader);
                Log.i(TAG, jsontext);
                JSONObject jsonObject = new JSONObject(jsontext);
                JSONArray array = jsonObject.getJSONArray("events");
                Log.i(TAG, "Array length: " + array.length());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject event = array.getJSONObject(i);
                    String date = event.getString("reservation_date");
                    char flag = event.getString("available").charAt(0);

                    Log.i(TAG, date);
                    java.util.Calendar cal = java.util.Calendar.getInstance();
                    String[] specifics = date.split(Pattern.quote("-"));
                    cal.set(Integer.parseInt(specifics[0]), Integer.parseInt(specifics[1]) - 1, Integer.parseInt(specifics[2]));
                    dates.add(new Date(cal.getTimeInMillis()));

                    if (flag == 'T') {
                        //Set background color to available
                    } else {
                        //Set background color to black
                    }
                }
            } catch (org.json.JSONException e) {
                Log.e(TAG, "JSON error: " + e.toString());
            }

            String line;
            String response = "";
            while((line = reader.readLine()) != null){
                response += line;
            }
            reader.close();
            in.close();
            connection.disconnect();

            responseClass.setText(response);

        } catch (Exception e)
        {
            Log.e(TAG, "Setting up URL connection returned an exception: " + e.toString());
        }
        return "finished";
    }
}
