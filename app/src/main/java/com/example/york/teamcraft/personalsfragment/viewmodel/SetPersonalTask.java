package com.example.york.teamcraft.personalsfragment.viewmodel;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.example.york.teamcraft.personalsfragment.view.PersonalsView;
import com.example.york.teamcraft.personalsmanage.model.DataPath;
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
    private ReadGroupTasks readGroupTasks;

    public SetPersonalTask(PersonalsView view) {
        this.personalsView = view;
    }

    // 設定RecyclerView的資料
    public void initData() {
        final int[] undoNum = {0};
        final int[] doneNum = {0};
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            readUser = new ReadUser();
            readGroupTasks = new ReadGroupTasks();
            readUser.getCurrentLogInUserData(new CallBack<User>() {
                @Override
                public void update(final User user) {
                    readUser.getUserId(new CallBack<String>() {
                        @Override
                        public void update(String userId) {
                            Log.d("SetPersonalTask", "update: " + user.getGroupIds());
                            final ArrayList<ContentTask> personalContentTasks = new ArrayList<>();
                            final ArrayList<DataPath> taskDataPaths = new ArrayList<>();
                            for(String groupId: user.getGroupIds()) {
                                readGroupTasks.getPersonalTask(groupId, userId, new CallBackTwoArgs<ArrayList<DataPath>, ArrayList<ContentTask>>() {
                                    @Override
                                    public void update(ArrayList<DataPath> dataPaths, ArrayList<ContentTask> contentTasks) {
                                        Log.d("initData", "tasks: " + contentTasks.size());
//                                        Log.d("initData", "personal tasks: " + personalContentTasks.size());
                                        personalContentTasks.addAll(contentTasks);
                                        taskDataPaths.addAll(dataPaths);
                                        // 設定RecyclerView
                                        personalsView.initRecyclerView(dataPaths, contentTasks);
                                        Log.d("SetPersonalTask", "contentTasks size: " + contentTasks.size());
                                        personalContentTasks.clear();
                                        taskDataPaths.clear();
                                        // 設定UNDO, DONE
                                        for(ContentTask task: contentTasks) {
                                            if(task.getStatus().equals("undo")) {
                                                undoNum[0]++;
                                            } else if(task.getStatus().equals("done")) {
                                                doneNum[0]++;
                                            }
                                        }
                                        personalsView.setTaskNum(undoNum, doneNum);
                                    }
                                });
                            }

                        }
                    });
                }
            });
        }
    }
}
