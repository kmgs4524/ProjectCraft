package com.example.york.teamcraft.personalsfragment.viewmodel;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.personalsfragment.view.PersonalsView;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadTeamGroup;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/3.
 */

public class SetProfileData {
    private static final String TAG = "SetProfileData";
    // model
    private ReadUser readUser;
    private ReadTeam readTeam;
    private ReadTeamGroup readTeamGroup;
    private ReadGroupMember readGroupMember;
    // view
    private PersonalsView personalsView;

    public SetProfileData(PersonalsView view) {
        this.personalsView = view;
    }

    public void setProfileData() {
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            //
        } else {
            readUser = new ReadUser();
            readTeamGroup = new ReadTeamGroup();
            readUser.getCurrentLogInUserData(new CallBack<User>() {
                @Override
                public void update(final User user) {
                    personalsView.setCirImgPersonals(user.getImageUrl());
                    readTeam = new ReadTeam();
                    readTeam.getTeamName(user.getTeamId(), new CallBack<String>() {
                        @Override
                        public void update(final String teamName) {
                            Log.d(TAG, "groupId size: " + user.getGroupIds().size());
                            if(!user.getGroupIds().equals("0")) {
                                Log.d(TAG, "has group ");
                                // 若使用者已屬於某個群組下
                                readTeamGroup.getTeamGroups(user.getTeamId(), new CallBack<ArrayList<Group>>() {
                                    @Override
                                    public void update(final ArrayList<Group> groups) {
                                        String groupNames = "";
                                        for(Group group: groups) {
                                            Log.d(TAG, "group: " + group);
                                            for(String groupId: user.getGroupIds()) {
                                                Log.d(TAG, "groupId: " + group.getId() + "user groupId: " + groupId);
                                                if (group.getId().equals(groupId)) {
                                                    groupNames += group.getName() + "  ";
                                                }
                                            }
                                        }
                                        final String finalGroupName = groupNames;
                                        personalsView.setProfile(user.getName(), user.getEmail(), teamName, finalGroupName);

                                    }
                                });
                            } else {
                                // 使用者尚未屬於任何群組
                                personalsView.setProfile(user.getName(), user.getEmail(), teamName, "未分組");
                            }
                        }
                    });
                }
            });
        }
    }

}
