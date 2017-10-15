package com.example.york.teamcraft.taskfragment.presenter;

import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.taskfragment.view.TaskFragmentView;
import com.example.york.teamcraft.taskfragment.viewmodel.SetDialogListener;

/**
 * Created by York on 2017/10/11.
 */

public class TaskFragmentPresenterImpl implements TaskFragmentPresenter{
    private TaskFragmentView taskFragmentView;
    private ReadGroupTasks readGroupTasks;
    private SetDialogListener setDialogListener;

    public TaskFragmentPresenterImpl(TaskFragmentView taskFragmentView) {
        this.taskFragmentView = taskFragmentView;
        this.readGroupTasks = new ReadGroupTasks();
    }

    @Override
    public void showAddGroupTaskDialog(String groupId) {
        setDialogListener = new SetDialogListener(groupId);
        setDialogListener.showAddGroupTaskDialog(taskFragmentView);
    }

}
