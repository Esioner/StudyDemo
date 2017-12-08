package com.study.esioner.studydemo.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.HTTP;

/**
 * @author Esioner
 * @date 2017/12/8
 */

public class RetrofitLesson1 extends AppCompatActivity {
    private static final String TAG = "RetrofitLesson1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.baidu.com/").build();
        interfaces interfaces = retrofit.create(RetrofitLesson1.interfaces.class);
        Call<ResponseBody> call = interfaces.getResponse();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
            }
        });

    }

    interface interfaces {

        @GET("?tn=78040160_26_pg")
        Call<ResponseBody> getResponse();
    }
}
