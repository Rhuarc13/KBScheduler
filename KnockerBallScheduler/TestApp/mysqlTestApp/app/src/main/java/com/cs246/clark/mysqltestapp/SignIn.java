package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

        user.setEmail(emailLogin.getText().toString());
        user.setPassword(passLogin.getText().toString());

        String method = "login";

        BackgroundTask backgroundTask = new BackgroundTask(user, method, response);
        backgroundTask.execute();
        Log.i(TAG, "Once your a jet your a jet all the way");

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

            if (response.getCode() == 200) {
                try {
                    lock.wait(500);
                    if (response.getText().equals("password") || response.getText().equals("email")) {
                        Log.e(TAG, "Incorrect response string: " + response.getText());
                        if (response.getText().equals("email") || response.getText().equals("password")) {
                            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                            emailLogin.setText("");
                            passLogin.setText("");
                            emailLogin.requestFocus();
                        }
                    } else {
                        Intent intent = new Intent(this, Calendar.class);
                        String[] info = getName(response).split(":");
                        if (info.length > 1) {
                            intent.putExtra("name", info[0]);
                            intent.putExtra("phone", info[1]);
                            intent.putExtra("email", emailLogin.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            Log.e(TAG, "Response Array to small...");
                        }
                    }
                } catch (InterruptedException ie) {
                    Log.e(TAG, "Wait was interrupted...");
                }
            } else {
                Toast.makeText(this, "Error occurred connecting to the database: code " + response.getCode(), Toast.LENGTH_LONG).show();
            }
        }

    }

    private String getName (Response response) {
        String name = "";
        try {
            JSONObject obj = new JSONObject(response.getText());
            JSONArray array = obj.getJSONArray("info");
            name = array.get(0) + " " + array.get(1) + ":" + array.get(2);

        } catch (JSONException je) {
            Log.e(TAG, "Could not get JSON object from string");
        }
        return name;
    }
}


