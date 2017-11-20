package com.example.york.teamcraft.teammanage.post.view;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.post.model.Comment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/7.
 */

public class CommentItemAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private static String TAG = "CommentItemAdapter";
    private FragmentActivity fragActivity;
    private ArrayList<Comment> dataList;

    public CommentItemAdapter(ArrayList<Comment> list, FragmentActivity act) {
        this.dataList = list;
        this.fragActivity = act;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_comment, parent, false);

        CommentViewHolder holder = new CommentViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + dataList.get(position).getAuthorName() + " " + dataList.get(position).getImageUrl());
        // 設定CircleImageView的來源
        Picasso.with(fragActivity)
                .load(dataList.get(position).getImageUrl())
                .into(holder.getCirView());
        holder.getTxtName().setText(dataList.get(position).getAuthorName());
        holder.getTxtComm().setText(dataList.get(position).getMessage());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
