package com.example.grow;

//Specific a customised data for easy passing to the RecycleView
//This is corresponding to list_items.xml factors, used for RecycleView in Todo Class
//extra id info to pass later in delete
public class Data {
    private int mImageResource;
    private String mTime;
    private String mTask;
    private String mId;
    public Data(String id, int imageResource, String time, String task){
        mId = id;
        mImageResource = imageResource;
        mTime = time;
        mTask = task;
    }

    public String getId(){
        return mId;
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
