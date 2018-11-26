package com.myandroid.qiye.news;

import java.io.Serializable;

/**
 * Created by Huochai on 2018/11/26.
 */

public class News implements Serializable {
    private int id;
    private String title;
    private int imageId;

    public News(int id,String title,int imageId){
        this.id = id;
        this.title = title;
        this.imageId = imageId;
    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public int getImageId(){
        return imageId;
    }

}
