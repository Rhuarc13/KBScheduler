package com.cs246.clark.mysqltestapp;

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
 *User
 *
 * This class is a User class that stores all the data related to a user
 **********************************************************************/
public class User{
    //user data
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;

    //public setters
    public void setFirstName(String _firstName){firstName = _firstName;}
    public void setLastName (String _lastName){lastName   = _lastName;}
    public void setPassword (String _password){password   = _password;}
    public void setEmail    (String _email)   {email      = _email;}
    public void setPhone    (String _phone)   {phone      = _phone;}

    //public getters
    public String getFirstName(){return firstName;}
    public String getLastName (){return lastName;}
    public String getPassword (){return password;}
    public String getEmail    (){return email;}
    public String getPhone    (){return phone;}

}
