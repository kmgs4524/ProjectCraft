package com.example.york.teamcraft.taskfragment.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.addgrouptaskfragment.AddGroupTaskDialogFragment;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskFragment extends Fragment implements TaskFragmentView {
    private String id;

    // view
    private ImageView imgAdd;
    private ExpandableListView expandList;
    private ExpandableListAdapter adapter;

    // Database Model
    private ReadGroupTasks readGroupTasks;

    // passdataListener
    private PassDataListener callback;

    // Factory Method: 傳入需要的參數，讓fragment在建構時便擁有需要的參數
    public static TaskFragment newInstance(String groupId) {
        TaskFragment taskFragment = new TaskFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", groupId);
        taskFragment.setArguments(bundle);
        return taskFragment;
    }

    // 在與container activity連接時便指定他為負責傳遞資料的callback，故container activity需實作傳遞資料的方法
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
        View view = inflater.inflate(R.layout.team_fragment_task, container, false);
        // get passed id
        id = getArguments().getString("id");
        // set Add Group Task Image Button
        setImgAddListener(view);
        // set ExpandableList and Adapter
        setExpandList(view);

        return view;
    }

    public void setImgAddListener(View v) {
        imgAdd = (ImageView) v.findViewById(R.id.img_group_task);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddGroupTaskDialog();
            }
        });
    }

    public void showAddGroupTaskDialog() {
        AddGroupTaskDialogFragment groupTaskDialogFragment = new AddGroupTaskDialogFragment();
        groupTaskDialogFragment.show(getFragmentManager(), "add groupTask");
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
