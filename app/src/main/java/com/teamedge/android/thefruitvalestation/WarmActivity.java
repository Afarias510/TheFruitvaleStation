package com.teamedge.android.thefruitvalestation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WarmActivity extends AppCompatActivity {
    TextView firstTrainTextView;
    TextView firstTrainPlatformTextView;
    TextView firstTrainLengthTextView;
    TextView firstTrainColorTextView;
    TextView secondTrainTextView;
    TextView secondTrainPlatformTextView;
    TextView secondTrainLengthTextView;
    TextView secondTrainColorTextView;
    TextView thirdTrainTextView;
    TextView thirdTrainPlatformTextView;
    TextView thirdTrainLengthTextView;
    TextView thirdTrainColorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warm);
        firstTrainTextView = findViewById(R.id.first_train);
        firstTrainPlatformTextView = findViewById(R.id.first_platform);
        firstTrainLengthTextView  = findViewById(R.id.first_length);
        firstTrainColorTextView  = findViewById(R.id.first_color);
        secondTrainTextView =findViewById(R.id.second_train);
        secondTrainPlatformTextView = findViewById(R.id.second_platform);
        secondTrainLengthTextView  = findViewById(R.id.second_length);
        secondTrainColorTextView  = findViewById(R.id.second_color);
        thirdTrainTextView =findViewById(R.id.third_train);
        thirdTrainPlatformTextView = findViewById(R.id.third_platform);
        thirdTrainLengthTextView  = findViewById(R.id.third_length);
        thirdTrainColorTextView  = findViewById(R.id.third_color);

    }
    public void checkTimes(View view) {
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://api.bart.gov/api/etd.aspx?cmd=etd&orig=ftvl&json=y&key=MW9S-E7SL-26DU-VV8V";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJson = new JSONObject(response);
                            JSONObject root = responseJson.getJSONObject("root");
                            JSONArray station = root.getJSONArray("station");
                            JSONObject fruitvale = station.getJSONObject(0);
                            JSONArray etd = fruitvale.getJSONArray("etd");
                            JSONObject warmsprings = etd.getJSONObject(3);
                            JSONArray estimate = warmsprings.getJSONArray("estimate");
                            JSONObject firstTrain = estimate.getJSONObject(0);
                            String firstTrainMinute = firstTrain.getString("minutes");
                            String firstTrainPlatform = firstTrain.getString("platform");
                            String firstTrainLength = firstTrain.getString("length");
                            String firstTrainColor = firstTrain.getString("color");
                            JSONObject secondTrain = estimate.getJSONObject(1);
                            String secondTrainMinute = secondTrain.getString("minutes");
                            String secondTrainPlatform = firstTrain.getString("platform");
                            String secondTrainLength = firstTrain.getString("length");
                            String secondTrainColor = firstTrain.getString("color");
                            JSONObject thirdTrain = estimate.getJSONObject(2);
                            String thirdTrainMinute = thirdTrain.getString("minutes");
                            String thirdTrainPlatform = firstTrain.getString("platform");
                            String thirdTrainLength = firstTrain.getString("length");
                            String thirdTrainColor = firstTrain.getString("color");


                            firstTrainTextView.setText("Next train in " + firstTrainMinute);
                            firstTrainPlatformTextView.setText("Platform:"+ firstTrainPlatform);
                            firstTrainLengthTextView.setText(firstTrainLength + " Car Train");
                            firstTrainColorTextView.setText("Color:" +firstTrainColor);
                            secondTrainTextView.setText("Second train in " + secondTrainMinute);
                            secondTrainPlatformTextView.setText("Platform:"+ secondTrainPlatform);
                            secondTrainLengthTextView.setText(secondTrainLength + " Car Train");
                            secondTrainColorTextView.setText("Color:" + secondTrainColor);
                            thirdTrainTextView.setText("Third train in " + thirdTrainMinute);
                            thirdTrainPlatformTextView.setText("Platform:"+ thirdTrainPlatform);
                            thirdTrainLengthTextView.setText(thirdTrainLength + " Car Train");
                            thirdTrainColorTextView.setText("Color:" + thirdTrainColor);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                firstTrainTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
