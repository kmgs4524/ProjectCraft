package com.example.york.teamcraft.personalsmanage.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.personalsmanage.view.PersonalTasksView;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/14.
 */

public class InitRecyclerViewData {
    // view
    private PersonalTasksView personalTasksView;
    // model
    private ReadUser readUser;

    public InitRecyclerViewData(PersonalTasksView view) {
        this.personalTasksView = view;
        readUser = new ReadUser();
    }

    public void initData() {
        Task<User> task = readUser.getUserData();
        task.addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(final User user) {
                readUser.getUserId(new CallBack<String>() {
                    @Override
                    public void update(String data) {
                        ReadGroupTasks readGroupTasks = new ReadGroupTasks();
                        readGroupTasks.getPersonalTask(user.getGroupId(), data, new CallBack<ArrayList<ContentTask>>() {
                            @Override
                            public void update(ArrayList<ContentTask> data) {
                                personalTasksView.initRecycleView(data);
                            }
                        });
                    }
                });

            }
        });
    }
}
