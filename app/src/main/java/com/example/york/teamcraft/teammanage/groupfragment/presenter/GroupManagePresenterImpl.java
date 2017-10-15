package com.example.york.teamcraft.teammanage.groupfragment.presenter;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.groupfragment.view.GroupManageFragment;
import com.example.york.teamcraft.teammanage.groupfragment.view.GroupManageView;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/10.
 */

public class GroupManagePresenterImpl implements GroupManagePresenter{
    // view
    private GroupManageView groupManageFragment;

    // ReadTeam Model
    private ReadUser readUser;
    private ReadTeam readTeam;

    public GroupManagePresenterImpl(GroupManageView view) {
        this.groupManageFragment = view;
        this.readUser = new ReadUser();
        this.readTeam = new ReadTeam();
    }

    public void initMyGroupData() {
        Task<User> task = readUser.getUserData();
        task.addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                groupManageFragment.initMyGroup(user.getGroupId());
            }
        });
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
