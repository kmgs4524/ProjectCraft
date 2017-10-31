package com.example.york.teamcraft.teammanage.groupfragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenter;
import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenterImpl;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupActivity;
import com.example.york.teamcraft.teammanage.groupinformation.view.GroupInfoActivity;
import com.example.york.teamcraft.teammanage.model.Group;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/7/4.
 */
//群組管理分頁
public class GroupManageFragment extends Fragment implements GroupManageView{
    private static final String TAG = "GroupManageFragment";

    private GroupManagePresenter groupManagePresenter;

    // 介面元件
    @BindView(R.id.recycler_view_group) RecyclerView recyclerView;
//    private GridView gridGroup;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.team_fragment_manage_group, container, false);
        ButterKnife.bind(this, view);

        // init Presenter
        groupManagePresenter = new GroupManagePresenterImpl(this);
//        groupManagePresenter.initMyGroupData();
        groupManagePresenter.initRecyclerViewData();
        initFab(view);
        return view;
    }

    //初始化Floating Button
    private void initFab(View view){
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(getActivity(), "FAB Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getContext(), CreateGroupActivity.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    public void initMyGroup(final String groupId) {
//        cardMyGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("id", groupId);
//                Intent intent = new Intent();
//                intent.putExtras(bundle);
//                intent.setClass(getActivity(), MyGroupActivity.class);
//                startActivity(intent);
//            }
//        });
//    }

    @Override
    public void initRecyclerView(final ArrayList<Group> list) {
        recyclerView.setAdapter(new GroupItemAdapter(getActivity(), list));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public static GroupManageFragment newInstance() {
        Bundle args = new Bundle();

        GroupManageFragment fragment = new GroupManageFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
