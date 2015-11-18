package com.cs246.clark.mysqltestapp;

/*********************************************************************
 * User - stores and sets the user information
 *
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
