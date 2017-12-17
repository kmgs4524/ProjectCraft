package com.example.york.teamcraft.memberfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.memberfragment.view.MemberView;
import com.example.york.teamcraft.teammanage.creategroup.model.ReadTeamMember;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/12/17.
 */

public class CheckMemberExist {
    // model
    private ReadUser readUser;
    private ReadTeamMember readTeamMember;
    // view
    private MemberView memberView;

    public CheckMemberExist(MemberView view) {
        this.memberView = view;
    }

    public void checkMember(final CallBack<Boolean> callBack) {
        readUser = new ReadUser();
        readTeamMember = new ReadTeamMember();
        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                readTeamMember.checkTeamMemberExist(user.getTeamId(), new CallBack<Boolean>() {
                    @Override
                    public void update(Boolean isExisting) {
                        callBack.update(isExisting);
                    }
                });
            }
        });
    }
}
