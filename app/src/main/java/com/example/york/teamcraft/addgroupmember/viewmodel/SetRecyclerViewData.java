package com.example.york.teamcraft.addgroupmember.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.addgroupmember.view.AddGroupMemberView;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.creategroup.model.ReadTeamMember;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/12/14.
 */

public class SetRecyclerViewData {
    private ReadUser readUser;
    private ReadTeamMember readTeamMember;
    private ReadGroupMember readGroupMember;
    private AddGroupMemberView addGroupMemberView;

    public SetRecyclerViewData(AddGroupMemberView view) {
        this.addGroupMemberView = view;
    }

    public void getTeamMemberExcludedInGroup(final String groupId) {
        readUser = new ReadUser();
        readTeamMember = new ReadTeamMember();
        readGroupMember = new ReadGroupMember();

        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                readTeamMember.getTeamMember(user.getTeamId(), new CallBack<ArrayList<TeamMember>>() {
                    @Override
                    public void update(final ArrayList<TeamMember> teamMembers) {
                        readGroupMember.getGroupMember(groupId, new CallBack<ArrayList<GroupMember>>() {
                            @Override
                            public void update(ArrayList<GroupMember> groupMembers) {
                                Iterator<TeamMember> iterator = teamMembers.iterator();
                                while (iterator.hasNext()) {
                                    final TeamMember nextMember = iterator.next();
                                    for(GroupMember groupMember: groupMembers) {
                                        if(nextMember.getUserId().equals(groupMember.getUserId())) {
                                            iterator.remove();
                                        } else {
                                            readUser.getUserImageUrl(nextMember.getUserId(), new CallBack<String>() {
                                                @Override
                                                public void update(String imageUrl) {
                                                    nextMember.setPhotoUrl(imageUrl);
                                                    addGroupMemberView.initRecyclerView(teamMembers);
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}
