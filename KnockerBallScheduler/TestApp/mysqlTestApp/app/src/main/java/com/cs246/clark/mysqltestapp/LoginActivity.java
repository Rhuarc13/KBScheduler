package com.cs246.clark.mysqltestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText nameView;
    EditText passwordView;
    EditText passwordConfirmView;
    EditText emailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /*********************************************************************
    * This will run the background task to communicate with the MySQL DB
    **********************************************************************/
    public void registerNewUser(View view) {
        //set the TextViews
        nameView = (EditText) findViewById(R.id.name);
        passwordView = (EditText) findViewById(R.id.password);
        passwordConfirmView = (EditText) findViewById(R.id.passwordConfirm);
        emailView = (EditText) findViewById(R.id.email);

        //set the data in the user class from the TextViews
        UserData user = new UserData();
        user.setUser(nameView.getText().toString());
        user.setPassword(passwordView.getText().toString());
        user.setEmail(emailView.getText().toString());

        //Set the info to local variables to send
        String name = user.getUser();
        String password = user.getPassword();
        String passwordConfirm = passwordConfirmView.getText().toString();
        String email = user.getEmail();

        String method = "register";

       // if(!password.equals(passwordConfirm)){
        //    Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show();
        //} else {
            //start a background thread to connect and communicate w/ the DB
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, name, password, email);
            finish();
       // }
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



