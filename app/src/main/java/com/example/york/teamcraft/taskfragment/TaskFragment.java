package com.example.york.teamcraft.taskfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.groupinformation.view.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskFragment extends Fragment {
    private ExpandableListView expandList;
    private ExpandableListAdapter adapter;

    private ArrayList<String> groupList;
    private HashMap<String, ArrayList<String>> itemMap;
    private ArrayList<String> childList1;
    private ArrayList<String> childList2;

    // 在連接activity前就設定arguments
//    public TaskFragment() {
//        setArguments(new Bundle());
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.team_fragment_member, container, false);

        String id = getArguments().getString("id");
        Log.d("args in task", id);

        groupList = new ArrayList<>();
        groupList.add("找器材");
        groupList.add("整理器材");

        itemMap = new HashMap<>();
        childList1 = new ArrayList<>();
        childList2 = new ArrayList<>();

        childList1.add("小王要找器材");
        childList1.add("小玉要找器材");
        itemMap.put("找器材", childList1);

        childList2.add("小王要整理器材");
        childList2.add("小玉要整理器材");
        itemMap.put("整理器材", childList2);

        expandList = (ExpandableListView) view.findViewById(R.id.expand_list);
        adapter = new ExpandableListAdapter(getActivity(), groupList, itemMap);
        expandList.setAdapter(adapter);

        return view;
    }

}
