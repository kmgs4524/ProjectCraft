package com.example.york.teamcraft.taskfragment.viewmodel;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
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

    // 檢查傳入的groupId與user的groupId是否相同
    public void checkGroup(final String groupId, final CallBack<Boolean> callBack) {
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User user) {
                if(user.getGroupId().equals(groupId)) {
                    Log.d("checkGroup", "user group " + groupId + " passed group: " + groupId);
                    callBack.update(true);
                } else {
                    callBack.update(false);
                }
            }
        });
    }
}
