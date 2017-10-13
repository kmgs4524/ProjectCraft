package com.example.york.teamcraft.personalsmanage;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.taskfragment.model.ContentTask;

import java.util.ArrayList;

/**
 * Created by York on 2017/9/7.
 */

public  class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.TextHolder> {
    private static String TAG = "ItemViewAdapter";
    private ArrayList<ContentTask> viewDataSet;
    private Context activityContext;

    private View.OnClickListener onClickListener;

    public ItemViewAdapter(ArrayList<ContentTask> dataSet, Cursor cursor, Context context) {
        activityContext = context;
        viewDataSet = dataSet;

    }

    public class TextHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle, txtContent, txtDate;

        public TextHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtContent = (TextView) itemView.findViewById(R.id.txt_content);
//            txtDate = (TextView) itemView.findViewById(R.id.txt_date);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        // LayoutInflater.from(Context c): static LayoutInflater; Obtains the LayoutInflater from the given context.
        // inflate(int resource, ViewGroup root, boolean attachToRoot): View view; Inflate a new view hierarchy from the specified xml resource.

        View v = LayoutInflater.from(parent.getContext())   //
                .inflate(R.layout.recycler_view_contenttask, parent, false); // ViewGroup root則可以是null，null時就只創建一個resource對應的View，不是null時，會將創建的view自動加為root的child。

        // set the view's size, margins, paddings and layout parameters
        TextHolder textHolder = new TextHolder(v);
        Log.d(TAG, "call onCreateViewHolder()");

        return textHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TextHolder holder, final int position) {
//        holder.txtTitle.setText(viewDataSet.get(position).getTitle());
//        holder.txtContent.setText(viewDataSet.get(position).getContent());
//        holder.txtDate.setText(viewDataSet.get(position).getDate());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 0;
//        return viewDataSet.size();
    }

    public void setClickListener(View.OnClickListener callBack) {
        onClickListener = callBack;
    }

}
