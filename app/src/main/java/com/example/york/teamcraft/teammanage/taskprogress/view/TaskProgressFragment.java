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
import android.widget.RelativeLayout;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupMissionProgress;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupProgress;
import com.example.york.teamcraft.teammanage.taskprogress.presenter.TaskProgressPresenter;
import com.example.york.teamcraft.teammanage.taskprogress.presenter.TaskProgressPresenterImpl;
import com.example.york.teamcraft.teammanage.taskprogress.view.recyclerview.TaskProgressAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2017/7/4.
 */

//任務進度Fragment
public class TaskProgressFragment extends Fragment implements TaskProgressView{
    private static final String TAG = "TaskProgressFragment";
    // view
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    // presenter
    private TaskProgressPresenter taskFragmentPresenter;

    public static TaskProgressFragment newInstance() {
        Bundle args = new Bundle();

        TaskProgressFragment fragment = new TaskProgressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment_taskprogress, container, false);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.BELOW, R.id.linear_layout_action);
        view.setLayoutParams(params);

        initRecycler(view);
        taskFragmentPresenter = new TaskProgressPresenterImpl(this);
        taskFragmentPresenter.setAdapterData();

        return view;
    }

    public void initRecycler(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_group_progress);
    }

    @Override
    public void setRecyclerAdapter(ArrayList<GroupProgress> list) {
        adapter = new TaskProgressAdapter(list);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getActivity());
        Log.d("getwidth", Integer.toString(layoutManager.getWidth()));
        recyclerView.setLayoutManager(layoutManager);
    }

}
