package com.cs246.clark.mysqltestapp;

import java.util.Date;

/**
 * Created by rhuarc on 12/7/15.
 */
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
