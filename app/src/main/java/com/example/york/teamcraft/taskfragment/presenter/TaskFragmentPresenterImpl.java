package com.example.york.teamcraft.taskfragment.presenter;

import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.taskfragment.view.TaskFragmentView;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/11.
 */

public class TaskFragmentPresenterImpl {
    private TaskFragmentView taskFragment;
    private ReadGroupTasks readGroupTasks;

    public TaskFragmentPresenterImpl(TaskFragmentView taskFragment) {
        this.taskFragment = taskFragment;
        this.readGroupTasks = new ReadGroupTasks();
    }

    public void initGroupTaskData() {

    }
}
