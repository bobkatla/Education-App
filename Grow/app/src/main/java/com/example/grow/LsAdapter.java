package com.example.grow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LsAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    private ArrayList<Data> tasks;
    public LsAdapter(ArrayList<Data> tasksNN) {
        tasks = tasksNN;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextTime;
        private TextView mTextTask;
        private ImageView mImageItems;

        public ListViewHolder(View itemView){
            super(itemView);
            mTextTime = (TextView) itemView.findViewById(R.id.textTime);
            mTextTask = (TextView) itemView.findViewById(R.id.textTask);
            mImageItems = (ImageView) itemView.findViewById(R.id.image1);
        }

        public void bindView(int position){
            Data current = tasks.get(position);
            mTextTime.setText(current.getTime());
            mTextTask.setText(current.getTask());
            mImageItems.setImageResource(current.getImageResource());
        }
    }
}
