package com.example.york.teamcraft.teammanage.creategroup.presenter;

import com.example.york.teamcraft.teammanage.creategroup.model.WriteTeamGroup;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupView;
import com.example.york.teamcraft.teammanage.creategroup.viewmodel.CreateNewGroup;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by York on 2017/10/13.
 */

public class CreateGroupPresenterImpl implements CreateGroupPresenter{
    // view
    private CreateGroupView createGroupView;
    // view-model
    private CreateNewGroup createNewGroup;

    public CreateGroupPresenterImpl(CreateGroupView view) {
        createGroupView = view;
    }

    @Override
    public void createGroup(String groupName) {
        createNewGroup = new CreateNewGroup(createGroupView);
        createNewGroup.create(groupName);
    }
}
