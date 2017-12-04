package com.example.york.teamcraft.memberfragment.viewmodel;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.memberfragment.data.SectionOrItem;
import com.example.york.teamcraft.memberfragment.view.MemberView;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/12/4.
 */

public class GetMemberData {
    // view
    private MemberView memberView;
    // model
    private ReadTeam readTeam;
    private ReadGroupMember readGroupMember;
    private ReadUser readUser;

    public GetMemberData(MemberView view) {
        this.memberView = view;
        this.readTeam = new ReadTeam();
        this.readGroupMember = new ReadGroupMember();
        this.readUser = new ReadUser();
    }

    public void getData() {
        readUser.getCurrentLogInUserData(new CallBack<User>() {
            @Override
            public void update(User user) {
                // 先取得團隊的所有群組
                readTeam.getTeamGroupByDataChange(user.getTeamId(), new CallBack<ArrayList<Group>>() {
                    @Override
                    public void update(ArrayList<Group> groups) {
                        final ArrayList<SectionOrItem> sectionOrItems = new ArrayList<>();  // 傳入MemberListAdapter的ArrayList<SectionOrItem>
                        for(final Group group: groups) {
                            // 再用groupId取得每個群組的所有成員
                            readGroupMember.getGroupMember(group.getId(), new CallBack<ArrayList<GroupMember>>() {
                                @Override
                                public void update(final ArrayList<GroupMember> groupMembers) {
                                    // 迭代出Group的每個member並將其資料用來創造作為item的SectionOrItem object
                                    for(final GroupMember member: groupMembers) {
                                        final SectionOrItem item = SectionOrItem.createItem(member.getName(), "0", "0", member.getPosition());
                                        readUser.getUserDataById(member.getUserId(), new CallBack<User>() {
                                            @Override
                                            public void update(User userInGroup) {
                                                // 若迭代出第一個group member，則先放入作為群組名稱的section
                                                if (groupMembers.indexOf(member) == 0) {
                                                    // 將迭代出團隊的每個group其名稱用來創造作為section的SectionOrItem object
                                                    SectionOrItem section = SectionOrItem.createSection(group.getName());
                                                    sectionOrItems.add(section);
                                                }
                                                item.setEmail(userInGroup.getEmail());
                                                item.setImageUrl(userInGroup.getImageUrl());
                                                if(member.getPosition().equals("組長")) {
                                                    item.setPosition("組長");
                                                } else {
                                                    item.setPosition("組員");
                                                }
                                                sectionOrItems.add(item);
                                                memberView.initRecyclerView(sectionOrItems);
                                                Log.d("GetMemberData", "sectionOrItems size: " + sectionOrItems.size());
                                            }
                                        });
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
