package com.study.esioner.studydemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.study.esioner.studydemo.RxJava.RxJavaMainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 打开 rxjava
     */
    private Button btnRxJavaActivity;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        
        btnRxJavaActivity = findViewById(R.id.btn_rxjava_activity);
        btnRxJavaActivity.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_rxjava_activity:
                intent = new Intent(mContext, RxJavaMainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
