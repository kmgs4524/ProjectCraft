package com.example.york.teamcraft.memberfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.memberfragment.view.MemberView;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/12/12.
 */

public class GetSearchId {
    private ReadTeam readTeam;
    private ReadUser readUser;

    public void getSearchId(final MemberView memberView) {
        readTeam = new ReadTeam();
        readUser = new ReadUser();
        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                readTeam.getSearchId(user.getTeamId(), new CallBack<String>() {
                    @Override
                    public void update(String searchId) {
                        memberView.initTxtSearchId(searchId);
                    }
                });
            }
        });
    }
}
