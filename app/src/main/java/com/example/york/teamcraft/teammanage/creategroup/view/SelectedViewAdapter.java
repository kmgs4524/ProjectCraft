package com.example.york.teamcraft.teammanage.creategroup.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.model.Group;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/29.
 */

public class SelectedViewAdapter extends BaseAdapter{
    private ArrayList<GroupMember> memList;
    private Context context;

    public SelectedViewAdapter(Context context, ArrayList<GroupMember> list) {
        this.context = context;
        this.memList = list;
        Log.d("SelectedViewAdapter", "getView: " + memList.size());
    }

    @Override
    public int getCount() {
        return memList.size();
    }

    @Override
    public Object getItem(int position) {
        return memList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;  // 顯示被選擇成員的view
        SelectedMemberView selectedView;    // 將修改view的方法封裝在此物件中

        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.member_tag, parent, false);
            selectedView = new SelectedMemberView(context, view);
        } else {
            view = convertView;
            selectedView = new SelectedMemberView(context, view);
        }
        selectedView.setTxtName(memList.get(position).getName());

        return view;
    }
}
