package com.hackathon.bus;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.hackathon.bus.Adapter.StationResultRecyclerViewAdapter;
import com.hackathon.bus.BusSystemAPI.SearchBusStation;
import com.hackathon.bus.VO.StationVO;

import java.util.LinkedList;


public class bus_resuit_window extends AppCompatActivity {
    MenuItem mSearch;
    ImageView mCloseButton;
    SearchView searchView;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    private String str;
    private LinkedList<StationVO> vos;
    StationResultRecyclerViewAdapter busResultAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_result_window);
        setTitle("버스검색창");

        mRecyclerView = findViewById(R.id.search_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        vos=new LinkedList<>();


    }

    //메뉴 생성하는 onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //search_menu.xml 등록
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_window_menu, menu);
        mSearch = menu.findItem(R.id.search);



        mSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                TextView text = (TextView) findViewById(R.id.txtstatus);
//                text.setText("현재 상태 : 확장됨");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                TextView text = (TextView) findViewById(R.id.txtstatus);
//                text.setText("현재 상태 : 축소됨");
                onBackPressed();
                return true;
            }
        });


        //menuItem을 이용해서 SearchView 변수 생성
        SearchView sv = (SearchView) mSearch.getActionView();

        sv.setIconified(false);
        sv.setIconifiedByDefault(true);


        //확인버튼 활성화
        sv.setSubmitButtonEnabled(false);


        //SearchView의 검색 이벤트
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //검색버튼을 눌렀을 경우
            @Override
            public boolean onQueryTextSubmit(String query) {
                TextView text = (TextView) findViewById(R.id.txtresult);
                text.setText(" 겸색결과: " + query);
                StationAsyncTask task=new StationAsyncTask(query);
                task.execute();
                vos=task.getBus();
                mRecyclerView.setVisibility(View.VISIBLE);

                return true;
            }



            //텍스트가 바뀔때마다 호출
            @Override
            public boolean onQueryTextChange(String newText) {
//                TextView text = (TextView)findViewById(R.id.txtsearch);
//                text.setText("검색식 : "+newText);
                return true;
            }
        });

        mSearch.expandActionView();
        return true;
    }
    public Context getContext(){

        Context context=this;
        return context;
    }
    class StationAsyncTask extends AsyncTask<Void,Void,Void>{

        String str;
        SearchBusStation s;
        Context context;
        public StationAsyncTask(String str){
            this.str=str;
            context=new bus_resuit_window();
        }
        private LinkedList<StationVO> vos;
        @Override
        protected Void doInBackground(Void... voids) {
            s=new SearchBusStation();
            s.xmlParser_station(str);
            s.xmlParser_direction();
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
            vos=s.getStation();

            context=getContext();
            busResultAdapter= new StationResultRecyclerViewAdapter(vos,context);
            mRecyclerView.setAdapter(busResultAdapter);
            mRecyclerView.setVisibility(View.VISIBLE);

        }
        public LinkedList getBus(){
            return vos;
        }
    }
}


