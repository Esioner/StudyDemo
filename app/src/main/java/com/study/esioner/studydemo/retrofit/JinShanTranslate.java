package com.study.esioner.studydemo.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.esioner.studydemo.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Esioner
 * @date 2017/12/8
 * http://blog.csdn.net/carson_ho/article/details/73732076
 */

public class JinShanTranslate extends AppCompatActivity {
    private static final String TAG = "金山翻译";
    private EditText etFromText;
    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jin_shan_translate_layout);
        etFromText = findViewById(R.id.et_jin_shan_from_text);
        tvResult = findViewById(R.id.tv_jin_shan_show_result);
        Button btnTranslate = findViewById(R.id.btn_jin_shan_translate);

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etFromText.getText().toString();
                if (!"".equals(text)) {
                    getResult(text);
                } else {
                    Toast.makeText(JinShanTranslate.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getResult(String text) {
        //创建 Retrofit 对象
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://fy.iciba.com/")//设置网络请求 URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建网络请求接口的实例
        JinShanInterface jsi = retrofit.create(JinShanInterface.class);
        /**
         * // 参数说明：
         // a：固定值 fy
         // f：原文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
         // t：译文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
         // w：查询内容
         */
        //对请求进行封装
        Call<JinShanJson> call = jsi.getResponse(text);
//        Response<JinShanJson> responseBody = call.execute();
//        JinShanJson jsj = responseBody.body();
        //发送异步请求
        call.enqueue(new Callback<JinShanJson>() {
            @Override
            public void onResponse(Call<JinShanJson> call, final Response<JinShanJson> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JinShanJson jsonResult = response.body();
                        Log.d(TAG, "run: " + jsonResult.getContent());
                        StringBuffer buffer = new StringBuffer();
                        try {
                            String[] means = jsonResult.getContent().getWord_mean();
                            for (int i = 0; i < means.length; i++) {
                                if (i != means.length - 1) {
                                    buffer.append(means[i] + "\n");
                                } else {
                                    buffer.append(means[i]);
                                }
                            }
                        } catch (Exception e) {
                            buffer.append(jsonResult.getContent().getOut());
                        }
                        tvResult.setText(Html.fromHtml(buffer.toString()));

                    }
                });
            }

            @Override
            public void onFailure(Call<JinShanJson> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
            }
        });

    }

    interface JinShanInterface {
        //        @GET("ajax.php&a={a}")
        @GET("ajax.php?a=fy&f=auto&t=ch")
        Call<JinShanJson> getResponse(@Query("w") String w);
    }

    class JinShanJson {
        /**
         * 请求成功时取1
         */
        private int status;
        /**
         * 内容信息
         */
        private Content content;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Content getContent() {
            return content;
        }

        public void setContent(Content content) {
            this.content = content;
        }

        public class Content {
            private String[] word_mean;

            public String getOut() {
                return out;
            }

            public void setOut(String out) {
                this.out = out;
            }

            private String out;

            public String[] getWord_mean() {
                return word_mean;
            }

            public void setWord_mean(String[] word_mean) {
                this.word_mean = word_mean;
            }

            @Override
            public String toString() {
                StringBuffer buffer = new StringBuffer();
                if (out != null) {
                    buffer.append("out=").append(out);
                }
                if (word_mean != null) {
                    buffer.append("word_mean=");
                    for (int i = 0; i < word_mean.length; i++) {
                        buffer.append(word_mean[i]).append("\n");
                    }
                }
                return buffer.toString();
            }
        }

        @Override
        public String toString() {
            return content.toString();
        }
    }

}

