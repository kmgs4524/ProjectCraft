package com.example.york.teamcraft.teammanage.groupfragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenter;
import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenterImpl;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupProgress;
import com.example.york.teamcraft.teammanage.taskprogress.view.recyclerview.TaskProgressAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/7/4.
 */
// 團隊分頁
public class GroupManageFragment extends Fragment implements GroupManageView{
    private static final String TAG = "GroupManageFragment";

    private GroupManagePresenter groupManagePresenter;
    // view
    @BindView(R.id.recycler_view_group) RecyclerView recyclerGroup; // 群組
    @BindView(R.id.recycler_view_group_progress) RecyclerView recyclerProgress; // 各組進度
    private LinearLayoutManager progressLayoutManager;
    private LinearLayoutManager groupLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.team_fragment_manage_group, container, false);
        ButterKnife.bind(this, view);
        // init Presenter
        groupManagePresenter = new GroupManagePresenterImpl(this);
        groupManagePresenter.addTaskProgress(); // add task progress fragment
        groupManagePresenter.initRecyclerProgressData();
        groupManagePresenter.initRecyclerGroupData();
        return view;
    }

    @Override
    public void initRecyclerProgress(ArrayList<GroupProgress> progList) {
        progressLayoutManager = new LinearLayoutManager(getContext());
        recyclerProgress.setAdapter(new TaskProgressAdapter(progList));
        recyclerProgress.setLayoutManager(progressLayoutManager);
    }

    @Override
    public void initRecyclerGroup(final ArrayList<Group> list) {
        recyclerGroup.setAdapter(new GroupItemAdapter(getActivity(), list));
        groupLayoutManager = new LinearLayoutManager(getContext());
        recyclerGroup.setLayoutManager(groupLayoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerGroup.getContext(), groupLayoutManager.getOrientation());
        recyclerGroup.addItemDecoration(divider);
    }

    @Override
    public void addTaskProgressFrag() {
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.add(R.id.team_groupmanage_fragment, ActionFragment.newInstance());  // 包含新增成員, 建立群組, 工作表選項的fragment
//        transaction.add(R.id.team_groupmanage_fragment, TaskProgressFragment.newInstance());    // 群組進度的fragment
//        transaction.commit();
//
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        params.addRule(RelativeLayout.BELOW, R.id.linearLayout_task_progress);
//
//        recyclerGroup.setLayoutParams(params);
    }

    public static GroupManageFragment newInstance() {
        Bundle args = new Bundle();

        GroupManageFragment fragment = new GroupManageFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
