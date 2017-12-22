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

    public SetPersonalTask(PersonalsView view) {
        this.personalsView = view;
    }

    // 設定RecyclerView的資料
    public void initData() {
        final int[] undoNum = {0};
        final int[] doneNum = {0};
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            readUser = new ReadUser();
            readUser.getCurrentLogInUserData(new CallBack<User>() {
                @Override
                public void update(final User user) {
                    readUser.getUserId(new CallBack<String>() {
                        @Override
                        public void update(String userId) {
                            ReadGroupTasks readGroupTasks = new ReadGroupTasks();
                            Log.d("SetPersonalTask", "update: " + user.getGroupIds());
                            for(String groupId: user.getGroupIds()) {
                                Log.d("SetPersonalTask", "groupId: " + groupId);
                                readGroupTasks.getPersonalTask(groupId, userId, new CallBackTwoArgs<ArrayList<DataPath>, ArrayList<ContentTask>>() {
                                    @Override
                                    public void update(ArrayList<DataPath> dataPaths, ArrayList<ContentTask> contentTasks) {
                                        Log.d("initData", "update: " + contentTasks.size());
                                        // 設定RecyclerView
                                        personalsView.initRecyclerView(dataPaths, contentTasks);
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
