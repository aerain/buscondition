package com.hackathon.bus;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.hackathon.bus.BusSystemAPI.BusAPI;

import com.hackathon.bus.BusSystemAPI.SearchBusAPI;

public class SearchedBusActivity extends AppCompatActivity implements View.OnClickListener{


    //출발지 목적지 검색했을때 나오는 액티비티
    private String start,startX,startY;
    private String end,endX,endY;
    private ImageButton btn;

    private SearchBusAPI searchBusAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_bus);
        Intent intent = getIntent();
        start = intent.getStringExtra("start");
        end = intent.getStringExtra("end");

        Log.e(start, end);
        cutomActionBar();
        initView();



        new Thread() {
            @Override
            public void run() {
                BusAPI busapi = new BusAPI();
                busapi.getBusPos();
            }
        }.start();
    }

    //cutomactionBar설정
    public void cutomActionBar() {
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.back_actionbar, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar) actionbar.getParent();
        parent.setContentInsetsAbsolute(0, 0);

    }

    public void initView() {
        btn = findViewById(R.id.backspace);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.backspace) {
            finish();
        }
    }
}