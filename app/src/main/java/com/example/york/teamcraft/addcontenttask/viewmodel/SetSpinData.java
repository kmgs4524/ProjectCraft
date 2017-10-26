package com.example.york.teamcraft.addcontenttask.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.addcontenttask.view.AddContentTaskView;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/17.
 */

public class SetSpinData {
    // view
     AddContentTaskView addContentTaskView;
    // model
    private ReadUser readUser;
    private ReadGroupMember readGroupMember;

    public SetSpinData(AddContentTaskView view) {
        addContentTaskView = view;
        readUser = new ReadUser();
        readGroupMember = new ReadGroupMember();
    }

    public void setData() {
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                readGroupMember.getGroupMember(data.getGroupId(), new CallBack<ArrayList<GroupMember>>() {
                    @Override
                    public void update(ArrayList<GroupMember> data) {
                        addContentTaskView.setSpinMenu(data);
                    }
                });
            }
        });
    }
}
