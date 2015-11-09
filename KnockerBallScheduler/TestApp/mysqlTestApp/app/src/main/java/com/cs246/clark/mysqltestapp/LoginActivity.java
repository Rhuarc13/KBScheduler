package com.cs246.clark.mysqltestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText firstNameView;
    EditText lastNameView;
    EditText passwordView;
    EditText passwordConfirmView;
    EditText emailView;
    EditText phoneView;

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
        firstNameView       = (EditText) findViewById(R.id.firstName);
        lastNameView        = (EditText) findViewById(R.id.lastName);
        passwordView        = (EditText) findViewById(R.id.password);
        passwordConfirmView = (EditText) findViewById(R.id.passwordConfirm);
        emailView           = (EditText) findViewById(R.id.email);
        phoneView           = (EditText) findViewById(R.id.phone);

        //set the data in the user class from the TextViews
        UserData user = new UserData();
        user.setFirstName(firstNameView.getText().toString());
        user.setLastName(lastNameView.getText().toString());
        user.setPassword(passwordView.getText().toString());
        user.setEmail(emailView.getText().toString());
        user.setPhone(phoneView.getText().toString());

        //Set the info to local variables to send
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        String passwordConfirm = passwordConfirmView.getText().toString();
        String email = user.getEmail();
        String phone = user.getPhone();

        String method = "register";

        if(!password.equals(passwordConfirm)){
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show();
        } else {
            //start a background thread to connect and communicate w/ the DB
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, firstName, lastName, password, email, phone);
            finish();
        }
     }
    /*********************************************************************
    * User Data - stores and sets the user information
    * In all honesty, we could do just fine without having this class,
    * but it just feels right to have it here...
    **********************************************************************/
     public class UserData{
        //user data
        private String firstName;
        private String lastName;
        private String password;
        private String email;
        private String phone;

        //public setters
        public void setFirstName(String _firstName){firstName = _firstName;}
        public void setLastName(String _lastName){lastName = _lastName;}
        public void setPassword(String _password){password = _password;}
        public void setEmail(String _email){email = _email;}
        public void setPhone(String _phone){phone = _phone;}

        //public getters
        public String getFirstName(){return firstName;}
        public String getLastName(){return lastName;}
        public String getPassword(){return password;}
        public String getEmail(){return email;}
        public String getPhone(){return phone;}

    }
}



