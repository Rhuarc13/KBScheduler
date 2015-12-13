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
 *Response
 *
 *This class sets the response code for each connection to the server
 * for information and debugging
 **********************************************************************/
public class Response {
    int code;
    String responseText;

    /**
     *  these codes are set in place to tell us what, if anything, was wrong with the data sent
     */
    public Response() {
        code = 0;
        responseText = "";
    }

    public void setCode(int i) {
        code = i;
    }

    public void setText(String t) {
        responseText = t;
    }

    public int getCode() { return code; }

    public String getText() { return responseText; }
}
