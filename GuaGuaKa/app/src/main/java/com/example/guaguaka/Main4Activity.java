package com.example.guaguaka;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.guaguaka.adapter.MyBaseAdapter;
import com.example.guaguaka.bean.Bean;

import java.util.ArrayList;
import java.util.List;
public class Main4Activity extends AppCompatActivity implements View.OnClickListener{

    private ScrollListView slv;
    private ScrollView sv;
    private Button bt_all;
    private Button bt_no;
    private Button bt_ishide;
    private Bean[] dadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();

    }

    private void initData() {
        dadas = new Bean[40];
        Bean bean = null;
        for (int i = 0; i < dadas.length; i++) {
            bean = new Bean();
            bean.title = "这是第" + i + "条数据";
            bean.count = "i";
            bean.isHide = false;
            dadas[i] = bean;
        }
    }

    private void initView() {
        //初始化控件
        sv = (ScrollView) findViewById(R.id.);
        slv = (ScrollListView) findViewById(R.id.slv_main);
        bt_all = (Button) findViewById(R.id.button1);
        bt_no = (Button) findViewById(R.id.button3);
        bt_ishide = (Button) findViewById(R.id.button2);
        bt_all.setOnClickListener(this);
        bt_no.setOnClickListener(this);
        bt_ishide.setOnClickListener(this);
    }
    private void initEvent() {
        MyBaseAdapter adapter = new MyBaseAdapter(this,dadas,false);
        slv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
}


}
