package com.cs246.clark.mysqltestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/***********************************************************************
 *
 *                  ~~KnockerBall Schedule App~~
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
public class MainActivity extends AppCompatActivity {

    //Button button = (Button) findViewById(R.id.registerButton);
    TextView status;

    EditText nameView;
    EditText passwordView;
    EditText emailView;

    /*********************************************************************
    * On Create - sets the xml main layout
    **********************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /*********************************************************************
    * Register 'button' connects to and sends data to the MySQL DB
    **********************************************************************/
    public void registerNewUser(){

        status = (TextView) findViewById(R.id.status);
        status.setText("Connecting...");

        //set the TextViews
        nameView     = (EditText) findViewById(R.id.name);
        passwordView = (EditText) findViewById(R.id.password);
        emailView    = (EditText) findViewById(R.id.email);

        //set the data in the user class from the TextViews
        UserData user = new UserData();
        user.setUser(nameView.getText().toString());
        user.setPassword(passwordView.getText().toString());
        user.setEmail(emailView.getText().toString());

        //Set the info to local variables to send
        String name = user.getUser();
        String password = user.getPassword();
        String email = user.getEmail();

        String method = "register";

        //start a background thread to connect and communicate w/ the DB
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, name, password, email);
        //finish...when it's finished...
        status.setText("");
        finish();
    }

    /*********************************************************************
    * User Data - stores and sets the user information
    * In all honesty, we could do just fine without having this class,
    * but it just feels right to have it here...
    **********************************************************************/
    public class UserData{
        //user data
        private String user;
        private String password;
        private String email;

        //public setters
        public void setUser(String _user){user = _user;}
        public void setPassword(String _password){password = _password;}
        public void setEmail(String _email){email = _email;}

        //public getters
        public String getUser(){return user;}
        public String getPassword(){return password;}
        public String getEmail(){return email;}

    }
}
