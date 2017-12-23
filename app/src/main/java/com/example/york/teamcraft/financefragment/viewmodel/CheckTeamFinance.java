package com.example.york.teamcraft.financefragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.financefragment.model.ReadTeamBudget;
import com.example.york.teamcraft.financefragment.model.ReadTeamFinance;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/12/23.
 */

public class CheckTeamFinance {
    private ReadTeamBudget readTeamBudget;
    private ReadUser readUser;

    public void checkBudgetExist(final CallBack<Boolean> callBack) {
        readUser = new ReadUser();
        readTeamBudget = new ReadTeamBudget();
        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                readTeamBudget.checkBudgetExist(user.getTeamId(), new CallBack<Boolean>() {
                    @Override
                    public void update(Boolean exist) {
                        callBack.update(exist);
                    }
                });
            }
        });
    }
}
