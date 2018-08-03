package com.hackathon.bus;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.hackathon.bus.Adapter.EditRecyclerViewAdapter;
import com.hackathon.bus.Adapter.FavoriteRecyclerViewAdapter;
import com.hackathon.bus.VO.FavoritBusVo;

public class EditMainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton backspace;
    private FavoritBusVo busVO;
    private RecyclerView favoritBus;
    private FavoriteRecyclerViewAdapter favoriteRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_main);
        cutomActionBar();
        initRecyclerView();
        initView();
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
        View actionbar = inflater.inflate(R.layout.back_actionbar, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

    }
    public void initView(){
        backspace=findViewById(R.id.backspace);
        backspace.setOnClickListener(this);
    }

    //recyclerview설정
    public void initRecyclerView(){

        busVO=new FavoritBusVo();
        favoritBus=findViewById(R.id.favorite_bus_edit);
        favoritBus.setLayoutManager(new LinearLayoutManager(this));
        favoritBus.setAdapter(new EditRecyclerViewAdapter(this,busVO));
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backspace){
            this.finish();
        }
    }
}
