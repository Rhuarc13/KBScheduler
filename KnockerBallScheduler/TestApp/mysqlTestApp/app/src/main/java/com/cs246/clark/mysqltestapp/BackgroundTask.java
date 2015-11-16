package com.cs246.clark.mysqltestapp;
import android.os.AsyncTask;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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

        //check the method to see if we will register or login
        String login = "";
        String data = "";
        String response = "";
        String line = "";
        //set up the strings so we can send them to the php files
        String user_firstName = "";
        String user_lastName = "";
        String user_password = "";
        String user_email = "";
        String user_phone = "";


        String method = params[0];


        if (method.equals("register")) {
            //directs to the register php file
            login = "http://96.18.168.42:80/register_user.php";

            //set up the strings so we can send them to the php files
            user_firstName = params[1];
            user_lastName  = params[2];
            user_password  = params[3];
            user_email     = params[4];
            user_phone     = params[5];

        } else if(method.equals("login")) {
            //directs to the login php file
            login = "http://96.18.168.42:80/verify_login.php";

            user_email    = params[1];
            user_password = params[2];
        }

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

                if(method.equals("register")) {
                    //write & encode the data to be sent via the "POST" method
                    data = URLEncoder.encode("first_name", "UTF-8")+ "=" + URLEncoder.encode(user_firstName, "UTF-8")+ "&" +
                           URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(user_lastName, "UTF-8") + "&" +
                           URLEncoder.encode("password", "UTF-8")  + "=" + URLEncoder.encode(user_password, "UTF-8") + "&" +
                           URLEncoder.encode("email", "UTF-8")     + "=" + URLEncoder.encode(user_email, "UTF-8")    + "&" +
                           URLEncoder.encode("phone", "UTF-8")     + "=" + URLEncoder.encode(user_phone, "UTF-8");
                } else if(method.equals("login")){
                    data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(user_email, "UTF-8") + "&" +
                           URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(user_password, "UTF-8");
                    System.out.println(user_email);
                    System.out.println(user_password);
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
                System.out.println(responseCode);
                if(responseCode != 200){
                    return "Failed to connect to the server...";
                }

                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));

                while((line = reader.readLine()) != null){
                    response+= line;
                }
                reader.close();
                in.close();
                connection.disconnect();

                System.out.println(response);

                if(method.equals("register")) {
                    return "Registration Complete";
                } else if(method.equals("login")){
                    if(response.equals("Success")) {
                        return "Welcome";
                    } else{
                        return "Error: Check your information and try again";
                    }
                }

            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
            }


    //this should never ever happen, so if it does..something went terribly wrong
    return null;
    }

    @Override
    protected void onPostExecute(String result){
        //display a confirmation message as Toast when we're done
        Toast toast = Toast.makeText(context, result, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 25);
        toast.show();
    }
}