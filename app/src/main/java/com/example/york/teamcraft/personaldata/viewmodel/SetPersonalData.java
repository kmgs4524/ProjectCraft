package com.example.york.teamcraft.personaldata.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.personaldata.view.PersonalDataView;
import com.example.york.teamcraft.personalsfragment.view.PersonalsView;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/12/11.
 */

public class SetPersonalData {
    // view
    private PersonalDataView personalDataView;
    // model
    private ReadUser readUser;

    public SetPersonalData(PersonalDataView view) {
        this.personalDataView = view;
    }

    public void setData() {
        readUser = new ReadUser();
        readUser.getCurrentLogInUserData(new CallBack<User>() {
            @Override
            public void update(User user) {
                personalDataView.initAllData(user.getImageUrl(), user.getName(), user.getEmail(), user.getStatus());
            }
        });
    }
}
