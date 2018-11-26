package com.myandroid.qiye.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myandroid.qiye.MainActivity;
import com.myandroid.qiye.NewsViewActivity;
import com.myandroid.qiye.R;

import java.util.List;

/**
 * Created by Huochai on 2018/11/26.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> mNewsList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View newsView;
        ImageView newsPic;
        TextView newsTitle;

        public ViewHolder(View view){
            super(view);
            newsView = view;
            newsPic = (ImageView) view.findViewById(R.id.item_news_pic);
            newsTitle = (TextView) view.findViewById(R.id.item_news_title);

        }
    }

    public NewsAdapter(List<News> newsList){
        mNewsList = newsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                News news = mNewsList.get(position);
                Toast.makeText(v.getContext(),"你点击了ID为"+news.getId()+"的"+news.getTitle(),Toast.LENGTH_SHORT).show();
                gotoView(v.getContext(),news);
            }
        });
        holder.newsPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                News news = mNewsList.get(position);
                Toast.makeText(v.getContext(),"你点击了ID为"+news.getId()+"的"+news.getTitle(),Toast.LENGTH_SHORT).show();
                gotoView(v.getContext(),news);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsPic.setImageResource(news.getImageId());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void gotoView(Context context,News news){
        Intent intent = new Intent(context,NewsViewActivity.class);
        intent.putExtra("newsData", news);
        context.startActivity(intent);
    }
}
