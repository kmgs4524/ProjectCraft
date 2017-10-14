package com.example.york.teamcraft.taskfragment.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.taskfragment.model.ContentTask;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by York on 2017/10/10.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter{
    private Context context;
    private ArrayList<String> groupList;
    private HashMap< String, ArrayList<ContentTask> > itemMap;

    public ExpandableListAdapter(Context context, ArrayList<String> list, HashMap<String, ArrayList<ContentTask>> map) {
        this.context = context;
        this.groupList = list;
        this.itemMap = map;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemMap.get(groupList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemMap.get(groupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.expandlist_group, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_expand_group);
        txtTitle.setText(groupList.get(groupPosition));

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // contentTask object
        ContentTask itemTask = itemMap.get(groupList.get(groupPosition)).get(childPosition);
        // view
        View view = LayoutInflater.from(context).inflate(R.layout.expandlist_item, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_expand_item);
        ImageView imgStatus = (ImageView) view.findViewById(R.id.img_expand_item);

        txtTitle.setText(itemTask.getTopic());
        // 判斷目前該項工作是否完成
        if(itemTask.getStatus() == false) {
            imgStatus.setVisibility(View.INVISIBLE);    // 若未完成則不顯示勾勾圖案
        } else {
            imgStatus.setVisibility(View.VISIBLE);  // 若完成則顯示勾勾圖案
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
