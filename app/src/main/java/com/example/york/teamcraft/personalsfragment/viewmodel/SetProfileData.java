package com.example.york.teamcraft.personalsfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.personalsfragment.view.PersonalsView;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by York on 2017/11/3.
 */

public class SetProfileData {
    // model
    private ReadUser readUser;
    // view
    private PersonalsView personalsView;

    public SetProfileData(PersonalsView view) {
        this.personalsView = view;
    }

    public void setData() {
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {

        } else {
            readUser = new ReadUser();
            readUser.getUserData(new CallBack<User>() {
                @Override
                public void update(User data) {
                    personalsView.setCirImgPersonals(data.getImageUrl());
                    personalsView.setTxtEmail(data.getEmail());
                }
            });
        }
    }

}
