package com.example.york.teamcraft.personalsfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.personalsfragment.view.PersonalsView;
import com.example.york.teamcraft.personalsmanage.model.DataPath;
import com.example.york.teamcraft.personalsmanage.view.PersonalTasksView;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/17.
 */

public class SetPersonalTask {
    // view
    private PersonalsView personalsView;
    // model
    private ReadUser readUser;

    public SetPersonalTask(PersonalsView view) {
        this.personalsView = view;
        readUser = new ReadUser();
    }

    // 設定RecyclerView的資料
    public void initData() {
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                final User user = data;
                readUser.getUserId(new CallBack<String>() {
                    @Override
                    public void update(String data) {
                        ReadGroupTasks readGroupTasks = new ReadGroupTasks();
                        readGroupTasks.getPersonalTask(user.getGroupId(), data, new CallBackTwoArgs<ArrayList<DataPath>, ArrayList<ContentTask>>() {
                            @Override
                            public void update(ArrayList<DataPath> pathList, ArrayList<ContentTask> taskList) {
                                personalsView.initRecyclerView(pathList, taskList);
                            }
                        });
                    }
                });
            }
        });
    }
}
