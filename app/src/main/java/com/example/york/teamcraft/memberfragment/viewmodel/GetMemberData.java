package com.example.york.teamcraft.memberfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.creategroup.model.ReadTeamMember;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;

/**
 * Created by York on 2017/12/4.
 */

public class GetMemberData {
    private ReadTeamMember readTeamMember;
    private ReadUser readUser;

    public GetMemberData() {
        readTeamMember = new ReadTeamMember();
        readUser = new ReadUser();
    }

    public void getData() {
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User user) {
                readTeamMember.getTeamMember(user.getTeamId(), new CallBack<ArrayList<TeamMember>>() {
                    @Override
                    public void update(ArrayList<TeamMember> data) {

                    }
                });
            }
        });
    }
}
