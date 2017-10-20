package com.example.york.teamcraft.teammanage.taskprogress.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.taskprogress.model.TaskProgress;
import com.example.york.teamcraft.teammanage.taskprogress.view.recyclerview.TaskProgressAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2017/7/4.
 */

//任務進度Fragment
public class TaskProgressFragment extends Fragment {
    private static final String TAG = "TaskProgressFragment";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // test data
    private ArrayList<TaskProgress> dataList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.team_fragment_taskprogress, container, false);

        dataList = new ArrayList<>();
        dataList.add(new TaskProgress("活動組", 10, 4));
        dataList.add(new TaskProgress("器材組", 7, 3));
        dataList.add(new TaskProgress("美工組", 9, 2));

        initRecycler(view);
        setRecyclerAdapter(dataList);

        return view;
    }

    public void initRecycler(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_group_progress);
    }

    public void setRecyclerAdapter(ArrayList<TaskProgress> list) {
        adapter = new TaskProgressAdapter(list);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        Log.d("getwidth", Integer.toString(layoutManager.getWidth()));
        recyclerView.setLayoutManager(layoutManager);
    }


}
