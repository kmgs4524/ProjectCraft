package com.example.york.teamcraft.teammanage.groupfragment.presenter;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.groupfragment.view.GroupManageView;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
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
    private ReadTeam readTeam;
    // view model
    private SetAdapterData setAdapterData;

    public GroupManagePresenterImpl(GroupManageView view) {
        this.groupManageFragment = view;
        this.readTeam = new ReadTeam();
        this.setAdapterData = new SetAdapterData();
    }

    @Override
    public void initRecyclerProgressData() {
        setAdapterData.setData(new CallBack<ArrayList<GroupProgress>>() {
            @Override
            public void update(ArrayList<GroupProgress> data) {
                groupManageFragment.initRecyclerProgress(data);
            }
        });
    }

    @Override
    public void initRecyclerGroupData() {
        readTeam.getTeamGroup(new CallBack<ArrayList<Group>>() {
            @Override
            public void update(ArrayList<Group> data) {
                groupManageFragment.initRecyclerGroup(data);
            }
        });
    }

}
