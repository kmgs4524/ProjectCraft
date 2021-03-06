package com.example.york.teamcraft.taskfragment.presenter;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.taskfragment.view.TaskFragmentView;
import com.example.york.teamcraft.taskfragment.viewmodel.SetDialogListener;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/10/11.
 */

public class TaskFragmentPresenterImpl implements TaskFragmentPresenter {
    private TaskFragmentView taskFragmentView;
    private ReadUser readUser;
    private ReadGroupMember readGroupMember;
    private SetDialogListener setDialogListener;

    public TaskFragmentPresenterImpl(TaskFragmentView taskFragmentView) {
        this.taskFragmentView = taskFragmentView;
        this.readUser = new ReadUser();
        this.readGroupMember = new ReadGroupMember();
    }

    // 檢查使用者的group id 是否等於目前點擊群組的id
    // 再檢查使用者的職位是否為director
    // 若上述兩者皆為是的話就在群組任務旁邊顯示加號按鈕，並新增按鈕的事件
    @Override
    public void checkUserGroup(final String groupId) {
        Log.d("checkUserGroup", "groupId: " + groupId);
        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                for(final String userGroupId: user.getGroupIds()) {
                    if (groupId.equals(userGroupId)) { // 若使用者的group id 等於目前點擊群組的id
                        readUser.getUserId(new CallBack<String>() {
                            @Override
                            public void update(final String userId) {
                                readGroupMember.getGroupMember(groupId, new CallBack<ArrayList<GroupMember>>() {
                                    @Override
                                    public void update(ArrayList<GroupMember> groupMembers) {
                                        Iterator<GroupMember> iterator = groupMembers.iterator();
                                        while (iterator.hasNext()) {
                                            GroupMember nextMem = iterator.next();
                                            if (nextMem.getUserId().equals(userId)) {
                                                if(nextMem.getPosition().equals("組長")) {  // 若使用者的position等於"director"
                                                    taskFragmentView.setImgAddGroupTaskListener();
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        });
                    }
                }

            }
        });
    }

    @Override
    public void showAddGroupTaskDialog(String groupId) {
        setDialogListener = new SetDialogListener(groupId);
        setDialogListener.showAddGroupTaskDialog(taskFragmentView);
    }

}
