package com.example.york.teamcraft.taskfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.login.view.SignUpActivity;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by York on 2017/10/18.
 */

public class CheckUserGroup {
    // model
    private ReadUser readUser;
    private ReadGroupMember readGroupMember;

    public CheckUserGroup() {
        this.readUser = new ReadUser();
        this.readGroupMember = new ReadGroupMember();
    }

    public void checkGroup(final String groupId, final CallBack<Boolean> callBack) {
        readUser.getUserData().addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                if(user.getGroupId().equals(groupId)) {
                    callBack.update(true);
                } else {
                    callBack.update(false);
                }
            }
        });
    }
}
