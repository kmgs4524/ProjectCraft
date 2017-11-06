package com.example.york.teamcraft.teammanage.groupfragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.finance.view.FinanceFragment;
import com.example.york.teamcraft.schedulefragment.ScheduleFragment;
import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenter;
import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenterImpl;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.taskprogress.view.TaskProgressFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2017/7/4.
 */
// 團隊分頁
public class GroupManageFragment extends Fragment implements GroupManageView{
    private static final String TAG = "GroupManageFragment";

    private GroupManagePresenter groupManagePresenter;
    // view
    @BindView(R.id.recycler_view_group) RecyclerView recyclerView;
    @BindView(R.id.fab_schedule)
    com.getbase.floatingactionbutton.FloatingActionButton fabSched;
    @BindView(R.id.fab_finance)
    com.getbase.floatingactionbutton.FloatingActionButton fabFinan;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.team_fragment_manage_group, container, false);
        ButterKnife.bind(this, view);
        // init Presenter
        groupManagePresenter = new GroupManagePresenterImpl(this);
        groupManagePresenter.addTaskProgress(); // add task progress fragment
        groupManagePresenter.initRecyclerViewData();
        initFab();
        return view;
    }

    @Override
    public void initRecyclerView(final ArrayList<Group> list) {
        recyclerView.setAdapter(new GroupItemAdapter(getActivity(), list));
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
    }

    @Override
    public void addTaskProgressFrag() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.team_groupmanage_fragment, TaskProgressFragment.newInstance());
        transaction.commit();

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.BELOW, R.id.linearLayout_task_progress);
        recyclerView.setLayoutParams(params);
    }

    //設定FloatingActionButton的listener
    private void initFab(){
        fabSched.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content, ScheduleFragment.newInstance());
                transaction.commit();
            }
        });
    }

    @OnClick(R.id.fab_finance)
    public void startFinance() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content, FinanceFragment.newInstance());
        transaction.commit();
    }

    public static GroupManageFragment newInstance() {
        Bundle args = new Bundle();

        GroupManageFragment fragment = new GroupManageFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
