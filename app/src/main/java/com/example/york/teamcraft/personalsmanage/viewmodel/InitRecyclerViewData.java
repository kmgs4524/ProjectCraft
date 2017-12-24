package com.example.york.teamcraft.personalsmanage.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.personalsmanage.model.DataPath;
import com.example.york.teamcraft.personalsmanage.view.PersonalTasksView;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

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
//        readUser.getCurrentLogInUserData(new CallBack<User>() {
//            @Override
//            public void update(User data) {
//                final User user = data;
//                readUser.getUserId(new CallBack<String>() {
//                    @Override
//                    public void update(String data) {
//                        ReadGroupTasks readGroupTasks = new ReadGroupTasks();
//                        readGroupTasks.getPersonalTask(user.getGroupIds(), data, new CallBackTwoArgs<ArrayList<DataPath>, ArrayList<ContentTask>>() {
//                            @Override
//                            public void update(ArrayList<DataPath> pathList, ArrayList<ContentTask> taskList) {
//                                personalTasksView.initRecycleView(pathList, taskList);
//                            }
//                        });
//                    }
//                });
//            }
//        });
    }
}
