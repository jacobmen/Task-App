package com.example.taskapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<String> {

    ArrayList<String> tasks;
    ArrayList<String> times;
    Context mContext;

    public Adapter(@NonNull Context context, ArrayList<String> setTasks, ArrayList<String> setTimes) {
        super(context, R.layout.chunk_task);
        this.tasks = setTasks;
        this.times = setTimes;
        mContext = context;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.chunk_task, parent, false);
            mViewHolder.taskDesc = convertView.findViewById(R.id.taskDesc);
            mViewHolder.taskTime = convertView.findViewById(R.id.taskTime);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.taskDesc.setText(tasks.get(position));
        mViewHolder.taskTime.setText(times.get(position));

        return convertView;
    }

    static class ViewHolder {
        TextView taskDesc;
        TextView taskTime;
    }
}
