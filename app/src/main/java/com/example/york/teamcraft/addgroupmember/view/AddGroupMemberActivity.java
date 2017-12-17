package com.example.york.teamcraft.addgroupmember.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.addgroupmember.presenter.AddGroupMemberPresenter;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddGroupMemberActivity extends AppCompatActivity implements AddGroupMemberView{
    private static String TAG = "AddGroupMemberActivity";

    @BindView(R.id.recycler_view_team_member)
    RecyclerView recyclerViewMemberList;
    private MemberListAdapter memberListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    // presenter
    private AddGroupMemberPresenter addGroupMemberPresenter;
    // group id
    private String groupId;
    // 儲存從FireBase讀來的群組成員
    private ArrayList<TeamMember> teamMembers;

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
        this.teamMembers = teamMembers;
    }

    @OnClick(R.id.btn_confirm_add_group_member)
    public void addGroupMember() {
        for(int i = 0; i < teamMembers.size(); i++) {
            MemberListAdapter.MemberViewHolder itemViewHolder = (MemberListAdapter.MemberViewHolder) recyclerViewMemberList.findViewHolderForAdapterPosition(i);
            if(itemViewHolder.getCheckBoxStatus()) {
                Log.d(TAG, "addGroupMember: " + itemViewHolder.getTxtName());
                addGroupMemberPresenter.addGroupMembers(groupId, teamMembers.get(i));
            }
        }
    }

}
