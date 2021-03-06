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
//                            final ArrayList<ContentTask> personalContentTasks = new ArrayList<>();
//                            final ArrayList<DataPath> taskDataPaths = new ArrayList<>();
                                readGroupTasks.getPersonalTask(userId, new CallBackTwoArgs<ArrayList<DataPath>, ArrayList<ContentTask>>() {
                                    @Override
                                    public void update(ArrayList<DataPath> dataPaths, ArrayList<ContentTask> contentTasks) {
                                        // 設定RecyclerView
                                        personalsView.initRecyclerView(dataPaths, contentTasks);
//                                       // 設定UNDO, DONE
                                        int undoNum = 0;    // 未完成的工作數量
                                        int doneNum = 0;    // 已完成的工作數量
                                        for(ContentTask task: contentTasks) {
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
