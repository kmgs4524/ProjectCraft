package com.example.york.teamcraft.targetfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.personalsmanage.model.WriteGroupTask;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

/**
 * Created by York on 2017/10/19.
 */

public class ConfirmContentTask {
    private ReadUser readUser;
    private WriteGroupTask writeGroupTask;

    public ConfirmContentTask() {
        this.readUser = new ReadUser();
        this.writeGroupTask = new WriteGroupTask();
    }

    public void confirmTask(final String groupTaskName, final String taskId) {
        readUser.getCurrentLogInUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                writeGroupTask.confirmContentTask(data.getGroupId(), groupTaskName, taskId);
            }
        });
    }
}
