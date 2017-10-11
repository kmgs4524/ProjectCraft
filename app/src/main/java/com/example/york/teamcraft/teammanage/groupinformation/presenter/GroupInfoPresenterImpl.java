package com.example.york.teamcraft.teammanage.groupinformation.presenter;

import com.example.york.teamcraft.teammanage.groupfragment.presenter.GroupManagePresenter;
import com.example.york.teamcraft.teammanage.groupinformation.view.GroupInfoView;
import com.example.york.teamcraft.teammanage.model.Group;

/**
 * Created by York on 2017/10/10.
 */

public class GroupInfoPresenterImpl implements GroupInfoPresenter{
    private GroupInfoView groupInfoView;

    public GroupInfoPresenterImpl(GroupInfoView view) {
        this.groupInfoView = view;
    }

    public void setFragArgment() {

    }

    @Override
    public void setFragTaskArg(String id) {

    }
}
