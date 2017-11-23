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
import com.google.firebase.auth.FirebaseAuth;

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
    }

    // 設定RecyclerView的資料
    public void initData() {
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            readUser = new ReadUser();
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
                                    // 設定RecyclerView
                                    personalsView.initRecyclerView(pathList, taskList);
                                    // 設定UNDO, DONE
                                    int undoNum = 0;
                                    int doneNum = 0;
                                    for(ContentTask task: taskList) {
                                        if(task.getStatus().equals("undo")) {
                                            undoNum++;
                                        } else if(task.getStatus().equals("done")) {
                                            doneNum++;
                                        }
                                    }
                                    personalsView.setTaskNum(undoNum, doneNum);
                                }
                            });
                        }
                    });
                }
            });
        }
    }
}
