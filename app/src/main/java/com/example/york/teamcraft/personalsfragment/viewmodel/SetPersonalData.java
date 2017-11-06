package com.example.york.teamcraft.personalsfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.personalsfragment.view.PersonalsView;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/11/3.
 */

public class SetPersonalData {
    // model
    private ReadUser readUser;
    // view
    private PersonalsView personalsView;

    public SetPersonalData(PersonalsView view) {
        this.readUser = new ReadUser();
        this.personalsView = view;
    }

    public void setData() {
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                personalsView.setCirImgPersonals(data.getImageUrl());
                personalsView.setTxtEmail(data.getEmail());
            }
        });
    }

}
