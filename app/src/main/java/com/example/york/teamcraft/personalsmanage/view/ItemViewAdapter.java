package com.example.york.teamcraft.personalsmanage.view;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.personalsmanage.view.ItemContentTaskHolder;
import com.example.york.teamcraft.taskfragment.model.ContentTask;

import java.util.ArrayList;

/**
 * Created by York on 2017/9/7.
 */

public  class ItemViewAdapter extends RecyclerView.Adapter<ItemContentTaskHolder> {
    private static String TAG = "ItemViewAdapter";
    private ArrayList<ContentTask> taskList;
    private Context context;

    private View.OnClickListener onClickListener;

    public ItemViewAdapter(ArrayList<ContentTask> list, Context c) {
        context = c;
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
        Log.d(TAG, "call onCreateViewHolder()");

        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ItemContentTaskHolder holder, final int position) {
        holder.getTxtTitle().setText(taskList.get(position).getTopic());
        holder.getTxtDate().setText(taskList.get(position).getDate());
        holder.getTxtTime().setText(taskList.get(position).getTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void setClickListener(View.OnClickListener callBack) {
        onClickListener = callBack;
    }

}
