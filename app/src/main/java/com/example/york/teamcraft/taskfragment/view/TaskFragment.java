package com.example.york.teamcraft.taskfragment.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskFragment extends Fragment implements TaskFragmentView {
    private ExpandableListView expandList;
    private ExpandableListAdapter adapter;

    private ArrayList<String> groupList;
    private HashMap<String, ArrayList<String>> itemMap;
    private ArrayList<String> childList1;
    private ArrayList<String> childList2;

    private ReadGroupTasks readGroupTasks;

    public static TaskFragment newInstance(String groupId) {
        TaskFragment taskFragment = new TaskFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", groupId);
        taskFragment.setArguments(bundle);
        return taskFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.team_fragment_member, container, false);

        String id = getArguments().getString("id");
        Log.d("args in task", id);

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

        readGroupTasks = new ReadGroupTasks();
        readGroupTasks.getGroupTaskName(id, new CallBackTwoArgs<ArrayList<String>, HashMap<String, ArrayList<ContentTask>>>() {
            @Override
            public void update(ArrayList<String> list, HashMap<String, ArrayList<ContentTask>> map) {
//                Log.d("hashmap", Integer.toString(map.get("活動實行").size()));
                adapter = new ExpandableListAdapter(getActivity(), list, map);
                expandList.setAdapter(adapter);
            }
        });

//        setExpandListAdpater(view);

//        adapter = new ExpandableListAdapter(getActivity(), groupList, itemMap);
//        expandList.setAdapter(adapter);

        return view;
    }

    public void setExpandListAdpater(View v) {


    }

}
