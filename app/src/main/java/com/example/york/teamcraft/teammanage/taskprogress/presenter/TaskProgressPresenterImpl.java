package com.example.york.teamcraft.teammanage.taskprogress.presenter;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupProgress;
import com.example.york.teamcraft.teammanage.taskprogress.view.TaskProgressView;
import com.example.york.teamcraft.teammanage.taskprogress.viewmodel.SetAdapterData;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/21.
 */

public class TaskProgressPresenterImpl implements TaskProgressPresenter{
    // view
    private TaskProgressView taskProgressView;
    // model
    private SetAdapterData setAdapterData;

    public TaskProgressPresenterImpl(TaskProgressView view) {
        this.taskProgressView = view;

    }


    @Override
    public void setAdapterData() {
        this.setAdapterData = new SetAdapterData();
        setAdapterData.setData(new CallBack<ArrayList<GroupProgress>>() {
            @Override
            public void update(ArrayList<GroupProgress> data) {
                taskProgressView.setRecyclerAdapter(data);
            }
        });
    }
}
