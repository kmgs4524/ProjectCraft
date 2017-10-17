package com.example.york.teamcraft.taskfragment.presenter;

import android.view.View;

import com.example.york.teamcraft.taskfragment.view.TaskFragmentView;

/**
 * Created by York on 2017/10/15.
 */

public interface TaskFragmentPresenter {
    public void checkUserGroup(String groupId);
    public void showAddGroupTaskDialog(String groupId);
}
