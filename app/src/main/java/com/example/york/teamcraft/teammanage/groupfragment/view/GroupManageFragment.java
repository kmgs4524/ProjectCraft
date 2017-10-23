package com.example.york.teamcraft.teammanage.groupfragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenter;
import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenterImpl;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupActivity;
import com.example.york.teamcraft.teammanage.groupinformation.view.GroupInfoActivity;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.mygroup.view.MyGroupActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017/7/4.
 */
//群組管理分頁
public class GroupManageFragment extends Fragment implements GroupManageView{
    private static final String TAG = "GroupManageFragment";

    private GroupManagePresenter groupManagePresenter;

    // 介面元件
    private View cardMyGroup;
    private GridView gridGroup;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.team_fragment_manage_group, container, false);
        // find view
        cardMyGroup = view.findViewById(R.id.card_my_group); // 使用者的群組
        gridGroup = (GridView) view.findViewById(R.id.gridView); // 其他小組
        // init Presenter
        groupManagePresenter = new GroupManagePresenterImpl(this);
        groupManagePresenter.initMyGroupData();
        groupManagePresenter.initGridViewData();
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

    @Override
    public void initMyGroup(final String groupId) {
        cardMyGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", groupId);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), MyGroupActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initGridView(final ArrayList<Group> list) {
        gridGroup.setAdapter(new GridItemAdapter(getActivity(), list));

        gridGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("id", list.get(position).getId());
                bundle.putString("name", list.get(position).getName());
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(), GroupInfoActivity.class);
                startActivity(intent);  // 進入CreateGroupActivity
            }
        });
    }

}
