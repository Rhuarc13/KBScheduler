package com.cs246.clark.mysqltestapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DayView extends Activity {

    private final static String TAG = "DAY_VIEW";
    private final static HashMap<Integer, String> TIMELORD = new HashMap<Integer, String>(){
        {
            put(0, "default");
            put(1, "six");
            put(2, "sixT");
            put(3, "seven");
            put(4, "sevenT");
            put(5, "eight");
            put(6, "eightT");
            put(7, "nine");
            put(8, "nineT");
            put(9, "ten");
            put(10, "eight");
            put(11, "eight");
            put(12, "eight");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_view);


        String date = getIntent().getStringExtra("date");

        String dateList[] = date.split("\\s");

        String method = "pull_time";
        String year = dateList[5];
        String month = dateList[1];
        String day = dateList[2];

        if (month.equals("Jan")) {
            month = "01";
        } else if (month.equals("Feb")) {
            month = "02";
        } else if (month.equals("Mar")) {
            month = "03";
        } else if (month.equals("Apr")) {
            month = "04";
        } else if (month.equals("May")) {
            month = "05";
        } else if (month.equals("Jun")) {
            month = "06";
        } else if (month.equals("Jul")) {
            month = "07";
        } else if (month.equals("Aug")) {
            month = "08";
        } else if (month.equals("Sep")) {
            month = "09";
        } else if (month.equals("Oct")) {
            month = "10";
        } else if (month.equals("Nov")) {
            month = "11";
        } else if (month.equals("Dec")) {
            month = "12";
        }

        date = year + "-" + month + "-" + day;

        Response r = new Response();
        BackgroundTask backgroundTask = new BackgroundTask(date, method, r);
        backgroundTask.execute();

        Lock lock = new ReentrantLock();
        int waitTime = 0;
        synchronized (lock) {
            while (r.getCode() == 0 && waitTime < 26) {
                try {
                    lock.wait(100);
                    waitTime++;
                } catch (InterruptedException e) {
                    Log.e(TAG, "Ran into an InterruptedException");
                }
            }

            /*try {
                lock.wait(500);
            } catch (InterruptedException e) {
                Log.e(TAG, "Ran into an InterruptedException");
            }*/
        }

        int start_hr, start_min, end_hr, end_min = 0;

        if (r.getCode() == 200) {
            Log.i(TAG, "Response is: " + r.getText());
            try {
                JSONObject jsonObject = new JSONObject(r.getText());
                JSONArray array = jsonObject.getJSONArray("times");
                for (int i = 0; i < array.length() - 1; i++) {
                    JSONObject times = array.getJSONObject(i);
                    Log.i(TAG, times.toString());
                    String startTime = times.getString("start_time");
                    start_hr = Integer.parseInt(startTime.split(":")[0]);
                    start_min = Integer.parseInt(startTime.split(":")[1]);
                    if (start_hr > 12) {
                        start_hr = start_hr - 12;
                    }

                    String endTime   = times.getString("end_time");
                    end_hr = Integer.parseInt(endTime.split(":")[0]);
                    if (end_hr > 12) {
                         end_hr = end_hr - 12;
                    }
                    end_min = Integer.parseInt(endTime.split(":")[1]);


                    Log.i(TAG, "start time: " + start_hr + ":" + start_min);
                    Log.i(TAG, "end time: " + end_hr + ":" + end_min);

                    String timeView = "";
                    int index = 0;
                    int eindex = 0;

                    for (int j = 0; j < 2; j++) {
                        int target1 = 0;
                        int target2 = 0;
                        if (j == 0) {
                            target1 = start_hr;
                            target2 = start_min;
                        } else {
                            target1 = end_hr;
                            target2 = end_min;
                        }
                        switch (target1) {
                            case 6:
                                if (target2 < 30) {
                                    if (j == 0) {
                                        index = 1;
                                    } else {
                                        eindex = 1;
                                    }
                                } else {
                                    if (j == 0) {
                                        index = 2;
                                    } else {
                                        eindex = 2;
                                    }
                                }
                                break;
                            case 7:
                                if (target2 < 30) {
                                    if (j == 0) {
                                        index = 3;
                                    } else {
                                        eindex = 3;
                                    }
                                } else {
                                    if (j == 0) {
                                        index = 4;
                                    } else {
                                        eindex = 4;
                                    }
                                }
                                break;
                            case 8:
                                if (target2 < 30) {
                                    if (j == 0) {
                                        index = 5;
                                    } else {
                                        eindex = 5;
                                    }
                                } else {
                                    if (j == 0) {
                                        index = 6;
                                    } else {
                                        eindex = 6;
                                    }
                                }
                                break;
                            case 9:
                                if (target2 < 30) {
                                    if (j == 0) {
                                        index = 7;
                                    } else {
                                        eindex = 7;
                                    }
                                } else {
                                    if (j == 0) {
                                        index = 8;
                                    } else {
                                        eindex = 8;
                                    }
                                }
                                break;
                            case 10:
                                if (target2 < 30) {
                                    if (j == 0) {
                                        index = 9;
                                    } else {
                                        eindex = 9;
                                    }
                                } else {
                                    if (j == 0) {
                                        index = 10;
                                    } else {
                                        eindex = 10;
                                    }
                                }
                                break;
                            case 11:
                                if (target2 < 30) {
                                    if (j == 0) {
                                        index = 11;
                                    } else {
                                        eindex = 11;
                                    }
                                } else {
                                    if (j == 0) {
                                        index = 12;
                                    } else {
                                        eindex = 12;
                                    }
                                }
                                break;
                            default:
                                index = 0;
                                eindex = 0;
                                break;
                        }
                    }


                    for (int j = index; j < eindex; j++) {
                        timeView = TIMELORD.get(j);
                        Log.i(TAG, "timeView name = " + timeView + ": indexes: " + index + "/" + eindex);
                        Resources res = getResources();
                        int id = res.getIdentifier(timeView, "id", this.getPackageName());

                        TextView tempTextView = (TextView) findViewById(id);
                        if (tempTextView != null) {
                            tempTextView.setBackgroundColor(getResources().getColor(R.color.timeSlotReserved));
                            tempTextView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getApplicationContext(), R.string.no_click, Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Log.e(TAG, "tempTextView was null... :(");
                        }
                    }
                //here is where we need to set the color/click-ability of the time slots
                }
            } catch (JSONException je) {
                Log.e(TAG, "JSONException: " + je.toString());
            }


            } else {
                Toast.makeText(this, "Error occurred connecting to the database", Toast.LENGTH_SHORT).show();
            }

    }


    public void onClick(View view) {
        Intent intent = new Intent(this, Confermation.class);
        startActivity(intent);
        finish();
    }

}
