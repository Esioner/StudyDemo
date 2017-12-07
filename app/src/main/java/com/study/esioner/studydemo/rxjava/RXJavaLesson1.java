package com.study.esioner.studydemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.study.esioner.studydemo.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Lenovo on 2017/12/5.
 */

public class RXJavaLesson1 extends AppCompatActivity {
    private static final String TAG = "RXJavaLesson1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_layout_1);
        //创建一个 上游 Observable,被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    //发送事件
                    e.onNext("yang");
                    e.onNext("zhang");
                    e.onNext("xu");
                    e.onNext("yang");
                    e.onComplete();
                    e.onNext("han");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        //创建一个下游 Observer，观察者
        Observer observer = new Observer() {
            private Disposable d;
            private int i = 0;
            @Override
            public void onSubscribe(Disposable d) {
                this.d = d;

            }
            @Override
            public void onNext(Object value) {
                Log.d(TAG, "onNext: " + value);
                i++;
                if (i >= 1) {
                    Log.d(TAG, "Disposable: ");
                    d.dispose();//取消接收事件，被观察者会继续接收事件，但是观察者已经停止接收事件
                }
            }
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };
        //将 Observable 与 Observer 绑定
        observable.subscribe(observer);
    }
}
