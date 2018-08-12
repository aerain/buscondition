package com.hackathon.bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NowBus_Info extends AppCompatActivity {
    private String busNum;
    private TextView tvNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_bus__info);
        Intent intent =getIntent();
        busNum=intent.getStringExtra("BusNum");
        tvNum=(TextView)findViewById(R.id.num_bus) ;
        tvNum.setText(busNum);
    }
}
