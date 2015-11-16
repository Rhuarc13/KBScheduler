package com.cs246.clark.mysqltestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterNewUser extends AppCompatActivity {

    EditText firstNameView;
    EditText lastNameView;
    EditText passwordView;
    EditText passwordConfirmView;
    EditText emailView;
    EditText phoneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
    }

    /*********************************************************************
    * This will run the background task to communicate with the MySQL DB
    **********************************************************************/
    public void registerButtonPress(View view) {

        //set the TextViews so we can pull the user input
        firstNameView       = (EditText) findViewById(R.id.firstName);
        lastNameView        = (EditText) findViewById(R.id.lastName);
        passwordView        = (EditText) findViewById(R.id.password);
        passwordConfirmView = (EditText) findViewById(R.id.passwordConfirm);
        emailView           = (EditText) findViewById(R.id.email);
        phoneView           = (EditText) findViewById(R.id.phone);

        //set the data in the user class from the TextViews
        User user = new User();

        user.setFirstName(firstNameView.getText().toString());
        user.setLastName (lastNameView.getText().toString());
        user.setPassword (passwordView.getText().toString());
        user.setEmail    (emailView.getText().toString());
        user.setPhone    (phoneView.getText().toString());


        //ensure they have matching passwords
        if(!user.getPassword().equals(passwordConfirmView.getText().toString())){
            Toast toast = Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 25);
            toast.show();
            passwordView.setFocusable(true);
            passwordView.requestFocus();
        } else {
            //Create a server class and run it to send the info to the DB
            String method = "register";
            BackgroundTask backgroundTask = new BackgroundTask(user, method);
            backgroundTask.execute();
        }
     }
}



