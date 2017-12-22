package com.example.york.teamcraft.teammanage.groupfragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupActivity;
import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenter;
import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenterImpl;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupProgress;
import com.example.york.teamcraft.teammanage.taskprogress.view.recyclerview.TaskProgressAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/7/4.
 */
// 群組分頁，包含群組工作進度、群組列表
public class GroupManageFragment extends Fragment implements GroupManageView{
    private static final String TAG = "GroupManageFragment";

    private GroupManagePresenter groupManagePresenter;
    // empty view
    @BindView(R.id.img_group_manage_empty_state)
    ImageView imgEmptState;
    @BindView(R.id.divider_group_manage_empty_state)
    View dividerEmptyState;
    @BindView(R.id.txt_group_manage_empty_state)
    TextView txtEmptyState;
    @BindView(R.id.btn_group_manage_empty_state)
    Button btnCreateGroupEmptyState;
    // view
    @BindView(R.id.frame_fragment)
    FrameLayout frameFragment;
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
        groupManagePresenter.checkTeamGroupExist();
        groupManagePresenter.initRecyclerProgressData();
        groupManagePresenter.initRecyclerGroupData();
        return view;
    }

    @Override
    public void showActionLayout() {
        frameFragment.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyState() {
        imgEmptState.setVisibility(View.VISIBLE);
        dividerEmptyState.setVisibility(View.VISIBLE);
        txtEmptyState.setVisibility(View.VISIBLE);
        btnCreateGroupEmptyState.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyState() {
        imgEmptState.setVisibility(View.GONE);
        dividerEmptyState.setVisibility(View.GONE);
        txtEmptyState.setVisibility(View.GONE);
        btnCreateGroupEmptyState.setVisibility(View.GONE);
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

    @OnClick(R.id.btn_group_manage_empty_state)
    public void createGroup() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CreateGroupActivity.class);
        startActivity(intent);
    }

    public static GroupManageFragment newInstance() {
        Bundle args = new Bundle();

        GroupManageFragment fragment = new GroupManageFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
