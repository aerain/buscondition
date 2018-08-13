package com.hackathon.bus;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackathon.bus.Adapter.BusResultRecyclerViewAdapter;
import com.hackathon.bus.Adapter.StationResultRecyclerViewAdapter;
import com.hackathon.bus.BusSystemAPI.Congestion;
import com.hackathon.bus.BusSystemAPI.SearchBusStation;
import com.hackathon.bus.VO.BusVO;
import com.hackathon.bus.VO.StationVO;

import java.util.ArrayList;
import java.util.LinkedList;

public class StationInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private String name;
    private String num;
    private String direction;
    private LinkedList<BusVO> busVOS;
    BusResultRecyclerViewAdapter busResultAdapter;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_info);
        Intent intent=getIntent();
        name=intent.getStringExtra("StationName");
        num=intent.getStringExtra("StationNum");
        direction=intent.getStringExtra("StationDirection");

        mRecyclerView = findViewById(R.id.bus_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        busVOS=new LinkedList<>();

        BusAsyncTask task=new BusAsyncTask();
        task.execute();
        initView();
        cutomActionBar();


    }
    private void initView(){
        TextView tname=findViewById(R.id.station_name);
        TextView tinfo=findViewById(R.id.station_info);
        TextView tnum=findViewById(R.id.station_id);

        tname.setText(name);
        Log.e("dk",direction);
        tinfo.setText(direction);
        tnum.setText(num);
    }
    public Context getContext(){
        Context context=this;
        return context;
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
        View actionbar = inflater.inflate(R.layout.home_actionbar, null);

        actionBar.setCustomView(actionbar);

        ImageView back=findViewById(R.id.backspace);
        ImageView home=findViewById(R.id.home);
        back.setOnClickListener(this);
        home.setOnClickListener(this);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        actionBar.setElevation(0);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.backspace){
            finish();
        }
        if (view.getId()==R.id.home){
            Intent intent=new Intent(StationInfoActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }


    class BusAsyncTask extends AsyncTask<Void,Void,Void> {

        String str;
        SearchBusStation s;
        Context context;
        Congestion cgn;
//        public BusAsyncTask(String str){
//            this.str=str;
//        }
        private LinkedList<BusVO> vos;
        private ArrayList<Integer> parseCongestion;
        @Override
        protected Void doInBackground(Void... voids) {
            s=new SearchBusStation();
            s.xmlParser_station(name);
            s.xmlParser_direction();
            s.xmlParser_Bus(num);
            s.xmlParser_Congestion();
            cgn = new Congestion();
            parseCongestion = cgn.parser(num);
//            try{
//                HttpURLConnection conn;
//                BufferedReader br;
//                String json;
//
//                URL url = new URL("http://117.17.102.139:3000/getBusTime?arsid=21111");
//                conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//                json = br.readLine();
//                json = json.replaceAll("&#34;","\"");
//                System.out.println(json);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            vos=s.getBus();

            Log.i("원형", parseCongestion.toString());
            context=getContext();
            busResultAdapter= new BusResultRecyclerViewAdapter(vos, context, parseCongestion);
            mRecyclerView.setAdapter(busResultAdapter);
            mRecyclerView.setVisibility(View.VISIBLE);

        }
        public LinkedList getBus(){
            return vos;
        }
    }
}
