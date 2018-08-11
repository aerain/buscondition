package com.hackathon.bus;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class bus_lists extends AppCompatActivity {
    MenuItem mSearch;
    ImageView mCloseButton;
    SearchView searchView;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_result_window);
        setTitle("버스검색창");
//
//        mRecyclerView = findViewById(R.id.search_recycler);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        ArrayList<bus_result_item> bus_result_itemsList = new ArrayList<>();
//        bus_result_itemsList.add(new bus_result_item("360", "제주대학교방면" ,"한라대학교 → 제주대학교"));
//        bus_result_itemsList.add(new bus_result_item("360", "한라대학교방면","제주대학교 → 한라대학교"));
//        bus_result_itemsList.add(new bus_result_item("477", "제주대학교순환","제주대학교 → 제주대학교"));
//        bus_result_itemsList.add(new bus_result_item("477", "제주대학교순환" ,"제주대학교 → 제주대학교"));
//        bus_result_itemsList.add(new bus_result_item("473", "한라대학교방면","국제대학교 → 한라대학교"));
//        bus_result_itemsList.add(new bus_result_item("473", "국제대학교방면","한라대학교 → 국제대학교"));
//        bus_result_itemsList.add(new bus_result_item("365", "제주대학교방면" ,"한라대학교 → 제주대학교"));
//        bus_result_itemsList.add(new bus_result_item("365", "한라대학교방면","제주대학교 → 한라대학교"));
//        bus_result_Adapter busResultAdapter= new bus_result_Adapter(bus_result_itemsList);
//
//        mRecyclerView.setAdapter(busResultAdapter);




    }

    //메뉴 생성하는 onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        //search_menu.xml 등록
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_window_menu, menu);
        mSearch = menu.findItem(R.id.search);
        mCloseButton = (ImageView) findViewById(R.id.search_close_btn);


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


                Toast.makeText(bus_lists.this, query , Toast.LENGTH_SHORT).show();
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
}



//    // 검색 확장,축소를 버튼으로 생성
//    public void mOnClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnexpand:
//                mSearch.expandActionView();
//                break;
//            case R.id.btncollapse:
//                mSearch.collapseActionView();
//                break;
//        }
//
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.closeButton:{
////                onBackPressed();
//                Log.e("여기가","실행은돼?");
//                finish();
//
//                break;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}