package com.example.york.teamcraft.addcontenttask.presenter;

import com.example.york.teamcraft.addcontenttask.view.AddContentTaskActivity;
import com.example.york.teamcraft.personalsmanage.model.WriteGroupTask;
import com.example.york.teamcraft.taskfragment.model.ContentTask;

/**
 * Created by York on 2017/10/16.
 */

public class AddContentTaskPresenterImpl implements AddContentTaskPresenter{
    private AddContentTaskActivity addContentTaskActivity;
    private WriteGroupTask writeGroupTask;

    public AddContentTaskPresenterImpl(AddContentTaskActivity act) {
        this.addContentTaskActivity = act;
    }

    @Override
    public void writeContentTask() {

    }
}
