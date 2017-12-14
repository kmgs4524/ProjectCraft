package com.example.york.teamcraft.addgroupmember.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.addgroupmember.presenter.AddGroupMemberPresenter;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddGroupMemberActivity extends AppCompatActivity implements AddGroupMemberView{
    @BindView(R.id.recycler_view_team_member)
    RecyclerView recyclerViewMemberList;
    private MemberListAdapter memberListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    // presenter
    private AddGroupMemberPresenter addGroupMemberPresenter;
    // group id
    private String groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_member);
        ButterKnife.bind(this);
        getGroupIdBundle();
        addGroupMemberPresenter = new AddGroupMemberPresenter(this);
        addGroupMemberPresenter.initRecyclerViewData(groupId);
    }

    public void getGroupIdBundle() {
        try {
            Bundle groupIdBundle = getIntent().getExtras();
            groupId = groupIdBundle.getString("GROUP_ID");
            Log.d("getGroupIdBundle", "getGroupIdBundle: " + groupId);
        } catch (NullPointerException e) {
            Log.d("getGroupIdBundle", "getGroupIdBundle: " + e.toString());
        }
    }

    @Override
    public void initRecyclerView(ArrayList<TeamMember> teamMembers) {
        layoutManager = new LinearLayoutManager(this);
        memberListAdapter = new MemberListAdapter(teamMembers, this);
        recyclerViewMemberList.setLayoutManager(layoutManager);
        recyclerViewMemberList.setAdapter(memberListAdapter);
    }

}
