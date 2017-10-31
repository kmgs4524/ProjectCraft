package com.example.york.teamcraft.teammanage.groupfragment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.drawer.GroupHolder;
import com.example.york.teamcraft.teammanage.groupinformation.view.GroupInfoActivity;
import com.example.york.teamcraft.teammanage.model.Group;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by York on 2017/10/6.
 */

public class GroupItemAdapter extends RecyclerView.Adapter<GroupHolder>{
    // listener
    private View.OnClickListener listener;
    // context
    private FragmentActivity fragActivity;
    // data
    private ArrayList<Group> dataList;

    public GroupItemAdapter(FragmentActivity act, ArrayList<Group> list) {
        fragActivity = act;
        dataList = list;
    }


    @Override
    public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragActivity).inflate(R.layout.recycler_view_group, null);
        GroupHolder holder = new GroupHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(GroupHolder holder, final int position) {
        holder.getTxtName().setText(dataList.get(position).getName());
        holder.setItemViewListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", dataList.get(position).getId());
                bundle.putString("name", dataList.get(position).getName());
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(fragActivity, GroupInfoActivity.class);
                fragActivity.startActivity(intent);  // 進入CreateGroupActivity
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
