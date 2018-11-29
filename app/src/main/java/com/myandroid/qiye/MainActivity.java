package com.myandroid.qiye;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myandroid.qiye.news.News;
import com.myandroid.qiye.news.NewsAdapter;
import com.myandroid.qiye.service.AutoUpdateService;
import com.myandroid.qiye.util.HttpUtil;
import com.myandroid.qiye.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<News> newsList = new ArrayList<>();
    private RecyclerView newsListRecycler;
    private TextView newsMore;
    private TextView btnGoMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        newsMore = (TextView) findViewById(R.id.news_more);

        initNews();

        newsListRecycler = (RecyclerView) findViewById(R.id.index_newslist);
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
        /*
        Log.d("newsList:",newsList.toString());
        NewsAdapter adapter = new NewsAdapter(newsList);
        newsListRecycler.setAdapter(adapter);
        */
        newsMore.setOnClickListener(this);

        btnGoMember = (TextView) findViewById(R.id.btn_gomember);
        btnGoMember.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news_more:
                Intent intent = new Intent(MainActivity.this,NewsListActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_gomember:
                Intent intent1 = new Intent(MainActivity.this,MemberActivity.class);
                startActivity(intent1);
                break;
        }
    }

    public void updateUI(){
        Log.d("newsList:",newsList.toString());
        NewsAdapter adapter = new NewsAdapter(getApplicationContext(),newsList);
        newsListRecycler.setAdapter(adapter);
    }
    public void initNews(){
        /*
        for(int i=0;i<10;i++){
            News inews = new News(i,"海上丝路看今朝：2018年习近平亚太之行",R.drawable.newspic);
            newsList.add(inews);
        }
        */

        String newsUrl = "http://wanjia.196tuan.com/api/article/index";
        //Toast.makeText(WeatherActivity.this,weatherUrl,Toast.LENGTH_LONG).show();
        HttpUtil.sendOkHttpRequest(newsUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //final String responseText = response.body().toString();
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
                        Toast.makeText(MainActivity.this,"获取新闻列表失败",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }


}
