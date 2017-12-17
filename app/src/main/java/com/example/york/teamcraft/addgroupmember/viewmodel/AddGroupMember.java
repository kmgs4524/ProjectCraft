package com.example.york.teamcraft.addgroupmember.viewmodel;

import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.creategroup.model.WriteGroupMember;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;

import java.util.ArrayList;

/**
 * Created by York on 2017/12/15.
 */

public class AddGroupMember {
    private ArrayList<GroupMember> groupMembers;

    private WriteGroupMember writeGroupMember;

    public AddGroupMember() {
        writeGroupMember = new WriteGroupMember();
        groupMembers = new ArrayList<>();
    }

    public void addMember(String groupId, TeamMember teamMember) {
        GroupMember groupMember = new GroupMember(teamMember.getName(), teamMember.getUserId(), "組員", null);
        groupMembers.add(groupMember);
        writeGroupMember.pushData(groupMembers, groupId);
    }
}
