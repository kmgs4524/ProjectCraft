package com.example.york.teamcraft.teammanage.groupfragment.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.model.Group;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/6.
 */

public class GridItemAdapter extends BaseAdapter{
    private FragmentActivity fragActivity;
    private ArrayList<Group> dataList;

    public GridItemAdapter(FragmentActivity act, ArrayList<Group> list) {
        fragActivity = act;
        dataList = list;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(fragActivity)
                    .inflate(R.layout.cardview_team_group, null);
        TextView txtGroupName = (TextView) view.findViewById(R.id.txt_card_item_group_name);
        String name = dataList.get(position).getName();

        txtGroupName.setText(name);

        return view;
    }
}
