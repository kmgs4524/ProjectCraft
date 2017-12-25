package com.example.york.teamcraft.teammanage.jointeam.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.teammanage.jointeam.model.WriteTeamMember;
import com.example.york.teamcraft.teammanage.jointeam.view.JoinTeamView;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.example.york.teamcraft.teammanage.model.WriteUser;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/23.
 */

public class JoinExistTeam {
    // view
    private JoinTeamView joinTeamView;
    // model
    private ReadTeam readTeam;
    private ReadUser readUser;
    private WriteUser writeUser;
    private WriteTeamMember writeTeamMember;

    public JoinExistTeam(JoinTeamView view) {
        this.joinTeamView = view;
    }

    public void checkTeam(final String searchId) {
        final ArrayList<String> initGroupIds = new ArrayList<>();   // 為新加入團隊的成員建立groupId的初始值
        initGroupIds.add("0");
        this.readTeam = new ReadTeam();
        readTeam.checkTeamExist(searchId, new CallBackTwoArgs<Boolean, String>() {
            @Override
            public void update(Boolean isExist, final String teamId) {
                if(isExist) {
                    readUser = new ReadUser();
                    readUser.getUserId(new CallBack<String>() {
                        @Override
                        public void update(final String userId) {
                            writeUser = new WriteUser();
                            writeUser.updateUserTeam(userId, teamId); // 將teamId寫入user child
                            writeUser.updateUserGroup(userId, initGroupIds);
                            readUser.getCurrentLogInUserData(new CallBack<User>() {
                                @Override
                                public void update(User user) {
                                    writeTeamMember = new WriteTeamMember();
                                    writeTeamMember.updateTeamMember(teamId, userId, user.getName()); // 將user id, user name 寫入teamsMember child node
                                    joinTeamView.startTeamMainActivity();   // 進入團隊管理的MainActivity
                                }
                            });
                        }
                    });

                } else {
                    joinTeamView.showTextMessage();
                }
            }
        });
    }

}
