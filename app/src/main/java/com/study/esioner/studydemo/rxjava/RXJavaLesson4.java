package com.study.esioner.studydemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lenovo on 2017/12/7.
 */

public class RXJavaLesson4 extends AppCompatActivity {
    private static final String TAG = "RXJavaLesson4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        zipOperation();

    }

    private void zipOperation() {
        Observable observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "observable1" + Thread.currentThread().getName());
                Log.d(TAG, "emitter 1");
                e.onNext(1);
                Log.d(TAG, "emitter 2");
                e.onNext(2);
                Log.d(TAG, "emitter 3");
                e.onNext(3);
                Log.d(TAG, "emitter 4");
                e.onNext(4);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(TAG, "observable2" + Thread.currentThread().getName());
                Log.d(TAG, "emitter A");
                e.onNext("emitter A");
                Log.d(TAG, "emitter B");
                e.onNext("emitter B");
                Log.d(TAG, "emitter D");
                e.onNext("emitter D");
                Log.d(TAG, "emitter E");
                e.onNext("emitter E");
                Log.d(TAG, "emitter F");
                e.onNext("emitter F");
                Log.d(TAG, "emitter G");
                e.onNext("emitter G");
                Log.d(TAG, "emitter H");
                e.onNext("emitter H");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer o, String o2) throws Exception {
                return o + o2;
            }
        }).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });

    }
}
