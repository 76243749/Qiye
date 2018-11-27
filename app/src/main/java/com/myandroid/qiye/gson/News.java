package com.myandroid.qiye.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by Huochai on 2018/11/27.
 */

public class News {
    public String code;
    public String msg;
    @SerializedName("data")
    public List<NewsList> newsDataList;
}
