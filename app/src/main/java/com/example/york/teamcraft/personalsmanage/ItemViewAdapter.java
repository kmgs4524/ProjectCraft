package com.example.york.teamcraft.personalsmanage;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.york.teamcraft.R;

import java.util.ArrayList;

/**
 * Created by York on 2017/9/7.
 */

public  class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.TextHolder> {
    private static String TAG = "ItemViewAdapter";
    private ArrayList<Note> viewDataSet;

    CursorAdapter cursorAdapter;
    Context activityContext;

    public ItemViewAdapter(ArrayList<Note> dataSet, Cursor cursor, Context context) {
        activityContext = context;
        viewDataSet = dataSet;

        cursorAdapter = new CursorAdapter(context, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext())   //
                .inflate(R.layout.recycle_view_item, parent, false); // ViewGroup root則可以是null，null時就只創建一個resource對應的View，不是null時，會將創建的view自動加為root的child。
                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {

            }


        };
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
        Log.d(TAG, "call onCreateViewHolder()");
        // create a new view
        // LayoutInflater.from(Context c): static LayoutInflater; Obtains the LayoutInflater from the given context.
        // 	inflate(int resource, ViewGroup root, boolean attachToRoot): View view; Inflate a new view hierarchy from the specified xml resource.

//        View v = LayoutInflater.from(parent.getContext())   //
//                .inflate(R.layout.recycle_view_item, parent, false); // ViewGroup root則可以是null，null時就只創建一個resource對應的View，不是null時，會將創建的view自動加為root的child。

        // set the view's size, margins, paddings and layout parameters

//        TextHolder textHolder = new TextHolder(v);
//        Log.d(TAG, "call onCreateViewHolder()");
//        return textHolder;

        View v = cursorAdapter.newView(activityContext, cursorAdapter.getCursor(), parent);
        Log.d("newView.view", v.toString());
        return new TextHolder(v);
    }

    @Override
    public void onBindViewHolder(TextHolder holder, int position) {
//        holder.txtTitle.setText(viewDataSet.get(position).getTitle());
//        holder.txtContent.setText(viewDataSet.get(position).getContent());
//        holder.txtDate.setText(viewDataSet.get(position).getDate());


        // Passing the binding operation to cursor loader
        cursorAdapter.getCursor().moveToPosition(position); //EDITED: added this line as suggested in the comments below, thanks :)
        Cursor cursor = cursorAdapter.getCursor();
        String itemTitle = cursor.getString(
                cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NAME_TITLE)
        );
        String itemContent = cursor.getString(
                cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NAME_CONTENT)
        );
        holder.txtTitle.setText(itemTitle);
        holder.txtContent.setText(itemContent);
//        cursorAdapter.bindView(holder., activityContext, cursorAdapter.getCursor());

    }

// Replace the contents of a view (invoked by the layout manager)

//    public void onBindViewHolder(ViewHolder holder, int position) {
//
//    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
//        return viewDataSet.size();
        return cursorAdapter.getCount();
    }

}
