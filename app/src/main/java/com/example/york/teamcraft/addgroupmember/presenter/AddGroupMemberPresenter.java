package com.example.york.teamcraft.addgroupmember.presenter;

import com.example.york.teamcraft.addgroupmember.view.AddGroupMemberView;
import com.example.york.teamcraft.addgroupmember.viewmodel.AddGroupMember;
import com.example.york.teamcraft.addgroupmember.viewmodel.SetRecyclerViewData;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;

/**
 * Created by York on 2017/12/14.
 */

public class AddGroupMemberPresenter {
    // view
    private AddGroupMemberView addGroupMemberView;
    // view model
    private SetRecyclerViewData setRecyclerViewData;
    private AddGroupMember addGroupMember;

    public AddGroupMemberPresenter(AddGroupMemberView view) {
        this.addGroupMemberView = view;
        this.setRecyclerViewData = new SetRecyclerViewData(addGroupMemberView);
        this.addGroupMember = new AddGroupMember();
    }

    public void initRecyclerViewData(String groupId) {
        setRecyclerViewData.getTeamMemberExcludedInGroup(groupId);
    }

    public void addGroupMembers(String groupId, TeamMember teamMember) {
        addGroupMember.addMember(groupId, teamMember);
    }
}
