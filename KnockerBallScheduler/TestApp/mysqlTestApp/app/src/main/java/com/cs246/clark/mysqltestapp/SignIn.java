package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/***********************************************************************
 *
 *                  ~~ KnockerBall Schedule App ~~
 *
 * This application is intended to serve as an interface to communicate
 * with a MySQL Database to create and store scheduling information for
 * the KnockerBall rental service. The app will provide users with a means
 * of scheduling a reserved time to rent the KnockerBalls and will express
 * those reservations on a master calendar for the renter to manage.
 *
 * 10/26/2015
 *
 * @author Weston Clark, Shem Sedrick, Jared Mefford
 * @version 1.0
 **********************************************************************/

/***********************************************************************
 * SignIn
 *
 * This class allows you to enter your account email and password
 * to be able to log in. This is verified in the database
 **********************************************************************/
public class SignIn extends Activity {

    TextView emailLogin;
    TextView passLogin;
    Response response;
    private static final String TAG = "SIGN_IN";
    private static final String SUCCESS = "Success";


    /*********************************************************************
     * On Create - sets the xml main layout
     **********************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);

        response = new Response();
    }

    /*********************************************************************
     * Sign In 'button' takes you to the sign in activity
     **********************************************************************/
    public void signInButton(View view){

        //needs to verify if the username/pass are valid
        emailLogin = (TextView) findViewById(R.id.email);
        passLogin  = (TextView) findViewById(R.id.passwordLogin);

        User user = new User();

        //sets the local vars
        user.setEmail(emailLogin.getText().toString());
        user.setPassword(passLogin.getText().toString());

        //our method to tell backgroundTask what php page to access
        String method = "login";

        //sends the info to backgroundTask to speak with the server
        BackgroundTask backgroundTask = new BackgroundTask(user, method, response);
        backgroundTask.execute();
        Log.i(TAG, "Once your a jet your a jet all the way");

        //this locks the app till we get confirmation back from the server, thus preventing errors
        Lock lock = new ReentrantLock();
        int waitTime = 0;
        synchronized (lock) {
            while (response.getCode() == 0 && waitTime < 26) {
                try {
                    lock.wait(100);
                    waitTime++;
                } catch (InterruptedException e) {
                    Log.e(TAG, "Ran into an InterruptedException");
                }
            }
            try {
                lock.wait(300);
                //check to see if we connected to the server or not (200 is good!)
                if (response.getCode() == 200) {
                    //iff we did get in, we need to then check and set some vars
                    if (!response.getText().equals("password") || !response.getText().equals("email")) {

                        Intent intent = new Intent(this, Menu.class);
                        intent.putExtra("name", getData("name", response));
                        intent.putExtra("phone", getData("phone", response));
                        intent.putExtra("email", emailLogin.getText().toString());
                        startActivity(intent);
                        finish();
                    } else {
                        //what happens if we didn't get in - check why if the if/else
                        Log.e(TAG, "Incorrect response string: " + response.getText());
                        if (response.getText().equals("email") || response.getText().equals("password")) {
                            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                            emailLogin.setText("");
                            passLogin.setText("");
                            emailLogin.requestFocus();
                        } else {
                            Toast.makeText(this, "Error occurred connecting to the database: code " + response.getCode(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            } catch (InterruptedException ie) {
                Log.e(TAG, "There was an interrupted exception");
            }
        }
    }

    //this handles the response from the server, telling us what was wrong, if anything
    private String getData (String temp, Response response) {

        try {
            JSONObject obj = new JSONObject(response.getText());
            JSONArray array = obj.getJSONArray("info");

            if (temp == "name") {
                temp = array.get(0) + " " + array.get(1);
            }
            else if (temp == "phone") {
                temp = array.get(2).toString();
            }

        } catch (JSONException je) {
            Log.e(TAG, "Could not get JSON object from string");
        }

        return temp;
    }
}
