package com.myandroid.qiye;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.myandroid.qiye.news.News;
import com.myandroid.qiye.news.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<News> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNews();

        RecyclerView newsListRecycler = (RecyclerView) findViewById(R.id.index_newslist);
        newsListRecycler.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                //解决ScrollView里存在多个RecyclerView时滑动卡顿的问题
                //如果你的RecyclerView是水平滑动的话可以重写canScrollHorizontally方法
                return false;
            }
        });
        //解决数据加载不完的问题
        newsListRecycler.setNestedScrollingEnabled(false);
        newsListRecycler.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        newsListRecycler.setFocusable(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        newsListRecycler.setLayoutManager(layoutManager);

        NewsAdapter adapter = new NewsAdapter(newsList);
        newsListRecycler.setAdapter(adapter);


    }

    public void initNews(){
        for(int i=0;i<10;i++){
            News inews = new News(i,"海上丝路看今朝：2018年习近平亚太之行",R.drawable.newspic);
            newsList.add(inews);
        }
    }
}
