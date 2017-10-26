package com.example.york.teamcraft.targetfragment.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.personalsmanage.model.WriteGroupTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by York on 2017/10/18.
 */

public class DeleteContentTask {
    private ReadUser readUser;
    private ReadGroupTasks readGroupTasks;
    private WriteGroupTask writeGroupTask;

    public DeleteContentTask() {
        this.readUser = new ReadUser();
        this.readGroupTasks = new ReadGroupTasks();
        this.writeGroupTask = new WriteGroupTask();
    }

    public void deleteTask(final String groupTaskName, final String taskId) {
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                writeGroupTask.deleteContentTask(data.getGroupId(), groupTaskName, taskId);
            }
        });
    }
}
