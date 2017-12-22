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
import com.example.york.teamcraft.teammanage.model.ReadTeamGroup;
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
    private ReadTeamGroup readTeamGroup;

    private ArrayList<GroupMember> allGroupMembers = new ArrayList<>(); // 儲存各個小組的所有群組成員，用在getUnDistributedMember()中與teamMembers做比較

    public GetMemberData(MemberView view) {
        this.memberView = view;
        this.readTeam = new ReadTeam();
        this.readGroupMember = new ReadGroupMember();
        this.readUser = new ReadUser();
        this.readTeamMember = new ReadTeamMember();
        this.readTeamGroup = new ReadTeamGroup();
    }

    public void getData() {
        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(final User user) {
                readTeamMember.getTeamMember(user.getTeamId(), new CallBack<ArrayList<TeamMember>>() {
                    @Override
                    public void update(final ArrayList<TeamMember> teamMembers) {
                        // 檢查teamGroups下是否有該團隊的資料
                        readTeam.checkTeamGroupExist(user.getTeamId(), new CallBack<Boolean>() {
                            @Override
                            public void update(Boolean isExisting) {
                                if(isExisting) {
                                    // 先取得團隊的所有群組
                                    readTeamGroup.getTeamGroups(user.getTeamId(), new CallBack<ArrayList<Group>>() {
                                        @Override
                                        public void update(final ArrayList<Group> groups) {
                                            // 取得未分組成員的ArrayList<SectionOrItem>
                                            getUnDistributedMember(teamMembers, groups, new CallBack<ArrayList<SectionOrItem>>() {
                                                @Override
                                                public void update(final ArrayList<SectionOrItem> undistributedSection) {
                                                    final ArrayList<SectionOrItem> sectionOrItems = new ArrayList<>();  // 傳入MemberListAdapter的ArrayList<SectionOrItem>
                                                    Log.d("getData", "undistribute size: " + undistributedSection.size());
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
                                                                            // 若已迭代到最後一個群組的最後一位成員時，則將未分組成員的secOrItems加到目前已分組的secOrItems ArrayList
                                                                            if ((groups.indexOf(group) == groups.size() - 1) && (groupMembers.indexOf(member) == groupMembers.size() - 1)) {
                                                                                sectionOrItems.addAll(undistributedSection);
                                                                            }
                                                                            Log.d("getData", "sectionOrItems: " + sectionOrItems.size());
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
                                } else {
                                    // 若該團隊尚未有群組，則顯示未分組的團隊成員
                                    getUnDistributedMember(teamMembers, new ArrayList<Group>(), new CallBack<ArrayList<SectionOrItem>>() {
                                        @Override
                                        public void update(ArrayList<SectionOrItem> sectionOrItems) {
                                            memberView.initRecyclerView(sectionOrItems);
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

    public void getUnDistributedMember(final ArrayList<TeamMember> teamMembers, ArrayList<Group> groups, final CallBack<ArrayList<SectionOrItem>> callBack) {
        if(groups.size() != 0) {
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
                        if(teamMembers.isEmpty()) {
                            // 若沒有未分組的成員就傳空的SectionOrItem ArrayList
                            callBack.update(new ArrayList<SectionOrItem>());
                        } else {
                            // 將未分組的TeamMembers轉換成SectionOrItems
                            translateTeamMembersToSection(teamMembers, new CallBack<ArrayList<SectionOrItem>>() {
                                @Override
                                public void update(ArrayList<SectionOrItem> sectionOrItems) {
                                    callBack.update(sectionOrItems);
                                }
                            });
                        }
                    }
                });
            }
        } else {
            translateTeamMembersToSection(teamMembers, new CallBack<ArrayList<SectionOrItem>>() {
                @Override
                public void update(ArrayList<SectionOrItem> sectionOrItems) {
                    callBack.update(sectionOrItems);
                }
            });
        }
    }

    // 將TeamMembers轉換成SectionOrItems
    public void translateTeamMembersToSection(final ArrayList<TeamMember> teamMembers, final CallBack<ArrayList<SectionOrItem>> callBack) {
        final ArrayList<SectionOrItem> sectionOrItems = new ArrayList<>();
        for (final TeamMember teamMember : teamMembers) {
            final SectionOrItem item = SectionOrItem.createItem(teamMember.getName(), "0", "0", "0");
            readUser.getUserDataById(teamMember.getUserId(), new CallBack<User>() {
                @Override
                public void update(User userInTeam) {
                    if (teamMembers.indexOf(teamMember) == 0) {
                        SectionOrItem section = SectionOrItem.createSection("未分組");
                        sectionOrItems.add(section);
                    }
                    item.setIsItem(true);
                    item.setEmail(userInTeam.getEmail());
                    item.setImageUrl(userInTeam.getImageUrl());
                    item.setPosition("無");

                    sectionOrItems.add(item);
                    callBack.update(sectionOrItems);
                }
            });
        }
    }
}
