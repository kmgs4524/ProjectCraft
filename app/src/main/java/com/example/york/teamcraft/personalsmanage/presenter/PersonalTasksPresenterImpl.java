package com.example.york.teamcraft.personalsmanage.presenter;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.personalsmanage.view.PersonalTasksView;
import com.example.york.teamcraft.personalsmanage.viewmodel.InitRecyclerViewData;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.model.ReadGroupTasks;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/13.
 */

public class PersonalTasksPresenterImpl implements PersonalTasksPresenter{
    private PersonalTasksView personalTasksView;
    private InitRecyclerViewData initRecyclerViewData;

    public PersonalTasksPresenterImpl(PersonalTasksView view) {
        personalTasksView = view;
    }

    public void readData() {
        initRecyclerViewData = new InitRecyclerViewData(personalTasksView);
        initRecyclerViewData.initData();
    }
}
