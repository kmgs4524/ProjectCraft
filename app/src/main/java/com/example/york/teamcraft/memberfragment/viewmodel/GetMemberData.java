package com.example.york.teamcraft.memberfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.memberfragment.data.SectionOrItem;
import com.example.york.teamcraft.memberfragment.view.MemberView;
import com.example.york.teamcraft.teammanage.creategroup.model.ReadTeamMember;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;

/**
 * Created by York on 2017/12/4.
 */

public class GetMemberData {
    // view
    private MemberView view;
    // model
    private ReadTeam readTeam;
    private ReadGroupMember readGroupMember;
    private ReadUser readUser;

    public GetMemberData(MemberView view) {
        this.view = view;
        this.readGroupMember = new ReadGroupMember();
        this.readUser = new ReadUser();
    }

    public void getData() {
        final ArrayList<SectionOrItem> sectionOrItems = new ArrayList<>();
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User user) {
                readTeam.getTeamGroupByDataChange(user.getTeamId(), new CallBack<ArrayList<Group>>() {
                    @Override
                    public void update(ArrayList<Group> groups) {
                        for(Group group: groups) {
                            SectionOrItem section = SectionOrItem.createSection(group.getName());
                            sectionOrItems.add(section);
                            readGroupMember.getGroupMember(group.getId(), new CallBack<ArrayList<GroupMember>>() {
                                @Override
                                public void update(ArrayList<GroupMember> groupMembers) {
                                    for(GroupMember groupMember: groupMembers) {
//                                        SectionOrItem item = SectionOrItem.createItem(groupMember.getName(), );

                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
