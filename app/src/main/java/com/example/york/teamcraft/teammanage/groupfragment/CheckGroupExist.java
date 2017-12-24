package com.example.york.teamcraft.teammanage.groupfragment;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.groupfragment.view.GroupManageView;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/12/22.
 */

public class CheckGroupExist {
    // view
    private GroupManageView groupManageView;
    // model
    private ReadUser readUser;
    private ReadTeam readTeam;

    public void checkTeamGroupExist(final CallBack<Boolean> callBack) {
        readUser = new ReadUser();
        readTeam = new ReadTeam();
        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                readTeam.checkTeamGroupExist(user.getTeamId(), new CallBack<Boolean>() {
                    @Override
                    public void update(Boolean isExisting) {
                        callBack.update(isExisting);
                    }
                });
            }
        });
    }
}
