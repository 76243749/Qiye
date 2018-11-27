package com.myandroid.qiye.util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.myandroid.qiye.news.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Huochai on 2018/11/27.
 */

public class Utility {

    //  解析返回的文章数据
    public static News handleNewsResponse(String response){
        try {
            JSONObject jsonObject   = new JSONObject(response);
            JSONArray jsonArray     = jsonObject.getJSONArray("NEWS");
            String newsString       = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(newsString,News.class);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
