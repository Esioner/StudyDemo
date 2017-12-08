package com.study.esioner.studydemo.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Esioner
 * @date 2017/12/8
 * http://blog.csdn.net/carson_ho/article/details/73732076
 */

public class JinShanTranslate extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    interface JinShanInterface{
        @GET("ajax.php?a={a}&f={f}&t={t}&w={word}")
        Call<ResponseBody> getResponse();
    }
}
