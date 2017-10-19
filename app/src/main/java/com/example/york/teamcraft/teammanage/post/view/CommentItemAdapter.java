package com.example.york.teamcraft.teammanage.post.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.post.model.Comment;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/7.
 */

public class CommentItemAdapter extends RecyclerView.Adapter<CommentViewHolder>{
//    private Context context;
    private ArrayList<Comment> dataList;

    public CommentItemAdapter(ArrayList<Comment> list) {
       dataList = list;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_comment, null);

        CommentViewHolder holder = new CommentViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.getTxtName().setText(dataList.get(position).getAuthorName());
        holder.getTxtComm().setText(dataList.get(position).getMessage());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
