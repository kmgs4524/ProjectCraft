package com.example.york.teamcraft.addcontenttask.viewmodel;

import android.support.v4.app.FragmentActivity;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.addcontenttask.view.AddContentTaskView;
import com.example.york.teamcraft.member.Member;
import com.example.york.teamcraft.teammanage.creategroup.viewmodel.SetSpinnerData;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/17.
 */

public class SetSpinData {
    // view
     AddContentTaskView addContentTaskView;
    // model
    private ReadUser readUser;
    private ReadGroupMember readGroupMember;

    public SetSpinData(AddContentTaskView view) {
        addContentTaskView = view;
        readUser = new ReadUser();
        readGroupMember = new ReadGroupMember();
    }

    public void setData() {
        readUser.getUserData().addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                readGroupMember.getGroupMember(user.getGroupId(), new CallBack<ArrayList<Member>>() {
                    @Override
                    public void update(ArrayList<Member> data) {
                        addContentTaskView.setSpinMenu(data);
                    }
                });
            }
        });
    }
}
