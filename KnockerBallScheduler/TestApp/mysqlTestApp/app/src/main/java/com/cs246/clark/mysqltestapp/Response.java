package com.cs246.clark.mysqltestapp;

/**
 * Created by rhuarc on 11/21/15.
 */
public class Response {
    int code;
    String responseText;

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
