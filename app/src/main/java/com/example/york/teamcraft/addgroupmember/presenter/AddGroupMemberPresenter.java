package com.example.york.teamcraft.addgroupmember.presenter;

import com.example.york.teamcraft.addgroupmember.view.AddGroupMemberView;
import com.example.york.teamcraft.addgroupmember.viewmodel.SetRecyclerViewData;

/**
 * Created by York on 2017/12/14.
 */

public class AddGroupMemberPresenter {
    // view
    private AddGroupMemberView addGroupMemberView;
    // view model
    private SetRecyclerViewData setRecyclerViewData;

    public AddGroupMemberPresenter(AddGroupMemberView view) {
        this.addGroupMemberView = view;
        this.setRecyclerViewData = new SetRecyclerViewData(addGroupMemberView);
    }

    public void initRecyclerViewData(String groupId) {
        setRecyclerViewData.getTeamMemberExcludedInGroup(groupId);
    }
}
