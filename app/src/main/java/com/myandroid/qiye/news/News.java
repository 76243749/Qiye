package com.myandroid.qiye.news;

import java.io.Serializable;

/**
 * Created by Huochai on 2018/11/26.
 */

public class News implements Serializable {
    private int id;
    private String title;
    private int imageId;
    private String demo;
    private String imageurl;

    public News(int id,String title,String demo,String imageurl,int imageId){
        this.id = id;
        this.title = title;
        this.demo = demo;
        this.imageId = imageId;
        this.imageurl = imageurl;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public int getImageId(){
        return imageId;
    }
    public void setImageId(int imageId){
        this.imageId = imageId;
    }

    public String getDemo(){
        return demo;
    }
    public void setDemo(String demo){
        this.demo = demo;
    }

    public String getImageurl(){
        return imageurl;
    }
    public void setImageurl(String imageurl){
        this.imageurl = imageurl;
    }

}
