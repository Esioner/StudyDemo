package com.study.esioner.studydemo.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.study.esioner.studydemo.R;

/**
 * @author Esioner
 * @date 2017/12/8
 */

public class RetrofitMainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_main_layout);

        initView();
    }

    private void initView() {
        Button btnRetrofit1 = findViewById(R.id.btn_retrofit_1);
        btnRetrofit1.setOnClickListener(this);
        Button btnRetrofitJinShanTranslate = findViewById(R.id.btn_jin_shan_translate);
        btnRetrofitJinShanTranslate.setOnClickListener(this);
        Button btnRetrofitYouDaoTranslate = findViewById(R.id.btn_you_dao_translate);
        btnRetrofitYouDaoTranslate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_retrofit_1:
                intent = new Intent(this, RetrofitLesson1.class);
                break;
            case R.id.btn_jin_shan_translate:
                intent = new Intent(this, JinShanTranslate.class);
                break;
            case R.id.btn_you_dao_translate:
                intent = new Intent(this, YouDaoTranslation.class);
                break;

            default:
                intent = null;
                break;
        }
        startActivity(intent);
    }
}
