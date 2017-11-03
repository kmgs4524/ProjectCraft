package com.example.york.teamcraft.teammanage.board.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.york.teamcraft.teammanage.model.Post;
import com.example.york.teamcraft.R;

import java.util.ArrayList;

/**
 * Created by York on 2017/9/11.
 */

public class BoardItemAdapter extends RecyclerView.Adapter<BoardItemAdapter.ViewHolder>{
    private static String TAG = "BoardItemAdapter";

    private View.OnClickListener listener;
    private ArrayList<Post> dataList;  // 存放TeamPosts的List

    public BoardItemAdapter(ArrayList<Post> list, View.OnClickListener listener) {
        dataList = list;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtDate;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_cardItem_title);
            txtDate = (TextView) itemView.findViewById(R.id.txt_cardItem_date);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_cardview_item, parent, false);
        v.setOnClickListener(listener);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = dataList.get(position).getTopic();
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