package com.example.york.teamcraft.taskfragment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.targetfragment.TargetFragment;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskFragment extends Fragment implements TaskFragmentView {
    private String id;

    // view
    private ExpandableListView expandList;
    private ExpandableListAdapter adapter;

    // Database Model
    private ReadGroupTasks readGroupTasks;

    // passdataListener
    private PassDataListener callback;

    public static TaskFragment newInstance(String groupId) {
        TaskFragment taskFragment = new TaskFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", groupId);
        taskFragment.setArguments(bundle);
        return taskFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (PassDataListener) context;
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.team_fragment_member, container, false);
        // get passed id
        id = getArguments().getString("id");
        // set ExpandableList and Adapter
        setExpandList(view);

        return view;
    }

    public void setExpandList(View v) {
        expandList = (ExpandableListView) v.findViewById(R.id.expand_list);

        readGroupTasks = new ReadGroupTasks();
        readGroupTasks.getGroupTaskName(id, new CallBackTwoArgs<ArrayList<String>, HashMap<String, ArrayList<ContentTask>>>() {
            @Override
            public void update(final ArrayList<String> list, final HashMap<String, ArrayList<ContentTask>> map) {
                // init adapter
                adapter = new ExpandableListAdapter(getActivity(), list, map);
                // set adapter
                expandList.setAdapter(adapter);
                // set listener
                expandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        ContentTask contentTask = map.get(list.get(groupPosition)).get(childPosition);
                        callback.passData(contentTask);
                        return false;
                    }
                });
            }
        });
    }

}
