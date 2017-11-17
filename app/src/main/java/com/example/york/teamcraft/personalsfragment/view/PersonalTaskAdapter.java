package com.example.york.teamcraft.personalsfragment.view;

import android.content.Context;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.taskfragment.model.ContentTask;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/17.
 */

public class PersonalTaskAdapter extends RecyclerView.Adapter<PersonalTaskHolder>{
    private RecyclerView recyclerView;
    private Context context;
    private ArrayList<ContentTask> dataList;
    int expandedPosition = -1;

    public PersonalTaskAdapter(Context context, RecyclerView view, ArrayList<ContentTask> list) {
        this.context = context;
        this.recyclerView = view;
        this.dataList = list;
    }

    @Override
    public PersonalTaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_personal_task, null);
        PersonalTaskHolder holder = new PersonalTaskHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(PersonalTaskHolder holder, final int position) {
        holder.setTxtTitle(dataList.get(position).getTopic());
        holder.setTxtDate(dataList.get(position).getDate());
        holder.setTxtContent(dataList.get(position).getContent());

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
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
