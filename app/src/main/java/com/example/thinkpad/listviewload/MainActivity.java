package com.example.thinkpad.listviewload;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreListView myListView;
    private List<String> list = new ArrayList();
    private MainAdapter adapter;
    private int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        //设置圈圈颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorAccent, R.color.colorPrimary,
                R.color.colorAccent);

        myListView = (LoadMoreListView) findViewById(R.id.lv);
        adapter = new MainAdapter(this, list);
        myListView.setAdapter(adapter);

        generateData();
        setOnClickListener();

    }

    private void setOnClickListener() {
        /**
         * 下拉刷新
         */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIndex = 0;
                        list.clear();
                        generateData();
                        adapter.notifyDataSetChanged();
                        //刷新完成
                        swipeRefreshLayout.setRefreshing(false);
                        myListView.refreshComplete();
                    }
                }, 1500);
            }
        });

        /**
         * 上拉加载更多
         */
        myListView.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mIndex < 30) {
                            generateData();
                            adapter.notifyDataSetChanged();
                            //设置loading完成
                            myListView.loadMoreComplete();
                        } else {
                            //设置加载到底
                            myListView.loadMoreEnd();
                        }
                    }
                }, 1500);

            }
        });

    }

    /**
     * 生成数据
     */
    private void generateData() {
        for (int i = 0; i < 15; i++) {
            list.add("数据" + mIndex++);
        }
    }
}
