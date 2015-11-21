package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class SignIn extends Activity {

    TextView emailLogin;
    TextView passLogin;
    Response response;
    private static final String TAG = "SIGN_IN";
    private static final String SUCCESS = "SUCCESS: Customer creation complete";


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

        if (response.getCode() == 200) {
            if (response.getText().equals(SUCCESS)) {
                Intent intent = new Intent(this, Calendar.class);
                startActivity(intent);
                finish();
            } else {
                Log.e(TAG, "Incorrect response string: " + response.getText());
                if (response.getText().equals("email") || response.getText().equals("password")) {
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                    emailLogin.setText("");
                    passLogin.setText("");
                }
            }
        } else {
            Toast.makeText(this, "Error occurred connecting to the database", Toast.LENGTH_LONG).show();
        }

    }
}


