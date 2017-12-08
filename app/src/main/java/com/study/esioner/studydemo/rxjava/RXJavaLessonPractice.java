package com.study.esioner.studydemo.rxjava;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.study.esioner.studydemo.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lenovo on 2017/12/8.
 */

public class RXJavaLessonPractice extends AppCompatActivity {
    private static final String TAG = "RXJavaLessonPractice";
    private List<File> imageFileList;
    private RecyclerView rvPicture;
    private File files;
    private MyRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_practice_layout);
        imageFileList = new ArrayList();
        initData();
        rvPicture = findViewById(R.id.rv_rxjava_practice);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rvPicture.setLayoutManager(manager);
        mAdapter = new MyRecyclerAdapter(imageFileList);
        rvPicture.setAdapter(mAdapter);
    }


    private void initData() {
        files = new File(Environment.getExternalStorageDirectory().getPath());
        Log.d(TAG, "initData: " + files.getAbsolutePath());
        Log.d(TAG, "initData: " + files.getName());
        //遍历手机文件

    }


    class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
        private List<File> imageList;

        public MyRecyclerAdapter(List<File> list) {
            this.imageList = list;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private final ImageView iv;

            public ViewHolder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.iv_rxjava_practice);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RXJavaLessonPractice.this).inflate(R.layout.rxjava_image_layout, null);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            File file = imageList.get(position);
            Glide.with(RXJavaLessonPractice.this).load(file).into(holder.iv);
            Log.d(TAG, "onBindViewHolder: " + file.getAbsolutePath());
        }

        @Override
        public int getItemCount() {
            return imageList.size();
        }
    }
}

