package com.example.york.teamcraft.addteammember.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/12/19.
 */

public class GetUserData {
    private ReadUser readUser;

    public GetUserData() {
        readUser = new ReadUser();
    }

    public void getTeamId(final CallBack<String> callBack) {
        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                callBack.update(user.getTeamId());
            }
        });
    }

    public void getMemberUserIdAndData(String email, final CallBackTwoArgs<String, User> callBack) {
        readUser.getUserIdAndDataByEmail(email, new CallBackTwoArgs<String, User>() {
            @Override
            public void update(String userId, User user) {
                callBack.update(userId, user);
            }
        });
    }

}
