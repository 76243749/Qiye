package com.myandroid.qiye.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Huochai on 2018/11/27.
 */

public class NewsList {
    @SerializedName("list")
    public Lists lists;
    public class Lists{
        public String id;
        public String title;
    }
}
