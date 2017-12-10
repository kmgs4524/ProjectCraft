package com.example.york.teamcraft.memberfragment.viewmodel;

import android.util.Log;

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
    private ReadTeamMember readTeamMember;

    public GetMemberData(MemberView view) {
        this.memberView = view;
        this.readTeam = new ReadTeam();
        this.readGroupMember = new ReadGroupMember();
        this.readUser = new ReadUser();
        this.readTeamMember = new ReadTeamMember();
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
                        for (final Group group : groups) {
                            // 再用groupId取得每個群組的所有成員
                            readGroupMember.getGroupMember(group.getId(), new CallBack<ArrayList<GroupMember>>() {
                                @Override
                                public void update(final ArrayList<GroupMember> groupMembers) {
                                    // 迭代出Group的每個member並將其資料用來創造作為item的SectionOrItem object
                                    for (final GroupMember member : groupMembers) {
                                        final SectionOrItem item = SectionOrItem.createItem(member.getName(), "0", "0", member.getPosition());
                                        // 取得每個成員的email, imageUrl
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
                                                if (member.getPosition().equals("組長")) {
                                                    item.setPosition("組長");
                                                } else {
                                                    item.setPosition("組員");
                                                }
                                                sectionOrItems.add(item);

//                                                getUnDistributedMember();
                                                memberView.initRecyclerView(sectionOrItems);
//                                                Log.d("GetMemberData", "sectionOrItems size: " + sectionOrItems.size());
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

    public void getUnDistributedMember() {
        readUser.getCurrentLogInUserData(new CallBack<User>() {
            @Override
            public void update(final User user) {
                readTeamMember.getTeamMember(user.getTeamId(), new CallBack<ArrayList<TeamMember>>() {
                    @Override
                    public void update(final ArrayList<TeamMember> teamMembers) {
                        readTeam.getTeamGroupByDataChange(user.getTeamId(), new CallBack<ArrayList<Group>>() {
                            @Override
                            public void update(ArrayList<Group> groups) {
                                final ArrayList<GroupMember> allGroupMembers = new ArrayList<>(); // 儲存所有的群組成員
//                                Log.d("getUnDistributedMember", "groups size: " + groups.size());
                                for (Group group : groups) {
                                    readGroupMember.getGroupMember(group.getId(), new CallBack<ArrayList<GroupMember>>() {
                                        @Override
                                        public void update(ArrayList<GroupMember> groupMembers) {
                                            Log.d("getUnDistributedMember", "groupMembers size: " + groupMembers.size());
                                            for (GroupMember groupMember : groupMembers) {
                                                allGroupMembers.add(groupMember);
                                            }
                                            Log.d("getUnDistributedMember", "allGroupMembers size: " + allGroupMembers.size());

                                            // 比較團隊成員中和所有已分組成員，將已分組的成員從teamMembers中刪除
                                            for (GroupMember groupMember : allGroupMembers) {
                                                Iterator<TeamMember> iterator = teamMembers.iterator();
                                                while (iterator.hasNext()) {
                                                    TeamMember nextGroupMember = iterator.next();
                                                    if (nextGroupMember.getUserId().equals(groupMember.getUserId())) {
                                                        iterator.remove();
                                                    }
                                                }
                                            }
                                            Log.d("getUnDistributedMember", "after restTeamMembers size: " + teamMembers.size());

                                            final ArrayList<SectionOrItem> sectionOrItems = new ArrayList<>();
                                            for(final TeamMember teamMember: teamMembers) {
                                                final SectionOrItem item = SectionOrItem.createItem(teamMember.getName(), "0", "0", "0");
                                                readUser.getUserDataById(teamMember.getUserId(), new CallBack<User>() {
                                                    @Override
                                                    public void update(User userInTeam) {
                                                        if(teamMembers.indexOf(teamMember) == 0) {
                                                            SectionOrItem section = SectionOrItem.createSection("未分組");
                                                            sectionOrItems.add(section);
                                                        }
                                                        item.setIsItem(true);
                                                        item.setEmail(userInTeam.getEmail());
                                                        item.setImageUrl(userInTeam.getImageUrl());
                                                        item.setPosition("無");

                                                        sectionOrItems.add(item);
                                                        memberView.initRecyclerView(sectionOrItems);
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
        });
    }
}
