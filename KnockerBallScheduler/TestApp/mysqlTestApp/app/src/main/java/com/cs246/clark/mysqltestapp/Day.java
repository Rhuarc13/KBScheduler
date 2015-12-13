package com.cs246.clark.mysqltestapp;

import java.util.Date;

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
public class Day {
    private Date date;
    private boolean available;

    public Day(Date _date, boolean t) {
        date = _date;
        available = t;
    }

    public Day(Date _date) {
        date = _date;
        available = true;
    }

    public void setDate(Date theDate) {
        date = theDate;
    }

    public void setAvailable(boolean t) {
        available = t;
    }

    public Date getDate() {
        return date;
    }
    public boolean isAvailable() {
        return available;
    }
}
