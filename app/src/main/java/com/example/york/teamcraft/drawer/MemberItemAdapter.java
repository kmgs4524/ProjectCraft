package com.example.york.teamcraft.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/27.
 */

public class MemberItemAdapter extends RecyclerView.Adapter<GroupHolder> {
    private ArrayList<TeamMember> memList;

    public MemberItemAdapter(ArrayList<TeamMember> list) {
        this.memList = list;
    }

    @Override
    public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_group, null);

        GroupHolder holder = new GroupHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(GroupHolder holder, int position) {
        holder.getTxtName().setText(memList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return memList.size();
    }
}
