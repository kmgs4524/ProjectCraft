package com.example.york.teamcraft.taskfragment.presenter;

import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.taskfragment.view.TaskFragmentView;
import com.example.york.teamcraft.taskfragment.viewmodel.SetDialogListener;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by York on 2017/10/11.
 */

public class TaskFragmentPresenterImpl implements TaskFragmentPresenter{
    private TaskFragmentView taskFragmentView;
    private ReadUser readUser;
    private ReadGroupTasks readGroupTasks;
    private SetDialogListener setDialogListener;

    public TaskFragmentPresenterImpl(TaskFragmentView taskFragmentView) {
        this.taskFragmentView = taskFragmentView;
        this.readUser = new ReadUser();
        this.readGroupTasks = new ReadGroupTasks();
    }


    @Override
    public void checkUserGroup(final String groupId) {
        readUser.getUserData().addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                if(groupId.equals(user.getGroupId())) {
                    taskFragmentView.setImgAddGroupTaskListener();
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
