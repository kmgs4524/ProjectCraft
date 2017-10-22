package com.example.york.teamcraft.teammanage.jointeam.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.jointeam.model.WriteTeamMember;
import com.example.york.teamcraft.teammanage.jointeam.view.JoinTeamView;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.example.york.teamcraft.teammanage.model.WriteTeam;
import com.example.york.teamcraft.teammanage.model.WriteUser;
import com.google.android.gms.tasks.OnSuccessListener;

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

    public void checkTeam(final String teamId) {
        this.readTeam = new ReadTeam();
        readTeam.checkTeamExist(teamId, new CallBack<Boolean>() {
            @Override
            public void update(Boolean data) {
                if(data) {
                    readUser = new ReadUser();
                    readUser.getUserId(new CallBack<String>() {
                        @Override
                        public void update(final String data) {
                            writeUser = new WriteUser();
                            writeUser.updateUserTeam(data, teamId); // 將teamId寫入user child

                            readUser.getUserData().addOnSuccessListener(new OnSuccessListener<User>() {
                                @Override
                                public void onSuccess(User user) {
                                    writeTeamMember = new WriteTeamMember();
                                    writeTeamMember.updateTeamMember(teamId, data, user.getName()); // 將user id, user name 寫入teamsMember child node
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
