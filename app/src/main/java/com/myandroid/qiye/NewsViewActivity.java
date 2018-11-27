package com.myandroid.qiye;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myandroid.qiye.news.News;

public class NewsViewActivity extends AppCompatActivity {
    private TextView viewNewsTitle;
    private ImageView viewNewsPic;
    private TextView viewNewsDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        viewNewsTitle = (TextView) findViewById(R.id.view_news_title);
        viewNewsPic = (ImageView) findViewById(R.id.view_news_pic);
        viewNewsDemo = (TextView) findViewById(R.id.view_news_demo);
        News news = (News) getIntent().getSerializableExtra("newsData");
        viewNewsTitle.setText(news.getTitle());
        //viewNewsPic.setImageResource(news.getImageId());
        viewNewsDemo.setText(news.getDemo());
        Glide.with(NewsViewActivity.this).load(news.getImageurl()).into(viewNewsPic);
    }
}
