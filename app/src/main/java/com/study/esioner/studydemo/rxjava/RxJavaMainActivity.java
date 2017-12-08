package com.study.esioner.studydemo.rxjava;

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
    private Button btnLesson2;
    private Button btnLesson3;
    private Button btnLesson4;
    private Button btnLesson7;
    private Button btnLessonPractice;
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
        btnLesson2 = findViewById(R.id.btn_rxjava_lesson_2);
        btnLesson2.setOnClickListener(this);
        btnLesson3 = findViewById(R.id.btn_rxjava_lesson_3);
        btnLesson3.setOnClickListener(this);
        btnLesson4 = findViewById(R.id.btn_rxjava_lesson_4);
        btnLesson4.setOnClickListener(this);
        btnLesson7 = findViewById(R.id.btn_rxjava_lesson_7);
        btnLesson7.setOnClickListener(this);
        btnLessonPractice = findViewById(R.id.btn_rxjava_lesson_practice);
        btnLessonPractice.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_rxjava_lesson_1:
                intent = new Intent(mContext, RXJavaLesson1.class);
                break;
            case R.id.btn_rxjava_lesson_2:
                intent = new Intent(mContext, RXJavaLesson2.class);
                break;
            case R.id.btn_rxjava_lesson_3:
                intent = new Intent(mContext, RXJavaLesson3.class);
                break;
            case R.id.btn_rxjava_lesson_4:
                intent = new Intent(mContext, RXJavaLesson4.class);
                break;
            case R.id.btn_rxjava_lesson_7:
                intent = new Intent(mContext, RXJavaLesson7.class);
                break;

            case R.id.btn_rxjava_lesson_practice:
                intent = new Intent(mContext, RXJavaLessonPractice.class);
                break;
            default:
                intent = null;
        }
        startActivity(intent);
    }
}
