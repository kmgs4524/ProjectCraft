package com.example.york.teamcraft.targetfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.targetfragment.view.TargetFragment;
import com.example.york.teamcraft.taskfragment.viewmodel.CheckUserGroup;
import com.example.york.teamcraft.taskfragment.viewmodel.CheckUserPosition;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/11/24.
 */

public class SetButtonVisibility {
    // model
    private ReadUser readUser;
    private CheckUserGroup checkUserGroup;
    private CheckUserPosition checkUserPosition;
    // view
    private TargetFragment targetFragment;

    public SetButtonVisibility(TargetFragment fragment) {
        readUser = new ReadUser();
        checkUserGroup = new CheckUserGroup();
        checkUserPosition = new CheckUserPosition();
        targetFragment = fragment;
    }

    public void setVisibility(final String groupId) {
        readUser.getCurrentLogInUserData(new CallBack<User>() {
            @Override
            public void update(final User user) {
                // 先根據TargetFragment收到的groupId判斷是否與user目前的組別相同
                checkUserGroup.checkGroup(groupId, new CallBack<Boolean>() {
                    @Override
                    public void update(Boolean isOwnGroup) {
                        if(isOwnGroup) {
                            // 若為相同的group再判斷user是否為該group的組長
                            checkUserPosition.checkPosition(groupId, new CallBack<Boolean>() {
                                @Override
                                public void update(Boolean isDirector) {
                                    if(isDirector) {
                                        // 若為該group組長則顯示確認、刪除Button
                                        targetFragment.setBtnVisibility(true);
                                    }
                                }
                            });
                        } else {
                            // 若非為相同的group則不顯示確認、刪除Button
                            targetFragment.setBtnVisibility(false);
                        }
                    }
                });
            }
        });
    }
}
