package com.example.york.teamcraft.teammanage.taskprogress.view.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupProgress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by York on 2017/10/20.
 */

public class TaskProgressAdapter extends RecyclerView.Adapter<TaskProgressHolder>{
    private List<GroupProgress> progList;

    public TaskProgressAdapter(ArrayList<GroupProgress> list) {
        this.progList = list;
    }

    @Override
    public TaskProgressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_group_progress, parent, false);
        TaskProgressHolder holder = new TaskProgressHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(TaskProgressHolder holder, int position) {
        holder.getTxtGroupName().setText(progList.get(position).getGroupName());
        holder.getProgGroup().setMax(progList.get(position).getTotalTaskNum());
        holder.getProgGroup().setProgress(progList.get(position).getCheckedTaskNum());
    }

    @Override
    public int getItemCount() {
        return progList.size();
    }
}
