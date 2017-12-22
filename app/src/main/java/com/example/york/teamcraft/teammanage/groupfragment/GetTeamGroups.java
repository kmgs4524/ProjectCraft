package com.example.york.teamcraft.teammanage.groupfragment;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.ReadTeamGroup;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;

/**
 * Created by York on 2017/12/22.
 */

public class GetTeamGroups {
    private ReadUser readUser;
    private ReadTeamGroup readTeamGroup;

    public void getTeamGroups(final CallBack<ArrayList<Group>> callBack) {
        readUser = new ReadUser();
        readTeamGroup = new ReadTeamGroup();

        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                readTeamGroup.getTeamGroups(user.getTeamId(), new CallBack<ArrayList<Group>>() {
                    @Override
                    public void update(ArrayList<Group> groups) {
                        callBack.update(groups);
                    }
                });
            }
        });
    }
}
