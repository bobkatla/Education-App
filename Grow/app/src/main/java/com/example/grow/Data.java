package com.example.grow;

//Specific a customised data for easy passing to the RecycleView
//This is corresponding to list_items.xml factors, used for RecycleView in Todo Class
public class Data {
    private int mImageResource;
    private String mTime;
    private String mTask;
    public Data(int imageResource, String time, String task){
        mImageResource = imageResource;
        mTime = time;
        mTask = task;
    }

    public int getImageResource(){
        return mImageResource;
    }
    public String getTime(){
        return mTime;
    }
    public String getTask(){
        return mTask;
    }
}
