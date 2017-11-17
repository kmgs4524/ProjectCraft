package com.example.york.teamcraft.personalsfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.personalsfragment.view.PersonalsView;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/11/3.
 */

public class SetProfileData {
    // model
    private ReadUser readUser;
    private ReadTeam readTeam;
    private ReadGroupMember readGroupMember;
    // view
    private PersonalsView personalsView;

    public SetProfileData(PersonalsView view) {
        this.personalsView = view;
    }

    public void setData() {
        readUser = new ReadUser();
        readTeam = new ReadTeam();
        readGroupMember = new ReadGroupMember();
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {

        } else {
            readUser.getUserData(new CallBack<User>() {
                @Override
                public void update(final User user) {
                    personalsView.setCirImgPersonals(user.getImageUrl());
                    readTeam.getTeamName(user.getTeamId(), new CallBack<String>() {
                        @Override
                        public void update(final String teamName) {
                            readTeam.getTeamGroup(new CallBack<ArrayList<Group>>() {
                                @Override
                                public void update(final ArrayList<Group> groupList) {
                                    final String groupName = "None";
                                    for(Group group: groupList) {
                                        if(group.getId().equals(user.getGroupId())) {
                                            groupName = group.getName();
                                        }
                                    }
                                    readGroupMember.getGroupMember(user.getGroupId(), new CallBack<ArrayList<GroupMember>>() {
                                        @Override
                                        public void update(ArrayList<GroupMember> memList) {
                                            Iterator<GroupMember> iterator = memList.iterator();
                                            while(iterator.hasNext()) {
                                                // 若user存在於GroupMember List中
                                                if(iterator.next().getName().equals(user.getName())) {
                                                    String position = iterator.next().getPosition();
                                                    personalsView.setProfile(user.getName(), user.getEmail(), teamName, groupName, position);
                                                }
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });
        }
    }

}
