package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class SignIn extends Activity {

    TextView emailLogin;
    TextView passLogin;
    private static final String TAG = "SIGN_IN";


    /*********************************************************************
    * On Create - sets the xml main layout
    **********************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);
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

        BackgroundTask backgroundTask = new BackgroundTask(user, method);
        backgroundTask.execute();
        Log.i(TAG, "Once your a jet your a jet all the way");
        finish();
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);

        }
    }


