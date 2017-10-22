package com.example.york.teamcraft.teammanage.jointeam.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.jointeam.view.JoinTeamView;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.WriteUser;

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
                        public void update(String data) {
                            writeUser = new WriteUser();
                            writeUser.updateUserTeam(data, teamId);
                            joinTeamView.startTeamMainActivity();
                        }
                    });

                } else {
                    joinTeamView.showTextMessage();
                }
            }
        });
    }

}
