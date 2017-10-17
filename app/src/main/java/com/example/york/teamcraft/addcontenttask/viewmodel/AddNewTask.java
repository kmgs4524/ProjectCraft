package com.example.york.teamcraft.addcontenttask.viewmodel;

import com.example.york.teamcraft.personalsmanage.model.WriteGroupTask;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by York on 2017/10/17.
 */

public class AddNewTask {
    private ReadUser readUser;
    private WriteGroupTask writeGroupTask;

    public AddNewTask() {
        readUser = new ReadUser();
        writeGroupTask = new WriteGroupTask();
    }

    public void addContentTask(final String groupName, final ContentTask contentTask) {
        readUser.getUserData().addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                writeGroupTask.writeContentTask(user.getGroupId(), groupName, contentTask);
            }
        });

    }
}
