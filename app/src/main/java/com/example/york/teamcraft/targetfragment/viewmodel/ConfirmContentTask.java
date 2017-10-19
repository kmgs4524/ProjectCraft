package com.example.york.teamcraft.targetfragment.viewmodel;

import com.example.york.teamcraft.personalsmanage.model.WriteGroupTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;

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
        readUser.getUserData().addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                writeGroupTask.confirmContentTask(user.getGroupId(), groupTaskName, taskId);
            }
        });
    }
}
