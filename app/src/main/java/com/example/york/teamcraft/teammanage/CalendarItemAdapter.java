package com.example.york.teamcraft.teammanage;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.york.teamcraft.R;

import java.util.ArrayList;

/**
 * Created by York on 2017/9/11.
 */

public class CalendarItemAdapter extends RecyclerView.Adapter<CalendarItemAdapter.ViewHolder>{
    private static String TAG = "CalendarItemAdapter";

    private ArrayList<TeamTasks> dataList;  // 存放TeamTasks的List

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtDate;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_cardItem_title);
            txtDate = (TextView) itemView.findViewById(R.id.txt_cardItem_date);
        }
    }

    public CalendarItemAdapter(ArrayList<TeamTasks> list) {
        dataList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_cardview_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        Log.d(TAG, "onCreateViewHolder");
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String TAG = "onBindViewHolder";

        String title = dataList.get(position).getTitle();
        String date = dataList.get(position).getDate();
        Log.d(TAG, title);
        Log.d(TAG, date);
        holder.txtTitle.setText(title);
        holder.txtDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
