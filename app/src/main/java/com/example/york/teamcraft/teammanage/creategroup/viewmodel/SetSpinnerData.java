package com.example.york.teamcraft.teammanage.creategroup.viewmodel;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.creategroup.model.ReadTeamMember;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupView;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

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
                readTeamMember.getGroupMember(data.getTeamId(), new CallBack<ArrayList<GroupMember>>() {
                    @Override
                    public void update(ArrayList<GroupMember> memList) {
                        Log.d("member data", Integer.toString(memList.size()));
                        for(final GroupMember member: memList) {
                            Log.d("setData", "update: " + "name:" + member.getName() + " url: " + member.getPhotoUrl());
                            readUser.getUserImageUrl(member.getUserId(), new CallBack<String>() {
                                @Override
                                public void update(String imageUrl) {
                                    member.setPhotoUrl(imageUrl);   // 設定photoUrl
                                    Log.d("setData", "update: " + "name:" + member.getName() + " url: " + member.getPhotoUrl());
                                }
                            });
                        }
                        createGroupView.setSpinMenu(memList);
                    }
                });
            }
        });
    }
}
