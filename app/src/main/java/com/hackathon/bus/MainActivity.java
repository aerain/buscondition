package com.hackathon.bus;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.hackathon.bus.Adapter.FavoriteRecyclerViewAdapter;
import com.hackathon.bus.BusSystemAPI.SearchBusStation;
import com.hackathon.bus.VO.FavoritBusVo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener,TextView.OnEditorActionListener {


    private ImageButton menu,cancel,searchSwqp;
    private EditText editText,searchStart, searchEnd;
    private String searchStr,strStart,strEnd;
    private RecyclerView favoritBus;
    private FavoritBusVo busVO;
    private Button findBus,edit;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout=findViewById(R.id.l1);
        setContentView(R.layout.activity_main);


        cutomActionBar();
        initView();

        initRecyclerView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SearchBusStation s=new SearchBusStation();
                s.xmlParser_station("가산동종점");
                s.xmlParser_direction();
                try{
                    HttpURLConnection conn;
                    BufferedReader br;
                    String json;

                    URL url = new URL("http://117.17.102.139:3000/getBusTime?arsid=21111");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    json = br.readLine();
                    json = json.replaceAll("&#34;","\"");
                    System.out.println(json);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }




    //layout view 설정
    public void initView(){

        menu=findViewById(R.id.menu);
        menu.setOnClickListener(this);

        editText=findViewById(R.id.search_bus);
        editText.setOnFocusChangeListener(this);
        editText.setOnEditorActionListener(this);
        editText.setOnClickListener(this);

        searchStart=findViewById(R.id.start);
        searchEnd=findViewById(R.id.end);

        cancel=findViewById(R.id.edit_text_cancel);
        cancel.setOnClickListener(this);

        searchSwqp=findViewById(R.id.swap);
        searchSwqp.setOnClickListener(this);

        findBus=findViewById(R.id.findbus);
        findBus.setOnClickListener(this);

        edit=findViewById(R.id.edit);
        edit.setOnClickListener(this);



    }
    //cutomactionBar설정
    public void cutomActionBar(){
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.search_actionbar, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

    }

    //recyclerview설정
    public void initRecyclerView(){
        busVO=new FavoritBusVo();
        favoritBus=findViewById(R.id.favorite_bus);
        favoritBus.setLayoutManager(new LinearLayoutManager(this));
        favoritBus.setAdapter(new FavoriteRecyclerViewAdapter(this,busVO));
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.edit_text_cancel){
            editText.setText("");
            cancel.setVisibility(View.GONE);
        }
//        if(v.getId()==R.id.search_bus){
//            Intent intent=new Intent(this,bus_resuit_window.class);
//            startActivity(intent);
//        }
        if(v.getId()==R.id.swap){
            strStart=searchStart.getText().toString();
            strEnd=searchEnd.getText().toString();
            searchStart.setText(strEnd);
            searchEnd.setText(strStart);

        }
        if(v.getId()==R.id.findbus){
            Intent intent=new Intent(this,SearchedBusActivity.class);
            strStart=searchStart.getText().toString();
            strEnd=searchEnd.getText().toString();
            intent.putExtra("start",strStart);
            intent.putExtra("end",strEnd);
            startActivity(intent);
        }
        if(v.getId()==R.id.edit){
            Intent intent=new Intent(this,EditMainActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.search_bus){
            Intent intent=new Intent(this,bus_resuit_window.class);
            startActivity(intent);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            if (v.getId()==R.id.search_bus){
                cancel.setVisibility(View.VISIBLE);

            }
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(v.getId()==R.id.search_bus && actionId== EditorInfo.IME_ACTION_DONE){ // 뷰의 id를 식별, 키보드의 완료 키 입력 검출

            searchStr=editText.getText().toString();
            Intent intent=new Intent(MainActivity.this,SearchedInfoActivity.class);
            intent.putExtra("searchStr",searchStr);
            startActivity(intent);

        }

        return false;

    }
}


