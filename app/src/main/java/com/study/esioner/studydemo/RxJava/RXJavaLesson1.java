package com.study.esioner.studydemo.RxJava;

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

        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    e.onNext("yang");
                    Thread.sleep(2000);
                    e.onNext("zhang");
                    Thread.sleep(2000);
                    e.onNext("xu");
                    Thread.sleep(2000);
                    e.onNext("yang");
                    e.onError(new Throwable("ERROR"));
                    Thread.sleep(2000);
                    e.onComplete();
                    e.onNext("han");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                Log.d(TAG, "onNext: " + value);
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
        observable.subscribe(observer);
    }
}
