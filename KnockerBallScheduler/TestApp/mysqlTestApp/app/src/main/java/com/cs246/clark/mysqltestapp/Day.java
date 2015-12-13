package com.cs246.clark.mysqltestapp;

import java.util.Date;

/***********************************************************************
 *
 *                  ~~ KnockerBall Schedule App ~~
 *
 * This helper class encapsulates the Date object and a flag.
 * We had to find a way to pass around a flag for color coding the
 * days.
 *
 * @author Weston Clark, Shem Sedrick, Jared Mefford
 * @version 1.0
 **********************************************************************/
public class Day {
    private Date date;
    private boolean available;


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
