package com.study.esioner.studydemo.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Esioner on 2017/12/10.
 */

public class YouDaoTranslation extends AppCompatActivity {
    private static final String TAG = "YouDaoTranslation";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YouDaoPostInterface ydpi = retrofit.create(YouDaoPostInterface.class);
        final Call<YouDaoBean> call = ydpi.getContent("I love you");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<YouDaoBean> response = call.execute();
                    Log.d(TAG, "run: " + response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    interface YouDaoPostInterface {
        // 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
        // 需要配合@Field 向服务器提交需要的字段
        @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
        @FormUrlEncoded
        Call<YouDaoBean> getContent(@Field("i") String targetSentence);
    }

    class YouDaoBean {
        private String type;
        private int errorCode;
        private int elapsedTime;
        private List<List<TranslateResultBean>> translateResult;

        @Override
        public String toString() {
            return "YouDaoBean{" +
                    "type='" + type + '\'' +
                    ", errorCode=" + errorCode +
                    ", elapsedTime=" + elapsedTime +
                    ", translateResult=" + translateResult +
                    '}';
        }

        public class TranslateResultBean {
            /**
             * src : merry me
             * tgt : 我快乐
             */
            public String src;
            public String tgt;

            public String getSrc() {
                return src;
            }

            @Override
            public String toString() {
                return "TranslateResultBean{" +
                        "src='" + src + '\'' +
                        ", tgt='" + tgt + '\'' +
                        '}';
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getTgt() {
                return tgt;
            }

            public void setTgt(String tgt) {
                this.tgt = tgt;
            }
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public int getElapsedTime() {
            return elapsedTime;
        }

        public void setElapsedTime(int elapsedTime) {
            this.elapsedTime = elapsedTime;
        }

        public List<List<TranslateResultBean>> getTranslateResult() {
            return translateResult;
        }

        public void setTranslateResult(List<List<TranslateResultBean>> translateResult) {
            this.translateResult = translateResult;
        }
    }
}
