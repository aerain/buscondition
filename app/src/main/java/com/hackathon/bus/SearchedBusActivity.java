package com.hackathon.bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hackathon.bus.BusSystemAPI.BusAPI;

public class SearchedBusActivity extends AppCompatActivity {

    String start;
    String end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_bus);
        Intent intent=getIntent();
        start=intent.getStringExtra("start");
        end=intent.getStringExtra("end");

        Log.e(start,end);

        new Thread() {
            @Override
            public void run() {
                BusAPI busapi = new BusAPI();
                busapi.getBusPos();
            }
        }.start();



    }
}
