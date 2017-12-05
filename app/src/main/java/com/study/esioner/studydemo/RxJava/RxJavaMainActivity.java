package com.study.esioner.studydemo.RxJava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.study.esioner.studydemo.R;

/**
 * Created by Lenovo on 2017/12/5.
 */

public class RxJavaMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLesson1;
    private Context mContext = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_layout_main);

        initView();

    }

    private void initView() {
        btnLesson1 = findViewById(R.id.btn_rxjava_lesson_1);
        btnLesson1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_rxjava_lesson_1:
                intent = new Intent(mContext, RXJavaLesson1.class);
                startActivity(intent);
                break;
        }
    }
}
