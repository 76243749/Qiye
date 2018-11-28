package com.myandroid.qiye;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.myandroid.qiye.news.News;
import com.myandroid.qiye.news.NewsAdapter;
import com.myandroid.qiye.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView newsListWrap;
    private SwipeRefreshLayout newsListSwiper;
    private List<News> newsList = new ArrayList<>();
    private Button newsListMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_news_list);

        newsListWrap = (RecyclerView) findViewById(R.id.news_list_wrap);
        /*
        newsListWrap.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        //解决数据加载不完的问题
        newsListWrap.setNestedScrollingEnabled(false);
        newsListWrap.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        newsListWrap.setFocusable(false);
        */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        newsListWrap.setLayoutManager(layoutManager);
        loadNewsList();
        newsListSwiper = (SwipeRefreshLayout) findViewById(R.id.news_list_swiper);
        //设置下拉刷新的箭头颜色
        newsListSwiper.setColorSchemeResources(android.R.color.holo_red_light);
        //设置下拉刷新的背景颜色为白色
        newsListSwiper.setProgressBackgroundColorSchemeResource(android.R.color.white);
        newsListSwiper.setOnRefreshListener(this);

        newsListMore = (Button) findViewById(R.id.news_list_more);
        newsListMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNewsList();
            }
        });

    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "下拉刷新成功", Toast.LENGTH_SHORT).show();
        if (newsListSwiper.isRefreshing()) {//如果正在刷新

            newsList.removeAll(newsList);//清空数据集
            updateUI();
            loadNewsList();

            newsListSwiper.setRefreshing(false);//取消刷新
        }
    }

    private void loadNewsList() {
        String apiUrl = "http://wanjia.196tuan.com/api/article/index";
        HttpUtil.sendOkHttpRequest(apiUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject   = new JSONObject(responseText);
                            JSONObject dataList = jsonObject.getJSONObject("data");
                            JSONArray Lists = dataList.getJSONArray("list");
                            for(int i=0;i<Lists.length();i++){
                                JSONObject listObject = Lists.getJSONObject(i);
                                Log.d("Title:",listObject.getString("title"));
                                News inews = new News(listObject.getInt("id"),
                                        listObject.getString("title"),
                                        listObject.getString("demo"),
                                        listObject.getString("imageurl"),
                                        R.drawable.newspic);
                                newsList.add(inews);
                            }
                            updateUI();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NewsListActivity.this,"获取新闻列表失败",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void updateUI() {
        NewsAdapter adapter = new NewsAdapter(getApplicationContext(),newsList);
        newsListWrap.setAdapter(adapter);
    }
}
