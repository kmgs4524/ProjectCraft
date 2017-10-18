package com.example.york.teamcraft.taskfragment.presenter;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.member.Member;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.taskfragment.view.TaskFragmentView;
import com.example.york.teamcraft.taskfragment.viewmodel.SetDialogListener;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;

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
    // 若相同的話就在群組任務旁邊顯示加號按鈕，並新增按鈕的事件
    @Override
    public void checkUserGroup(final String groupId) {
        readUser.getUserData().addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                if (groupId.equals(user.getGroupId())) { // 若使用者的group id 等於目前點擊群組的id
                    readUser.getUserId(new CallBack<String>() {
                        @Override
                        public void update(String data) {
                            final String userId = data;
                            readGroupMember.getGroupMember(groupId, new CallBack<ArrayList<Member>>() {
                                @Override
                                public void update(ArrayList<Member> data) {
                                    Iterator<Member> iterator = data.iterator();
                                    while (iterator.hasNext()) {
                                        Member nextMem = iterator.next();
                                        if (nextMem.getUserId().equals(userId)) {
                                            if(nextMem.getPosition().equals("director")) {
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
        });
    }

    @Override
    public void showAddGroupTaskDialog(String groupId) {
        setDialogListener = new SetDialogListener(groupId);
        setDialogListener.showAddGroupTaskDialog(taskFragmentView);
    }

}
