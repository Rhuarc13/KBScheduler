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

/***********************************************************************
 *
 *                  ~~ KnockerBall Schedule App ~~
 *                   ~ Calendar Events class ~
 * This class is a helper class for the CalendarView. This code was
 * originally provided by the GitHub user a7med, but we modified
 * the code to fit our needs.
 *
 * @author Weston Clark, Shem Sedrick, Jared Mefford, a7med
 * @version 1.0
 **********************************************************************/
public class CalendarEvents extends AsyncTask<String, String, String> {
    Response responseClass;
    HashSet<Day> dates;
    private static final String TAG = "Pull Calendar Events";

    CalendarEvents(HashSet<Day> _events, Response _response){
        dates = _events;
        responseClass = _response;
    }

    /*****************************************
     * readAll
     * This function takes an input reader and takes the output
     * from the server and returns that as a JSON string
     *
     * @param reader Our input reader for getting our response from the server
     * @return String This returns the full JSON string from the server
     * @throws IOException
     *****************************************/
    private String readAll(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /*****************************************
     * doInBackground
     *
     * This is our background method for getting
     * all the reservations and adds them to the calendar
     * view
     *
     * @param params We don't do anything with these parameters :(
     * @return We don't return an actual string. The response is stored
     *          in the response class that was passd into the constructor
     *****************************************/
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

                //Parsing out the JSON Object
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
                    Day day = new Day(new Date(cal.getTimeInMillis()));


                    if (flag == 'T') {
                        //Set background color to available
                        day.setAvailable(true);
                    } else {
                        //Set background color to black
                        day.setAvailable(false);
                    }
                    dates.add(day);
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
