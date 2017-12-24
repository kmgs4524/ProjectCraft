package com.example.york.teamcraft.teammanage.groupfragment.presenter;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.groupfragment.CheckGroupExist;
import com.example.york.teamcraft.teammanage.groupfragment.GetTeamGroups;
import com.example.york.teamcraft.teammanage.groupfragment.view.GroupManageView;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeamGroup;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupProgress;
import com.example.york.teamcraft.teammanage.taskprogress.viewmodel.SetAdapterData;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/10.
 */

public class GroupManagePresenterImpl implements GroupManagePresenter{
    // view
    private GroupManageView groupManageFragment;
    // ReadTeam Model
    private ReadTeamGroup readTeamGroup;
    // view model
    private SetAdapterData setAdapterData;
    private CheckGroupExist checkGroupExist;
    private GetTeamGroups getTeamGroups;

    public GroupManagePresenterImpl(GroupManageView view) {
        this.groupManageFragment = view;
        this.readTeamGroup = new ReadTeamGroup();
        this.setAdapterData = new SetAdapterData();
        this.checkGroupExist = new CheckGroupExist();
        this.getTeamGroups = new GetTeamGroups();
    }

    // 設置群組進度的資料
    @Override
    public void initRecyclerProgressData() {
        setAdapterData.setData(new CallBack<ArrayList<GroupProgress>>() {
            @Override
            public void update(ArrayList<GroupProgress> data) {
                groupManageFragment.initRecyclerProgress(data);
            }
        });
    }

    // 設置群組列表的資料
    @Override
    public void initRecyclerGroupData() {
        getTeamGroups.getTeamGroups(new CallBack<ArrayList<Group>>() {
            @Override
            public void update(ArrayList<Group> groups) {
                groupManageFragment.initRecyclerGroup(groups);
            }
        });
    }

    public void checkTeamGroupExist() {
        checkGroupExist.checkTeamGroupExist(new CallBack<Boolean>() {
            @Override
            public void update(Boolean isExisting) {
                if (!isExisting) {
                    groupManageFragment.showEmptyState();
                } else {
                    groupManageFragment.hideEmptyState();
                    groupManageFragment.showActionLayout();
                }
            }
        });
    }

}
