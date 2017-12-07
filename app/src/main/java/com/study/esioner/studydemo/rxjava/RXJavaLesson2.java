package com.study.esioner.studydemo.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lenovo on 2017/12/5.
 */

public class RXJavaLesson2 extends AppCompatActivity {
    private static final String TAG = "RXJavaLesson2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        multiSetThread();

    }

    public void singleSetThread() {
        //被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(555);
                Log.d(TAG, "被观察者当前线程: " + Thread.currentThread().getName());
            }
        });
        //观察者
        Consumer consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept: " + integer);
                Log.d(TAG, "观察者当前线程: " + Thread.currentThread().getName());
            }
        };
        observable.subscribeOn(Schedulers.newThread())//设置被观察者所在线程
                .observeOn(AndroidSchedulers.mainThread())//设置观察者所在线程
                .subscribe(consumer);

        /**
         * 被观察者当前线程: RxNewThreadScheduler-1
         * accept: 555
         * 观察者当前线程: main
         */

    }

    public void multiSetThread() {
        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(666);
                Log.d(TAG, "被观察者当前线程: " + Thread.currentThread().getName());
            }
        });
        //观察者
        Consumer consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "accept: " + integer);
                Log.d(TAG, "观察者当前线程: " + Thread.currentThread().getName());
            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer o) throws Exception {
                        Log.d(TAG, "accept: " + o);
                        Log.d(TAG, "观察者当前线程1: " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer o) throws Exception {
                        Log.d(TAG, "accept: " + o);
                        Log.d(TAG, "观察者当前线程2: " + Thread.currentThread().getName());
                    }
                })
                .subscribe(consumer);
        /**
         * 被观察者当前线程: RxNewThreadScheduler-1
         * accept: 666
         * 观察者当前线程1: main
         * accept: 666
         * 观察者当前线程2: RxCachedThreadScheduler-2
         * accept: 666
         * 观察者当前线程: RxCachedThreadScheduler-2
         */
    }
}
