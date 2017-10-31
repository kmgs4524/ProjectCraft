package com.example.york.teamcraft.personalsmanage.view;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.personalsmanage.model.DataPath;
import com.example.york.teamcraft.personalsmanage.model.WriteGroupTask;
import com.example.york.teamcraft.personalsmanage.view.ItemContentTaskHolder;
import com.example.york.teamcraft.taskfragment.model.ContentTask;

import java.util.ArrayList;

/**
 * Created by York on 2017/9/7.
 */

public  class ItemViewAdapter extends RecyclerView.Adapter<ItemContentTaskHolder> {
    private static String TAG = "ItemViewAdapter";
    private ArrayList<DataPath> taskPathList;
    private ArrayList<ContentTask> taskList;
    private Context context;

    private CompoundButton.OnCheckedChangeListener onCheckedListener;

    public ItemViewAdapter(ArrayList<DataPath> pathList, ArrayList<ContentTask> list, Context c) {
        context = c;
        taskPathList = pathList;
        taskList = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemContentTaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        // LayoutInflater.from(Context c): static LayoutInflater; Obtains the LayoutInflater from the given context.
        // inflate(int resource, ViewGroup root, boolean attachToRoot): View view; Inflate a new view hierarchy from the specified xml resource.

        View v = LayoutInflater.from(parent.getContext())   //
                .inflate(R.layout.recycler_view_contenttask, parent, false); // ViewGroup root則可以是null，null時就只創建一個resource對應的View，不是null時，會將創建的view自動加為root的child。

        // set the view's size, margins, paddings and layout parameters
        ItemContentTaskHolder holder = new ItemContentTaskHolder(v);
        holder.getCheckTask().setOnCheckedChangeListener(onCheckedListener);

        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ItemContentTaskHolder holder, final int position) {
        if(taskList.get(position).getStatus().equals("undo")) {
            holder.getCheckTask().setChecked(false);
        } else {
            holder.getCheckTask().setChecked(true);
        }
        holder.getCheckTask().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                WriteGroupTask writeGroupTask = new WriteGroupTask();
                writeGroupTask.updateTaskStatus(taskPathList.get(position), isChecked);
            }
        });
        holder.getTxtTitle().setText(taskList.get(position).getTopic());
        holder.getTxtDate().setText(taskList.get(position).getDate());
        holder.getTxtTime().setText(taskList.get(position).getTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return taskList.size();
    }

}
