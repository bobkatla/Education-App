package com.example.grow;

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
