package com.example.york.teamcraft.teammanage.groupfragment.presenter;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.groupfragment.view.GroupManageFragment;
import com.example.york.teamcraft.teammanage.groupfragment.view.GroupManageView;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeam;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/10.
 */

public class GroupManagePresenterImpl implements GroupManagePresenter{
    // view
    private GroupManageView groupManageFragment;

    // ReadTeam Model
    private ReadTeam readTeam;

    public GroupManagePresenterImpl(GroupManageView view) {
        this.groupManageFragment = view;
        this.readTeam = new ReadTeam();
    }

    public void initGridViewData() {
        readTeam.getTeamGroup(new CallBack<ArrayList<Group>>() {
            @Override
            public void update(ArrayList<Group> data) {
                groupManageFragment.initGridView(data);
            }
        });
    }

}
