package com.example.york.teamcraft.teammanage.creategroup.viewmodel;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.creategroup.model.ReadTeamMember;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupView;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/14.
 */

public class SetSpinnerData {
    // view
    private CreateGroupView createGroupView;
    // model
    private ReadUser readUser;
    private ReadTeamMember readTeamMember;

    public SetSpinnerData(CreateGroupView view) {
        this.createGroupView = view;
    }

    public void setData() {
        readUser = new ReadUser();
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                Log.d("member", data.getTeamId());
                readTeamMember = new ReadTeamMember();
                readTeamMember.getMember(data.getTeamId(), new CallBack<ArrayList<GroupMember>>() {
                    @Override
                    public void update(ArrayList<GroupMember> data) {
                        Log.d("member data", Integer.toString(data.size()));
                        createGroupView.setSpinMenu(data);
                    }
                });
            }
        });
    }
}
