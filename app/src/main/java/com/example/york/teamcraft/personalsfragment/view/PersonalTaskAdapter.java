package com.example.york.teamcraft.personalsfragment.view;

import android.content.Context;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.personalsmanage.model.DataPath;
import com.example.york.teamcraft.personalsmanage.model.WriteGroupTask;
import com.example.york.teamcraft.taskfragment.model.ContentTask;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/17.
 */

public class PersonalTaskAdapter extends RecyclerView.Adapter<PersonalTaskHolder>{
    private RecyclerView recyclerView;
    private Context context;
    private ArrayList<ContentTask> taskList;
    private ArrayList<DataPath> pathList;
    int expandedPosition = -1;

    public PersonalTaskAdapter(Context context, RecyclerView view, ArrayList<DataPath> pList, ArrayList<ContentTask> tlist) {
        this.context = context;
        this.recyclerView = view;
        this.pathList = pList;
        this.taskList = tlist;
    }

    @Override
    public PersonalTaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_personal_task, null);
        PersonalTaskHolder holder = new PersonalTaskHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(PersonalTaskHolder holder, final int position) {
        holder.setTxtTitle(taskList.get(position).getTopic());
        holder.setTxtDate(taskList.get(position).getDate());
        holder.setTxtContent(taskList.get(position).getContent());
        // 設定expand animation
        final boolean isExpanded = position == expandedPosition;
        holder.getTxtContent().setVisibility(isExpanded? View.VISIBLE: View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandedPosition = isExpanded? -1: position;
                TransitionManager.beginDelayedTransition(recyclerView);
                notifyDataSetChanged();
            }
        });
        // 設定checkbox status
        if(taskList.get(position).getStatus().equals("done")) { // 若為done則將checkbox打勾
            holder.getCheckBox().setChecked(true);
        } else if(taskList.get(position).getStatus().equals("checked")) {   // 若為checked則不顯示該項目
            holder.itemView.setVisibility(View.GONE);
        } else {
            holder.getCheckBox().setChecked(false);
        }
        // 設定checkbox的OnCheckedChangeListener
        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                WriteGroupTask writeGroupTask = new WriteGroupTask();
                writeGroupTask.updateTaskStatus(pathList.get(position), isChecked); // 寫入firebase groupTask node
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
